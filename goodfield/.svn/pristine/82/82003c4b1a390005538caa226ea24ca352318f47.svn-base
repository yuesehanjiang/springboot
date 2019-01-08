package com.alibaba.controller;

import java.io.OutputStream;
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
import com.alibaba.service.EntranceReportService;
import com.alibaba.util.ExcelUtil;
import com.alibaba.util.ParamInfo;
import com.alibaba.util.RedisUtils;


@Transactional(rollbackFor = Exception.class)
@RestController
@RequestMapping("/web/entrancereport")
public class EntranceReportController {

	@Autowired
	private EntranceReportService entranceReportService;
	
	Logger logger=Logger.getLogger(UserController.class);

	//发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	
    /**
	 * 入场报告详情：Excel导出 url : http://localhost:8080/goodfield/web/entrancereport/exportDetails
	 * @param {"pageNum":"0", "pageSize":"10", "user_type":"1", "user_id":"2", "stores_id":"1","keyword":"", "positive":"","reverse":"", "datetime":"", "startTime":"", "endTime":""}
	 * @return
	 */
	@RequestMapping(value = "exportDetails", method = RequestMethod.GET)
	public HashMap<String, Object> exportDetails(HttpServletRequest request,HttpServletResponse response,ParamInfo pInfo) {
		try {
			ParamInfo pInfo2 = new ParamInfo();
			if((pInfo.getPageNum() != "" && pInfo.getPageNum() != null) && ( pInfo.getPageSize() != "" && pInfo.getPageSize() != null)){
				pInfo2.setPageNum(pInfo.getPageNum());
				int pageNum = (Integer.parseInt(pInfo.getPageNum()) ) * Integer.parseInt(pInfo.getPageSize());
				pInfo.setPageNum( String.valueOf(pageNum) );
				pInfo.setPageSize(pInfo.getPageSize());
			}
			List<Role> userRoleInfo = entranceReportService.getUserRoleRelationInfo(pInfo);
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
				return JsonResult.build("201", "暂无数据!");
			}
			if("2".equals(pInfo.getUser_type())){	// 查询非会员数据
				if(entranceReportService.getEntranceReportNonMemberDetails(pInfo) == null){
					return JsonResult.build("201", "暂无数据!");
				}
				String[] title = {"会员姓名","门店","会员id","卡号","入场时间", "图像"};
				String fileName = System.currentTimeMillis()+".xls";
		        String sheetName = "入场报告";
		        String [][] cont = new String[1][];
		        for (int i = 0; i < 1; i++) {
		        	cont[i] = new String[title.length];
			        ParamInfo paramInfo = entranceReportService.getEntranceReportNonMemberDetails(pInfo);
			        cont[i][0] = paramInfo.getUser_name();
			        cont[i][1] = paramInfo.getStores_name();
			        cont[i][2] = paramInfo.getUser_id();
			        cont[i][3] = paramInfo.getUser_card();
			        cont[i][4] = paramInfo.getUser_in_time();
			        cont[i][5] = paramInfo.getUser_pic();
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
			List<ParamInfo> entranceReportInfo = entranceReportService.getgetEntranceReportDetails(pInfo);
			if(entranceReportInfo.size() < 1){
				return JsonResult.build("201", "暂无数据!");
			}
			String[] title = {"会员姓名","门店","会员id","卡号","入场时间"};
			String fileName = System.currentTimeMillis()+".xls";
	        String sheetName = "入场报告";
	        int size = entranceReportInfo.size();
	        String [][] cont = new String[size][];
	        for (int i = 0; i < size; i++) {
	        	cont[i] = new String[title.length];
		        ParamInfo paramInfo = entranceReportInfo.get(i);
		        cont[i][0] = paramInfo.getUser_name();
		        cont[i][1] = paramInfo.getStores_name();
		        cont[i][2] = paramInfo.getUser_id();
		        cont[i][3] = paramInfo.getUser_card();
		        cont[i][4] = paramInfo.getUser_in_time();
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
			return JsonResult.build("205", "操作失败，请重试!");
		}
	}
    
	/**
	 * 入场报告：Excel导出 url : http://localhost:8080/goodfield/web/entrancereport/export
	 * @param {"pageNum":"0", "pageSize":"10", "user_type":"1", "user_id":"2", "stores_id":"1","keyword":"", "positive":"","reverse":"", "datetime":"", "startTime":"", "endTime":""}
	 * @return
	 */
	@RequestMapping(value = "export", method = RequestMethod.GET)
	public HashMap<String, Object> export(HttpServletRequest request,HttpServletResponse response,ParamInfo pInfo) {
		try {
			ParamInfo pInfo2 = new ParamInfo();
			if((pInfo.getPageNum() != "" && pInfo.getPageNum() != null) && ( pInfo.getPageSize() != "" && pInfo.getPageSize() != null)){
				pInfo2.setPageNum(pInfo.getPageNum());
				int pageNum = (Integer.parseInt(pInfo.getPageNum()) ) * Integer.parseInt(pInfo.getPageSize());
				pInfo.setPageNum( String.valueOf(pageNum) );
				pInfo.setPageSize(pInfo.getPageSize());
			}
			List<Role> userRoleInfo = entranceReportService.getUserRoleRelationInfo(pInfo);
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
				return JsonResult.build("201", "暂无数据!");
			}
			if("2".equals(pInfo.getUser_type())){	// 查询非会员数据
				List<ParamInfo> entranceReportNonMemberInfo = entranceReportService.getEntranceReportNonMemberInfo(pInfo);
				if(entranceReportNonMemberInfo.size() < 1){
					return JsonResult.build("201", "暂无数据!");
				}
				String[] title = {"会员姓名","门店","会员id","卡号","头像", "入场次数"};
				String fileName = System.currentTimeMillis()+".xls";
		        String sheetName = "入场报告";
		        int size = entranceReportNonMemberInfo.size();
		        String [][] cont = new String[size][];
		        for (int i = 0; i < size; i++) {
		        	cont[i] = new String[title.length];
			        ParamInfo paramInfo = entranceReportNonMemberInfo.get(i);
			        cont[i][0] = paramInfo.getUser_name();
			        cont[i][1] = paramInfo.getStores_name();
			        cont[i][2] = paramInfo.getUserId();
			        cont[i][3] = paramInfo.getUser_card();
			        cont[i][4] = paramInfo.getUser_pic();
			        cont[i][5] = paramInfo.getFrequency();
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
			List<ParamInfo> entranceReportInfo = entranceReportService.getEntranceReportInfo(pInfo);
			if(entranceReportInfo.size() < 1){
				return JsonResult.build("201", "暂无数据!");
			}
			String[] title = {"会员姓名","门店","会员id","卡号","入场次数"};
			String fileName = System.currentTimeMillis()+".xls";
	        String sheetName = "入场报告";
	        int size = entranceReportInfo.size();
	        String [][] cont = new String[size][];
	        for (int i = 0; i < size; i++) {
	        	cont[i] = new String[title.length];
		        ParamInfo paramInfo = entranceReportInfo.get(i);
		        cont[i][0] = paramInfo.getUser_name();
		        cont[i][1] = paramInfo.getStores_name();
		        cont[i][2] = paramInfo.getUserId();
		        cont[i][3] = paramInfo.getUser_card();
		        cont[i][4] = paramInfo.getFrequency();
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
			logger.error(e.getMessage());
			return JsonResult.build("205", "操作失败，请重试!");
		}
	}
	
	/**
	 * 查询入场报告-会员+门店 url : http://localhost:8080/goodfield/web/entrancereport/getEntranceReportInfo
	 * @param {"pageNum":"0", "pageSize":"10", "user_type":"1", "user_id":"2", "stores_id":"1","keyword":"", "positive":"","reverse":"", "datetime":"", "startTime":"", "endTime":""}
	 * @return
	 */
	@RequestMapping(value = "getEntranceReportInfo", method = RequestMethod.POST)
	public HashMap<String, Object> getEntranceReportInfo(@RequestBody ParamInfo pInfo) {
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
			List<Role> userRoleInfo = entranceReportService.getUserRoleRelationInfo(pInfo);
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
				return JsonResult.build("201", "暂无数据!");
			}
			int count = entranceReportService.getCount(pInfo);
			if("2".equals(pInfo.getUser_type())){	// 查询非会员数据
				int count3 = entranceReportService.getCount3(pInfo);
				List<ParamInfo> entranceReportNonMemberInfo = entranceReportService.getEntranceReportNonMemberInfo(pInfo);
				if(entranceReportNonMemberInfo.size() > 0){
					return JsonResult.build("200", "查询成功！", entranceReportNonMemberInfo, count3, pInfo2.getPageNum(), pInfo.getPageSize(), r);
				} else {
					return JsonResult.build("201", "暂无数据!", r);
				}
			}
			List<ParamInfo> entranceReportInfo = entranceReportService.getEntranceReportInfo(pInfo);
			if (entranceReportInfo.size() > 0) {
				return JsonResult.build("200", "查询成功！", entranceReportInfo,count, pInfo2.getPageNum(), pInfo.getPageSize(), r);
			} else {
				return JsonResult.build("201", "暂无数据!",r);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试!");
		}
	}

	/**
	 * 查询入场次数详情-会员+门店 url : http://localhost:8080/goodfield/web/entrancereport/getgetEntranceReportDetails
	 * @param {"u_id":"1", "pageNum":"0", "id":"1", "user_type":"2", "pageSize":"10","startTime":"", "endTime":"", "user_id":"2", "stores_id":"","datetime":""}
	 * @return
	 */
	@RequestMapping(value = "getgetEntranceReportDetails", method = RequestMethod.POST)
	public HashMap<String, Object> getgetEntranceReportDetails(@RequestBody ParamInfo pInfo) {
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
			List<Role> userRoleInfo = entranceReportService.getUserRoleRelationInfo(pInfo);
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
				return JsonResult.build("201", "暂无数据!");
			}
			int count = entranceReportService.getCount2(pInfo);
			if("2".equals(pInfo.getUser_type())){	// 查询非会员数据
				ParamInfo entranceReportNonMemberDetails = entranceReportService.getEntranceReportNonMemberDetails(pInfo);
				if(null != entranceReportNonMemberDetails){
					return JsonResult.build("200", "查询成功！", entranceReportNonMemberDetails);
				} else {
					return JsonResult.build("201", "暂无数据！");
				}
			}
			List<ParamInfo> entranceReportInfo = entranceReportService.getgetEntranceReportDetails(pInfo);
			if (entranceReportInfo.size() > 0) {
				return JsonResult.build("200", "查询成功！", entranceReportInfo,count, pInfo2.getPageNum(), pInfo.getPageSize(), r);
			} else {
				return JsonResult.build("201", "暂无数据!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试!");
		}
	}

}
