package com.alibaba.service.isservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.common.param.UserRoleInfo;
import com.alibaba.dao.AdminMapper;
import com.alibaba.pojo.Admin;
import com.alibaba.pojo.Role;
import com.alibaba.pojo.User;
import com.alibaba.pojo.UserRoleRelation;
import com.alibaba.util.ParamInfo;

public interface AdminI {


	
		/**
		 * 修改登录状态
		 * @return
		 */
		public int updateLoginStatus(Admin admin);
		
		/**
		 * 查询登录状态
		 * @return
		 */
		public String getLoginStatus();
		
		/**
		 *	删除用户的时候把权限表的数据一起删除
		 * @return
		 */
		public int deleteRoleRelation(User pInfo);
		
		/**
		 *	分页
		 * @return
		 */
		public List<String> getlistUserRoleRelationsPages(ParamInfo pInfo);
		
		/**
		 * 查询某个所有门店权限
		 * @return
		 */
		public List<String> getStoresIdRelationInfo(ParamInfo pInfo);
		
		/**
		 * 查询企业名称
		 * @return
		 */
		public String getEnterpriseNameInfo();
		
		/**
		 * 查询所有门店id不重复
		 * @return
		 */
		public Role getRoleId2(Role pInfo);
		
		/**
		 * 查询id供终端机列表分页
		 * @return
		 */
		public List<String> getUserIdGroup(ParamInfo pInfo);
		
		/**
		 * 修改终端机离线状态
		 * @return
		 */
		public int updateMachineStatusoffLine();
		
		/**
		 * 修改终端机在线状态
		 * @return
		 */
		public int updateMachineStatusonLine(ParamInfo pInfo);
		
		/**
		 * 查询所有终端机
		 * @return
		 */
		public List<String> getMachineIds();
		
		/**
		 * 分页查询城市
		 * @return
		 */
		public List<String> getPageInfoCity(ParamInfo pInfo);
		
		/**
		 * 查询每个用户下门店终端机的总和
		 * @return
		 */
		public List<String> getAdminMachineCount2(ParamInfo pInfo);
		
		/**
		 * 查询总数
		 * @return
		 */
		public int getCount3(ParamInfo pInfo);
		
		/**
		 * 查询未分发
		 * @return
		 */
		public List<User> getAdminMachineDistribute(ParamInfo pInfo);
		
		/**
		 * 查询那一段特殊的id
		 * @return
		 */
		public List<String> getNotInStoresId();
		
		/**
		 * 查询每个用户下门店终端机的总和
		 * @return
		 */
		public List<String> getAdminMachineCount(ParamInfo pInfo);
		
		/**
		 * 查询终端机总数
		 * @return
		 */
		public Integer getMachineCount(ParamInfo pInfo);
		
		/**
		 * 查询总数
		 * @return
		 */
		public int getCount2(ParamInfo pInfo);
		
		/**
		 * 终端机管理
		 * @return
		 */
		public List<User> getAdminMachine(ParamInfo pInfo);
		
		/**
		 * 查询图像存储个数
		 * @return
		 */
		public String getPicSize();
		
		/**
		 * 查询企业信息
		 * @return
		 */
		public Admin getEnterpriseInfo();
		
		/**
		 * 查询用户id
		 * @return
		 */
		public User getUserIdInfo(User user);
		
		/**
		 * 在修改密码的时候查询管理员账号和手机号
		 * @return
		 */
		public Admin getAdminAccountPhoneInfo();
		
		/**
		 * 查询所有门店数据
		 * @return
		 */
		public List<Role> getStoresList();
		
		/**
		 * 查询所有用户的门店权限信息
		 * @return
		 */
		public Admin adminLogin(Admin admin);
		
		/**
		 * 查询所有用户的门店权限信息
		 * @return
		 */
		public List<User> listUserRoleRelationsInfo(ParamInfo pInfo);
		
		/**
		 * 查询总数
		 * @return
		 */
		public int getCount(ParamInfo pInfo);
		
		/**
		 * 查询设置权限总数
		 * @return
		 */
		public int getUserRoleRelationCount(ParamInfo pInfo);
		
