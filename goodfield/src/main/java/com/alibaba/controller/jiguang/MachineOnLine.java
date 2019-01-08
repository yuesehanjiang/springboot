package com.alibaba.controller.jiguang;



import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.common.result.JsonResult;
import com.alibaba.controller.UserController;
import com.alibaba.pojo.UserFolder;
import com.alibaba.service.isservice.FileUploadService;
import com.alibaba.service.isservice.JiGuangService;

@Transactional(rollbackFor = Exception.class)
@RestController
public class MachineOnLine {
	@Autowired
	JiGuangService  jiGuangService;
	
	@Autowired
	FileUploadService  fileUploadService;
	
	Logger logger=Logger.getLogger(UserController.class);

	/**
	 * 终端机收到文件调用这个接口
	 * http://192.168.5.223:8080/goodfield/web/terminal/terminalMachineUpload
	 * @param {"folder_id":"1", "machine_id":"device001"}
	 * @return
	 */
	@RequestMapping("/web/terminal/terminalMachineUpload")
	public HashMap<String, Object> terminalMachineUpload(
			@RequestParam("machine_id") String machine_id, 
			@RequestParam(value="folder_id") String folder_id){
		try {
		    System.out.println("收到 改变状态");	
			 jiGuangService.updatePushStatus(machine_id,folder_id);
		   return JsonResult.build("200", "操作成功!");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return JsonResult.build("205", "操作失败!");
		}
	}
	
	
	/**
	 * 终端机一上线就调用这个接口
	 * http://192.168.5.223:8080/goodfield/web/terminal/terminalMachineOffLine
	 * @param {"machine_id":"device001", "folder_id":"1"}
	 * @return
	 */
	@RequestMapping("/web/terminal/terminalMachineOffLine")
	public HashMap<String, Object> terminalMachineOffLine (
			@RequestParam("machine_id") String machine_id,
			@RequestParam(value="folder_id",required=false) String folder_id){
		try {
			HashMap<String, Object>  mapsHashMap = jiGuangService.onLine(machine_id,folder_id);
			return JsonResult.build("200", "推送成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return JsonResult.build("205", "推送失败!");
		}
	}
	
	
	//终端机删除文件	
	@RequestMapping(value="/web/terminal/terminaldelFoler")
	public HashMap<String, Object> MachineOffLine (
			@RequestParam("machine_id") String machine_id,
			@RequestParam(value="folder_id",required=false) String folder_id
			,@RequestParam(value="type",required=false)String type){
		try {
			HashMap<String, Object>  mapsHashMap 
			= fileUploadService.terminaIsDel(machine_id,folder_id,type);
			return JsonResult.build("200", "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return JsonResult.build("205", "操作失败!");
		}
	}
	
	
	
	//给前端页面 使用
	
	@RequestMapping(value="/web/terminal/terminaldelFolertoFront")
	public HashMap<String, Object> Machineisdel (
			@RequestBody UserFolder userFolder
			/*
		     @RequestParam(value="userId" ,required=false)String userId
			,@RequestParam(value="folder_id",required=false) String folder_id
			,@RequestParam(value="type",required=false)String type*/){
		
		/*String userToken = RedisUtils.getKV("adminToken_"+userFolder.getLoginStatus());
		if(userToken == null){
			if(RedisUtils.getAdminKVLikes().size() > 0){
				return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
			}
			return JsonResult.build("210", "登陆超时，请重新登陆");
		}
		
		*/
		
		try {
			List<LinkedHashMap<String, Object>>  mapsHashMap 
			= fileUploadService.selectTerminaIsDel(userFolder.getFolder_id(),
					userFolder.getType(),userFolder.getUserId());
			return JsonResult.build("200", "操作成功!",mapsHashMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return JsonResult.build("205", "操作失败!");
		}
	}
	
	
	
	
	//前端页面对于删除数据  做推送
	@RequestMapping(value="/web/terminal/terminaldelFolertoFrontTui")
	public HashMap<String, Object> Machineisdetui (
			@RequestBody UserFolder userFolder
			){
		
		/*String userToken = RedisUtils.getKV("adminToken_"+userFolder.getLoginStatus());
		if(userToken == null){
			if(RedisUtils.getAdminKVLikes().size() > 0){
				return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
			}
			return JsonResult.build("210", "登陆超时，请重新登陆");
		}
		
		*/
		
		try {
			Map<String, Object>  mapsHashMap 
			= fileUploadService.pushTerminaIsDel(userFolder);
			return JsonResult.build("200", "操作成功!",mapsHashMap);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return JsonResult.build("205", "操作失败!");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="yaui")
	public String jjj() {
		try {
			int aa=10/0;
		} catch (Exception e) {
		    logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
}
