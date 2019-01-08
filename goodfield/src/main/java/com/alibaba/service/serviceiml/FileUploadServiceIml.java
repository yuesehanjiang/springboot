package com.alibaba.service.serviceiml;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.common.jpush.JiGunag;
import com.alibaba.common.result.JsonResult;
import com.alibaba.controller.UserController;
import com.alibaba.dao.FileUploadMapper;
import com.alibaba.dao.FolderMapper;
import com.alibaba.dao.JiGuangMapper;
import com.alibaba.pojo.File;
import com.alibaba.pojo.Folder;
import com.alibaba.pojo.UserFolder;
import com.alibaba.service.FolderService;
import com.alibaba.service.isservice.FileUploadService;
import com.alibaba.service.isservice.JiGuangService;
import com.alibaba.util.StringToIntegerArray;
import com.yun.utils.OosFileupload;

@Transactional(rollbackFor = Exception.class)
@Service
public class FileUploadServiceIml implements FileUploadService {
	@Autowired
	FileUploadMapper fileUploadMapper;

	@Autowired
	JiGuangService jiGuangService;

	@Autowired
	JiGuangMapper jiGuangMapper;

	@Autowired
	FolderService folderService;

	@Autowired
	FolderMapper folderMapper;

	Logger logger = Logger.getLogger(UserController.class);

	ObjectMapper objectMapper = new ObjectMapper();

	// 终端机 停用

	public Map<String, Object> startMachine(String floderid, String status) throws Exception {
		insertDelPushstatus(floderid, "0");

		fileUploadMapper.updateFolderStatus(floderid, "0", "0");
		fileUploadMapper.updatePushStatsIsting(floderid, "0");
		Map<String, Object> pushFile = jiGuangService.pushFileByDel(floderid);

		return pushFile;
	}

	public Map<String, Object> pushByMachine(String machineId) {
		// 1 查找可以推送的文件s
		Folder maps = jiGuangMapper.selectFolder(machineId, null, "1");
		if (maps == null)
			return JsonResult.build("203", "没有可以推送 的内容");

		String writeValueAsString = null;
		try {
			writeValueAsString = objectMapper.writeValueAsString(maps);
		} catch (Exception e) {

			e.printStackTrace();
		}

		String[] strings = { machineId };

		// 3 开始推送
		// JiGuangUtil.jpushAndroid(strings, writeValueAsString);
		JiGunag.pushMessage(strings, writeValueAsString);

		logger.info("推送的内容：：" + writeValueAsString);
		return JsonResult.build("200", "推送成功");
	}

	// 插入前的修改

