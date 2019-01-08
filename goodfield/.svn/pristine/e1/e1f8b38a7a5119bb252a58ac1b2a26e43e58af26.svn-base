package com.alibaba.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.common.result.JsonResult;
import com.alibaba.common.result.JsonResult2;
import com.alibaba.pojo.ManagementPic;
import com.alibaba.pojo.NomemberImageLibrary;
import com.alibaba.pojo.Role;
import com.alibaba.service.AdminService;
import com.alibaba.service.AuditReportService;
import com.alibaba.service.InterfaceInfoService;
import com.alibaba.service.UserService;
import com.alibaba.util.ExcelUtil;
import com.alibaba.util.IdCreator;
import com.alibaba.util.ImageType;
import com.alibaba.util.ParamInfo;
import com.alibaba.util.RedisUtils;


@Transactional(rollbackFor = Exception.class)
@RestController
@RequestMapping("/web/auditreport")
public class AuditReportController {

	@Autowired
	private AuditReportService auditReportService;

	@Autowired
	private InterfaceInfoService membershipService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userService;
	
	Logger logger=Logger.getLogger(UserController.class);
	
	/**
	 * http://localhost:8080/goodfield/web/auditreport/isLogin
	 * @return
	 */
	@RequestMapping("isLogin")
	public HashMap<String, Object> isLogin(@RequestBody ParamInfo pInfo){
		try {
			String userToken = RedisUtils.getKV("userToken_"+pInfo.getUserToken()+"_"+pInfo.getLoginStatus());
			if(userToken == null){
				if(RedisUtils.getUserKVLikes(pInfo.getUserToken()).size() > 0){
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			} else {
				return JsonResult.build("200", "success");
			}
		} catch (Exception e) {
			return JsonResult.build("205", "服务器异常!");
		}
	}

	/**
	 * 导出Excel url : http://localhost:8080/goodfield/web/auditreport/export
	 * {"user_id":"1"}
	 * @return
	 */
	@RequestMapping(value = "export", method = RequestMethod.GET)
	public HashMap<String, Object> export(HttpServletRequest request,HttpServletResponse response, ParamInfo pInfo) {
		ParamInfo pInfo2 = new ParamInfo();
		if((pInfo.getPageNum() != "" && pInfo.getPageNum() != null) && ( pInfo.getPageSize() != "" && pInfo.getPageSize() != null)){
			pInfo2.setPageNum(pInfo.getPageNum());
			int pageNum = (Integer.parseInt(pInfo.getPageNum()) ) * Integer.parseInt(pInfo.getPageSize());
			pInfo.setPageNum( String.valueOf(pageNum) );
			pInfo.setPageSize(pInfo.getPageSize());
		}
		List<Role> userRoleInfo = auditReportService.getUserRoleRelationInfo(pInfo);
		if(userRoleInfo.size() < 1){
			return JsonResult.build("202", "暂无权限!");
		}
		List<Role> r = new ArrayList<Role>();
		for (Role role : userRoleInfo) {
			r.add(new Role(null, role.getStores_id(), role.getStores_name(), null, null));
		}
		String content = "";
		for (Role role : r) {
			content += "\'"+role.getStores_id()+"\'" + ",";
		}
		if (content != "") {
			pInfo.setContent(content.substring(0, content.length() - 1));
		} else {
			return JsonResult.build("201", "暂无数据!", r);
		}
		List<ParamInfo> auditReportInfo = auditReportService.getAuditReportInfo(pInfo);
		if(auditReportInfo.size() < 1){
			return JsonResult.build("201", "暂无数据!");
		}
		String[] title = {"刷卡时间","门店","会员名称","会员id","卡号","入场流水号", "识别id", "约课情况", "是否有效会员"};
		String fileName = System.currentTimeMillis()+".xls";
        String sheetName = "稽核报告";
        int size = auditReportInfo.size();
        String [][] cont = new String[size][];
        for (int i = 0; i < size; i++) {
        	cont[i] = new String[title.length];
	        ParamInfo paramInfo = auditReportInfo.get(i);
	        cont[i][0] = paramInfo.getUser_in_time();
	        cont[i][1] = paramInfo.getStores_name();
	        cont[i][2] = paramInfo.getUser_name();
	        cont[i][3] = paramInfo.getUser_id();
	        cont[i][4] = paramInfo.getUser_card();
	        cont[i][5] = paramInfo.getRunning_water();
	        cont[i][6] = paramInfo.getDistinguish_id();
	        cont[i][7] = "1".equals(paramInfo.getAppoint()) ? "是" : "否";
	        cont[i][8] = "1".equals(paramInfo.getValid_member()) ? "是" : "否";
        }
        HSSFWorkbook hssfWorkbook = ExcelUtil.getHSSFWorkbook(sheetName, title, cont, null);
        try {
        	this.setResponseHeader(response, fileName);
			OutputStream os = response.getOutputStream();
			hssfWorkbook.write(os);
			os.flush();
			os.close();
			return JsonResult.build("200", "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.build("205", "操作失败!");
		}
	}
	
	//发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	
	/**
	 * 查询未读状态 url : http://localhost:8080/goodfield/web/auditreport/getUnread
	 * {"user_id":"1"}
	 * @return
	 */
	@RequestMapping(value = "getUnread", method = RequestMethod.POST)
	public HashMap<String, Object> getUnread(@RequestBody ParamInfo pInfo) {
		try {
			List<Role> userRoleInfo = auditReportService.getUserRoleRelationInfo(pInfo);
			if(userRoleInfo.size() < 1){
				return JsonResult.build("202", "暂无权限!");
			}
			List<Role> r = new ArrayList<Role>();
			for (Role role : userRoleInfo) {
				r.add(new Role(null, role.getStores_id(), role.getStores_name(), null, null));
			}
			String content = "";
			for (Role role : r) {
				content += "\'"+role.getStores_id()+"\'" + ",";
			}
			if (content != "") {
				pInfo.setContent(content.substring(0, content.length() - 1));
			} else {
				return JsonResult.build("201", "暂无数据!", r);
			}
			List<ParamInfo> auditReportUnread = auditReportService.getAuditReportUnread(pInfo);
			if(auditReportUnread.size() > 0){
				if(RedisUtils.getKV("auditReportUnread_"+pInfo.getUser_id()) != null){
					if(Integer.parseInt(RedisUtils.getKV("auditReportUnread_"+pInfo.getUser_id())) != auditReportUnread.size()){
						userService.updateNotUnread(pInfo.getUser_id());
					}
				}
				RedisUtils.setKV("auditReportUnread_"+pInfo.getUser_id(), auditReportUnread.size()+"");
				Integer unread = userService.getUnread(pInfo.getUser_id());
				return JsonResult.build("200", "操作成功!", unread);
			} else {
				Object num = 1;
				return JsonResult.build("200", "操作成功!", num);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "操作失败!");
		}
	}
	
	/**
	 * 修改为已读状态 url : http://localhost:8080/goodfield/web/auditreport/updateUnread
	 * {"user_id":"1"}
	 * @return
	 */
	@RequestMapping(value = "updateUnread", method = RequestMethod.POST)
	public HashMap<String, Object> updateUnread(@RequestBody ParamInfo pInfo) {
		try {
			userService.updateUnread(pInfo.getUser_id());
			return JsonResult.build("200", "操作成功!");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "操作失败!");
		}
	}
	
	/**
	 * 查询稽核报告 url :
	 * http://localhost:8080/goodfield/web/auditreport/getAuditReportInfo
	 * 
	 * @param {"pageNum":"0", "pageSize":"10", "keyword":"", "user_id":"2", "stores_id":"","datetime":"2018-07-16", "startTime":"", "endTime":""}
	 * @return
	 */
	@RequestMapping(value = "getAuditReportInfo", method = RequestMethod.POST)
	public HashMap<String, Object> getAuditReportInfo(@RequestBody ParamInfo pInfo) {
		try {
			String userToken = RedisUtils.getKV("userToken_"+pInfo.getUserToken()+"_"+pInfo.getLoginStatus());
			if(userToken == null){
				if(RedisUtils.getUserKVLikes(pInfo.getUserToken()).size() > 0){
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			ParamInfo pInfo2 = new ParamInfo();
			if((pInfo.getPageNum() != "" && pInfo.getPageNum() != null) && ( pInfo.getPageSize() != "" && pInfo.getPageSize() != null)){
				pInfo2.setPageNum(pInfo.getPageNum());
				int pageNum = (Integer.parseInt(pInfo.getPageNum()) ) * Integer.parseInt(pInfo.getPageSize());
				pInfo.setPageNum( String.valueOf(pageNum) );
				pInfo.setPageSize(pInfo.getPageSize());
			}
			List<Role> userRoleInfo = auditReportService.getUserRoleRelationInfo(pInfo);
			if(userRoleInfo.size() < 1){
				return JsonResult.build("202", "暂无权限!");
			}
			List<Role> r = new ArrayList<Role>();
			for (Role role : userRoleInfo) {
				r.add(new Role(null, role.getStores_id(), role.getStores_name(), null, null));
			}
			String content = "";
			for (Role role : r) {
				content += "\'"+role.getStores_id()+"\'" + ",";
			}
			if (content != "") {
				pInfo.setContent(content.substring(0, content.length() - 1));
			} else {
				return JsonResult.build("201", "暂无数据!", r);
			}
			int count = auditReportService.getCount(pInfo);
			List<ParamInfo> auditReportInfo = auditReportService.getAuditReportInfo(pInfo);
			if (auditReportInfo.size() > 0) {
				return JsonResult.build("200", "查询成功！", auditReportInfo, count, pInfo2.getPageNum(), pInfo.getPageSize(), r);
			} else {
				return JsonResult.build("201", "暂无数据!", r);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return JsonResult.build("205", "查询失败，请重试!");
		}
	}

	/**
	 * 查询非会员图像库 url : http://localhost:8080/goodfield/web/auditreport/getNomemberImageLibraryInfo
	 * 
	 * @param {"pageNum":"0", "pageSize":"10", "datetime":"2018-09-19","startTime":"2018-07-16", "endTime":"2018-07-17", "user_id":"1"}
	 * @return
	 */
	@RequestMapping(value = "getNomemberImageLibraryInfo", method = RequestMethod.POST)
	public HashMap<String, Object> getNomemberImageLibraryInfo(@RequestBody ParamInfo pInfo) {
		try {
			String userToken = RedisUtils.getKV("userToken_"+pInfo.getUserToken()+"_"+pInfo.getLoginStatus());
			if(userToken == null){
				if(RedisUtils.getUserKVLikes(pInfo.getUserToken()).size() > 0){
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			ParamInfo pInfo2 = new ParamInfo();
			if((pInfo.getPageNum() != "" && pInfo.getPageNum() != null) && ( pInfo.getPageSize() != "" && pInfo.getPageSize() != null)){
				pInfo2.setPageNum(pInfo.getPageNum());
				int pageNum = (Integer.parseInt(pInfo.getPageNum()) ) * Integer.parseInt(pInfo.getPageSize());
				pInfo.setPageNum( String.valueOf(pageNum) );
				pInfo.setPageSize(pInfo.getPageSize());
			}
			pInfo.setDatetime("");
			int count = auditReportService.getCount2(pInfo);
			List<NomemberImageLibrary> nomemberImageLibraryInfo = auditReportService.getNomemberImageLibraryInfo(pInfo);
			if(nomemberImageLibraryInfo.size() < 1){
				return JsonResult.build("201", "暂无数据");
			}
			for (NomemberImageLibrary nomemberImageLibrary : nomemberImageLibraryInfo) {
				String imagesTrue = ImageType.isImagesTrue(nomemberImageLibrary.getPic_path());
				if(imagesTrue.equals("error")){
					auditReportService.delNomemberImageLibrary(nomemberImageLibrary.getPic_path());
				}
			}
			List<NomemberImageLibrary> nomemberImageLibraryInfo2 = auditReportService.getNomemberImageLibraryInfo(pInfo);
			return JsonResult.build("200", "查询成功！", nomemberImageLibraryInfo2, count, pInfo2.getPageNum(), pInfo.getPageSize());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试！");
		}
	}
	
	/**
	 * 查询30分钟以内的调整图像库
	 *  url : http://localhost:8080/goodfield/web/auditreport/getNomemberImageInterval
	 * @param {"datetime1":"", "datetime2":"", "datetime3":"", "pageNum":"0","pageSize":"1", "user_id":"1"}
	 * @return
	 */
	@RequestMapping(value = "getNomemberImageInterval", method = RequestMethod.POST)
	public HashMap<String, Object> getNomemberImageInterval(@RequestBody ParamInfo pInfo) {
		try {
			String userToken = RedisUtils.getKV("userToken_"+pInfo.getUserToken()+"_"+pInfo.getLoginStatus());
			if(userToken == null){
				if(RedisUtils.getUserKVLikes(pInfo.getUserToken()).size() > 0){
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			ParamInfo pInfo2 = new ParamInfo();
			if((pInfo.getPageNum() != "" && pInfo.getPageNum() != null) && ( pInfo.getPageSize() != "" && pInfo.getPageSize() != null)){
				pInfo2.setPageNum(pInfo.getPageNum());
				int pageNum = (Integer.parseInt(pInfo.getPageNum()) ) * Integer.parseInt(pInfo.getPageSize());
				pInfo.setPageNum( String.valueOf(pageNum) );
				pInfo.setPageSize(pInfo.getPageSize());
			}
			int count = auditReportService.getCount3(pInfo);
			List<NomemberImageLibrary> nomemberImageLibraryInfo = auditReportService.getNomemberImageInterval(pInfo);
			if(nomemberImageLibraryInfo.size() < 1){
				return JsonResult.build("201", "暂无数据");
			}
			for (NomemberImageLibrary nomemberImageLibrary : nomemberImageLibraryInfo) {
				String imagesTrue = ImageType.isImagesTrue(nomemberImageLibrary.getPic_path());
				if(imagesTrue.equals("error")){
					auditReportService.delNomemberImageLibrary(nomemberImageLibrary.getPic_path());
				}
			}
			List<NomemberImageLibrary> nomemberImageLibraryInfo2 = auditReportService.getNomemberImageInterval(pInfo);
			return JsonResult.build("200", "查询成功!", nomemberImageLibraryInfo2, count, pInfo2.getPageNum(), pInfo.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试！");
		}
	}
	
	/**
	 * 点击调整图像的时候传参数过去
	 * url : http://localhost:8080/goodfield/web/auditreport/getadjustTheImageById
	 * @param {"id":"", "loginStatus":"1", "userToken":"1"}
	 * @return
	 */
	@RequestMapping(value = "getadjustTheImageById", method = RequestMethod.POST)
	public HashMap<String, Object> getadjustTheImageById(@RequestBody ParamInfo pInfo) {
		try {
			String userToken = RedisUtils.getKV("userToken_"+pInfo.getUserToken()+"_"+pInfo.getLoginStatus());
			if(userToken == null){
				if(RedisUtils.getUserKVLikes(pInfo.getUserToken()).size() > 0){
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			ManagementPic managementPic = new ManagementPic();
			managementPic.setId(pInfo.getId());
			ManagementPic getadjustTheImageById = auditReportService.getadjustTheImageById(managementPic);
			if(getadjustTheImageById != null){
				return JsonResult2.build("200", "查询成功！", getadjustTheImageById.getUuid(), getadjustTheImageById.getUser_id());
			} else {
				return JsonResult.build("201", "暂无数据！");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试！");
		}
	}
	
	/**
	 * 调整图像完毕
	 *  url : http://localhost:8080/goodfield/web/auditreport/adjustmentImage
	 * @param {"uuid":"", "pic_path":""}
	 * @return 企智云
	 */
	@RequestMapping(value = "adjustmentImage", method = RequestMethod.POST)
	public HashMap<String, Object> adjustmentImage(@RequestBody ParamInfo pInfo) {
		try {
			String userToken = RedisUtils.getKV("userToken_"+pInfo.getUserToken()+"_"+pInfo.getLoginStatus());
			if(userToken == null){
				if(RedisUtils.getUserKVLikes(pInfo.getUserToken()).size() > 0){
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			ManagementPic managementPic = new ManagementPic();
			if(pInfo.getFlag() != null){	// 判断是否调整过图像
				managementPic.setPic_path(pInfo.getPic_path());
				managementPic.setUuid(pInfo.getUuid());
				auditReportService.updateJustTheImage(managementPic);
			} else {
				managementPic.setRunning_water(IdCreator.getId(18));
				managementPic.setPic_path(pInfo.getPic_path());
				managementPic.setUser_id(pInfo.getUser_id());
				managementPic.setUuid(pInfo.getUuid());
				auditReportService.adjustTheImage(managementPic);
			}
			membershipService.truncateAdmissionBind();
			return JsonResult.build("200", "操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return JsonResult.build("205", "操作失败，请重试！");
		}
	}

}
