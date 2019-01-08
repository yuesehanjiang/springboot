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
import com.alibaba.pojo.Role;
import com.alibaba.service.AdmissionService;
import com.alibaba.util.ExcelUtil;
import com.alibaba.util.ParamInfo;
import com.alibaba.util.RedisUtils;

@Transactional(rollbackFor = Exception.class)
@RestController
@RequestMapping("/web/admission")
public class AdmissionController {

	@Autowired
	private AdmissionService admissionService;
	
	Logger logger=Logger.getLogger(UserController.class);

	/**
	 * Excel导出 url :
	 * http://localhost:8080/goodfield/web/admission/export
	 * @param {"startTime":"", "endTime":"", "stores_id":"", "user_id":"2", "datetime":""}
	 * @return
	 */
	@RequestMapping(value = "export", method = RequestMethod.GET)
	public HashMap<String, Object> export(HttpServletRequest request,HttpServletResponse response,ParamInfo paramInfo) {
		try {
			ParamInfo pInfo2 = new ParamInfo();
			if((paramInfo.getPageNum() != "" && paramInfo.getPageNum() != null) && ( paramInfo.getPageSize() != "" && paramInfo.getPageSize() != null)){
				pInfo2.setPageNum(paramInfo.getPageNum());
				int pageNum = (Integer.parseInt(paramInfo.getPageNum()) ) * Integer.parseInt(paramInfo.getPageSize());
				paramInfo.setPageNum( String.valueOf(pageNum) );
				paramInfo.setPageSize(paramInfo.getPageSize());
			}
			List<Role> userRoleInfo = admissionService.getUserRoleRelationInfo(paramInfo);
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
				paramInfo.setContent(content.substring(0, content.length() - 1));
			} else {
				return JsonResult.build("201", "暂无数据!");
			}
			if(!paramInfo.getUser_type().equals("0") && !paramInfo.getUser_type().equals("1") && !paramInfo.getUser_type().equals("2")){
				return JsonResult.build("201", "暂无数据!");
			}
			if("2".equals(paramInfo.getUser_type())){	// 非会员数据
				List<ParamInfo> getnonMember = admissionService.getnonMember(paramInfo);
				if(getnonMember.size() < 1){
					return JsonResult.build("201", "暂无数据!");
				}
				String[] title = {"门店","会员姓名", "会员id", "卡号", "入场时间"};
				String fileName = +System.currentTimeMillis()+".xls";
		        String sheetName = "入场人数统计";
		        int size = getnonMember.size();
		        String [][] cont = new String[size][];
		        for (int i = 0; i < size; i++) {
		        	cont[i] = new String[title.length];
			        ParamInfo param = getnonMember.get(i);
			        cont[i][0] = param.getStores_name();
			        cont[i][1] = param.getUser_name();
			        cont[i][2] = param.getUser_id();
			        cont[i][3] = param.getUser_card();
			        cont[i][4] = param.getUser_in_time();
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
			List<ParamInfo> identityInfo = admissionService.getIdentityInfoDetails(paramInfo);
			if(identityInfo.size() < 1){
				return JsonResult.build("201", "暂无数据!");
			}
			String[] title = {"门店","会员姓名", "会员id", "卡号", "入场时间"};
			String fileName = System.currentTimeMillis()+".xls";
	        String sheetName = "入场人数统计";
	        int size = identityInfo.size();
	        String [][] cont = new String[size][];
	        for (int i = 0; i < size; i++) {
	        	cont[i] = new String[title.length];
		        ParamInfo param = identityInfo.get(i);
		        cont[i][0] = param.getStores_name();
		        cont[i][1] = param.getUser_name();
		        cont[i][2] = param.getUser_id();
		        cont[i][3] = param.getUser_card();
		        cont[i][4] = param.getUser_in_time();
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
	 * 查询入场人数统计 url :
	 * http://localhost:8080/goodfield/web/admission/getIdentityInfo
	 * 
	 * @param {"startTime":"", "endTime":"", "stores_id":"", "user_id":"2", "datetime":""}
	 * @return
	 */
	@RequestMapping(value = "getIdentityInfo", method = RequestMethod.POST)
	public HashMap<String, Object> getIdentityInfo(@RequestBody ParamInfo paramInfo) {
		try {
			String userToken = RedisUtils.getKV("userToken_"+paramInfo.getUserToken()+"_"+paramInfo.getLoginStatus());
			if(userToken == null){
				if(RedisUtils.getUserKVLikes(paramInfo.getUserToken()).size() > 0){
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			List<Role> userRoleInfo = admissionService.getUserRoleRelationInfo(paramInfo);
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
				paramInfo.setContent(content.substring(0, content.length() - 1));
			} else {
				return JsonResult.build("201", "暂无数据!");
			}
			ParamInfo identityInfo = admissionService.getIdentityInfo(paramInfo);
			if (identityInfo != null) {
				return JsonResult.build("200", "查询成功！", identityInfo, r);
			} else {
				return JsonResult.build("201", "暂无数据!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试!");
		}
	}

	/**
	 * 查询入场人数统计详情 url :
	 * http://localhost:8080/goodfield/web/admission/getIdentityInfoDetails
	 * @param {"pageNum:":"0", "pageSize:":"10", "startTime":"","endTime":"", "stores_id":"", "user_id":"", "datetime":"","user_type":"2"}
	 * @return
	 */
	@RequestMapping(value = "getIdentityInfoDetails", method = RequestMethod.POST)
	public HashMap<String, Object> getIdentityInfoDetails(ParamInfo paramInfo) {
		try {
			String userToken = RedisUtils.getKV("userToken_"+paramInfo.getUserToken()+"_"+paramInfo.getLoginStatus());
			if(userToken == null){
				if(RedisUtils.getUserKVLikes(paramInfo.getUserToken()).size() > 0){
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			ParamInfo pInfo2 = new ParamInfo();
			if((paramInfo.getPageNum() != "" && paramInfo.getPageNum() != null) && ( paramInfo.getPageSize() != "" && paramInfo.getPageSize() != null)){
				pInfo2.setPageNum(paramInfo.getPageNum());
				int pageNum = (Integer.parseInt(paramInfo.getPageNum()) ) * Integer.parseInt(paramInfo.getPageSize());
				paramInfo.setPageNum( String.valueOf(pageNum) );
				paramInfo.setPageSize(paramInfo.getPageSize());
			}
			List<Role> userRoleInfo = admissionService.getUserRoleRelationInfo(paramInfo);
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
				paramInfo.setContent(content.substring(0, content.length() - 1));
			} else {
				return JsonResult.build("201", "暂无数据!");
			}
			if(!paramInfo.getUser_type().equals("0") && !paramInfo.getUser_type().equals("1") && !paramInfo.getUser_type().equals("2")){
				return JsonResult.build("201", "暂无数据!");
			}
			List<String> semberSize = admissionService.getSemberSize(paramInfo);
			if("2".equals(paramInfo.getUser_type())){	// 非会员数据
				int count2 = admissionService.getCount2(paramInfo);
				List<ParamInfo> getnonMember = admissionService.getnonMember(paramInfo);
				if(getnonMember.size() > 0){
					return JsonResult.build("200", "查询成功！", getnonMember, count2, pInfo2.getPageNum(), paramInfo.getPageSize(), r,semberSize);
				} else {
					return JsonResult.build("201", "暂无数据!",r);
				}
			}
			int count = admissionService.getCount(paramInfo);
			List<ParamInfo> identityInfo = admissionService.getIdentityInfoDetails(paramInfo);
			if (identityInfo.size() > 0) {
				return JsonResult.build("200", "查询成功！", identityInfo, count, pInfo2.getPageNum(), paramInfo.getPageSize(), r,semberSize);
			} else {
				return JsonResult.build("201", "暂无数据!",r);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试!");
		}
	}
	
}
