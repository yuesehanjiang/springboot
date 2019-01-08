package com.yun.service;

import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import com.yun.pojo.Coach;
import com.yun.utils.ParameterUtil;

public interface CoachService {


	HashMap<String, Object> addVote(String id);	// 修改点赞数
	

	HashMap<String, Object> listCoachInfo(ParameterUtil parameterUtil);	// 查询所有教练
	
	HashMap<String, Object> saveCoach(Coach coach, MultipartFile file);	// 新增教练
	
	HashMap<String, Object> getCoachById(String value);	// 根据id查询教练
	
	HashMap<String, Object> updateCoach(Coach coach, MultipartFile file);	// 修改教练
	
	HashMap<String, Object> delCoach(String value);	// 删除教练
	
}
