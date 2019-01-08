package com.alibaba.controller.jiguang;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.common.result.JsonResult;
import com.alibaba.controller.UserController;
import com.alibaba.pojo.File;
import com.alibaba.pojo.Folder;
import com.alibaba.service.FolderService;
import com.alibaba.service.InterfaceInfoService;
import com.alibaba.service.PushStatusService;
import com.alibaba.service.isservice.FileUploadService;
import com.alibaba.service.isservice.JiGuangService;
import com.alibaba.util.TimeSlotUtil;

@Transactional(rollbackFor = Exception.class)
@RestController
public class FileUpdateController {

	@Autowired
	private FolderService folderService;

	@Autowired
	private InterfaceInfoService membershipService;

	@Autowired
	private PushStatusService pushStatusService;

	@Autowired
	JiGuangService jiGuangService;

	@Autowired
	FileUploadService fileUploadService;

	Logger logger = Logger.getLogger(UserController.class);

	// 修改问价夹的 内容
	@RequestMapping(value = "/web/folder/updateFolder", method = RequestMethod.POST)
	public HashMap<String, Object> updateFolder(@RequestBody Folder folder) {
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

			// 播放状态 是不可以修改的
			Folder selectFolder2 = jiGuangService.selectFolder(folder.getId(),
					"1", "1");
			if (null != selectFolder2) {
				return JsonResult.build("203", "在播放状态下是 不可以修改的");
			}

			// 当前门店下所有在播放的文件夹，有没有和在修改的问价夹有冲突
			// fileUploadService.selectFolderBYStoreid(stores_id)

			// 判断时间是否重叠
			if (folder.getUnlimitedTime().trim().equals("1")) {
				List<Folder> folderTimes = fileUploadService
						.selectFolderBYStoreid(folder.getUnlimitedTime(),
								folder.getScreenType(),null,null);
				if (folderTimes.size() > 0) {
					for (Folder folder2 : folderTimes) {
						Boolean overlap = TimeSlotUtil.isOverlap(
								folder2.getStartTime(), folder2.getEndTime(),
								folder.getStartTime(), folder.getEndTime());
						if (overlap) {
							return JsonResult.build("205", "时间重叠，无法添加 ！");
						}
					}
				}
			}

			// 控制文件的名字

			// 文件夹的名字是不是存在
			Folder selectFolder = jiGuangService.selectFolder(folder.getId(),
					null, "1");
			List<Folder> folderq = fileUploadService.selectFolerIsLive("1"); // 根据门店的id
																				// 查找
			for (int i = 0; i < folderq.size(); i++) {
				Folder folder3 = folderq.get(i);

				if (folder.getFolderName().equalsIgnoreCase(
						folder3.getFolderName())
						&& !folder3.getId().equalsIgnoreCase(folder.getId())) {

					return JsonResult.build("203", "文件的名字 已经存在 ");
				}
			}

			// 编辑逻罗竞技开始
			try {
				Map<String, Object> mapsss = jiGuangService.updateFile(folder);
				return (HashMap<String, Object>) mapsss;
			} catch (Exception e) {

				logger.error(e.getMessage());
				e.printStackTrace();
			}
		} finally {

		}
		return JsonResult.build("203", "推送失败");
	}

	// 处理停用 的逻辑
	@RequestMapping(value = "/web/folder/stop", method = RequestMethod.POST)
	public Map<String, Object> startMachine(@RequestBody Folder folder) {

	
		try {
			Map<String, Object> maps = fileUploadService.startMachine(
					folder.getId(), "0");
			return maps;
		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage());
		}
		return JsonResult.build("203", "推送失败");
	}

	// 处理启用 的逻辑
	@RequestMapping(value = "/web/folder/start", method = RequestMethod.POST)
	public Map<String, Object> startMachine1(@RequestBody Folder folder) {

		try {
			Map<String, Object> maps = fileUploadService.stopMachine(
					folder.getId(), "0");
			return maps;
		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage());
		}
		return JsonResult.build("203", "推送失败");
	}
	
	 //文件上传后接受文件的路径   现在有前台上传
	
	@RequestMapping(value = "/web/folder/upload", method = RequestMethod.POST)
	public Map<String, Object> startMachine23(@RequestBody String file) {

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			// src：json字符串 valueType：复杂类型
		/*	objectMapper.configure(
					DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			// 允许使用未带引号的字段名
			objectMapper.configure(
					DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);*/

			JavaType constructType = objectMapper.getTypeFactory()
					.constructParametricType(List.class, File.class);
		List<File>	lists = objectMapper.readValue(file, constructType);
			
			
			
			int maps = fileUploadService.uploadByList(
					lists);
			return JsonResult.build("200", "上传成功");
		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage());
		}
		return JsonResult.build("203", "上传失败");
	}
	
	
	

}
