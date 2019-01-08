package com.alibaba.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.common.listener.PutObjectProgressListener;
import com.alibaba.common.result.JsonResult;
import com.alibaba.pojo.File;
import com.alibaba.pojo.Folder;
import com.alibaba.pojo.Machine;
import com.alibaba.pojo.Role;
import com.alibaba.service.FolderService;
import com.alibaba.service.InterfaceInfoService;
import com.alibaba.service.PushStatusService;
import com.alibaba.service.isservice.FileUploadService;
import com.alibaba.service.isservice.JiGuangService;
import com.alibaba.service.serviceiml.AsycServiceIml;
import com.alibaba.util.ImageType;
import com.alibaba.util.ParamInfo;
import com.alibaba.util.RedisUtils;
import com.alibaba.util.StringToIntegerArray;
import com.alibaba.util.validTest;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

@Transactional(rollbackFor = Exception.class)
@RestController
@RequestMapping("/web/folder/")
public class FolderController {

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

	@Autowired
	AsycServiceIml asycService;

	Logger logger = Logger.getLogger(UserController.class);

	/**
	 * 查询文件夹 url : http://localhost:8080/goodfield/web/folder/getFoldersInfoNews
	 * 
	 * @param {"user_id":"2", "type":"0", "pageNum":"0", "pageSize":"10",
	 *        "content_type":"0"}
	 * @return
	 */
	@RequestMapping(value = "getFoldersInfoNews", method = RequestMethod.POST)
	public HashMap<String, Object> getFoldersInfoNews(
			@RequestBody ParamInfo pInfo) {
		try {
			
		
			String pageNums=pInfo.getPageNum();
			System.out.println(123123);
			int count = folderService.getCount(pInfo);
			//返回数据给前端展示
		    List<Folder> lists=	jiGuangService.selectFolderbyFolder(pInfo);
	             
	             
				return JsonResult.build("200", "查询成功！", lists, count,
						(Integer.parseInt(pageNums)), pInfo.getPageSize());
			    
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试！");
		}
	}

	/**
	 * 查询文件夹详情以及文件列表 url :
	 * http://localhost:8080/goodfield/web/folder/getFolderDetailsFilesInfo
	 * 
	 * @param {"user_id":"3", "id":"1", "pageNum":"0","pageSize":"10"}
	 * @return
	 */
	@RequestMapping(value = "getFolderDetailsFilesInfo", method = RequestMethod.POST)
	public HashMap<String, Object> getFolderDetailsFilesInfo(
			@RequestBody ParamInfo pInfo, HttpSession session) {
		    
		  try {
			Folder selectFolderbyFolder = 
					  jiGuangService.selectFolderbyFolderforFile(pInfo); 
				
			 	return JsonResult.build("200", "查询成功！", selectFolderbyFolder
					);
		} catch (Exception e) {		
			e.printStackTrace();			
			logger.error(e.getMessage());
			return JsonResult.build("203", "查询失败！");
		}
	}

