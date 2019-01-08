package com.alibaba.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.alibaba.pojo.Machine;
import com.alibaba.pojo.User;
import com.alibaba.util.ParamInfo;

@Repository
public interface UserMapper {

	Integer getUnread(String id);	// 查询为读状态
	int updateUnread(String id);	// 修改为读状态
	int updateNotUnread(String id);	// 修改为读状态
	
	String getLoginStatus(User user);	// 查询登录状态
	
	int updateLoginStatus(User user);	// 修改登录状态
	
	List<String> getUserIdGroup(ParamInfo pInfo);	// 查询id供终端机列表分页
	
	Integer getMachineCount(ParamInfo paramInfo);	// 查询终端机总数
	
	List<String> getPageInfoCity(ParamInfo paramInfo);		// 分页查询城市
	
	String getStoresCitySum(ParamInfo paramInfo);		// 根据城市查询总数
	
	int getCount(ParamInfo paramInfo);		// 查询总数
	
	List<Machine> getMachineInfo(ParamInfo paramInfo);		// 终端机管理
	
	int updateUserPic(User user);	// 修改用户头像
	
	User getUserPic(User user);		// 查询要修改用户的头像
	
	String getUserPhone(User user);	// 查询用户手机号
	
	User getUserAccountPhoneInfo(User user);	// 修改用户密码的时候，根据账号查询用户的账号手机号
	
	User userLogin(User user);   // 用户登陆
	
	User getUserLastLogin(User user);   // 根据密码判断用户是否是第一次登陆
	
	int changeUserPwd(ParamInfo paramInfo);	// 用户第一次登陆，需设置用户密码
	
	int updateUserPwd(ParamInfo paramInfo); // 修改用户密码
	
	String getUserPwd(ParamInfo paramInfo);	// 查询用户密码
	
}
