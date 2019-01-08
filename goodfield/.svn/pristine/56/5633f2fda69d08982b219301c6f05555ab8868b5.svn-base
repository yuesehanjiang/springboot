package com.alibaba.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.common.result.JsonResult;
import com.alibaba.pojo.ManagementPic;
import com.alibaba.pojo.MemberManagement;
import com.alibaba.pojo.Role;
import com.alibaba.service.MemberManagementService;
import com.alibaba.util.ParamInfo;
import com.alibaba.util.RedisUtils;

@Transactional(rollbackFor = Exception.class)
@RestController
@RequestMapping("/web/membermanagement")
public class MemberManagementController {

	@Autowired
	private MemberManagementService managementService;

	Logger logger = Logger.getLogger(UserController.class);

	/**
	 * 查询会员信息 url : http://localhost:8080/goodfield/web/membermanagement/
	 * getMemberManagementInfo
	 * 
	 * @param {"pageNum":"0", "pageSize":"10", "stores_id":"", "user_id":"2",
	 *        "keyword":""}
	 * @return
	 */
	@RequestMapping(value = "getMemberManagementInfo", method = RequestMethod.POST)
	public HashMap<String, Object> getMemberManagementInfo(
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
			ParamInfo pInfo2 = new ParamInfo();
			if ((pInfo.getPageNum() != "" && pInfo.getPageNum() != null)
					&& (pInfo.getPageSize() != "" && pInfo.getPageSize() != null)) {
				pInfo2.setPageNum(pInfo.getPageNum());
				int pageNum = (Integer.parseInt(pInfo.getPageNum()))
						* Integer.parseInt(pInfo.getPageSize());
				pInfo.setPageNum(String.valueOf(pageNum));
				pInfo.setPageSize(pInfo.getPageSize());
			}
			List<Role> userRoleRelationInfo = managementService
					.getUserRoleRelationInfo(pInfo);
			if (userRoleRelationInfo.size() < 1) {
				return JsonResult.build("202", "暂无权限");
			}
			List<Role> r = new ArrayList<Role>();
			for (Role role : userRoleRelationInfo) {
				r.add(new Role(null, role.getStores_id(),
						role.getStores_name(), null, null));
			}
			String content = "";
			for (Role role : r) {
				content += "\'" + role.getStores_id() + "\'" + ",";
			}
			if (content != "") {
				pInfo.setContent(content.substring(0, content.length() - 1));
			} else {
				return JsonResult.build("201", "暂无数据");
			}
			int count = managementService.getCount(pInfo);
			List<MemberManagement> memberManagementInfo = managementService
					.getMemberManagementInfo(pInfo);
			if (memberManagementInfo.size() > 0) {
				return JsonResult.build("200", "查询成功!", memberManagementInfo,
						count, pInfo2.getPageNum(), pInfo.getPageSize(), r);
			} else {
				return JsonResult.build("201", "暂无数据!", r);
			}
		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试!");
		}
	}

	/**
	 * 查询会员图像库 url : http://localhost:8080/goodfield/web/membermanagement/
	 * getMemberManagementPicInfo
	 * 
	 * @param {"pageNum":"0", "pageSize":"10", "user_id":"", "datetime":"",
	 *        "startTime":"", "endTime":""}
	 * @return
	 */
	@RequestMapping(value = "getMemberManagementPicInfo", method = RequestMethod.POST)
	public HashMap<String, Object> getMemberManagementPicInfo(
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
			List<Role> userRoleRelationInfo = managementService
					.getUserRoleRelationInfo(pInfo);
			if (userRoleRelationInfo.size() < 1) {
				return JsonResult.build("202", "暂无权限");
			}
			if (pInfo.getEndTime().compareTo(pInfo.getStartTime()) < 0) {
				return JsonResult.build("205", "亲，开始时间不能大于结束时间哦！");
			}
			ParamInfo pInfo2 = new ParamInfo();
			if ((pInfo.getPageNum() != "" && pInfo.getPageNum() != null)
					&& (pInfo.getPageSize() != "" && pInfo.getPageSize() != null)) {
				pInfo2.setPageNum(pInfo.getPageNum());
				int pageNum = (Integer.parseInt(pInfo.getPageNum()))
						* Integer.parseInt(pInfo.getPageSize());
				pInfo.setPageNum(String.valueOf(pageNum));
				pInfo.setPageSize(pInfo.getPageSize());
			}
			int count = managementService.getCount2(pInfo);
			List<ManagementPic> memberManagementPicInfo = managementService
					.getMemberManagementPicInfo(pInfo);
			if (memberManagementPicInfo.size() > 0) {
				String userPic = managementService.getUserPic(pInfo);
				return JsonResult.build("200", "查询成功!",
						memberManagementPicInfo, count, pInfo2.getPageNum(),
						pInfo.getPageSize(), userPic);
			} else {
				String userPic = managementService.getUserPic(pInfo);
				return JsonResult.build("201", "暂无数据!", userPic);
			}
		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试!");
		}
	}

}