	/**
	 * 查询三屏管理门店列表以及终端机 url :
	 * http://localhost:8080/gym/web/folder/getstoresNameMachine
	 * 
	 * @param {"user_id":"3"}
	 * @return
	 */
	@RequestMapping(value = "getstoresNameMachine", method = RequestMethod.POST)
	public HashMap<String, Object> getstoresNameMachine(
			@RequestBody ParamInfo pInfo) {
		try {
			String userToken = RedisUtils.getKV("userToken_"
					+ pInfo.getUserToken() + "_" + pInfo.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getUserKVLikes(pInfo.getUserToken()).size() > 0) {
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			List<Role> getstoresNameMachine = folderService
					.getstoresNameMachine(pInfo);
			if (getstoresNameMachine.size() > 0) {
				return JsonResult.build("200", "查询成功！", getstoresNameMachine);
			} else {
				return JsonResult.build("201", "暂无数据！");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试！");
		}
	}

	/**
	 * 选择门店终端机的时候判断一下 url :
	 * http://localhost:8080/goodfield/web/folder/saveFolderFinal
	 * 
	 * @param {"startTime":"", "endTime":"","machine_ids":"", "userId":""}
	 * @return
	 */
	

	/**
	 * 创建文件夹 url : http://localhost:8080/goodfield/web/folder/saveFolder
	 * 
	 * @param {"user_id":"3", "type":"0", "folderName":"", "roleId":"1,2,3",
	 *        "startTime":"", "endTime":"", "type":"0", "content_type":"1",
	 *        "machine_ids":"", "unlimited_time":""}
	 * @return
	 */
	/*
	 * @RequestMapping(value = "saveFolder", method = RequestMethod.POST) public
	 * HashMap<String, Object> saveFolder(@RequestBody Folder folder) { try {
	 * String userToken =
	 * RedisUtils.getKV("userToken_"+folder.getUserToken()+"_"
	 * +folder.getLoginStatus()); if(userToken == null){ return
	 * JsonResult.build("210", "登陆超时，请重新登陆"); } ParamInfo pInfo = new
	 * ParamInfo(); pInfo.setUser_id(folder.getUserId()); List<Role>
	 * userRoleInfo = folderService.getUserRoleRelationInfo(pInfo);
	 * if(userRoleInfo.size() < 1){ return JsonResult.build("202", "暂无权限!"); }
	 * if ((folder.getStartTime() != null && folder.getStartTime() != "") &&
	 * (folder.getEndTime() != null && folder.getEndTime() != "")) { if
	 * (folder.getStartTime().equals(folder.getEndTime())) { return
	 * JsonResult.build("205", "亲，开始时间不能和结束时间一样哦！"); } }
	 * if(!folder.getUnlimited_time().equals("0")){ if((folder.getStartTime() ==
	 * null || folder.getStartTime() == "") || (folder.getEndTime() == null ||
	 * folder.getEndTime() == "")){ return JsonResult.build("205", "亲，请选择时间！");
	 * } } if ((folder.getStartTime() != null && folder.getStartTime() != "") &&
	 * (folder.getEndTime() != null && folder.getEndTime() != "")) { if
	 * (folder.getEndTime().compareTo(folder.getStartTime()) < 0) { return
	 * JsonResult.build("205", "亲，开始时间不能大于结束时间哦！"); } }
	 * 
	 * List<String> folderName = folderService.getFolderName(folder);
	 * if(folderName.size() > 0){ return JsonResult.build("205", "文件夹名称已存在 ！");
	 * }
	 * 
	 * // 如果不限时间在此处处理 if(folder.getUnlimited_time().trim().equals("0")){ String
	 * folderUnlimitedTime = folderService.getFolderUnlimitedTime2();
	 * if(folderUnlimitedTime != null){ if(folder.getMachine_ids() != null &&
	 * folder.getMachine_ids() != "" && !folder.getMachine_ids().equals("")){
	 * String [] machineIds = folder.getMachine_ids().split(","); for (String
	 * string : machineIds) { if(folderUnlimitedTime.indexOf(string) > -1){
	 * return JsonResult.build("205", "已有终端机被不限时间的文件夹绑定 ！"); } } } } } //
	 * 判断时间是否重叠 if(folder.getUnlimited_time().trim().equals("1")){ List<Folder>
	 * folderTimes = folderService.getFolderTimes(folder); if(folderTimes.size()
	 * > 0){ for (Folder folder2 : folderTimes) { Boolean overlap =
	 * TimeSlotUtil.isOverlap(folder2.getStartTime(),
	 * folder2.getEndTime(),folder.getStartTime(), folder.getEndTime()); if
	 * (overlap) { Folder machineIds = folderService.getMachineIds(); //
	 * 查询所有文件夹下已拥有的终端机 String[] machine_ids =
	 * folder.getMachine_ids().split(","); for (String string : machine_ids) {
	 * if(machineIds.getMachine_ids().indexOf(string) > -1){ return
	 * JsonResult.build("205", "时间重叠，无法添加 ！"); } } } } } } int count =
	 * folderService.saveFolder(folder);
	 *//******* 下滑添加 ********/
	/*
	 * PushStatus a = new PushStatus(); String ids = folder.getMachine_ids();
	 * String[] split = ids.split(","); for (String string : split) {
	 * a.setDevice_id(string); a.setFolder_id(folder.getId());
	 * pushStatusService.savePushStatus(a); }
	 *//******* 下滑添加 ********/
	/*
	 * if (count > 0) { return JsonResult.build("200", "创建成功！"); } else { return
	 * JsonResult.build("205", "创建失败，请重试！"); } } catch (Exception e) {
	 * e.printStackTrace(); return JsonResult.build("205", "创建失败，请重试！"); } }
	 */

	/**
	 * 查询要修改的文件夹 url : http://localhost:8080/goodfield/web/folder/getFolderById
	 * 
	 * @param {"id":"1", "type":"0", "user_id":"3"}
	 * @return
	 */
	@RequestMapping(value = "getFolderById", method = RequestMethod.POST)
	public HashMap<String, Object> getFolderById(@RequestBody Folder folder) {
		try {
			
			
			Folder folde = folderService.getFolderById(folder);
			if (null != folde) {
				return JsonResult.build("200", "查询成功！", folde);
			} else {
				return JsonResult.build("201", "暂无数据！");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试！");
		}
	}

	/**
	 * 编辑文件夹 url : http://localhost:8080/goodfield/web/folder/updateFolder
	 * 
	 * @param {"id":"1", "userId":"3", "folderName":"文件夹名", "roleId":"1,2,3",
	 *        "startTime":"", "endTime":""}
	 * @return
	 */
	/*
	 * @RequestMapping(value = "updateFolder", method = RequestMethod.POST)
	 * public HashMap<String, Object> updateFolder(@RequestBody Folder folder) {
	 * try { String userToken =
	 * RedisUtils.getKV("userToken_"+folder.getUserToken
	 * ()+"_"+folder.getLoginStatus()); if(userToken == null){ return
	 * JsonResult.build("210", "登陆超时，请重新登陆"); } ParamInfo pInfo = new
	 * ParamInfo(); pInfo.setUser_id(folder.getUserId()); List<Role>
	 * userRoleInfo = folderService.getUserRoleRelationInfo(pInfo);
	 * if(userRoleInfo.size() < 1){ return JsonResult.build("202", "暂无权限!"); }
	 * if ((folder.getStartTime() != null && folder.getStartTime() != "") &&
	 * (folder.getEndTime() != null && folder.getEndTime() != "")) { if
	 * (folder.getStartTime().equals(folder.getEndTime())) { return
	 * JsonResult.build("205", "亲，开始时间不能和结束时间一样哦！"); } }
	 * if(!folder.getUnlimited_time().equals("0")){ if((folder.getStartTime() ==
	 * null || folder.getStartTime() == "") || (folder.getEndTime() == null ||
	 * folder.getEndTime() == "")){ return JsonResult.build("205", "亲，请选择时间！");
	 * } } if ((folder.getStartTime() != null && folder.getStartTime() != "") &&
	 * (folder.getEndTime() != null && folder.getEndTime() != "")) { if
	 * (folder.getEndTime().compareTo(folder.getStartTime()) < 0) { return
	 * JsonResult.build("205", "亲，开始时间不能大于结束时间哦！"); } }
	 * if(folder.getUnlimited_time().trim().equals("0")){ String
	 * folderUnlimitedTime = folderService.getFolderUnlimitedTime(folder);
	 * if(folderUnlimitedTime != null){ if(folder.getMachine_ids() != null &&
	 * folder.getMachine_ids() != "" && !folder.getMachine_ids().equals("")){
	 * String [] machineIds = folder.getMachine_ids().split(","); for (String
	 * string : machineIds) { if(folderUnlimitedTime.indexOf(string) > -1){
	 * return JsonResult.build("205", "已有终端机被不限时间的文件夹绑定 ！"); } } } } } //
	 * 判断时间是否重叠 if(folder.getUnlimited_time().trim().equals("1")){ List<Folder>
	 * folderTimes = folderService.getFolderTimes2(folder);
	 * if(folderTimes.size() > 0){ for (Folder folder2 : folderTimes) { Boolean
	 * overlap = TimeSlotUtil.isOverlap(folder2.getStartTime(),
	 * folder2.getEndTime(),folder.getStartTime(), folder.getEndTime()); if
	 * (overlap) { // 判断时间是否重叠 Folder machineIds =
	 * folderService.getMachineIds(); // 查询所有文件夹下已拥有的终端机 String[] machine_ids =
	 * folder.getMachine_ids().split(","); for (String string : machine_ids) {
	 * if(machineIds.getMachine_ids().indexOf(string) > -1){ return
	 * JsonResult.build("205", "时间重叠，无法添加 ！"); } } } } } }
	 * folderService.updateFolder(folder); return JsonResult.build("200",
	 * "编辑成功"); } catch (Exception e) { e.printStackTrace(); return
	 * JsonResult.build("205", "编辑失败，请重试！"); } }
	 */
	/**
	 * 批量删除文件 url : http://localhost:8080/goodfield/web/folder/deleteFolderFiles
	 * 
	 * @param {"folderId":"1", "fid":"1,2,3", "user_id":"1"}
	 * @return
	 */
	/*
	 * @RequestMapping(value = "deleteFolderFiles", method = RequestMethod.POST)
	 * public HashMap<String, Object> deleteFolderFiles(@RequestBody File file)
	 * { try { String userToken =
	 * RedisUtils.getKV("userToken_"+file.getUserToken
	 * ()+"_"+file.getLoginStatus()); if(userToken == null){ return
	 * JsonResult.build("210", "登陆超时，请重新登陆"); } ParamInfo pInfo = new
	 * ParamInfo(); pInfo.setId(file.getFolderId());
	 * pInfo.setUser_id(file.getUser_id()); List<Role> userRoleInfo =
	 * folderService.getUserRoleRelationInfo(pInfo); if(userRoleInfo.size() <
	 * 1){ return JsonResult.build("202", "暂无权限!"); } int count =
	 * folderService.deleteFolderFiles(file); if (count > 0) { return
	 * JsonResult.build("200", "删除成功！"); } else { return JsonResult.build("205",
	 * "删除失败！"); } } catch (Exception e) { e.printStackTrace(); return
	 * JsonResult.build("205", "删除失败，请重试！"); } }
	 */

	/**
	 * 重命名 url : http://localhost:8080/goodfield/web/folder/updateFileName
	 * 
	 * @param {"fileName":"文件名称", "fid":"1", "folderId"}
	 * @return
	 */
	@RequestMapping(value = "updateFileName", method = RequestMethod.POST)
	public HashMap<String, Object> updateFileName(@RequestBody File file) {
		try {
			
			/* 判断文件名是否已存在 */
			File fil = new File();
			fil.setFolderId(file.getFolderId());
			fil.setFid(file.getFid());
			List<String> nameExistence = folderService.getNameExistence2(fil);
			for (String string : nameExistence) {
				if (string.equals(file.getFileName())) {
					return JsonResult.build("205", "文件名已存在！");
				}
			}
			/* 判断文件名是否已存在 */
			folderService.updateFileName(file);
			return JsonResult.build("200", "修改成功！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "修改失败，请重试！");
		}
	}

	/**
	 * 修改文件夹播放状态 url :
	 * http://localhost:8080/goodfield/web/folder/updateFolderStatus
	 * 
	 * @param {"user_id":"2", "type":"1","status":"0", "id":"1"}
	 * @return
	 */
	/*
	 * @RequestMapping(value = "updateFolderStatus", method =
	 * RequestMethod.POST) public HashMap<String, Object>
	 * updateFolderStatus(@RequestBody Folder folder) { try { String userToken =
	 * RedisUtils
	 * .getKV("userToken_"+folder.getUserToken()+"_"+folder.getLoginStatus());
	 * if(userToken == null){ return JsonResult.build("210", "登陆超时，请重新登陆"); }
	 * ParamInfo pInfo = new ParamInfo(); pInfo.setUser_id(folder.getUser_id());
	 * List<Role> userRoleInfo = folderService.getUserRoleRelationInfo(pInfo);
	 * if(userRoleInfo.size() < 1){ return JsonResult.build("202", "暂无权限!"); }
	 * int count = folderService.updateFolderStatus(folder); if (count > 0) {
	 * Machine machine = new Machine(); machine.setId(folder.getId()); Folder
	 * folderStatus = membershipService.getFolderStatus(machine);
	 * if(folderStatus != null){ String machine_ids =
	 * folderStatus.getMachine_ids(); if(machine_ids != null){ String[] split =
	 * machine_ids.split(","); for (String string : split) {
	 * ScreenPushUtil.jpushFolderStatus(new Gson().toJson(folderStatus),
	 * string); } } } return JsonResult.build("200", "修改成功！"); } else { return
	 * JsonResult.build("205", "修改失败，请重试！"); } } catch (Exception e) {
	 * e.printStackTrace(); return JsonResult.build("205", "修改失败，请重试！"); } }
	 */

	/**
	 * 批量上传添加到数据库 url :
	 * http://localhost:8080/goodfield/web/folder/uploadImagesss form表达形式 ===>
	 * {"file":"", "file":"", "folder_id":"1", "user_id":"1"}
	 */
	@RequestMapping(value = "uploadImages", method = RequestMethod.POST)
	public HashMap<String, Object> fileUpload(
			@RequestParam("file") MultipartFile[] file,
			@RequestParam("folderId") String folderId,
			@RequestParam("userToken") String userToken,
			HttpServletResponse response,
			@RequestParam("loginStatus") String loginStatus, HttpSession session) {
		try {
			String userToken2 = RedisUtils.getKV("userToken_" + userToken + "_"
					+ loginStatus);

			if (userToken2 == null) {
				if (RedisUtils.getUserKVLikes(userToken).size() > 0) {
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			// 2 判断文件的个数 大于15 个 就不可以上传
			if (file.length > 15) {

				return JsonResult.build("220", "文件的个数大于15个 无法上传");
			}

			ParamInfo pInfo2 = new ParamInfo();
			pInfo2.setUser_id(userToken);
			List<Role> userRoleInfo = folderService
					.getUserRoleRelationInfo(pInfo2);
			if (userRoleInfo.size() < 1) {
				return JsonResult.build("202", "暂无权限!");
			}
			File ff = new File();
			ff.setFolderId(folderId);
			ParamInfo pInfo = new ParamInfo();
			pInfo.setId(folderId.trim());
			Folder foldersByIds = folderService.getFoldersByIds(pInfo);
			if (foldersByIds == null) {
				return JsonResult.build("205", "上传失败，该文件夹不存在！");
			}
			String folderContentType = folderService
					.getFolderContentType(pInfo);
			File fil = new File();
			fil.setFolderId(folderId);
			List<String> filesIdInfo = folderService.getFilesIdInfo(fil); // 查询已推送了的文件
			String fids = "";
			for (String string : filesIdInfo) {
				fids += string + ",";
			}
			if (fids != "") {
				fids = fids.substring(0, fids.length() - 1);
			}
			Long totalSize = 0L;

			if (folderContentType.trim().equals("0")) {

				if (file != null && file.length > 0) {
					File f = null;
					for (int i = 0; i < file.length; i++) {
						MultipartFile files = file[i];

						Long size = files.getSize();
						totalSize += size;
						InputStream inputStream = files.getInputStream();

						File fi = new File();
						fi.setFolderId(folderId);
						String fileSize = folderService.getFileSize(fi);

						String originalFilename = files.getName();
						String originalFilename2 = files.getOriginalFilename();

						if (!ImageType.getPicType2(files.getInputStream())) {
							return JsonResult.build("205", "请上传图片文件！");
						}
						if (fileSize != null && fileSize != ""
								&& !fileSize.equals("")) {
							if ((Long.parseLong(fileSize) + files.getSize()) > 524288000) {
								return JsonResult.build("205",
										"文件夹大小限制在500M以内！");
							}
						}
						f = new File();
						String fileName = files.getOriginalFilename()
								.replace("~", "").replace("%", "")
								.replace("{", "").replace("}", "")
								.replace("(", "").replace(")", "")
								.replace("【", "").replace("】", "")
								.replace("[", "").replace("]", "")
								.replace("&", "").replace("^", "")
								.replace("!", "").replace("@", "")
								.replace("#", "").replace("`", "")
								.replace("=", "").replace("+", "")
								.replace(";", "").replace(",", "")
								.replace("；", "").replace("‘", "")
								.replace("。", "").replace("，", "")
								.replace("·", "").replace("、", "")
								.replace(" ", "");
						/* 判断文件名是否已存在 */
						fil.setFolderId(fil.getFolderId());
						List<String> nameExistence = folderService
								.getNameExistence(fil);
						for (String string : nameExistence) {
							if (string.equals(originalFilename2)) {
								return JsonResult.build("205", "文件名已存在！");
							}
						}

						/* 判断文件名是否已存在 */
						f.setFileName(fileName);
						f.setOriginalName(fileName);
						f.setFile_size(files.getSize() + "");
						fileName = java.net.URLEncoder
								.encode(fileName, "utf-8");
						String imagePath = "https://tianjiulongoa.oss-cn-shenzhen.aliyuncs.com/folder/"
								+ "folder_" + 1 + "/" + fileName;
						// OSSUtil.uploadObject2OSSs(file, "tianjiulongoa",
						// "folder/"+"folder_"+folderId+"/");

						// Endpoint以杭州为例，其它Region请按实际情况填写。
						String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
						// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录
						// https://ram.console.aliyun.com 创建RAM账号。
						/*String accessKeyId = "LTAIncw5TlYKR4SL";
						String accessKeySecret = "YTMZ2cmGpoWoPgpxm4oXgz3HPRi8Ui";
						String bucketName = "tianjiulongoa";*/
						String accessKeyId = "LTAIj6VwurVuWjsD";
						String accessKeySecret = "MHIXLKKUXWFMCzuewlMkBgioaAjhGN";
						String bucketName = "tianjiulongoa1";
						// 创建OSSClient实例。
						// String objectName =
						// "G:\\Users\\qzy017\\Desktop\\1 001.jpg";
						OSSClient ossClient = new OSSClient(endpoint,
								accessKeyId, accessKeySecret);
						String objectName = System.currentTimeMillis() + ""
								+ UUID.randomUUID().toString().substring(1, 5)
								+ fileName.substring(fileName.lastIndexOf("."));

						try {
							// 带进度条的上传。s

							// 创建OSSClient实例。
							try {
								// 带进度条的上s传。s
								asycService.fileUpload(
										totalSize,
										userToken,
										objectName,
										inputStream,
										new PutObjectProgressListener(Integer
												.parseInt(totalSize + ""),
												userToken));

								System.err.println(123);
							} catch (Exception e) {
								e.printStackTrace();
							}

						} catch (Exception e) {
							e.printStackTrace();
						}
						f.setFolderId(folderId.trim());
						f.setFileName(files.getOriginalFilename());
						f.setFilePath("https://tianjiulongoa1.oss-cn-shenzhen.aliyuncs.com/"
								+ objectName);
						folderService.uploadFiles(f);
					}
				}
			} else {
				// 上传视频文件ss
				if (file != null && file.length > 0) {
					File f = null;
					for (int i = 0; i < file.length; i++) {
						MultipartFile files = file[i];
						File fi = new File();
						Long size = files.getSize();
						totalSize += size;

					}

					for (int i = 0; i < file.length; i++) {
						MultipartFile files = file[i];
						File fi = new File();

						fi.setFolderId(folderId);
						String fileSize = folderService.getFileSize(fi);
						String fileNames = files.getOriginalFilename();
						String[] allowTypes = new String[] { "avi", "mov",
								"mp4" };
						Boolean CanUploaded = validTest.isValid(fileNames,
								allowTypes);
						if (!CanUploaded) {
							return JsonResult.build("205",
									"请上传avi，mov，mp4等格式的视频文件");
						}
						if (fileSize != null && fileSize != ""
								&& !fileSize.equals("")) {
							if ((Long.parseLong(fileSize) + files.getSize()) > 524288000) {
								return JsonResult.build("205",
										"文件夹大小限制在500M以内！");
							}
						}
						f = new File();
						String fileName = files.getOriginalFilename()
								.replace("~", "").replace("%", "")
								.replace("{", "").replace("}", "")
								.replace("(", "").replace(")", "")
								.replace("【", "").replace("】", "")
								.replace("[", "").replace("]", "")
								.replace("&", "").replace("^", "")
								.replace("!", "").replace("@", "")
								.replace("#", "").replace("`", "")
								.replace("=", "").replace("+", "")
								.replace(";", "").replace(",", "")
								.replace("；", "").replace("‘", "")
								.replace("。", "").replace("，", "")
								.replace("·", "").replace("、", "")
								.replace(" ", "");
						/* 判断文件名是否已存在 */
						fil.setFolderId(fil.getFolderId());
						List<String> nameExistence = folderService
								.getNameExistence(fil);
						for (String string : nameExistence) {
							if (string.equals(files.getOriginalFilename())) {
								return JsonResult.build("205", "文件名已存在！");
							}
						}
						/* 判断文件名是否已存在s */
						f.setFileName(fileName);
						f.setOriginalName(fileName);
						f.setFile_size(files.getSize() + "");

						String imagePath = "https://tianjiulongoa.oss-cn-shenzhen.aliyuncs.com/folder/"
								+ "folder_" + folderId + "/" + fileName;
						System.out.println("===========" + imagePath);
						// OSSUtil.uploadObject2OSSs(file, "tianjiulongoa",
						// "folder/"+"folder_"+folderId+"/");
						// Endpoint以杭州为例，其它Region请按实际情况填写。  
						                 
						String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
						// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录
						// https://ram.console.aliyun.com 创建RAM账号。
					/*	String accessKeyId = "LTAIncw5TlYKR4SL";
						String accessKeySecret = "YTMZ2cmGpoWoPgpxm4oXgz3HPRi8Ui";
						String bucketName = "tianjiulongoa";*/
						String accessKeyId = "LTAIj6VwurVuWjsD";
						String accessKeySecret = "MHIXLKKUXWFMCzuewlMkBgioaAjhGN";
						String bucketName = "tianjiulongoa1";
						
						
						// 创建OSSClient实例。
						// String objectName =
						// "G:\\Users\\qzy017\\Desktop\\1 001.jpg";
						OSSClient ossClient = new OSSClient(endpoint,
								accessKeyId, accessKeySecret);
						String objectName = System.currentTimeMillis() + ""
								+ UUID.randomUUID().toString().substring(1, 5)
								+ fileName.substring(fileName.lastIndexOf("."));
						try {
							// 带进度条的上传。s

							// 创建OSSClient实例。
							try {
								// 带进度条的上s传。

								asycService.fileUpload(
										totalSize,
										userToken,
										objectName,
										files.getInputStream(),
										new PutObjectProgressListener(Integer
												.parseInt(totalSize + ""),
												userToken));
								System.err.println(123);
							} catch (Exception e) {
								logger.error(e.getMessage());
								e.printStackTrace();
							}

						} catch (Exception e) {
							logger.error(e.getMessage());
							e.printStackTrace();
						}
						f.setFolderId(folderId.trim());
						f.setFilePath("https://tianjiulongoa1.oss-cn-shenzhen.aliyuncs.com/"
								+ objectName);
						f.setFileName(files.getOriginalFilename());
						folderService.uploadFiles(f);
					}
				}
			}

			fileUploadService.updateFile_pushIs(folderId, "1");

			return JsonResult.build("200", "上传成功！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return JsonResult.build("205", "上传失败，请重试！");
		}

	}

	// 获取进度条
	@RequestMapping(value = "/progressbar", method = RequestMethod.POST)
	public Map<String, Object> getbar(@RequestBody String userToken) {

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			Map readValue = objectMapper.readValue(userToken, Map.class);
			String userid = (String) readValue.get("userToken");

			String hashKV = RedisUtils.getHashKV("upload", userid);

			System.err.println("haskv::" + hashKV);
			return JsonResult.build("200", hashKV);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return JsonResult.build("202", null);

	}

}