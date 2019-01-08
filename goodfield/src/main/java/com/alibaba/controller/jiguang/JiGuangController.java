package com.alibaba.controller.jiguang;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.common.result.JsonResult;
import com.alibaba.controller.UserController;
import com.alibaba.pojo.File;
import com.alibaba.service.AdminService;
import com.alibaba.service.InterfaceInfoService;
import com.alibaba.service.PushStatusService;
import com.alibaba.service.isservice.FileUploadService;
import com.alibaba.service.isservice.JiGuangService;
import com.alibaba.util.OSSUtil;
import com.alibaba.util.RedisUtils;

@Transactional(rollbackFor = Exception.class)
@RestController
public class JiGuangController {
	private static final File file = null;
	@Autowired
	JiGuangService jiGuangService;
	@Autowired
	private InterfaceInfoService membershipService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private PushStatusService pushStatusService;

	@Autowired
	FileUploadService fileUploadService;

	Logger logger = Logger.getLogger(UserController.class);

	// 点击推按钮(页面点击登陆)

	@RequestMapping(method = RequestMethod.POST, value = "/web/ship/pushController")
	public HashMap<String, Object> pushController(@RequestBody File file) {
		try {
			
			// 把所有的文件内容推过去
			String folderId = file.getFolderId();
			// 1.1 插叙可以推送的内容s
			fileUploadService.insertDelPushstatus_updatePushis(folderId);
			// fileUploadService.insertDelPushstatus(folderId,null);
			return (HashMap<String, Object>) jiGuangService.pushFile(folderId);
		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage());
			return JsonResult.build("205", "推送失败");
		}
	}

	// 测试上传
	@ResponseBody
	@RequestMapping(value = "uploadImages", method = RequestMethod.POST)
	public HashMap<String, Object> fileUpload(
			@RequestParam("file") MultipartFile file) throws IOException {

		InputStream inputStream = file.getInputStream();
		// OosFileupload.upload("tianjiulongoa", "12321323233.jpg", files);

		OSSUtil.uploadFile(inputStream);
		return JsonResult.build("200", "上传成功");

	}

}