		/**
		 * 设置权限
		 * @param user
		 * @return
		 */
		public List<Role> getUserRoleRelationInfo(ParamInfo user);
		
		/**
		 * 查询要编辑门店信息
		 * @param user
		 * @return
		 */
		public List<Role> getRoleNameInfo(ParamInfo pInfo);
		
		/**
		 * 编辑门店之前先删除原来的门店数据 
		 * @param user
		 * @return
		 */
		public int deleteUserRoleName(ParamInfo pInfo);
		
		/**
		 * 编辑门店之前先删除原来的门店数据 
		 * @param user
		 * @return
		 */
		public int deleteUserRoleName2(ParamInfo pInfo);
		
		/**
		 * 查询用户下的所有门店信息
		 * @param user
		 * @return
		 */
		public List<UserRoleRelation> getUserRoleNameInfo(ParamInfo pInfo);
		
		/**
		 * 查询门店的权限信息
		 * @param user
		 * @return
		 */
		public List<UserRoleRelation> getRoleRelationInfo(ParamInfo pInfo);
		
		/**
		 * 查询门店所有id
		 * @param user
		 * @return
		 */
		public List<Role> getRoleId();
		
		/**
		 * 编辑门店信息
		 * @param user
		 * @return
		 */
		public int updateRoleNameInfo(List<UserRoleInfo> list);
		
		/**
		 * 判断用户账号是否已存在
		 * @param user
		 * @return
		 */
		public User getAccountInfo(User user);
		
		/**
		 * 删除用户
		 * @param user
		 * @return
		 */
		public int deleteUserInfo(User user);
		
		/**
		 * 管理员创建用户
		 * @param user
		 * @return
		 */
		public int createUserInfo(User user);
		
		/**
		 * 查询用户手机号，保证在添加的时候手机号不能重复
		 * @param user
		 * @return
		 */
		public User getUserPhoneInfo(User user);
		
		/**
		 * 查询用户手机号，保证在添加的时候手机号不能重复
		 * @param user
		 * @return
		 */
		public User getUserPhoneInfoup(User user);
		
		/**
		 * 根据用户手机号查询账号
		 * @param user
		 * @return
		 */
		public User getUserAccountInfo(User user);
		
		/**
		 * 查询用户列表
		 * @return
		 */
		public List<User> getUserInfo(ParamInfo pInfo);
		
		/**
		 * 根据id查询要修改的用户列表
		 * @param user
		 * @return
		 */
		public User getUserInfoById(User user);
		
		/**
		 * 修改用户列表信息
		 * @param user
		 * @return
		 */
		public int updateUserInfo(User user);
		
		/**
		 * 根据密码判断用户是否是第一次登陆
		 * @param admin
		 * @return
		 */
		public Admin getAdminLastLogin(Admin admin);
		
		/**
		 * 第一次登陆，设置管理员密码
		 * @param admin
		 * @return
		 */
		public int updateAdminPwd(Admin admin);
		
		/**
		 * 修改企业信息
		 * @param admin
		 * @return
		 */
		public int updateEnterpriseInfo(Admin admin);
		
		/**
		 * 查询管理员密码
		 * @param admin
		 * @return
		 */
		public String getAdminPwd();
		
		/**
		 * 修改管理员绑定的手机号
		 * @return
		 */
		public int updateAdminPhone(Admin admin); 
		
		/**
		 * 修改管理员密码
		 * @param paramInfo
		 * @return
		 */
		public int changeAdminPwd(ParamInfo paramInfo);
		
		/**
		 * 分配之前先删除原来的权限数据
		 * @param list
		 * @return
		 */
		public int deleteUserRoleRelation(List<UserRoleInfo> list);
		
		/**
		 * 批量分配权限数据
		 * @param list
		 * @return
		 */
		public int addUserRoleRelation(List<UserRoleInfo> list);
		
		/**
		 * 查询企业头像
		 * @param list
		 * @return
		 */
		public String getPicInfo();
		
		/**
		 * 查询管理员手机号
		 * @param list
		 * @return
		 */
		public String getAdminPhone(Admin admin);
		
		/**
		 * 修改图像存储量
		 * @param admin
		 * @return
		 */
		public int updatePicSize(Admin admin);
	

}
