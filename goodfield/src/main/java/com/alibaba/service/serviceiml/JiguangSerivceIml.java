package com.alibaba.service.serviceiml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.common.jpush.JiGuangUtil;
import com.alibaba.common.jpush.JiGunag;
import com.alibaba.common.listener.PutObjectProgressListener;
import com.alibaba.common.result.JsonResult;
import com.alibaba.controller.UserController;
import com.alibaba.dao.FileUploadMapper;
import com.alibaba.dao.JiGuangMapper;
import com.alibaba.pojo.Folder;
import com.alibaba.pojo.PushStatus;
import com.alibaba.service.FolderService;
import com.alibaba.service.PushStatusService;
import com.alibaba.service.isservice.FileUploadService;
import com.alibaba.service.isservice.JiGuangService;
import com.alibaba.util.ParamInfo;
import com.alibaba.util.StringToIntegerArray;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

@Service
public class JiguangSerivceIml implements JiGuangService {
	@Autowired
	JiGuangMapper jiGuangMapper;
	@Autowired
	private PushStatusService pushStatusService;

	@Autowired
	FileUploadMapper fileUploadMapper;

	@Autowired
	FolderService folderService;
   /* @Autowired
    FileUploadService  fileUploadService;*/
	ObjectMapper objectMapper = new ObjectMapper();
	
	
	
	Logger logger=Logger.getLogger(UserController.class);

	// 激光推送 第一次
	public Map<String, Object> pushFile(String floderid) throws Exception {

		// 1 查找可以推送的文件s
		Folder maps = jiGuangMapper.selectFileByFloderid(floderid, null);

		if (maps == null)
			return JsonResult.build("200", "没有可以推送 的内容");
		String writeValueAsString = objectMapper.writeValueAsString(maps); // 转json

		// 文件夹下没有文件就不推送
		Integer selectCountFile = fileUploadMapper.selectCountFile(floderid);
		if (0 == selectCountFile) {
			return JsonResult.build("200", "没有可以推送的文件");
		}

		 // 2 查询可以推送的 终端机s
		List<String> machinesList = jiGuangMapper
				.selectmahineByFloderId(floderid);
		if (machinesList == null || machinesList.size() == 0)
			return JsonResult.build("200", "没有可以推送终端机");

		String[] strings = new String[machinesList.size()];
		machinesList.toArray(strings);
		// 3 开始推送
		// JiGuangUtil.jpushAndroid(strings, writeValueAsString);
		JiGunag.pushMessage(strings, writeValueAsString);
		System.err.println("推送的内容：：" + writeValueAsString);
        logger.info("推送的内容：：" + writeValueAsString);
		return JsonResult.build("200", "推送成功");
	}

	// 该 pushStatus 表的数据集
	public void updatePushStatusByFolerid(String folerid) {
		// TODO Auto-generated method stub
		jiGuangMapper.updatePushStatusByFolerid(folerid);
	}

	// 删除文件夹的推送

	public Map<String, Object> pushFileByDelFolder(String floderid)
			throws Exception {

		return null;
	}

	// 根据flowid 和machine——id 来pushStatus 的状态

	public void updatePushStatus(String machine_id, String folder_id) {
		// TODO Auto-generated method stub
		jiGuangMapper.updatePushStatus(machine_id, folder_id);
	}

	// 终端机上线 调用这个接口

	public HashMap<String, Object> onLine(String machine_id, String folder_id)
			throws Exception {

		// 根据终端机的id 来查找所有的数据

		List<Map> maps = jiGuangMapper.selectAll(machine_id);
		for (int i = maps.size() - 1; i >= 0; i--) {
			Map map = maps.get(i);
			String isDelte = (String) map.get("is_delete"); // 是不是已经删除
			String status = (String) map.get("status");

			if (null == status || status.equals("1")) {
				maps.remove(i);
			}
		}

		// 推送
		for (int i = maps.size() - 1; i >= 0; i--) {
			Map mapqq = maps.get(i);

			String floderid = (String) mapqq.get("folder_id");
			String machineId = (String) mapqq.get("device_id");

			Map<String, Object> pushFile = pushFilebyMachine(floderid,
					machineId);
		}

		return JsonResult.build("200", null);

	}

	// 插入数据s

	public void insertPushStatus(String floderid, String machine_id) {
		jiGuangMapper.insertFolidemachineid(floderid, machine_id, null);
		// int i=10/0;
	}

	// 修改问价夹的逻辑


