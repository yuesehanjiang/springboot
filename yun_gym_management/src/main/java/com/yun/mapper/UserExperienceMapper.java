package com.yun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yun.pojo.UserExperience;
import com.yun.utils.ParameterUtil;

@Mapper
public interface UserExperienceMapper {

	String getUniqueCachePhone(UserExperience userExperience);	// 查询是否有该手机号
	
	int saveUniqueCache(UserExperience userExperience);	  // 添加体验手机号
	
	List<UserExperience> listUserExperience(ParameterUtil parameterUtil);	// 查询客服体验数据
	
	int getUserExperienceCount(ParameterUtil parameterUtil);	// 查询客服体验数据总数
	
	int saveUserExperience(UserExperience userExperience);	// 添加客服体验数据
	
	int delUserExperience(String id);	// 删除客服体验数据
	
}