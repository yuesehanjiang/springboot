package com.alibaba.controller;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.common.param.UserRoleInfo;
import com.alibaba.common.param.UserRoleRelationList;
import com.alibaba.common.result.JsonResult;
import com.alibaba.dao.InterfaceInfoMapper;
import com.alibaba.pojo.Admin;
import com.alibaba.pojo.Machine;
import com.alibaba.pojo.Role;
import com.alibaba.pojo.User;
import com.alibaba.pojo.UserRoleRelation;
import com.alibaba.service.AdminService;
import com.alibaba.util.HttpClientUtil;
import com.alibaba.util.ImageType;
import com.alibaba.util.MD5Util;
import com.alibaba.util.OSSUtil;
import com.alibaba.util.ParamInfo;
import com.alibaba.util.RedisUtils;
import com.alibaba.util.YunPian;

@Transactional(rollbackFor = Exception.class)
@RestController
@RequestMapping("/web/admin/")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private InterfaceInfoMapper infoMapper;

	private final static String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$";

	private final static String regexUserName = "^[\u4e00-\u9fa5_a-zA-Z]+$";

	private final static String regexPhone = "^(10|11|12|13|14|15|16|17|18|19)\\d{9}$";

	Logger logger = Logger.getLogger(UserController.class);

	/**
	 * 接口请求接口
	 * 连坚持都做不到，还谈什么理想？
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "index", produces = "application/json;charset=utf-8")
	public String index(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		StringBuffer requestURL = request.getRequestURL();
		System.out.println("==================" + requestURI);
		System.out.println("==================" + requestURL);
		Map<String, Object> maps = new HashMap<String, Object>();
		String httpPostRequest = null;
		try {
			maps.put("username", "dsfsd");
			maps.put("account", "235367768588@qq.com");
			maps.put("phone", "1869225742");
			httpPostRequest = HttpClientUtil.httpPostRequest(
					"http://localhost:8080/goodfield/web/admin/createUserInfo",
					maps, maps);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();

			logger.error(e.getMessage());
		}
		return httpPostRequest;
	}

	/**
	 * 查询所有门店数据 url : http://localhost:8080/goodfield/web/admin/getStoresList
	 * 
	 * @return
	 */
	@RequestMapping(value = "getStoresList", method = RequestMethod.POST)
	public HashMap<String, Object> getStoresList() {
		try {
			List<Role> storesList = adminService.getStoresList();
			if (storesList.size() > 0) {
				return JsonResult.build("200", "查询成功！", storesList);
			} else {
				return JsonResult.build("201", "暂无数据！", storesList);
			}
		} catch (Exception e) {

			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试！");
		}
	}

	/**
	 * 管理员登陆 url : http://localhost:8080/goodfield/web/admin/adminLogin
	 * 
	 * @param {"account":"2353677685@qq.com", "password":"123456",
	 *        "loginStatus":"0"}
	 * @return
	 */
	@RequestMapping(value = "adminLogin", method = RequestMethod.POST)
	public HashMap<String, Object> adminLogin(@RequestBody Admin admin,
			HttpServletRequest request) {
		try {
			if (!admin.getPassword().matches(regex)) {
				return JsonResult.build("205", "密码必须是数字和英文字母组成（8到20位）！");
			}
			admin.setPassword(MD5Util.generate(admin.getPassword()));
			Admin adminToken = adminService.adminLogin(admin);
			if (adminToken != null) {
				try {
					String loginStatus = adminService.getLoginStatus(); // 查询登录状态
					RedisUtils.delKV("adminToken_" + loginStatus); // 删除之前redis存储的管理员key
					if (loginStatus.equals("100")) {
						loginStatus = "-1";
					}
					admin.setLoginStatus((Integer.parseInt(loginStatus)) + 1
							+ "");
					adminService.updateLoginStatus(admin); // 操作登录状态
					RedisUtils.setKV("adminToken_" + admin.getLoginStatus(),
							1800, JSONUtil.serialize(adminToken));
					adminToken.setLoginStatus(admin.getLoginStatus());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				return JsonResult.build("200", "登陆成功！", adminToken);
			} else {
				return JsonResult.build("205", "账号或密码错误！");
			}
		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage());
			return JsonResult.build("205", "登陆失败，请重试！");
		}
	}

	/**
	 * 批量分配权限数据 url :
	 * http://localhost:8080/goodfield/web/admin/addUserRoleRelation
	 * 
	 * @param { "lists":[ {"user_id":"3", "stores_id":"1", "relationId":"1"},
	 *        {"user_id":"3", "stores_id":"1", "relationId":"2"} ] }
	 * @return
	 */
	@RequestMapping(value = "addUserRoleRelation", method = RequestMethod.POST)
	public HashMap<String, Object> addUserRoleRelation(
			@RequestBody UserRoleRelationList lists) {
		try {
			String userToken = RedisUtils.getKV("adminToken_"
					+ lists.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getAdminKVLikes().size() > 0) {
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			List<UserRoleInfo> li = new ArrayList<UserRoleInfo>();
			List<UserRoleInfo> list = lists.getLists();
			for (UserRoleInfo userRoleInfo : list) {
				if (userRoleInfo.getUser_id() == null
						|| userRoleInfo.getUser_id() == "") {
					return JsonResult.build("205", "操作失败");
				}
				User user = new User();
				user.setId(userRoleInfo.getUser_id());
				User userIdInfo = adminService.getUserIdInfo(user);
				if (null == userIdInfo) {
					return JsonResult.build("205", "操作失败");
				}
				if ((userRoleInfo.getStores_id() == null || userRoleInfo
						.getStores_id() == "")) {
					return JsonResult.build("205", "操作失败");
				}
				li.add(userRoleInfo); // 添加用户和门店，权限id
			}
			adminService.deleteUserRoleRelation(li); // 分配之前先删除原来的权限数据
			adminService.addUserRoleRelation(li);
			return JsonResult.build("200", "操作成功");
		} catch (Exception e) {

			logger.error(e.getMessage());
			return JsonResult.build("205", "操作失败，请重试");
		}
	}

	/**
	 * 查询所有用户的门店权限信息 url :
	 * http://localhost:8080/goodfield/web/admin/listUserRoleRelationsInfo
	 * 
	 * @param {"pageNum":"0", "pageSize":"10", "keyword":"", "loginStatus":"0"}
	 * @return
	 */
	@RequestMapping(value = "listUserRoleRelationsInfo", method = RequestMethod.POST)
	public HashMap<String, Object> listUserRoleRelationsInfo(
			@RequestBody ParamInfo pInfo) {
		try {
			String userToken = RedisUtils.getKV("adminToken_"
					+ pInfo.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getAdminKVLikes().size() > 0) {
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			ParamInfo pInfo2 = new ParamInfo();
			if ((pInfo.getPageNum() != "" && pInfo.getPageNum() != null) && (pInfo.getPageSize() != "" && pInfo.getPageSize() != null)) {
				pInfo2.setPageNum(pInfo.getPageNum());
				int pageNum = (Integer.parseInt(pInfo.getPageNum())) * Integer.parseInt(pInfo.getPageSize());
				pInfo.setPageNum(String.valueOf(pageNum));
				pInfo.setPageSize(pInfo.getPageSize());
			}
			List<String> getlistUserRoleRelationsPages = adminService
					.getlistUserRoleRelationsPages(pInfo);
			String storesIds = "";
			for (String string : getlistUserRoleRelationsPages) {
				storesIds += "\'" + string + "\'" + ",";
			}
			if (storesIds.equals("")) {
				return JsonResult.build("201", "暂无数据！");
			}
			pInfo.setContent(storesIds.substring(0, storesIds.length() - 1));
			int count = adminService.getCount(pInfo);
			List<User> listUserRoleRelationsInfo = adminService
					.listUserRoleRelationsInfo(pInfo);
			if (listUserRoleRelationsInfo.size() > 0) {
				return JsonResult.build("200", "查询成功！",
						listUserRoleRelationsInfo, count, pInfo2.getPageNum(),
						pInfo.getPageSize());
			} else {
				String pic = adminService.getPicInfo();
				return JsonResult.build("201", "还没有用户，请先去创建用户!", pic);
			}
		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试!");
		}
	}

	/**
	 * 设置权限 url :
	 * http://localhost:8080/goodfield/web/admin/getUserRoleRelationInfo
	 * 
	 * @param {"user_id":"2", "pageNum":"0", "pageSize":"10"}
	 * @return
	 */
	@RequestMapping(value = "getUserRoleRelationInfo", method = RequestMethod.POST)
	public HashMap<String, Object> getUserRoleRelationInfo(
			@RequestBody ParamInfo pInfo) {
		try {
			String userToken = RedisUtils.getKV("adminToken_"
					+ pInfo.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getAdminKVLikes().size() > 0) {
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
			List<String> storesIdRelationInfo = adminService
					.getStoresIdRelationInfo(pInfo);
			String storesIds = "";
			for (String string : storesIdRelationInfo) {
				storesIds += "\'" + string + "\'" + ",";
			}
			if (storesIds != "") {
				pInfo.setContent(storesIds.substring(0, storesIds.length() - 1));
			} else {
				return JsonResult.build("201", "暂无数据!");
			}
			List<Role> listUserRoleRelationsInfo = adminService
					.getUserRoleRelationInfo(pInfo);
			if (pInfo.getKeyword() != null) {
				if (listUserRoleRelationsInfo.size() < 1) {
					return JsonResult.build("201", "暂无数据!");
				}
			}
			if (listUserRoleRelationsInfo.size() < 1) {
				return JsonResult.build("202", "请先添加门店!");
			}
			int count = adminService.getUserRoleRelationCount(pInfo);
			if (listUserRoleRelationsInfo.size() > 0) {
				return JsonResult.build("200", "查询成功！",
						listUserRoleRelationsInfo, count, pInfo2.getPageNum(),
						pInfo.getPageSize());
			} else {
				String pic = adminService.getPicInfo();
				return JsonResult.build("201", "还没有用户，请先去创建用户!", pic);
			}
		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试!");
		}
	}

	/**
	 * 查询要编辑门店信息 url : http://localhost:8080/goodfield/web/admin/getRoleNameInfo
	 * 
	 * @param {"user_id":"2"}
	 * @return
	 */
	@RequestMapping(value = "getRoleNameInfo", method = RequestMethod.POST)
	public HashMap<String, Object> getRoleNameInfo(@RequestBody ParamInfo pInfo) {
		try {
			List<Role> roleNameInfo = adminService.getRoleNameInfo(pInfo);
			if (roleNameInfo.size() > 0) {
				return JsonResult.build("200", "查询成功！", roleNameInfo);
			} else {
				return JsonResult.build("201", "还没有门店，请先去添加门店!");
			}
		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试!");
		}
	}

	/**
	 * 编辑门店信息 url : http://localhost:8080/goodfield/web/admin/updateRoleNameInfo
	 * 
	 * @param { "lists":[ {"stores_id":"1","user_id":"4"},
	 *        {"stores_id":"2","user_id":"4"} ] }
	 * @return
	 */
	@RequestMapping(value = "updateRoleNameInfo", method = RequestMethod.POST)
	public HashMap<String, Object> updateRoleNameInfo(@RequestBody UserRoleRelationList lists) {
		try {
			String userToken = RedisUtils.getKV("adminToken_"+ lists.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getAdminKVLikes().size() > 0) {
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			List<Role> roleId = adminService.getRoleId();
			String rIds = "";
			for (Role role : roleId) {
				rIds += role.getStores_id() + ",";
			}
			if (rIds != null && rIds != "") {
				rIds = rIds.substring(0, rIds.length() - 1);
			}
			List<UserRoleInfo> li = new ArrayList<UserRoleInfo>();
			List<UserRoleInfo> list = lists.getLists();
			ParamInfo paramInfo = new ParamInfo();
			ParamInfo pInfo = new ParamInfo();
			String roleIds = "";
			for (UserRoleInfo userRoleInfo : list) {
				if (userRoleInfo.getUser_id() == null
						|| userRoleInfo.getUser_id() == "") {
					return JsonResult.build("205", "操作失败");
				}
				User user = new User();
				user.setId(userRoleInfo.getUser_id());
				User userIdInfo = adminService.getUserIdInfo(user);
				if (null == userIdInfo) {
					return JsonResult.build("205", "操作失败");
				}
				paramInfo.setUser_id(userRoleInfo.getUser_id());
				paramInfo.setStores_id(userRoleInfo.getStores_id());
				pInfo.setUser_id(userRoleInfo.getUser_id());
				String[] ids = userRoleInfo.getStores_id().split(",");
				for (String string : ids) {
					if (rIds != null && rIds != "") {
						if (rIds.indexOf(string) < 0) { // 如果页面传来的门店id在门店表里找不到，直接提升操作失败
							return JsonResult.build("205", "操作失败，没有该门店信息!");
						}
					}
				}
				List<UserRoleRelation> userRoleNameInfo = adminService.getRoleRelationInfo(paramInfo); // 查询门店的权限信息
				for (UserRoleRelation userRoleRelation : userRoleNameInfo) {
					if (userRoleRelation.getRelationId() != null
						&& String.valueOf(userRoleRelation.getRelationId()) != "" && !userRoleRelation.getRelationId().equals("")) { // 查询那个门店的权限不为空！
						roleIds += "'" + userRoleRelation.getRoleId() + "'"+ ",";
					}
				}
			}
			if (roleIds != "") { // 如果有门店下有权限就进这里
				paramInfo.setStores_id(roleIds.substring(0,roleIds.length() - 1));
				adminService.deleteUserRoleName(paramInfo); // 编辑门店之前先删除原来的门店数据
				for (UserRoleInfo userRoleInfo : list) {
					if (paramInfo.getStores_id().indexOf(userRoleInfo.getStores_id()) > -1) { // 如果添加的门店有权限就不再添加，直接进入下一次循环
						continue;
					}
					li.add(userRoleInfo);
				}
			} else { // 如果有门店下没有权限就进这里
				List<UserRoleRelation> userRoleNameInfo = adminService.getUserRoleNameInfo(pInfo);
				if (userRoleNameInfo.size() < 1) {
					for (UserRoleInfo userRoleInfo : list) {
						li.add(userRoleInfo);
					}
				} else {
					String rids = "";
					for (UserRoleRelation userRoleRelation : userRoleNameInfo) {
						rids += "'" + userRoleRelation.getRoleId() + "'" + ",";
					}
					pInfo.setStores_id(rids.substring(0, rids.length() - 1));
					adminService.deleteUserRoleName2(pInfo); // 编辑门店之前先删除原来的门店数据
					for (UserRoleInfo userRoleInfo : list) {
						li.add(userRoleInfo);
					}
				}
			}
			// 编辑门店
			adminService.updateRoleNameInfo(li);
			return JsonResult.build("200", "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return JsonResult.build("200", "操作成功!");
		}
	}

	/**
	 * 发送短信验证码 url: http://localhost:8080/goodfield/web/admin/sendSms
	 * 
	 * @param {"phone":"18529527874"}
	 * @return
	 */
	@RequestMapping(value = "sendSms", method = RequestMethod.POST)
	public HashMap<String, Object> sendSms(@RequestBody ParamInfo paramInfo) {
		try {
			String userToken = RedisUtils.getKV("adminToken_"
					+ paramInfo.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getAdminKVLikes().size() > 0) {
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			String codes = YunPian.getRandomCode(6);
			if (paramInfo.getPassword() == null
					|| paramInfo.getPassword() == ""
					|| paramInfo.getPassword().equals("")) {
				Admin admin = new Admin();
				admin.setPhone(paramInfo.getPhone());
				String adminPhone = adminService.getAdminPhone(admin);
				if (!adminPhone.equals(paramInfo.getPhone())) {
					return JsonResult.build("205", "手机号错误，请联系管理员！");
				}
			}
			RedisUtils.setKV("codes_admin", 300, codes);
			// RedisUtils.setKV("codes_"+paramInfo.getUserToken(), 300, codes);
			Boolean singleSendNew = YunPian.singleSendNew(paramInfo.getPhone(), codes);
		
			if(singleSendNew)
			return JsonResult.build("200", "发送成功！");
		} catch (Exception e) {

			logger.error(e.getMessage());
			return JsonResult.build("205", "发送失败，请重试！");
		}
		return JsonResult.build("205", "发送失败，请重试！");
	}

	/**
	 * 第一次登陆，设置管理员密码 url:
	 * http://localhost:8080/goodfield/web/admin/updateAdminPwd param
	 * {"code":"123456", "password":"123456", "phone":"18529527874",
	 * "newPassword":"123456"}
	 * 
	 * @return
	 */
	@RequestMapping(value = "updateAdminPwd", method = RequestMethod.POST)
	public HashMap<String, Object> updateAdminPwd(@RequestBody Admin admin) {
		try {
			String codes = RedisUtils.getKV("codes_admin");
			String adminPhone = adminService.getAdminPhone(admin);
			if (!admin.getPassword().matches(regex)) {
				return JsonResult.build("205", "密码必须是数字和英文字母组成（8到20位）！");
			}
			if (!admin.getPassword().equals(admin.getNewPassword())) {
				return JsonResult.build("205", "2次密码输入不一致！");
			}
			if (!admin.getPhone().equals(adminPhone)) {
				return JsonResult.build("205", "管理员手机号错误！");
			}
			if (!admin.getCode().equals(codes)) {
				return JsonResult.build("205", "验证码错误！");
			}
			admin.setPassword(MD5Util.generate(admin.getPassword()));
			int updatePwd = adminService.updateAdminPwd(admin);
			if (updatePwd > 0) {
				RedisUtils.delKV("codes");
				return JsonResult.build("200", "操作成功！");
			} else {
				return JsonResult.build("205", "操作失败，请重试！");
			}
		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage());
			return JsonResult.build("205", "操作失败，请重试！");
		}
	}

	/**
	 * 管理员退出登陆 url : http://localhost:8080/goodfield/web/admin/adminLoginOut
	 * 
	 * @return
	 */
	@RequestMapping(value = "adminLoginOut", method = RequestMethod.POST)
	public HashMap<String, Object> adminLoginOut(@RequestBody Admin admin) {
		try {
			RedisUtils.delKV("adminToken_" + admin.getLoginStatus());
			return JsonResult.build("200", "退出成功！");
		} catch (Exception e) {

			logger.error(e.getMessage());
			return JsonResult.build("205", "退出失败，请重试！");
		}
	}

	/**
	 * 创建用户 url : http://localhost:8080/goodfield/web/admin/createUserInfo param
	 * {"username":"admin", "account":"123456", "phone":"18529527874"}
	 * 
	 * @return
	 */
	@RequestMapping(value = "createUserInfo", method = RequestMethod.POST)
	public HashMap<String, Object> createUserInfo(@RequestBody User user) {
		try {
			String userToken = RedisUtils.getKV("adminToken_"
					+ user.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getAdminKVLikes().size() > 0) {
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			if (user.getAccount().length() < 6
					|| user.getAccount().length() > 20) {
				return JsonResult.build("205", "账号长度必须在6到20位之间！");
			}
			if (!user.getPhone().matches(regexPhone)) {
				return JsonResult.build("205", "手机号格式不正确！");
			}
			if (!user.getUsername().matches(regexUserName)
					|| user.getUsername().length() < 2
					|| user.getUsername().length() > 16) {
				return JsonResult.build("205", "用户名必须是汉子和英文字母组成（2到16位之间）！");
			}
			User accountInfo = adminService.getAccountInfo(user); // 判断账号是否已经存在
			User userPhoneInfo = adminService.getUserPhoneInfo(user);
			User userAccountInfo = adminService.getUserAccountInfo(user);
			if (accountInfo != null) {
				return JsonResult.build("205", "账号已存在，不能重复创建！");
			}
			if (userPhoneInfo != null) {
				return JsonResult.build("205",
						"该手机号已被  " + userAccountInfo.getAccount() + " 绑定！");
			}
			int count = adminService.createUserInfo(user);
			if (count > 0) {
				return JsonResult.build("200", "创建成功！");
			} else {
				return JsonResult.build("205", "创建失败，请重试!");
			}
		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage());
			return JsonResult.build("205", "创建失败，请重试!");
		}
	}

	/**
	 * 删除用户 url : http://localhost:8080/goodfield/web/admin/deleteUserInfo param
	 * {"id":"1"}
	 * 
	 * @return
	 */
	@RequestMapping(value = "deleteUserInfo", method = RequestMethod.POST)
	public HashMap<String, Object> deleteUserInfo(@RequestBody User user) {
		try {
			String userToken = RedisUtils.getKV("adminToken_"
					+ user.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getAdminKVLikes().size() > 0) {
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			int count = adminService.deleteUserInfo(user);
			adminService.deleteRoleRelation(user);
			if (count > 0) {
				return JsonResult.build("200", "删除成功！");
			} else {
				return JsonResult.build("205", "删除失败，请重试!");
			}
		} catch (Exception e) {

			logger.error(e.getMessage());
			return JsonResult.build("205", "删除失败，请重试!");
		}
	}

	/**
	 * 根据id查询要操作的用户列表 url :
	 * http://localhost:8080/goodfield/web/admin/getUserInfoById param
	 * {"id":"1"}
	 * 
	 * @return
	 */
	@RequestMapping(value = "getUserInfoById", method = RequestMethod.POST)
	public HashMap<String, Object> getUserInfoById(@RequestBody User user) {
		try {
			String userToken = RedisUtils.getKV("adminToken_"
					+ user.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getAdminKVLikes().size() > 0) {
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			User userInfo = adminService.getUserInfoById(user);
			if (userInfo != null) {
				return JsonResult.build("200", "查询成功！", userInfo);
			} else {
				return JsonResult.build("201", "暂无数据!");
			}
		} catch (Exception e) {

			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试!");
		}
	}

	/**
	 * 操作用户 url : http://localhost:8080/goodfield/web/admin/updateUserInfo param
	 * {"id":"1", "username":"小猫", "phone":"15973718213"}
	 * 
	 * @return
	 */
	@RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
	public HashMap<String, Object> updateUserInfo(@RequestBody User user) {
		try {
			String userToken = RedisUtils.getKV("adminToken_"
					+ user.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getAdminKVLikes().size() > 0) {
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			if (!user.getPhone().matches(regexPhone)) {
				return JsonResult.build("205", "手机号格式不正确！");
			}
			if (!user.getUsername().matches(regexUserName)
					|| user.getUsername().length() < 2
					|| user.getUsername().length() > 16) {
				return JsonResult.build("205", "用户名必须是汉子和英文字母组成（2到16位之间）！");
			}
			User userAccountInfo = adminService.getUserAccountInfo(user);
			User userPhoneInfo = adminService.getUserPhoneInfoup(user);
			if (userPhoneInfo != null) {
				return JsonResult.build("205",
						"该手机号已被  " + userAccountInfo.getAccount() + " 绑定！");
			}
			int count = adminService.updateUserInfo(user);
			if (count > 0) {
				return JsonResult.build("200", "操作成功！");
			} else {
				return JsonResult.build("205", "操作失败，请重试!");
			}
		} catch (Exception e) {

			logger.error(e.getMessage());
			return JsonResult.build("205", "操作失败，请重试!");
		}
	}

	/**
	 * 查询用户列表 url : http://localhost:8080/goodfield/web/admin/getUserInfo
	 * 
	 * @param {"pageNum":"0", "pageSize":"5","keyword":"2353677685@qq.com"}
	 * @return
	 */
	@RequestMapping(value = "getUserInfo", method = RequestMethod.POST)
	public HashMap<String, Object> getUserInfo(@RequestBody ParamInfo pInfo) {
		try {
			String userToken = RedisUtils.getKV("adminToken_"
					+ pInfo.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getAdminKVLikes().size() > 0) {
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
			int count = adminService.getCount(pInfo);
			List<User> userInfo = adminService.getUserInfo(pInfo);
			if (userInfo.size() > 0) {
				return JsonResult.build("200", "查询成功！", userInfo, count,
						pInfo2.getPageNum(), pInfo.getPageSize());
			} else {
				String pic = adminService.getPicInfo();
				return JsonResult.build("201", "还没有用户，请先去创建用户!", pic);
			}
		} catch (Exception e) {

			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试！");
		}
	}

	/**
	 * 查询企业信息 url : http://localhost:8080/goodfield/web/admin/getEnterpriseInfo
	 * param
	 * 
	 * @return
	 */
	@RequestMapping(value = "getEnterpriseInfo", method = RequestMethod.POST)
	public HashMap<String, Object> getEnterpriseInfo(
			@RequestBody ParamInfo pInfo) {
		try {
			String userToken = RedisUtils.getKV("adminToken_"
					+ pInfo.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getAdminKVLikes().size() > 0) {
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			Admin adminInfo = adminService.getEnterpriseInfo();
			if (null != adminInfo) {
				return JsonResult.build("200", "查询成功！", adminInfo);
			} else {
				return JsonResult.build("201", "暂无数据！");
			}
		} catch (Exception e) {

			logger.error(e.getMessage());
			return JsonResult.build("205", "操作失败，请重试!");
		}
	}

	/**
	 * 上传头像 url : http://localhost:8080/goodfield/web/admin/uploadImage param
	 * form表达形式 ===> {"file":""}
	 */
	@RequestMapping(value = "uploadImage", method = RequestMethod.POST)
	public static HashMap<String, Object> fileUpload(MultipartFile file) {
		if (file.getSize() > 3145728) {
			return JsonResult.build("205", "图片大小必须在3M以内!");
		}
		Admin admin = null;
		try {
			InputStream is = file.getInputStream();
			if (!ImageType.getPicType(is)) {
				return JsonResult.build("205", "请上传jpg，png，gif等格式的图片文件！");
			}
			String fileName = file.getOriginalFilename().replace("~", "")
					.replace("%", "").replace("{", "").replace("}", "")
					.replace("(", "").replace(")", "").replace("【", "")
					.replace("】", "").replace("[", "").replace("]", "")
					.replace("&", "").replace("^", "").replace("!", "")
					.replace("@", "").replace("#", "").replace("`", "")
					.replace("=", "").replace("+", "").replace(";", "")
					.replace(",", "").replace("；", "").replace("‘", "")
					.replace("。", "").replace("，", "").replace("·", "")
					.replace("、", "").replace(" ", "");
			admin = new Admin();
			fileName = java.net.URLEncoder.encode(fileName, "utf-8");
			String imagePath = "https://tianjiulongoa1.oss-cn-shenzhen.aliyuncs.com/goodfield/"
					+ fileName;
			admin.setPic(imagePath);
			OSSUtil.uploadObject2OSS(file, "tianjiulongoa1", "goodfield/");
		} catch (Exception e) {
			e.printStackTrace();
			Logger logger=Logger.getLogger(UserController.class);

			logger.error(e.getMessage());
			return JsonResult.build("205", "上传失败，请重试！");
		}
		return JsonResult.build("200", "上传成功！", admin);
	}

	/**
	 * 操作企业信息 url :
	 * http://localhost:8080/goodfield/web/admin/updateEnterpriseInfo param
	 * {"enterpriseName":"哈哈哈", "pic":"1.jpg"}
	 * 
	 * @return
	 */
	@RequestMapping(value = "updateEnterpriseInfo", method = RequestMethod.POST)
	public HashMap<String, Object> updateEnterpriseInfo(@RequestBody Admin admin) {
		try {
			String userToken = RedisUtils.getKV("adminToken_"
					+ admin.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getAdminKVLikes().size() > 0) {
					return JsonResult.build("210", "你的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			int count = adminService.updateEnterpriseInfo(admin);
			if (count > 0) {
				return JsonResult.build("200", "操作成功！");
			} else {
				return JsonResult.build("205", "操作失败，请重试!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());

			return JsonResult.build("205", "操作失败，请重试!");
		}
	}

	/**
	 * 操作管理员绑定的手机号 url :
	 * http://localhost:8080/goodfield/web/admin/updateAdminPhone
	 * 
	 * @param {"password":"123456", "code":"123456", "phone":"15973718213"}
	 * @return
	 */
	@RequestMapping(value = "updateAdminPhone", method = RequestMethod.POST)
	public HashMap<String, Object> updateAdminPhone(@RequestBody Admin admin) {
		try {
			String userToken = RedisUtils.getKV("adminToken_"
					+ admin.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getAdminKVLikes().size() > 0) {
					return JsonResult.build("210", "你的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			String codes = RedisUtils.getKV("codes_admin");
			String adminPwd = adminService.getAdminPwd();
			if (!admin.getPassword().matches(regex)) {
				return JsonResult.build("205", "密码必须是数字和英文字母组成（8到20位）！");
			}
			if (!MD5Util.generate(admin.getPassword()).equals(adminPwd)) {
				return JsonResult.build("205", "原密码错误！");
			}
			if (!admin.getPhone().matches(regexPhone)) {
				return JsonResult.build("205", "手机号格式不正确！");
			}
			if (!admin.getCode().equals(codes)) {
				return JsonResult.build("205", "验证码错误！");
			}
			int count = adminService.updateAdminPhone(admin);
			if (count > 0) {
				RedisUtils.delKV("codes_admin");
				return JsonResult.build("200", "操作成功！");
			} else {
				return JsonResult.build("205", "操作失败，请重试!");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "操作失败，请重试!");
		}
	}

	/**
	 * 在操作密码的时候查询管理员账号和手机号 url :
	 * http://localhost:8080/goodfield/web/admin/getAdminAccountPhoneInfo
	 * 
	 * @return
	 */
	@RequestMapping(value = "getAdminAccountPhoneInfo", method = RequestMethod.POST)
	public HashMap<String, Object> getAdminAccountPhoneInfo() {
		try {
			Admin adminAccountPhoneInfo = adminService
					.getAdminAccountPhoneInfo();
			if (null != adminAccountPhoneInfo) {
				return JsonResult.build("200", "查询成功!", adminAccountPhoneInfo);
			} else {
				return JsonResult.build("201", "暂无数据!");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "操作失败，请重试!");
		}
	}

	/**
	 * 操作管理员密码 url : http://localhost:8080/goodfield/web/admin/changeAdminPwd
	 * param {"password":"12345678", "code":"123456", "original":"12345678"}
	 * 
	 * @return
	 */
	@RequestMapping(value = "changeAdminPwd", method = RequestMethod.POST)
	public HashMap<String, Object> changeAdminPwd(
			@RequestBody ParamInfo paramInfo) {
		try {
			String userToken = RedisUtils.getKV("adminToken_"
					+ paramInfo.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getAdminKVLikes().size() > 0) {
					return JsonResult.build("210", "你的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			String adminPwd = adminService.getAdminPwd();
			String codes = RedisUtils.getKV("codes_admin");
			if (!paramInfo.getPassword().matches(regex)
					|| !paramInfo.getOriginal().matches(regex)) {
				return JsonResult.build("205", "密码必须是数字和英文字母组成（8到20位）！");
			}
			if (!MD5Util.generate(paramInfo.getOriginal()).equals(adminPwd)) {
				return JsonResult.build("205", "旧密码错误！");
			}
			if (!paramInfo.getCode().equals(codes)) {
				return JsonResult.build("205", "验证码错误！");
			}
			paramInfo.setPassword(MD5Util.generate(paramInfo.getPassword()));
			int count = adminService.changeAdminPwd(paramInfo);
			if (count > 0) {
				RedisUtils.delKV("codes_admin");
				return JsonResult.build("200", "操作成功！");
			} else {
				return JsonResult.build("205", "操作失败，请重试!");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "操作失败，请重试!");
		}
	}

	/**
	 * 操作图像存储量 url : http://localhost:8080/goodfield/web/admin/updatePicSize
	 * 
	 * @param {"picSize":"5000"}
	 * @return
	 */
	@RequestMapping(value = "updatePicSize", method = RequestMethod.POST)
	public HashMap<String, Object> updatePicSize(@RequestBody Admin admin) {
		try {
			String userToken = RedisUtils.getKV("adminToken_"
					+ admin.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getAdminKVLikes().size() > 0) {
					return JsonResult.build("210", "你的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			if (admin.getPicSize().trim().length() > 10) {
				System.out.println(1 / 0);
			}
			int count = adminService.updatePicSize(admin);
			if (count > 0) {
				return JsonResult.build("200", "操作成功");
			} else {
				return JsonResult.build("205", "操作失败，请重试！");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "操作失败，位数不能超过10位！");
		}
	}

	/**
	 * 查询图像存储个数 url : http://localhost:8080/goodfield/web/admin/getPicSize
	 * 
	 * @return
	 */
	@RequestMapping(value = "getPicSize", method = RequestMethod.POST)
	public HashMap<String, Object> getPicSize(@RequestBody Admin admin) {
		try {
			String userToken = RedisUtils.getKV("adminToken_"
					+ admin.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getAdminKVLikes().size() > 0) {
					return JsonResult.build("210", "你的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			String count = adminService.getPicSize();
			admin.setPicSize(count);
			return JsonResult.build("200", "查询成功", admin);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试！");
		}
	}

	/**
	 * 恢复默认 url : http://localhost:8080/goodfield/web/admin/updatePicSizeDefalus
	 * 
	 * @param {"picSize":"5000"}
	 * @return
	 */
	@RequestMapping(value = "updatePicSizeDefalus", method = RequestMethod.POST)
	public HashMap<String, Object> updatePicSizeDefalus(@RequestBody Admin a) {
		try {
			String userToken = RedisUtils.getKV("adminToken_"
					+ a.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getAdminKVLikes().size() > 0) {
					return JsonResult.build("210", "你的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			Admin admin = new Admin();
			admin.setPicSize("5000");
			int count = adminService.updatePicSize(admin);
			if (count > 0) {
				return JsonResult.build("200", "操作成功");
			} else {
				return JsonResult.build("205", "操作失败，请重试！");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "操作失败，请重试！");
		}
	}

	/**
	 * 查询终端机内存大小
	 * http://192.168.5.223:8090/goodfield/web/admin/getMemorys
	 * {"loginStatus":""}
	 * @return
	 */
	@RequestMapping(value = "getMemorys", method = RequestMethod.POST)
	public HashMap<String, Object> getMemorys(@RequestBody ParamInfo paramInfo){
		try {
			List<Machine> memorys = infoMapper.getMemorys();
			if(memorys.size() > 0){
				return JsonResult.build("200", "查询成功!", memorys);
			} else {
				return JsonResult.build("201", "暂无数据!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.build("205", "查询失败!");
		}
	}
	
	/**
	 * 终端机管理-已分发 url : http://localhost:8090/goodfield/web/admin/getAdminMachine
	 * 
	 * @param {"pageNum":"0", "pageSize":"10", "keyword":"2353677685@qq.com",
	 *        "status":"1"}
	 * @return
	 */
	@RequestMapping(value = "getAdminMachine", method = RequestMethod.POST)
	public HashMap<String, Object> getAdminMachine(
			@RequestBody ParamInfo paramInfo) {
		try {
			/*String userToken = RedisUtils.getKV("adminToken_"
					+ paramInfo.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getAdminKVLikes().size() > 0) {
					return JsonResult.build("210", "你的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}*/
			ParamInfo pInfo2 = new ParamInfo();
			if ((paramInfo.getPageNum() != "" && paramInfo.getPageNum() != null)
					&& (paramInfo.getPageSize() != "" && paramInfo
							.getPageSize() != null)) {
				pInfo2.setPageNum(paramInfo.getPageNum());
				int pageNum = (Integer.parseInt(paramInfo.getPageNum()))
						* Integer.parseInt(paramInfo.getPageSize());
				paramInfo.setPageNum(String.valueOf(pageNum));
				paramInfo.setPageSize(paramInfo.getPageSize());
			}
			List<String> machineList = adminService
					.getAdminMachineCount(paramInfo);
			for (int i = 0; i < machineList.size(); i++) {
				if (machineList.get(i).equals("0")) {
					machineList.remove(i);
				}
			}
			Integer sum = 0;
			for (String string : machineList) {
				sum += Integer.parseInt(string);
				;
			}
			List<String> userIdGroup = adminService.getUserIdGroup(paramInfo);
			String dis = "";
			for (String string : userIdGroup) {
				dis += string + ",";
			}
			if (dis != "" && dis != null && !dis.equals("")) {
				paramInfo.setContent(dis.substring(0, dis.length() - 1));
			} else {
				return JsonResult.build("201", "暂无数据");
			}
			int count = adminService.getCount2(paramInfo);
			// ***********************************************************
			Integer machineCount = sum;
			List<User> listUser = adminService.getAdminMachine(paramInfo);
			if (listUser.size() > 0) {
				for (int i = 0; i < listUser.size(); i++) {
					List<Role> roles = listUser.get(i).getRoles();
					for (int j = 0; j < roles.size(); j++) {
						List<Machine> machine_name = roles.get(j).getMachines();
						for (Machine machine : machine_name) {
							String[] split = machine.getMachine_name().split(",");
							String ids = "";
							for (String string : split) {
								String machineId = adminService.getMachineId(string);
								ids += machineId+",";
								List<Machine> ma = roles.get(j).getMachines();
								for (Machine mac : ma) {
									mac.setMachine_id(ids.substring(0, ids.length()-1));
								}
							}
						}
					}
				}
				return JsonResult.build("200", "查询成功", listUser, count,
						pInfo2.getPageNum(), paramInfo.getPageSize(),
						machineCount == null ? 0 : machineCount, machineList,
						null);
			} else {
				return JsonResult.build("201", "暂无数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试！");
		}
	}
	
	/**
	 * 查询未分发 url :
	 * http://localhost:8080/goodfield/web/admin/getAdminMachineDistribute
	 * 
	 * @param {"pageNum":"0", "pageSize":"10", "keyword":"", "status":"1"}
	 * @return
	 */
	@RequestMapping(value = "getAdminMachineDistribute", method = RequestMethod.POST)
	public HashMap<String, Object> getAdminMachineDistribute(
			@RequestBody ParamInfo paramInfo) {
		try {
			String userToken = RedisUtils.getKV("adminToken_"
					+ paramInfo.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getAdminKVLikes().size() > 0) {
					return JsonResult.build("210", "你的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			ParamInfo pInfo2 = new ParamInfo();
			if ((paramInfo.getPageNum() != "" && paramInfo.getPageNum() != null)
					&& (paramInfo.getPageSize() != "" && paramInfo
							.getPageSize() != null)) {
				pInfo2.setPageNum(paramInfo.getPageNum());
				int pageNum = (Integer.parseInt(paramInfo.getPageNum()))
						* Integer.parseInt(paramInfo.getPageSize());
				paramInfo.setPageNum(String.valueOf(pageNum));
				paramInfo.setPageSize(paramInfo.getPageSize());
			}
			List<String> notInStoresId = adminService.getNotInStoresId();
			String storesIds = "";
			for (String string : notInStoresId) {
				storesIds += "'" + string + "'" + ",";
			}
			if (storesIds != "" && storesIds != null && !storesIds.equals("")) {
				paramInfo.setContent(storesIds.substring(0,
						storesIds.length() - 1));
			} else {
				return JsonResult.build("201", "暂无数据");
			}
			// 超级代码
			List<String> pageInfoCity = adminService.getPageInfoCity(paramInfo);
			if (pageInfoCity.size() < 1) {
				return JsonResult.build("201", "暂无数据");
			}
			String cityContent = "";
			for (String string : pageInfoCity) {
				cityContent += "'" + string + "'" + ",";
			}
			if (cityContent != "" && cityContent != null
					&& !cityContent.equals("")) {
				paramInfo.setCityContent(cityContent.substring(0,
						cityContent.length() - 1));
			} else {
				return JsonResult.build("201", "暂无数据");
			}
			// 超级代码
			int count = adminService.getCount3(paramInfo);
			// 总接口
			if (paramInfo.getCityContent().equals("'未录城市分类'")) {
				paramInfo.setCityContent("''");
			}
			List<String> machineList = adminService
					.getAdminMachineCount2(paramInfo);
			Integer sum = 0;
			for (String string : machineList) {
				sum += Integer.parseInt(string);
			}
			Integer machineCount = sum;
			List<User> adminMachineDistribute = adminService
					.getAdminMachineDistribute(paramInfo);
			if (adminMachineDistribute.size() > 0) {
				for (int i = 0; i < adminMachineDistribute.size(); i++) {
					List<Role> roles = adminMachineDistribute.get(i).getRoles();
					for (int j = 0; j < roles.size(); j++) {
						List<Machine> machine_name = roles.get(j).getMachines();
						for (Machine machine : machine_name) {
							String[] split = machine.getMachine_name().split(",");
							String ids = "";
							for (String string : split) {
								String machineId = adminService.getMachineId(string);
								ids += machineId+",";
								List<Machine> ma = roles.get(j).getMachines();
								for (Machine mac : ma) {
									mac.setMachine_id(ids.substring(0, ids.length()-1));
								}
							}
						}
					}
				}
				return JsonResult.build("200", "查询成功", adminMachineDistribute,
						count, pInfo2.getPageNum(), paramInfo.getPageSize(),
						machineCount == null ? 0 : machineCount, machineList,
						null);
			} else {
				return JsonResult.build("201", "暂无数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试！");
		}
	}
	
}
