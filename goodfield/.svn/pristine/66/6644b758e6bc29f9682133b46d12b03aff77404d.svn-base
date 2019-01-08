package com.alibaba.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.alibaba.common.param.UserRoleInfo;
import com.alibaba.pojo.Admin;
import com.alibaba.pojo.Role;
import com.alibaba.pojo.User;
import com.alibaba.pojo.UserRoleRelation;
import com.alibaba.util.ParamInfo;

@Repository
public interface AdminMapper {
	
	String getMachineId(String name);	// 查询终端机设备id
	
	int updateLoginStatus(Admin admin);	// 修改登录状态
	
	String getLoginStatus();	// 查询登录状态
	
	int deleteRoleRelation(User pInfo);	// 删除用户的时候把权限表的数据一起删除
	
	List<String> getlistUserRoleRelationsPages(ParamInfo pInfo);	// 分页
	
	List<String> getStoresIdRelationInfo(ParamInfo pInfo);	// 查询某个所有门店权限
	
	String getEnterpriseNameInfo();	// 查询企业名称
	
	Role getRoleId2(Role pInfo);	// 查询所有门店id不重复
	
	List<String> getUserIdGroup(ParamInfo pInfo);	// 查询id供终端机列表分页
	
	int updateMachineStatusonLine(ParamInfo pInfo);	// 修改终端机在线状态
	
	int updateMachineStatusoffLine();	// 修改终端机离线状态
	
	List<String> getMachineIds();	// 查询所有终端机
	
	List<String> getPageInfoCity(ParamInfo pInfo);	// 分页查询城市	
	
	List<String> getAdminMachineCount2(ParamInfo pInfo);		// 查询每个用户下门店终端机的总和
	
	int getCount3(ParamInfo pInfo);	// 查询总数

	List<User> getAdminMachineDistribute(ParamInfo pInfo);	// 查询未分发
	
	List<String> getNotInStoresId();	// 查询那一段特殊的id
	
	List<String> getAdminMachineCount(ParamInfo pInfo);		// 查询每个用户下门店终端机的总和
	
	Integer getMachineCount(ParamInfo pInfo);	// 查询终端机总数
	
	int getCount2(ParamInfo pInfo);	// 查询总数
	
	List<User> getAdminMachine(ParamInfo pInfo);	// 终端机管理
	
	String getPicSize();	// 查询图像存储个数
	
	Admin getEnterpriseInfo();	// 查询企业信息
	
	User getUserIdInfo(User user); 	// 查询用户id
	
	Admin getAdminAccountPhoneInfo();	// 在修改密码的时候查询管理员账号和手机号
	
	List<Role> getStoresList();	// 查询所有门店数据
	
	Admin adminLogin(Admin admin);  // 管理员登陆
	
	List<User> listUserRoleRelationsInfo(ParamInfo pInfo);   // 查询所有用户的门店权限信息
	
	int getCount(ParamInfo pInfo);	// 查询总数
	
	String getAdminPhone(Admin admin);	// 查询管理员手机号
	
	int getUserRoleRelationCount(ParamInfo pInfo);	// 查询设置权限总数
	
	List<Role> getUserRoleRelationInfo(ParamInfo user);  // 设置权限
	
	List<Role> getRoleNameInfo(ParamInfo pInfo);		// 查询要编辑门店信息
	
	int deleteUserRoleName(ParamInfo pInfo);	// 编辑门店之前先删除原来的门店数据 
	
	int deleteUserRoleName2(ParamInfo pInfo);	// 编辑门店之前先删除原来的门店数据 

	List<UserRoleRelation> getUserRoleNameInfo(ParamInfo pInfo);		// 查询用户下的所有门店信息
	
	List<UserRoleRelation> getRoleRelationInfo(ParamInfo pInfo);	// 查询门店的权限信息
	
	List<Role> getRoleId();		// 查询门店所有id
	
	int updateRoleNameInfo(List<UserRoleInfo> list);	// 编辑门店信息
	
	User getAccountInfo(User admin); // 查询账号是否存在
	
	int createUserInfo(User admin);	 // 创建用户
	
	User getUserPhoneInfo(User user); // 查询用户手机号，保证在添加的时候手机号不能重复
	
	User getUserPhoneInfoup(User user); // 查询用户手机号，保证在添加的时候手机号不能重复
	
	User getUserAccountInfo(User user); // 根据用户手机号查询账号
	
	int deleteUserInfo(User user);  // 删除用户
	
	Admin getAccountSettings();      // 查询账号设置
	
	User getUserInfoById(User user); // 根据id查询要修改的用户列表
	
	int updateUserInfo(User user);   // 修改用户列表信息
	
	List<User> getUserInfo(ParamInfo pInfo);		 // 查询用户列表
	
	Admin getAdminLastLogin(Admin admin);  // 根据密码判断用户是否是第一次登陆
	
	int updateAdminPwd(Admin admin);	// 第一次登陆，设置管理员密码
	
	int updateEnterpriseInfo(Admin admin);  // 修改企业信息
	
	String getAdminPwd();   // 查询管理员密码
	
	int updateAdminPhone(Admin admin);  // 修改管理员绑定的手机号
	
	int changeAdminPwd(ParamInfo paramInfo);  // 修改管理员密码
	
	int deleteUserRoleRelation(List<UserRoleInfo> list);	// 分配之前先删除原来的权限数据
	
	int addUserRoleRelation(List<UserRoleInfo> list);		// 批量分配权限数据
	
	String getPicInfo();	// 查询企业头像

	int updatePicSize(Admin admin);	// 修改图像存储量
	 
}
