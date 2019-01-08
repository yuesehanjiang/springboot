package com.alibaba.controller.jiguang;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.common.result.JsonResult;
import com.alibaba.common.result.JsonResult2;
import com.alibaba.controller.UserController;
import com.alibaba.pojo.File;
import com.alibaba.pojo.Folder;
import com.alibaba.pojo.PushStatus;
import com.alibaba.pojo.Role;
import com.alibaba.service.FolderService;
import com.alibaba.service.InterfaceInfoService;
import com.alibaba.service.PushStatusService;
import com.alibaba.service.isservice.FileUploadService;
import com.alibaba.service.isservice.JiGuangService;
import com.alibaba.util.ParamInfo;
import com.alibaba.util.RedisUtils;
import com.alibaba.util.StringToIntegerArray;
import com.alibaba.util.TimeSlotUtil;
import com.google.common.collect.Lists;

@Transactional(rollbackFor = Exception.class)
@RestController
public class DelFolderController {
	@Autowired
	private FolderService folderService;

	@Autowired
	private InterfaceInfoService membershipService;

	@Autowired
	private PushStatusService pushStatusService;

	@Autowired
	JiGuangService jiGuangService;

	Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	FileUploadService fileUploadService;

	// 删除文件夹
	@RequestMapping(value = "/web/folder/deleteFolder", method = RequestMethod.POST)
	public HashMap<String, Object> deleteFolder(@RequestBody Folder folder) {
		try {
			
			// 1 在播放的文件就不可以被删除
			Folder selectFolder = jiGuangService.selectFolder(folder.getId(),
					"1", "1");
			if ((null != selectFolder) && (null==folder.getEmpty())) {

				return JsonResult.build("203", "在播的文件  是不可以被删除的 ");
			}


			Map<String, Object> pushFile = jiGuangService.pushFileByDel(folder
					.getId());
			// OSS同步删除
			folderService.deleteFolderById(folder); // 文件夹删除，文件夹里的文件同步删除
			// 在 pushstatus 表中插入数据
			// jiGuangService.insertPushStatus(folder.getId(),folder.getMachine_id());
			return JsonResult.build("200", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage());
			return JsonResult.build("205", "删除失败，请重试！");
		}
	}

