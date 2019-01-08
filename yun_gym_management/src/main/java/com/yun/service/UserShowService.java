package com.yun.service;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import com.yun.pojo.UserShow;
import com.yun.utils.ParameterUtil;

public interface UserShowService {

	HashMap<String, Object> getTipsInfoCount(String value);		// 查询未审核的数据总数
	
	HashMap<String, Object> getExamineInfo(ParameterUtil parameterUtil);	// 查询待审核的数据
	
	HashMap<String, Object> getExamineDetailsInfo(ParameterUtil parameterUtil);	// 查询审核的详情
	
	HashMap<String, Object> getExamineAdoptInfo(ParameterUtil parameterUtil);   // 查询已审核的数据
	
	HashMap<String, Object> delExamineAdoptInfo(String value);	// 删除已审核的数据
	
	HashMap<String, Object> examineUserShow(String value);		// 同意审核
	
	HashMap<String, Object> examineRefuseUserShow(String value);	// 拒绝审核
	
	HashMap<String, Object> updateUserShow(UserShow userShow, MultipartFile file);	// 修改会员秀
	
	HashMap<String, Object> getUserShow(String value);	// 查询要修改的会员秀
	
	HashMap<String, Object> saveUserShow(UserShow userShow, MultipartFile file);	// 新增会员秀
	
	HashMap<String, Object> delUserShow(String value);	// 删除会员秀
	
	HashMap<String, Object> listUserShowInfo(ParameterUtil parameterUtil);	// 查询会员秀
	
}
