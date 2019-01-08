package com.yun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yun.pojo.UserShow;
import com.yun.utils.ParameterUtil;

@Mapper
public interface UserShowMapper {

	int getTipsInfoCount(String value);		// 查询未审核的数据总数
	
	List<UserShow> getExamineInfo(ParameterUtil parameterUtil);	// 查询待审核的数据
	
	UserShow getExamineDetailsInfo(ParameterUtil parameterUtil);	// 查询审核的详情
	
	int getExamineInfoCount(ParameterUtil parameterUtil);	// 查询待审核的数据总数
	
	List<UserShow> getExamineAdoptInfo(ParameterUtil parameterUtil);   // 查询已审核的数据
	
	int delExamineAdoptInfo(String value);	// 删除已审核的数据
	
	int getExamineAdoptInfoCount(ParameterUtil parameterUtil);		// 查询已审核的数据总数
	
	int examineUserShow(String value);		// 同意审核
	
	int examineRefuseUserShow(String value);	// 拒绝审核
	 
	int updateUserShow(UserShow userShow);	// 修改会员秀
	
	UserShow getUserShow(String value);	// 查询要修改的会员秀
	
	List<UserShow> listUserShowInfo(ParameterUtil parameterUtil);	// 查询会员秀
	
	int getUserShowCount(ParameterUtil parameterUtil);		// 查询会员秀总数
	
	int delUserShow(String value);	// 删除会员秀
	
	int getUserShowCountByGymAddr(String value);	// 会员保持在100
	
	String getMinCreateTime(String value);	// 查询最小的时间
	
	int deleteUserShowByCreate(UserShow userShow);	// 删除最前面新增的图片
	
	int saveUserShow(UserShow userShow);	// 新增会员秀
	
}