	/**
	 * 批量删除文件 url : http://localhost:8080/goodfield/web/folder/deleteFolderFiles
	 * 
	 * @param {"folderId":"1", "fid":"1,2,3", "user_id":"1"}
	 * @return
	 */
	@RequestMapping(value = "/web/folder/deleteFolderFiles", method = RequestMethod.POST)
	public HashMap<String, Object> deleteFolderFiles(@RequestBody File file) {
		try {
			
			// 删除的推送写在这里
			fileUploadService.delFiles(file);

			jiGuangService.pushFileByDel(file.getFolderId());

			// jiGuangService.pushFile(file.getFolderId());

			return JsonResult.build("200", "删除成功！");

		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage());
			return JsonResult.build("205", "删除失败，请重试！");
		}
	}

	// 创建文件夹
	@RequestMapping(value = "/web/folder/saveFolder", method = RequestMethod.POST)
	public HashMap<String, Object> saveFolder(@RequestBody Folder folder) {
		try {
			
		
			if ((folder.getStartTime() != null && folder.getStartTime() != "")
					&& (folder.getEndTime() != null && folder.getEndTime() != "")) {
				if (folder.getStartTime().equals(folder.getEndTime())) {
					return JsonResult.build("205", "亲，开始时间不能和结束时间一样哦！");
				}
			}
			if (!folder.getUnlimitedTime().equals("0")) {
				if ((folder.getStartTime() == null || folder.getStartTime() == "")
						|| (folder.getEndTime() == null || folder.getEndTime() == "")) {
					return JsonResult.build("205", "亲，请选择时间！");
				}
			}
			if ((folder.getStartTime() != null && folder.getStartTime() != "")
					&& (folder.getEndTime() != null && folder.getEndTime() != "")) {
				if (folder.getEndTime().compareTo(folder.getStartTime()) < 0) {
					return JsonResult.build("205", "亲，开始时间不能大于结束时间哦！");
				}
			}

			// 文件夹的名字是不是存在

		
			
			List<String> folders=fileUploadService.selectFolerIsLiveByFloderId(folder);
            if(null!=folders &&  folders.size()!=0) {
            	return JsonResult.build("205", "文件的名字重复");
            }
            
			// 如果不限时间在此处处理
			if (folder.getUnlimitedTime().trim().equals("0")) {
				//一个公司 一个屏 只可以有一个不限时文件
				List<Folder> selectFolderBYStoreid = fileUploadService.selectFolderBYStoreid(folder.getGymAddr()
						, folder.getScreenType(), 
						folder.getId(), folder.getUnlimitedTime());
				
				   if(null !=selectFolderBYStoreid && selectFolderBYStoreid.size()!=0) {
					   return JsonResult.build("205", "重复播放的文件重复");
				   }
				
				
				//给不限时 设置一个 默认开始或者是结束时间
				long curren = System.currentTimeMillis();
				curren += 60 * 60 * 1000 * 4;
				Date da = new Date(curren);
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String format = dateFormat.format(da);

				folder.setStartTime(format);
				folder.setEndTime("2770-12-12 00:00:00");

			}
			// 判断时间是否重叠 限时文件 重叠问题
		

			folder.setPushIsAll("0");
			String ids=jiGuangService.selectMachineIds(folder);
			 folder.setMachineIds(ids);
			int count = folderService.saveFolder(folder);
			/******* 下滑添加 ********/
			PushStatus a = new PushStatus();
			//查找机器ids
			
			//String ids = folder.getMachineIds();
			String[] split = ids.split(",");
			for (String string : split) {
				a.setDevice_id(string);
				a.setFolder_id(folder.getId());
				pushStatusService.savePushStatus(a);
			}
			/******* 下滑添加 ********/
			if (count > 0) {
				return JsonResult2.build("200", "创建成功！",
						Integer.parseInt(folder.getId()));
			} else {
				return JsonResult.build("205", "创建失败，请重试！");
			}
		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage());
			return JsonResult.build("205", "创建失败，请重试！");
		}
	}

	// 分段时间控制接口
	@RequestMapping(value = "/web/folder/unlimited_time2", method = RequestMethod.POST)
	public HashMap<String, Object> deleteFolder1(@RequestBody Folder folder) {
		
		// 传过来的数据
		//String rePlayDate2 = folder.getRePlayDate(); // 传过来的 天数
		String rePlayTime2 = folder.getRePlayTime(); // 传过来的时间
		
		
		ArrayList<String> fetureDaysList = TimeSlotUtil.fetureDaysList(90);
		
	   //传过来的日期 做成list
		 // List<String> stringToIntegerarry2 = StringToIntegerArray.stringToIntegerarry(rePlayDate2);
		
		
		
		
		
		
		 //天数为空或者 是时间为空
		
	  /*   if( StringUtil.isEmpty(rePlayTime2)) {
	    	 return JsonResult.build("201", "请你选择时间");
	     }*/

		String[] split1 = rePlayTime2.split("\\-");
		String replayStartTime2 = split1[0]; // 传开始时间

		String replayEndTime2 = split1[1]; // 传结束时间
		
		//把重复时间返回前台的list 集合
		List<String> lists=new ArrayList<String>();

		
		// 分段播放时间控制
		if (folder.getUnlimitedTime().trim().equals("2")) {
			// 查到所有 分段播放的文件夹
			List<Folder> folderTimes = fileUploadService.selectFolderBYStoreid(
					folder.getGymAddr(), folder.getScreenType(),folder.getId(),folder.getUnlimitedTime());

			for (int k = 0; k < fetureDaysList.size(); k++) {
				String string = fetureDaysList.get(k);  //90天的数据
			for (int i = 0; i < folderTimes.size(); i++) { //

				String rePlayDate = folderTimes.get(i).getRePlayDate(); // 重复日期 后台数据库
				String rePlayTime = folderTimes.get(i).getRePlayTime(); // 重复时间 数据库

				// 切割成开始时间和结束时间

				String[] split = rePlayTime.split("\\-");
				String replayStartTime = split[0]; // 开始时间

				String replayEndTime = split[1]; // 结束时间
				

				// 重复日期和时间做组合
				List<String> stringToIntegerarry = StringToIntegerArray
						.stringToIntegerarry(rePlayDate);

				tag:for (int j = 0; j < stringToIntegerarry.size(); j++) {
					// 开始时间加上日期
					String startTimeString = stringToIntegerarry.get(j) + " "
							+ replayStartTime;
					// 结束时间加上日期
					String endTimeString =  stringToIntegerarry.get(j) + " "
							+ replayEndTime;
                  
						// 开始时间加上日期
						String startTimeString2 = string + " " + replayStartTime2;
						// 结束时间加上日期
						String endTimeString2 = string + " " + replayEndTime2;

						
						
						Boolean overlap = TimeSlotUtil.isOverlap(startTimeString2,
								endTimeString2, startTimeString, endTimeString);
						
						if (overlap) { //有重叠
							lists.add(string);
							break tag;
							//return JsonResult.build("202", ""+startTimeString2+"--"+endTimeString2+"时间重叠 无法操作");
					       
						}
					}
					

				}

			}
		}

		return JsonResult.build("200", "时间正确  可以操作",lists);
	}

}