	public Map<String, Object> updateFile(Folder folder) throws Exception {

		// 1 查找pushstatus中的数据
		List<PushStatus> pushStatus = selectPushStatusByfloderId(folder.getId());

		List<String> machiedeidStrings = new ArrayList<String>();
		// 只要machiemid [device004, device005, device006, device007, device008,
		// 156BE885C119072AFA28C483FDAA1B1133]
		for (int i = 0; i < pushStatus.size(); i++) {
			String device_id = pushStatus.get(i).getDevice_id();
			machiedeidStrings.add(device_id);

		}

		// 2 传过来的机器id [device004, device005, device006, device007, device008,
		// 156BE885C119072AFA28C483FDAA1B1133]
		String machine_id = jiGuangMapper.selectMachineIds(folder);
		List<String> machies = StringToIntegerArray
				.stringToIntegerarry(machine_id);
		// 2.1 拿到后台 floder 表中的所有的数据
		Folder selectFolder = selectFolder(folder.getId(), null, "1");
		String startTime = selectFolder.getStartTime();
		String endTime = selectFolder.getEndTime();
		// 时间已经修改
		Boolean boolean1 = (null != startTime && null != endTime
				&& !"".equalsIgnoreCase(startTime) && !""
					.equalsIgnoreCase(endTime))
				&& (!startTime.equalsIgnoreCase(folder.getStartTime()) || (!endTime // 2770-12-12
																					// 00:00:00
						.equalsIgnoreCase(folder.getEndTime()))); // 2770-12-12
																	// 00:00:00
		// 3 传过来的 机器id和pushstatsu总的不对应直接插入
		// 后台 数据 有没有 包括 传过来的 数据 不包含就直接插入
		for (int j = 0; j < machies.size(); j++) { // 传过来的 数据
			// 1如果不相等 直接插入数据
			if (!machiedeidStrings.contains(machies.get(j))) {// 后台数据
				// 2插入数据
				insertPushStatus(folder.getId(), machies.get(j));
			}
			
			// 2 如果pushtaus中有 但是 却machien中没有带这个机器 说明 被删除
			
		}
		// 1 传过来的数据集合 有没有包括 后台的数据
		for (int i = 0; i < machiedeidStrings.size(); i++) { // 后台数据
			if (!machies.contains(machiedeidStrings.get(i))) { // 传过来的
				// 该pushstust的状态
				updatePushstatus_machine_is_delete(machiedeidStrings.get(i),
						folder.getId());
			}
			if (machies.contains(machiedeidStrings.get(i))) { // 如果包括，，并且是被删除的
																// 就要解除删除状态，，并且把staus值为0
				// 根据 machineid 和 floderid 确定这个数据 然后再

				fileUploadMapper.update_isDelete_statusi(
						machiedeidStrings.get(i), folder.getId());

			}
		}

		// 如果有 replay 这个状态改正 福报
		String replay = folder.getReplay();

		if (null != replay && replay.equalsIgnoreCase("1")) {

			fileUploadMapper.updateFolderStatus(folder.getId(), "1", replay);
		}

		// 5 日期改变。所有状态值为0
		if (boolean1) {

			updatePushStatusByFolerid(folder.getId());

		}

		// 6把停用状态 改为启用
		fileUploadMapper.updatePushStatsIsting(folder.getId(), "1");

		// 修改两个表的数据s
		folderService.updateFolder(folder);

		// 开始推送
		Map<String, Object> pushFile = onLineByFolserId(null, folder.getId());
		return pushFile;
	}

	// 根据问价夹来推送内功
	public Map<String, Object> onLineByFolserId(Object object, String floderida)
			throws Exception {
		// 根据终端机的id 来查找所有的数据

		List<Map> maps = jiGuangMapper.selectAllbyFlodrid(floderida);
		for (int i = maps.size() - 1; i >= 0; i--) {
			Map map = maps.get(i);
			String isDelte = (String) map.get("is_delete"); // 是不是已经删除
			String status = (String) map.get("status");

			if (null == status || status.equals("1")) {
				maps.remove(i);
			}
		}

		// 推送
		for (int i = maps.size() - 1; i >= 0; i--) {
			Map mapqq = maps.get(i);
			String machineMap = (String) mapqq.get("device_id");
			String floderid = (String) mapqq.get("folder_id");

			Map<String, Object> pushFile = pushFilebyMachine(floderid,
					machineMap);
		}

		return JsonResult.build("200", "没有可以推送的内容");
	}

	// 修改问价后的推送
	public Map<String, Object> pushFilebyMachine(String floderid,
			String machineId) throws Exception {
		// 1 查找可以推送的文件s
		Folder maps = jiGuangMapper.selectFileByFloderid(floderid, machineId);
		ObjectMapper objectMapper = new ObjectMapper();
		if (maps == null)
			return JsonResult.build("203", "没有可以推送 的内容");
		String writeValueAsString = objectMapper.writeValueAsString(maps); // 转json
            
		// 文件夹下没有文件就不推送
		Integer selectCountFile = fileUploadMapper.selectCountFile(floderid);
		if (0 == selectCountFile) {
			return JsonResult.build("200", "没有可以推送的文件");
		}
		// 2 查询可以推送的 终端机

		String[] strings = { machineId };

		// 3 开始推送
		// JiGuangUtil.jpushAndroid(strings, writeValueAsString);

		JiGunag.pushMessage(strings, writeValueAsString);
		System.err.println("推送的内容：：" + writeValueAsString);
	     logger.info("推送的内容：：" + writeValueAsString);
		return JsonResult.build("200", "推送成功");
	}

	// 改变 pushstaus 表中的数据+s
	public void updatePushstatus_machine_is_delete(String machieId,
			String floderid) {
		jiGuangMapper.updatePushstatus_machine_is_delete(machieId, floderid);
	}

