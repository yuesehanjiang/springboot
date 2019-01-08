package com.yun.service.impl;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yun.constant.ResponseMessage;
import com.yun.mapper.UserExperienceMapper;
import com.yun.pojo.UserExperience;
import com.yun.service.UserExperienceService;
import com.yun.utils.ParameterUtil;
import com.yun.utils.RedisUtils;
import com.yun.utils.ServerResponse;
import com.yun.utils.StringUtils;
import com.yun.utils.YunPian;

@Service
@Transactional
public class UserExperienceServiceImpl implements UserExperienceService {

	@Autowired
	private UserExperienceMapper userExperienceMapper;
	
	@Autowired
	private RedisUtils redisUtils;
	
	/**
	 * 添加客服体验信息
	 */
	@Override
	public HashMap<String, Object> saveUserExperience(UserExperience userExperience) {
		if(StringUtils.isHasEmpty(userExperience.getPhone(), userExperience.getCreateTime(),userExperience.getGymAddr())){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		String uniqueCachePhone = userExperienceMapper.getUniqueCachePhone(userExperience);
		if(uniqueCachePhone != null){
			return ServerResponse.build("201", "手机号已存在!");
		}
		int saveUserExperience = userExperienceMapper.saveUserExperience(userExperience);
		if(saveUserExperience > 0){
			int saveUniqueCache = userExperienceMapper.saveUniqueCache(userExperience);
			if(saveUniqueCache > 0){
				redisUtils.remove("code_"+userExperience.getPhone());
				return ServerResponse.build("200", ResponseMessage.success);
			} else {
				return ServerResponse.build("203", ResponseMessage.exception);
			}
		} else {
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}

	/**
	 * 删除客服体验数据
	 */
	@Override
	public HashMap<String, Object> delUserExperience(String id) {
		if(StringUtils.isHasEmpty(id)){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		int saveUserExperience = userExperienceMapper.delUserExperience(id);
		if(saveUserExperience > 0){
			return ServerResponse.build("200", ResponseMessage.success);
		} else {
			return ServerResponse.build("203", ResponseMessage.exception);
		}
	}

	/**
	 * 查询客服体验数据
	 */
	@Override
	public HashMap<String, Object> listUserExperience(ParameterUtil parameterUtil) {
		if(StringUtils.isHasEmpty(parameterUtil.getPageNum(), parameterUtil.getPageSize(), parameterUtil.getGymAddr())){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		HashMap<String, Object> calculationPage = StringUtils.calculationPage(parameterUtil.getPageNum(), parameterUtil.getPageSize());
		parameterUtil.setPageNum(calculationPage.get("pageNum")+"");
		parameterUtil.setPageSize(calculationPage.get("pageSize")+"");
		List<UserExperience> listUserExperience = userExperienceMapper.listUserExperience(parameterUtil);
		if(listUserExperience.size() < 1){
			return ServerResponse.build("200", ResponseMessage.isNull, listUserExperience);
		}
		int userExperienceCount = userExperienceMapper.getUserExperienceCount(parameterUtil);
		return ServerResponse.build("200", ResponseMessage.success, listUserExperience, userExperienceCount+"", calculationPage.get("pNum")+"", parameterUtil.getPageSize());
	}

	/**
	 * 发送验证码
	 */
	@Override
	public HashMap<String, Object> sendMessage(String phone, String gymAddr) {
		if(StringUtils.isHasEmpty(phone,gymAddr)){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		UserExperience userExperience = new UserExperience();
		userExperience.setPhone(phone);
		userExperience.setGymAddr(gymAddr);
		String uniqueCachePhone = userExperienceMapper.getUniqueCachePhone(userExperience);
		if(uniqueCachePhone != null){
			return ServerResponse.build("201", "手机号已存在!");
		}
		String codes = YunPian.getRandomCode(4);
		String sendMessage = YunPian.sendMessage(phone, codes);
		String json = null;
		try {
			json = JSONObject.fromObject(sendMessage).getJSONObject("e").getJSONObject("detailMessage").getString("msg");
			return ServerResponse.build("203", json);
		} catch (Exception e) {
			if(json == null){
				redisUtils.set("code_"+phone, codes, 120L);
				return ServerResponse.build("200", ResponseMessage.success);
			} else {
				return ServerResponse.build("203", json);
			}
		}
	}

}