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

import com.alibaba.common.jpush.JiGunag;
import com.alibaba.common.result.JsonResult;
import com.alibaba.pojo.Admission;
import com.alibaba.pojo.Greetings;
import com.alibaba.pojo.MemberShip;
import com.alibaba.pojo.Role;
import com.alibaba.service.GreetingsService;
import com.alibaba.service.InterfaceInfoService;
import com.alibaba.util.ImageType;
import com.alibaba.util.ParamInfo;
import com.alibaba.util.RedisUtils;

@Transactional(rollbackFor = Exception.class)
@RestController
@RequestMapping("/web/greetings/")
public class GreetingsController {

	@Autowired
	private GreetingsService greetingsService;
	@Autowired
	private InterfaceInfoService membershipService;
	Logger logger = Logger.getLogger(UserController.class);

	/**
	 * 查询所有问候语 url :
	 * http://localhost:8080/goodfield/web/greetings/getGreetingsInfoList
	 * 
	 * @param {"user_id":"2", "pageNum":"0", "pageSize":"10"}
	 * @return
	 */
	@RequestMapping(value = "getGreetingsInfoList", method = RequestMethod.POST)
	public HashMap<String, Object> getGreetingsInfoList(
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
			pInfo.setUser_id(pInfo.getUserToken());
			List<Role> userRoleInfo = greetingsService
					.getUserRoleRelationInfo(pInfo);
			if (userRoleInfo.size() < 1) {
				return JsonResult.build("202", "暂无权限!");
			}
			String stores_names = null;
			for (Role role : userRoleInfo) {
				stores_names += new String(role.getStores_name() + ",");
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
			int count = greetingsService.getCountGreetingsInfo(pInfo);
			stores_names = stores_names.substring(4);
			pInfo.setStores_names(stores_names.substring(0,
					stores_names.length() - 1));
			greetingsService.setGroupConcatMaxLen(); // 设置group_concat存储大小
			List<Greetings> greetingsInfoList = greetingsService
					.getGreetingsInfoList(pInfo);
			if (greetingsInfoList.size() > 0) {
				return JsonResult.build("200", "查询成功！", greetingsInfoList,
						count, pInfo2.getPageNum(), pInfo.getPageSize());
			} else {
				return JsonResult.build("201", "暂无数据！");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试！");
		}
	}

	/**
	 * 删除问候语 url :
	 * http://localhost:8080/goodfield/web/greetings/deleteGreetingsInfo
	 * 
	 * @param {"id":"1", "userId":"2"}
	 * @return
	 */
	@RequestMapping(value = "deleteGreetingsInfo", method = RequestMethod.POST)
	public HashMap<String, Object> deleteGreetingsInfo(
			@RequestBody Greetings greetings) {
		try {
			String userToken = RedisUtils.getKV("userToken_"
					+ greetings.getUserToken() + "_"
					+ greetings.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getUserKVLikes(greetings.getUserToken()).size() > 0) {
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			ParamInfo pInfo = new ParamInfo();
			pInfo.setUser_id(greetings.getUserId());
			List<Role> userRoleInfo = greetingsService
					.getUserRoleRelationInfo(pInfo);
			if (userRoleInfo.size() < 1) {
				return JsonResult.build("202", "暂无权限!");
			}
			// 开始推送
			String roleIds = greetingsService.getRoleIds(greetings.getId());
			String[] split = roleIds.split(",");
			for (String string : split) {
				List<String> storesIdByMachine = greetingsService.getStoresIdByMachine(string);	// 根据门店id查询终端机id
				for (String string2 : storesIdByMachine) {
					String[] str = {string2};
					String greetingsName = greetingsService.getGreetingsName(greetings.getId());
					JiGunag.pushMessage2(str, "{\"name\":\""+greetingsName+"\", \"greetings_type\":\"0\", \"type\":\"0\"}", "删除问候语");
				}
			}
			// 开始推送
			int deleteGreetingsInfo = greetingsService
					.deleteGreetingsInfo(greetings);
			if (deleteGreetingsInfo > 0) {
				return JsonResult.build("200", "删除成功！");
			} else {
				return JsonResult.build("205", "删除失败，请重试！");
			}
		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage());
			return JsonResult.build("205", "删除失败，请重试！");
		}
	}

	/**
	 * 添加问候语 url :
	 * http://localhost:8080/goodfield/web/greetings/addGreetingsInfo
	 * 
	 * @param {"name":"黄金大神", "stores_id":"1,2,3", "nonmemberName":"是的防护是",
	 *        "user_id":"2"}
	 * @return
	 */
	@RequestMapping(value = "addGreetingsInfo", method = RequestMethod.POST)
	public HashMap<String, Object> addGreetingsInfo(@RequestBody ParamInfo pInfo) {
		try {
			String userToken = RedisUtils.getKV("userToken_"
					+ pInfo.getUserToken() + "_" + pInfo.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getUserKVLikes(pInfo.getUserToken()).size() > 0) {
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			pInfo.setUser_id(pInfo.getUser_id());
			List<Role> userRoleInfo = greetingsService
					.getUserRoleRelationInfo(pInfo);
			if (userRoleInfo.size() < 1) {
				return JsonResult.build("202", "暂无权限!");
			}
			String clusive = pInfo.getName().replace("{{name}}", "{{n}}");
			if (clusive.length() > 36) {
				return JsonResult.build("205", "亲，请输入36个字符以内的问候语！");
			}
			clusive = clusive.replace("{{n}}", "{{name}}");
			if (pInfo.getNonmemberName().length() > 34) {
				return JsonResult.build("205", "亲，请输入36个字符以内的非会员问候语！");
			}
			String rid = pInfo.getStores_id().replace(" ", "");
			String name = clusive;
			String nonmemberName = pInfo.getNonmemberName();
			String[] rids = rid.split(",");
			for (String string : rids) {
				pInfo.setStores_id(string);
				Greetings greetingRoleName = greetingsService
						.getOtherRoleIdAll(pInfo);
				if (null != greetingRoleName) {
					return JsonResult.build("205", "所选门店中，已有门店被其他问候语绑定！");
				}
			}
			ParamInfo pInfo2 = new ParamInfo();
			pInfo2.setStores_id(rid);
			pInfo2.setName(name);
			pInfo2.setNonmemberName(nonmemberName);
			pInfo2.setUser_id(pInfo.getUser_id());
			if (pInfo2.getNonmemberName() == null
					|| pInfo2.getNonmemberName() == ""
					|| !pInfo2.getNonmemberName().equals("")) {
				pInfo2.setNonmemberName("欢迎光临");
			}
			int addGreetingsInfo = greetingsService.addGreetingsInfo(pInfo2);
			if (addGreetingsInfo > 0) {
				// 开始推送
				String roleIds = greetingsService.getRoleIds(pInfo2.getId());
				if(roleIds != null){
					String[] split = roleIds.split(",");
					for (String string : split) {
						List<String> storesIdByMachine = greetingsService.getStoresIdByMachine(string);	// 根据门店id查询终端机id
						for (String string2 : storesIdByMachine) {
							String[] str = {string2};
							String greetingsName = greetingsService.getGreetingsName(pInfo2.getId());
							JiGunag.pushMessage2(str, "{\"name\":\""+greetingsName+"\", \"greetings_type\":\"0\", \"type\":\"1\"}", "添加问候语");
						}
					}
				}
			} 
			return JsonResult.build("200", "添加成功！");
		} catch (Exception e) {

			e.printStackTrace();
			return JsonResult.build("205", "添加失败，请重试！");
		}
	}

	/**
	 * 查询要修改的问候语 url :
	 * http://localhost:8080/goodfield/web/greetings/getGreetingsInfo
	 * 
	 * @param {"id":"1", "userId":"2"}
	 * @return
	 */
	@RequestMapping(value = "getGreetingsInfo", method = RequestMethod.POST)
	public HashMap<String, Object> getGreetingsInfo(
			@RequestBody Greetings greetings) {
		try {
			String userToken = RedisUtils.getKV("userToken_"
					+ greetings.getUserToken() + "_"
					+ greetings.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getUserKVLikes(greetings.getUserToken()).size() > 0) {
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			ParamInfo paramInfo = new ParamInfo();
			paramInfo.setUser_id(greetings.getUserId());
			List<Role> userRoleInfo = greetingsService
					.getUserRoleRelationInfo(paramInfo);
			if (userRoleInfo.size() < 1) {
				return JsonResult.build("202", "暂无权限!");
			}
			List<Role> r = new ArrayList<Role>();
			for (Role role : userRoleInfo) {
				r.add(new Role(null, role.getStores_id(),
						role.getStores_name(), null, null));
			}
			String stores_names = null;
			for (Role role : userRoleInfo) {
				stores_names += new String(role.getStores_name() + ",");
			}
			stores_names = stores_names.substring(4);
			greetings.setStores_names(stores_names.substring(0,
					stores_names.length() - 1));
			Greetings greetingsInfo = greetingsService
					.getGreetingsInfo(greetings);
			if (greetingsInfo != null) {
				return JsonResult.build("200", "查询成功！", greetingsInfo, r);
			} else {
				return JsonResult.build("201", "暂无数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试！");
		}
	}

	/**
	 * 修改问候语 url :
	 * http://localhost:8080/goodfield/web/greetings/updateGreetingsInfo
	 * 
	 * @param {"stores_id":"1,2,3", "name":"哈哈", "user_id":"2", "id":"2",
	 *        "nonmemberName":"大师傅似的"}
	 * @return
	 */
	@RequestMapping(value = "updateGreetingsInfo", method = RequestMethod.POST)
	public HashMap<String, Object> updateGreetingsInfo(
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
			List<Role> userRoleInfo = greetingsService
					.getUserRoleRelationInfo(pInfo);
			if (userRoleInfo.size() < 1) {
				return JsonResult.build("202", "暂无权限!");
			}
			String clusive = pInfo.getName().replace("{{name}}", "{{n}}");
			if (clusive.length() > 36) {
				return JsonResult.build("205", "亲，请输入36个字符以内的问候语！");
			}
			clusive = clusive.replace("{{n}}", "{{name}}");
			if (pInfo.getNonmemberName().length() > 34) {
				return JsonResult.build("205", "亲，请输入36个字符以内的非会员问候语！");
			}

			String rid = pInfo.getStores_id().replace(" ", "");
			String name = clusive;
			String id = pInfo.getId();
			String userId = pInfo.getUser_id();
			String nonmemberName = pInfo.getNonmemberName();
			Greetings greetings = new Greetings();
			greetings.setId(id);
			greetings.setUserId(userId);
			Greetings greetingsInfo = greetingsService
					.getGreetingsInfo(greetings);
			ParamInfo pInfo2 = new ParamInfo();
			pInfo2.setStores_id(rid);
			pInfo2.setName(name);
			pInfo2.setId(id);
			pInfo2.setNonmemberName(nonmemberName);
			pInfo2.setUserId(userId);

			String greetingsInfos = greetingsService
					.getGreetingsInfos(greetings); // 查询问候语所有门店
			String stores_names = null;
			for (Role role : userRoleInfo) {
				stores_names += new String(role.getStores_id() + ",");
			}
			stores_names = stores_names.substring(4);
			greetings.setStores_names(greetingsInfos);
			String fun = ImageType.fun(greetings.getStores_names(),
					pInfo2.getStores_id());

			if (greetings.getStores_names().length() > fun.length()) { // 减少了门店
				// String fun2 = ImageType.fun(greetingsInfos, fun);
				pInfo2.setStores_id(fun);
			} else { // 添加了门店
				String fun2 = ImageType.fun(greetingsInfos, fun);
				pInfo2.setStores_id(fun2);
			}

			// 查找其他门店中是否有该id
			String otherRole = greetingsService.getOtherRoleId(pInfo);
			if (otherRole != null) {
				if (otherRole.indexOf(",") < 0) {
					otherRole += ",";
				}
				String[] otherRoless = otherRole.split(",");
				for (String string : otherRoless) {
					if (string.indexOf(rid) > -1) {
						return JsonResult.build("205", "所选门店中，已有门店被其他问候语绑定！");
					}
				}
			}
			if (greetingsInfo.getRoleId().indexOf(rid) < 0) {
				int i = 0;
				String otherRoleId = greetingsService.getOtherRoleId(pInfo);
				if (otherRoleId != null) {
					String[] otherRoleIds = otherRoleId.split(",");
					for (String string : otherRoleIds) {
						if (rid.indexOf(string) < 0) {
							i++;
						}
						if (i == otherRoleIds.length) {
							int addGreetingsInfo = greetingsService
									.updateGreetingsInfo(pInfo2);
							if (addGreetingsInfo > 0) {
								
								// 开始推送
								String roleIds = greetingsService.getRoleIds(pInfo.getId());
								if(roleIds != null){
									String[] split = roleIds.split(",");
									for (String string2 : split) {
										List<String> storesIdByMachine = greetingsService.getStoresIdByMachine(string2);	// 根据门店id查询终端机id
										for (String string3 : storesIdByMachine) {
											String[] str = {string3};
											String greetingsName = greetingsService.getGreetingsName(pInfo.getId());
											JiGunag.pushMessage2(str, "{\"name\":\""+greetingsName+"\", \"greetings_type\":\"0\", \"type\":\"2\"}", "修改问候语");
										}
									}
								}
								
								return JsonResult.build("200", "修改成功！");
							} else {
								return JsonResult.build("205", "修改失败，请重试！");
							}
						}
					}
				}
				// 判断门店不能一样
				String[] rids = pInfo.getStores_id().split(",");
				for (String string : rids) {
					pInfo.setStores_id(string);
					List<Greetings> greetingRoleName = greetingsService
							.getGreetingRoleName(pInfo);
					if (greetingRoleName.size() > 0) {
						return JsonResult.build("205", "所选门店中，已有门店被其他问候语绑定！");
					}
				}
			} else {
				int addGreetingsInfo = greetingsService
						.updateGreetingsInfo(pInfo2);
				if (addGreetingsInfo > 0) {
					
					// 开始推送
					String roleIds = greetingsService.getRoleIds(pInfo.getId());
					if(roleIds != null){
						String[] split = roleIds.split(",");
						for (String string2 : split) {
							List<String> storesIdByMachine = greetingsService.getStoresIdByMachine(string2);	// 根据门店id查询终端机id
							for (String string3 : storesIdByMachine) {
								String[] str = {string3};
								String greetingsName = greetingsService.getGreetingsName(pInfo.getId());
								JiGunag.pushMessage2(str, "{\"name\":\""+greetingsName+"\", \"greetings_type\":\"0\", \"type\":\"2\"}", "修改问候语");
							}
						}
					}
					
					return JsonResult.build("200", "修改成功！");
				} else {
					return JsonResult.build("205", "修改失败，请重试！");
				}
			}

			int addGreetingsInfo = greetingsService.updateGreetingsInfo(pInfo2);
			if (addGreetingsInfo > 0) {
				
				// 开始推送
				String roleIds = greetingsService.getRoleIds(pInfo.getId());
				if(roleIds != null){
					String[] split = roleIds.split(",");
					for (String string2 : split) {
						List<String> storesIdByMachine = greetingsService.getStoresIdByMachine(string2);	// 根据门店id查询终端机id
						for (String string3 : storesIdByMachine) {
							String[] str = {string3};
							String greetingsName = greetingsService.getGreetingsName(pInfo.getId());
							JiGunag.pushMessage2(str, "{\"name\":\""+greetingsName+"\", \"greetings_type\":\"0\", \"type\":\"2\"}", "修改问候语");
						}
					}
				}
				
				return JsonResult.build("200", "修改成功！");
			} else {
				return JsonResult.build("205", "修改失败，请重试！");
			}
		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage());
			return JsonResult.build("205", "修改失败，请重试！");
		}
	}

	/**
	 * 查询所有专属问候语 url :
	 * http://localhost:8080/goodfield/web/greetings/getClusiveGreetingsInfo
	 * 
	 * @param {"isNull":"", "user_id":"2",
	 *        "keyword":"","stores_id":"","pageNum":"0","pageSize":"10"}
	 * @return
	 */
	@RequestMapping(value = "getClusiveGreetingsInfo", method = RequestMethod.POST)
	public HashMap<String, Object> getClusiveGreetingsInfo(
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
			pInfo2.setPageNum(pInfo.getPageNum());
			int pageNum = (Integer.parseInt(pInfo.getPageNum()))
					* Integer.parseInt(pInfo.getPageSize());
			pInfo.setPageNum(String.valueOf(pageNum));
			pInfo.setPageSize(pInfo.getPageSize());
			List<Role> userRoleInfo = greetingsService
					.getUserRoleRelationInfo(pInfo);
			if (userRoleInfo.size() < 1) {
				return JsonResult.build("202", "暂无权限!");
			}
			List<Role> r = new ArrayList<Role>();
			for (Role role : userRoleInfo) {
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
			if (pInfo.getIsNull() != null) {
				if (!pInfo.getIsNull().equals("0")
						&& !pInfo.getIsNull().equals("1")) {
					return JsonResult.build("201", "暂无数据", r);
				}
			}
			int count = greetingsService.getCount(pInfo);
			List<MemberShip> greetingsInfoList = greetingsService
					.getClusiveGreetingsInfo(pInfo);
			if (greetingsInfoList.size() > 0) {
				return JsonResult.build("200", "查询成功！", greetingsInfoList,
						count, pInfo2.getPageNum(), pInfo.getPageSize(), r);
			} else {
				return JsonResult.build("201", "暂无数据", r);
			}
		} catch (Exception e) {
			e.printStackTrace();

			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试！");
		}
	}

	/**
	 * 删除专属问候语 url : http://localhost:8080/goodfield/web/greetings/
	 * delClusiveGreetingsInfoById
	 * 
	 * @param {"user_id":"1", "id":"1"}
	 * @return
	 */
	@RequestMapping(value = "delClusiveGreetingsInfoById", method = RequestMethod.POST)
	public HashMap<String, Object> delClusiveGreetingsInfoById(
			@RequestBody MemberShip clusiveGreetings) {
		try {
			String userToken = RedisUtils.getKV("userToken_"
					+ clusiveGreetings.getUserToken() + "_"
					+ clusiveGreetings.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getUserKVLikes(clusiveGreetings.getUserToken())
						.size() > 0) {
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			ParamInfo pInfo = new ParamInfo();
			pInfo.setUser_id(clusiveGreetings.getUserToken());
			List<Role> userRoleInfo = greetingsService
					.getUserRoleRelationInfo(pInfo);
			if (userRoleInfo.size() < 1) {
				return JsonResult.build("202", "暂无权限!");
			}
			int greetingsInfoList = greetingsService
					.delClusiveGreetingsInfoById(clusiveGreetings);
			if (greetingsInfoList > 0) {
				// 开始推送
				String memberByIdStoresId = greetingsService.getMemberByIdStoresId(clusiveGreetings.getUser_id());	// 获取门店id
				if(memberByIdStoresId != null){
					List<String> storesIdByMachine = greetingsService.getStoresIdByMachine(memberByIdStoresId);	// 根据门店id查询终端机id
					for (String string : storesIdByMachine) {
						String[] str = {string};
						String content = "{\"user_id\":\""+clusiveGreetings.getUser_id()+"\", \"greetings_type\":\"1\", \"type\":\"0\"}";
						JiGunag.pushMessage2(str, content, "删除专属问候语");
					}
				}
				return JsonResult.build("200", "删除成功！");
			} else {
				return JsonResult.build("205", "删除失败，请重试！");
			}
		} catch (Exception e) {

			logger.error(e.getMessage());
			return JsonResult.build("205", "删除失败，请重试！");
		}
	}

	/**
	 * 添加专属问候语 url : http://localhost:8080/goodfield/web/greetings/
	 * addClusiveGreetingsInfoById
	 * 
	 * @param {"exclusive":"现场一位", "user_id":"2", "id":"1"}
	 * @return
	 */
	@RequestMapping(value = "addClusiveGreetingsInfoById", method = RequestMethod.POST)
	public HashMap<String, Object> addClusiveGreetingsInfoById(
			@RequestBody MemberShip clusiveGreetings) {
		try {
			String userToken = RedisUtils.getKV("userToken_"
					+ clusiveGreetings.getUserToken() + "_"
					+ clusiveGreetings.getLoginStatus());
			if (userToken == null) {
				if (RedisUtils.getUserKVLikes(clusiveGreetings.getUserToken())
						.size() > 0) {
					return JsonResult.build("210", "您的账号已在别处登录，您被迫下线！");
				}
				return JsonResult.build("210", "登陆超时，请重新登陆");
			}
			String clusive = clusiveGreetings.getExclusive().replace(
					"{{name}}", "{{n}}");
			if (clusive.length() > 36) {
				return JsonResult.build("205", "亲，请输入36个字符以内的问候语！");
			}
			clusive = clusive.replace("{{n}}", "{{name}}");
			ParamInfo pInfo = new ParamInfo();
			pInfo.setUser_id(clusiveGreetings.getUser_id());
			List<Role> userRoleInfo = greetingsService
					.getUserRoleRelationInfo(pInfo);
			if (userRoleInfo.size() < 1) {
				return JsonResult.build("202", "暂无权限!");
			}
			clusiveGreetings.setExclusive(clusive);
			greetingsService.addClusiveGreetingsInfoById(clusiveGreetings);
			
			
			// 开始推送
			String memberByIdStoresId = greetingsService.getMemberByIdStoresId(clusiveGreetings.getId());	// 获取门店id
			if(memberByIdStoresId != null){
				List<String> storesIdByMachine = greetingsService.getStoresIdByMachine(memberByIdStoresId);	// 根据门店id查询终端机id
				for (String string : storesIdByMachine) {
					Admission admission = new Admission();
					admission.setUser_id(clusiveGreetings.getId());
					String memberName = membershipService.getMemberName(admission);	// 查询会员名称
					String[] str = {string};
					String content = "{\"user_id\":\""+clusiveGreetings.getId()+"\", \"exclusive\":\""+clusiveGreetings.getExclusive()+"\", \"name\":\""+memberName+"\",\"greetings_type\":\"1\", \"type\":\"1\"}";
					JiGunag.pushMessage2(str, content, "修改专属问候语");
				}
			}
			return JsonResult.build("200", "添加成功！");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return JsonResult.build("205", "添加失败，请重试！");
		}
	}

	/**
	 * 查询要修改的专属问候语 url : http://localhost:8080/goodfield/web/greetings/
	 * getClusiveGreetingsInfoById
	 * 
	 * @param {"id":"1", "user_id":"2"}
	 * @return
	 */
	@RequestMapping(value = "getClusiveGreetingsInfoById", method = RequestMethod.POST)
	public HashMap<String, Object> getClusiveGreetingsInfoById(
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
			pInfo2.setUser_id(pInfo.getUserToken());
			List<Role> userRoleInfo = greetingsService
					.getUserRoleRelationInfo(pInfo2);
			if (userRoleInfo.size() < 1) {
				return JsonResult.build("202", "暂无权限!");
			}
			MemberShip greetingsInfoList = greetingsService
					.getClusiveGreetingsInfoById(pInfo);
			if (greetingsInfoList != null) {
				return JsonResult.build("200", "查询成功！", greetingsInfoList);
			} else {
				return JsonResult.build("201", "查询成功！", greetingsInfoList);
			}
		} catch (Exception e) {

			logger.error(e.getMessage());
			return JsonResult.build("205", "查询失败，请重试！");
		}
	}

}
