package com.yun.service;

import java.util.HashMap;

import com.yun.pojo.UserExperience;
import com.yun.utils.ParameterUtil;

public interface UserExperienceService {

	HashMap<String, Object> listUserExperience(ParameterUtil parameterUtil);	// 查询客服体验数据
	
	HashMap<String, Object> saveUserExperience(UserExperience userExperience);	// 添加客服体验数据
	
	HashMap<String, Object> delUserExperience(String value);	// 删除客服体验数据
	
	HashMap<String, Object> sendMessage(String phone, String gymAddr) ;	// 发送验证码

}