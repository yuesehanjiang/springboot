package com.alibaba.controller;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.common.result.JsonResult;
import com.alibaba.dao.JiGuangMapper;
import com.alibaba.pojo.Admin;
import com.alibaba.pojo.Machine;
import com.alibaba.pojo.User;
import com.alibaba.service.AdminService;
import com.alibaba.service.UserService;
import com.alibaba.service.isservice.JiGuangService;
import com.alibaba.util.MD5Util;
import com.alibaba.util.OSSUtil;
import com.alibaba.util.ParamInfo;
import com.alibaba.util.RedisUtils;
import com.alibaba.util.YunPian;

@Transactional(rollbackFor = Exception.class)
@RestController
@RequestMapping("/web/user/")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private AdminService adminService;

	@Autowired
	JiGuangMapper jiGuangMapper;

	Logger logger = Logger.getLogger(UserController.class);

	private final static String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$";

	private final static String regexPhone = "^(10|11|12|13|14|15|16|17|18|19)\\d{9}$";

	/**
	 * 终端机管理 url : http://localhost:8080/goodfield/web/user/getMachineInfo
	 * 
	 * @param {"pageNum":"0", "pageSize":"10", "user_id":"1",
	 *        "account":"2353677685@qq.com", "keyword":"深圳", "status":"1"}
	 * @return
	 */
	@RequestMapping(value = "getMachineInfo", method = RequestMethod.POST)
	public HashMap<String, Object> getMachineInfo(
			@RequestBody ParamInfo paramInfo) {
		try {
			String userToken = RedisUtils.getKV("userToken_"
					+ paramInfo.getUserToken() + "_"
					+ paramInfo.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getUserKVLikes(paramInfo.getUserToken()).size() > 0) {
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
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
			// 超级代码
			List<String> pageInfoCity = userService.getPageInfoCity(paramInfo);
			if (pageInfoCity.size() < 1) {
				return JsonResult.build("201", "暂无数据！");
			}
			String cityContent = "";
			for (String string : pageInfoCity) {
				cityContent += "'" + string + "'" + ",";
			}
			if (pageInfoCity.equals("''")) {
				return JsonResult.build("201", "暂无数据！");
			}
			paramInfo.setCityContent(cityContent.substring(0,
					cityContent.length() - 1));
			// 超级代码
			int count = userService.getCount(paramInfo);
			if (paramInfo.getCityContent().equals("'未录城市分类'")) {
				paramInfo.setCityContent("''");
			}
			String machineList = userService.getStoresCitySum(paramInfo);
			List<Machine> machineInfo = userService.getMachineInfo(paramInfo);
			if (machineInfo.size() > 0) {
				return JsonResult.build("200", "查询成功！", machineInfo, count,
						pInfo2.getPageNum(), paramInfo.getPageSize(),
						machineList);
			} else {
				return JsonResult.build("201", "暂无数据！");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return JsonResult.build("205", "查询失败，请重试！");
		}
	}

	/**
	 * 上传头像 url : http://localhost:8080/goodfield/web/user/uploadImage param
	 * form表达形式 ===> {"file":"", "user_id":""}
	 */
	@RequestMapping(value = "uploadImage", method = RequestMethod.POST)
	public static HashMap<String, Object> fileUpload(MultipartFile file) {
		if (file.getSize() > 3 * 1048576) {
			return JsonResult.build("205", "文件大小必须在3M以内!");
		}
		Admin admin = null;
		try {
			String fileName = file.getOriginalFilename().replace("~", "")
					.replace("%", "").replace("{", "").replace("}", "")
					.replace("(", "").replace(")", "").replace("【", "")
					.replace("】", "").replace("[", "").replace("]", "")
					.replace("&", "").replace("^", "").replace("!", "")
					.replace("@", "").replace("#", "").replace("`", "")
					.replace("=", "").replace("+", "").replace(";", "")
					.replace(",", "").replace("；", "").replace("‘", "")
					.replace("。", "").replace("，", "").replace("·", "")
					.replace("、", "");
			fileName = java.net.URLEncoder.encode(fileName, "utf-8");
			String imagePath = "https://tianjiulongoa1.oss-cn-shenzhen.aliyuncs.com/goodfield/"
					+ fileName;
			// 获取输出流
			admin = new Admin();
			admin.setPic(imagePath);
			OSSUtil.uploadObject2OSS(file, "tianjiulongoa1", "goodfield/");
		} catch (Exception e) {
			e.printStackTrace();
			Logger logger = Logger.getLogger(UserController.class);
			logger.error(e.getMessage());
			return JsonResult.build("205", "上传失败，请重试！");
		}
		return JsonResult.build("200", "上传成功！", admin);
	}

	/**
	 * 用户登陆 url : http://localhost:8080/goodfield/web/user/userLogin
	 * {"password":"123456", "account":"2353677685@qq.com"}
	 * 
	 * @return
	 */
	@RequestMapping(value = "userLogin", method = RequestMethod.POST)
	public HashMap<String, Object> userLogin(@RequestBody ParamInfo paramInfo) {
		try {
			if (!paramInfo.getPassword().matches(regex)) {
				return JsonResult.build("205", "密码必须是数字和英文字母组成（8到20位）！");
			}
			User user = new User();
			user.setPassword(MD5Util.generate(paramInfo.getPassword()));
			user.setAccount(paramInfo.getAccount());
			User userLogin = userService.userLogin(user);
			if (userLogin != null) { // 验证身份通过
				String pic = adminService.getPicInfo();
				String enterprisename = adminService.getEnterpriseNameInfo();
				userLogin.setAdminPic(pic);
				userLogin.setEnterpriseName(enterprisename);
				try {
					user.setId(userLogin.getId());
					String loginStatus = userService.getLoginStatus(user); // 查询登录状态
					RedisUtils.delKV("userToken_" + userLogin.getId() + "_"
							+ loginStatus);
					if (loginStatus.equals("100")) {
						loginStatus = "-1";
					}
					user.setLoginStatus((Integer.parseInt(loginStatus)) + 1
							+ "");
					userService.updateLoginStatus(user); // 修改登录状态
					RedisUtils.setKV(
							String.valueOf("userToken_" + userLogin.getId()
									+ "_" + user.getLoginStatus()), 1800,
							JSONUtil.serialize(userLogin));
					userLogin.setLoginStatus(user.getLoginStatus());
				} catch (Exception e) {
					e.printStackTrace();
				}
				return JsonResult.build("200", "登陆成功！", userLogin);
			} else {
				return JsonResult.build("205", "登陆失败，账号或密码错误！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return JsonResult.build("205", "登陆失败，请重试！");
		}
	}

	/**
	 * 用户退出登陆 url : http://localhost:8080/goodfield/web/user/userLoginOut
	 * {"id":"1"}
	 * 
	 * @return
	 */
	@RequestMapping(value = "userLoginOut", method = RequestMethod.POST)
	public HashMap<String, Object> userLoginOut(@RequestBody User user) {
		try {
			RedisUtils.delKV("userToken_" + user.getUserToken() + "_"
					+ user.getLoginStatus());
			return JsonResult.build("200", "退出成功！");
		} catch (Exception e) {
			
			logger.error(e.getMessage());
			return JsonResult.build("205", "退出失败，请重试！");
		}
	}

	/**
	 * 查询要修改用户的头像 url : http://localhost:8080/goodfield/web/user/getUserPic
	 * 
	 * @param {"account":"2353677685@qq.com", "id":""}
	 * @return
	 */
	@RequestMapping(value = "getUserPic", method = RequestMethod.POST)
	public HashMap<String, Object> getUserPic(@RequestBody User user) {
		try {
			String userToken = RedisUtils.getKV("userToken_"
					+ user.getUserToken() + "_" + user.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getUserKVLikes(user.getUserToken()).size() > 0) {
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			User userPic = userService.getUserPic(user);
			if (userPic == null) {
				return JsonResult.build("201", "暂无数据！");
			}
			if (!userPic.getPic().equals("")) {
				return JsonResult.build("200", "查询成功！", userPic);
			} else {
				return JsonResult.build("201", "暂无数据！");
			}
		} catch (Exception e) {

			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试！");
		}
	}

	/**
	 * 修改用户头像 url : http://localhost:8080/goodfield/web/user/updateUserPic param
	 * {"account":"2353677685@qq.com", "id":"1",
	 * "pic":"https://xiangchao.oss-cn-shenzhen.aliyuncs.com/goodfield/.jpg"}
	 * 
	 * @return
	 */
	@RequestMapping(value = "updateUserPic", method = RequestMethod.POST)
	public HashMap<String, Object> updateUserPic(@RequestBody User user) {
		try {
			String userToken = RedisUtils.getKV("userToken_"
					+ user.getUserToken() + "_" + user.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getUserKVLikes(user.getUserToken()).size() > 0) {
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			int count = userService.updateUserPic(user);
			if (count > 0) {
				return JsonResult.build("200", "修改成功！");
			} else {
				return JsonResult.build("205", "修改失败，请重试!");
			}
		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage());
			return JsonResult.build("205", "修改失败，请重试!");
		}
	}

	/**
	 * 用户第一次登陆，需设置密码 url :
	 * http://localhost:8080/goodfield/web/user/changeUserPwd
	 * {"password":"12345678", "phone":"18529527874", "code":"123456", "id":"1"}
	 * 
	 * @return
	 */
	@RequestMapping(value = "changeUserPwd", method = RequestMethod.POST)
	public HashMap<String, Object> changeUserPwd(
			@RequestBody ParamInfo paramInfo) {
		try {
			String codes = RedisUtils.getKV("codes_" + paramInfo.getId());
			User user = new User();
			user.setPhone(paramInfo.getPhone());
			String adminPhone = userService.getUserPhone(user);
			if (!paramInfo.getPassword().matches(regex)) {
				return JsonResult.build("205", "密码必须是数字和英文字母组成（8到20位）！");
			}
			if (!paramInfo.getPhone().matches(regexPhone)) {
				return JsonResult.build("205", "手机号格式错误！");
			}
			if (!adminPhone.equals(paramInfo.getPhone())) {
				return JsonResult.build("205", "用户手机号错误！");
			}
			if (!paramInfo.getCode().equals(codes)) {
				return JsonResult.build("205", "验证码错误！");
			}
			paramInfo.setPassword(MD5Util.generate(paramInfo.getPassword()));
			int count = userService.changeUserPwd(paramInfo);
			if (count > 0) {
				RedisUtils.delKV("codes_" + paramInfo.getUserToken());
				return JsonResult.build("200", "修改成功！");
			} else {
				return JsonResult.build("205", "修改失败，请重试!");
			}
		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage());
			return JsonResult.build("205", "修改失败，请重试!");
		}
	}

	/**
	 * 修改用户密码的时候，根据账号查询用户的账号手机号 url :
	 * http://localhost:8080/goodfield/web/user/getUserAccountPhoneInfo
	 * 
	 * @param {"account":"2353677685@qq.com", "id":"1"}
	 * @return
	 */
	@RequestMapping(value = "getUserAccountPhoneInfo", method = RequestMethod.POST)
	public HashMap<String, Object> getUserAccountPhoneInfo(
			@RequestBody User user) {
		try {
			String userToken = RedisUtils.getKV("userToken_"
					+ user.getUserToken() + "_" + user.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getUserKVLikes(user.getUserToken()).size() > 0) {
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			User adminAccountPhoneInfo = userService
					.getUserAccountPhoneInfo(user);
			if (null != adminAccountPhoneInfo) {
				return JsonResult.build("200", "查询成功!", adminAccountPhoneInfo);
			} else {
				return JsonResult.build("201", "暂无数据!");
			}
		} catch (Exception e) {

			logger.error(e.getMessage());
			return JsonResult.build("205", "修改失败，请重试!");
		}
	}

	/**
	 * 发送短信验证码 url: http://localhost:8080/goodfield/web/user/sendSms
	 * 
	 * @param {"phone":"18529527874", "userToken":""}
	 * @return
	 */

	@RequestMapping(value = "sendSms", method = RequestMethod.POST)
	public HashMap<String, Object> sendSms(@RequestBody ParamInfo paramInfo) {
		try {
			String codes = YunPian.getRandomCode(6);
			RedisUtils.setKV("codes_" + paramInfo.getUserToken(), 300, codes);
			Boolean singleSendNew = YunPian.singleSendNew(paramInfo.getPhone(), codes);
			
			if(singleSendNew)
			return JsonResult.build("200", "发送成功！");
		} catch (Exception e) {

			logger.error(e.getMessage());
			e.printStackTrace();
			return JsonResult.build("205", "发送失败，请重试！");
		}
		return JsonResult.build("205", "发送失败，请重试！");
	}

	/**
	 * 修改用户密码 url : http://localhost:8080/goodfield/web/user/updateUserPwd param
	 * {"password":"12345678", "code":"123456", "id":"1",
	 * "original":"18529527874","account":"xiangchao"}
	 * 
	 * @return
	 */
	@RequestMapping(value = "updateUserPwd", method = RequestMethod.POST)
	public HashMap<String, Object> updateUserPwd(
			@RequestBody ParamInfo paramInfo) {
		try {
			String userToken = RedisUtils.getKV("userToken_"
					+ paramInfo.getUserToken() + "_"
					+ paramInfo.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getUserKVLikes(paramInfo.getUserToken()).size() > 0) {
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			String codes = RedisUtils
					.getKV("codes_" + paramInfo.getUserToken());
			String userPwd = userService.getUserPwd(paramInfo);
			if (!paramInfo.getPassword().matches(regex)
					|| !paramInfo.getOriginal().matches(regex)) {
				return JsonResult.build("205", "密码必须是数字和英文字母组成（8到20位）！");
			}
			if (!MD5Util.generate(paramInfo.getOriginal()).equals(userPwd)) {
				return JsonResult.build("205", "原密码错误！");
			}
			if (!paramInfo.getCode().equals(codes)) {
				return JsonResult.build("205", "验证码错误！");
			}
			paramInfo.setPassword(MD5Util.generate(paramInfo.getPassword()));
			int count = userService.updateUserPwd(paramInfo);
			if (count > 0) {
				RedisUtils.delKV("codes_" + paramInfo.getUserToken());
				return JsonResult.build("200", "修改成功！");
			} else {
				return JsonResult.build("205", "修改失败，请重试!");
			}
		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage());
			return JsonResult.build("205", "修改失败，请重试!");
		}
	}

	@Autowired
	JiGuangService jiGuangService;

	@RequestMapping(value = "/insert")
	public String insert() {

		try {
			jiGuangService.insertPushStatus("12", "12");
			int i = 10 / 0;
		} catch (Exception e) {

			e.printStackTrace();

			logger.error(e.getMessage());
		}
		return null;
	}

}
