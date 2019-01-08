package com.yun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yun.pojo.Coach;
import com.yun.utils.ParameterUtil;

@Mapper
public interface CoachMapper {

	int addVote(String value);	// 修改点赞数
	
	List<Coach> listCoachInfo(ParameterUtil parameterUtil);	// 查询所有教练
	
	int getCoachCount(ParameterUtil parameterUtil);	// 查询教练总数
	
	int updateCoach(Coach coach);	// 修改教练
	
	int delCoach(String value);	// 删除教练
	
	Coach getCoachById(String value);	// 根据id查询教练
		
	int getCoachCountByGymAddr(String value);	// 教练保持在50
	
	String getMinCreateTime(String value);	// 查询最小的时间
	
	int deleteCoachByCreate(Coach coach);	// 删除最前面新增的图片
	
	int saveCoach(Coach coach);	// 新增教练

	
}