	// folder 表中的数据
	public Folder selectFolder(String id, String status, String liveStatus) {
		// TODO Auto-generated method stub
		return jiGuangMapper.selectFolder(id, status, liveStatus);
	}

	// 找找 pushstatus表中的数据
	public List<PushStatus> selectPushStatusByfloderId(String floerid) {
		// TODO Auto-generated method stub
		return jiGuangMapper.selectPushStatusByfloderId(floerid);
	}

	// 启用时候的推送

	public Map<String, Object> pushFileStop(String floderId) throws Exception {
		// 1 查找可以推送的文件s
		Folder maps = fileUploadMapper.selectFileByFloderid(floderId, null);

		if (maps == null)
			return JsonResult.build("203", "没有可以推送 的内容");
		String writeValueAsString = objectMapper.writeValueAsString(maps); // 转json

		// 2 查询可以推送的 终端机s
		List<String> machinesList = jiGuangMapper
				.selectmahineByFloderId(floderId);
		if (machinesList == null || machinesList.size() == 0)
			return JsonResult.build("203", "没有可以推送终端机");

		// 文件夹下没有文件就不推送
		Integer selectCountFile = fileUploadMapper.selectCountFile(floderId);
		if (0 == selectCountFile) {
			return JsonResult.build("200", "没有可以推送的文件");
		}

		String[] strings = new String[machinesList.size()];
		machinesList.toArray(strings);
		// 3 开始推送
		// JiGuangUtil.jpushAndroid(strings, writeValueAsString);
		JiGunag.pushMessage(strings, writeValueAsString);
		System.err.println("推送的内容：：" + writeValueAsString);
		  logger.error("推送的内容：：" + writeValueAsString);
		return JsonResult.build("200", "推送成功");
	}

	// 删除时候 推送
	public Map<String, Object> pushFileByDel(String floderid) throws Exception {
		// 1 查找可以推送的文件s
		Folder maps = jiGuangMapper.selectFileByFloderidtoDel(floderid, null);

		if (maps == null)
			return JsonResult.build("200", "没有可以推送 的内容");
		String writeValueAsString = objectMapper.writeValueAsString(maps); // 转json

		// 2 查询可以推送的 终端机s
		List<String> machinesList = jiGuangMapper
				.selectmahineByFloderId(floderid);
		if (machinesList == null || machinesList.size() == 0)
			return JsonResult.build("200", "没有可以推送终端机");

		// 文件夹下没有文件就不推送
		Integer selectCountFile = fileUploadMapper.selectCountFile(floderid);
		//应该吧文件的状态改正过来 标记为删除
		/*if (0 == selectCountFile) {
			return JsonResult.build("200", "没有可以推送的文件");
		}*/

		String[] strings = new String[machinesList.size()];
		machinesList.toArray(strings);
		// 3 开始推送
		// JiGuangUtil.jpushAndroid(strings, writeValueAsString);
		JiGunag.pushMessage(strings, writeValueAsString);
		System.err.println("推送的内容：：" + writeValueAsString);
		  logger.info("推送的内容：：" + writeValueAsString);
		return JsonResult.build("200", "推送成功");
	}

	// 带进度条的上传s

	@Async

	public void fileUpload(Long totalSize, String userToken, String objectName,
			InputStream inputStream,
			PutObjectProgressListener putObjectProgressListener) {
		// Endpoint以杭州为例，其它Region请按实际情况填写。
		String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
		// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录
		// https://ram.console.aliyun.com 创建RAM账号。
		String accessKeyId = "LTAIncw5TlYKR4SL";
		String accessKeySecret = "YTMZ2cmGpoWoPgpxm4oXgz3HPRi8Ui";
		String bucketName = "tianjiulongoa";
		// 创建OSSClient实例。
		// String objectName = "G:\\Users\\qzy017\\Desktop\\1 001.jpg";
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId,
				accessKeySecret);

		PutObjectResult putObject = ossClient
				.putObject(new PutObjectRequest(bucketName, objectName,
						inputStream)
						.<PutObjectRequest> withProgressListener(new PutObjectProgressListener(
								Integer.parseInt(totalSize + ""), userToken)));
	}

	
	
	
	
	//查找文件
	public List<Folder> selectFolderbyFolder(ParamInfo pInfo) {
		// TODO Auto-generated method stub
		
		int pageNum = Integer.parseInt(pInfo.getPageNum());
		
		int pageSize=Integer.parseInt(pInfo.getPageSize());
		
	int	pageNums=(pageNum-1)*pageSize;
	
		pInfo.setPageNum(pageNums+"").setPageSize(pageSize+"");
		return jiGuangMapper.selectFolderbyFolder( pInfo);
	}

	
	 // cha 问价夹的详情
	public Folder selectFolderbyFolderforFile(ParamInfo pInfo) {
		// TODO Auto-generated method stub
		return jiGuangMapper.selectFolderbyFolderforFile(pInfo);
	
	}

	
	//查找机器ids
	@Override
	public String selectMachineIds(Folder folder) {
		// TODO Auto-generated method stub
		return jiGuangMapper.selectMachineIds(folder);
	}

}