	public void insertDelPushstatus(String folderId, String isTing) {
		Folder selectFolder = jiGuangService.selectFolder(folderId, null, "1");
		String machine_ids = selectFolder.getMachineIds();
		List<String> stringToIntegerarry = StringToIntegerArray.stringToIntegerarry(machine_ids);
		for (int i = 0; i < stringToIntegerarry.size(); i++) {
			try {
				deletePushStatus(stringToIntegerarry.get(i), folderId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 插入数据

		for (int i = 0; i < stringToIntegerarry.size(); i++) {
			jiGuangMapper.insertFolidemachineid(folderId, stringToIntegerarry.get(i), isTing);

		}

	}

	public void deletePushStatus(String machieId, String folderId) {
		fileUploadMapper.deletePushStatus(machieId, folderId);

	}

	// 该 folder live_status的 数据

	public void updateFolder_live_status(String folderid) {

		fileUploadMapper.updateFolder_live_status(folderid);
	}

	// 得到文件夹中文件的 数量s
	public Integer selectCountFile(String folderId) {
		return fileUploadMapper.selectCountFile(folderId);
	}

	// 处理启用的逻辑ss

	public Map<String, Object> stopMachine(String floderId, String string) throws Exception {
		// 时间控制 看开播时间是不是距离现在还有 4 个小时s

		/*
		 * fileUploadMapper.updateFolderStatus(floderId, "1","1");
		 * insertDelPushstatus(floderId,"1");
		 */
		fileUploadMapper.updatePushStatsIsting(floderId, "1");
		Map<String, Object> pushFile = jiGuangService.pushFileStop(floderId);
		// 该这个终端机的状态
		// if("200".equalsIgnoreCase(pushFile.get("code").toString()))

		return pushFile;

	}

	// 推送前的准备ss

	public void insertDelPushstatus_updatePushis(String folderId) {
		// fileUploadMapper.updateFolderStatus(folderId, "0", "1");
		insertDelPushstatus(folderId, null);
		// 2 改变 pushIs的数据
		updateFile_pushIs(folderId, "0");

	}

	// 该便 file 中 push is的数据
	public void updateFile_pushIs(String folderId, String pushis) {
		// TODO Auto-generated method stub
		fileUploadMapper.updateFile_pushIs(folderId, pushis);
	}

	// 删除文件
	public void delFiles(File file) {
		Integer counts = selectCountFile(file.getFolderId());
		updateFile_pushIs(file.getFolderId(), "1");
		if (counts.equals(0)) {
			// 改变 floder 表的值 为 0ss
			// updateFolder_live_status(file.getFolderId());
			jiGuangService.updatePushStatusByFolerid(file.getFolderId());

		}

		// 删除 oos 中的数据
		String fidString = file.getFid();
		List<String> stringToIntegerarry = StringToIntegerArray.stringToIntegerarry(fidString);
		List<String> selectFileByfolderId = null;
		for (int i = 0; i < stringToIntegerarry.size(); i++) {
			selectFileByfolderId = selectFileByfolderId(null, stringToIntegerarry.get(i));
			OosFileupload.deleteProject("advertisa",
					selectFileByfolderId.get(0).substring(selectFileByfolderId.get(0).lastIndexOf("/") + 1));

		}

		int count = folderService.deleteFolderFiles(file);

	}

	// 根据门店的 id 查找 文件
	public List<Folder> selectFolderBYStoreid(String stores_id, String type, String folderId, String unTime) {

		return fileUploadMapper.selectFolderBYStoreid(stores_id, type, folderId, unTime);

	}

	// 拿到所有 的Filepath的名字

	public List<String> selectFileByfolderId(String folderid, String fid) {
		// TODO Auto-generated method stub
		return fileUploadMapper.selectFileByfolderId(folderid, fid);
	}

	// 找存在的文件夹

	public List<Folder> selectFolerIsLive(String liveStatus) {
		// TODO Auto-generated method stub
		return fileUploadMapper.selectFolerIsLive(liveStatus);
	}

	// 删除过期 半个月的 数据 pushstatus 表中的stauts 为1 也要删除

	public void delexpireDateFolder() {
		// 删除pushstatus表中status 为1 的数据
		fileUploadMapper.delete_by_staus(1);

		List<LinkedHashMap<String, Object>> maps = fileUploadMapper.selectDateFolder();

		for (int i = 0; i < maps.size(); i++) {
			LinkedHashMap kinkedHashMap = maps.get(i);

			String idString = kinkedHashMap.get("id").toString();
			String type = kinkedHashMap.get("type").toString();
			// 删除oos中的文件
			List<String> filePath = selectFileByfolderId(idString, null);
			for (int j = 0; j < filePath.size(); j++) {
				OosFileupload.deleteProject("tianjiulongoa",
						filePath.get(j).substring(filePath.get(j).lastIndexOf("/") + 1));
			}

			updateFolder_live_status(idString);

			Folder folder = new Folder();
			folder.setId(idString);
			folder.setType(type);
			folderMapper.deleteFolderById(folder); // 删除文夹里面的文件

			folderMapper.deleteFolder(folder);// 删文夹加

			// 删除pushstatus 表数据
			fileUploadMapper.deletePushStatus(null, idString);

		}

	}

	// 终端机 删除文件

	public HashMap<String, Object> terminaIsDel(String machine_id, String folder_id, String type) {
		// 1.1 根据终端机id 和floderid删除数据

		fileUploadMapper.deletePushStatus(machine_id, folder_id);
		// 2.2插入数据
		fileUploadMapper.insertPushstaus(machine_id, folder_id, type, "0");

		return null;
	}

	// 前端页面 确定是不是已经删除

	public List<LinkedHashMap<String, Object>> selectTerminaIsDel(String folder_id, String type, String userid) {

		List<LinkedHashMap<String, Object>> list = fileUploadMapper.selectMacheIdAndName(folder_id, type, userid);
		return list;
	}

	// 终端机删除文件时候的推送

	public Map<String, Object> pushTerminaIsDel(UserFolder userFolder) {

		String machineId = userFolder.getMachineId();
		// 机器码
		List<String> stringToIntegerarry = StringToIntegerArray.stringToIntegerarry(machineId);
		//
		String stores_id = userFolder.getStores_id();
		List<String> stringToIntegerarry2 = StringToIntegerArray.stringToIntegerarry(stores_id);
		for (int i = 0; i < stringToIntegerarry.size(); i++) {
			// 删除machine 表数据
			// fileUploadMapper.pushTerminaIsDel(stringToIntegerarry2.get(i),stringToIntegerarry.get(i));

			// 修改 pushtatus 表数据
			fileUploadMapper.updatePushStatustoIdo(stringToIntegerarry.get(i), userFolder.getFolder_id());

		}

		return JsonResult.build("200", "修改成功");
	}
	// 查找所有被删除的数据

	public List<LinkedHashMap<String, Object>> selectByDelFolder(String type, String user_id, String floderId) {

		return fileUploadMapper.selectByDelFolder(type, user_id, floderId);
	}

	// 没有读的被删除的文件夹
	public List<LinkedHashMap<String, Object>> selectByDelFolderGolal(String user_id, int idian) {

		return fileUploadMapper.selectByDelFolderGolal(user_id, idian);

	}

	// 修改点击的状态
	public void updateIsDian(List<LinkedHashMap<String, Object>> maps) {

		for (int i = 0; i < maps.size(); i++) {
			Integer id = (Integer) maps.get(i).get("id");

			fileUploadMapper.updateIsDian(id);

		}

	}

	// 上传文件
	public int upload(File file) {

		return fileUploadMapper.upload(file);
	}

	// 批量插入图片的上传
	public int uploadByList(List<File> lists) {
		// 对重名的 图片进行修改
		for (int i = 0; i < lists.size(); i++) {
			String fileName = lists.get(i).getFileName().trim();
			String folderId = lists.get(i).getFolderId().trim();
			// 1 看每个图片的名字是不是在数据

			Folder folder = new Folder();
			folder.setFolderName(fileName).setId(folderId);
			List<String> lists1 = fileUploadMapper.selectFileIsLiveByFloderIdAndFolderId(folder);
			if (null != lists1 && lists1.size() != 0) {
                 String substring = fileName.substring(0,fileName.lastIndexOf("."));
                     String uuid=System.currentTimeMillis()+""+UUID.randomUUID().toString();
                String fileName2=   substring +""+folderId+uuid.substring(18, 30)+fileName.substring(fileName.lastIndexOf("."));
                 lists.get(i).setFileName(fileName2);
			}

		}

		// 修改 pushis 的状态
		fileUploadMapper.updateFile_pushIs(lists.get(0).getFolderId(), "1");

		return fileUploadMapper.uploadByList(lists);
	}

	// 查找文件的名字 是不是重复
	@Override
	public List<String> selectFolerIsLiveByFloderId(Folder folder) {
		// TODO Auto-generated method stub
		return fileUploadMapper.selectFolerIsLiveByFloderId(folder);
	}

}
