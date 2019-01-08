package com.alibaba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dao.UserMapper;
import com.alibaba.pojo.Machine;
import com.alibaba.pojo.User;
import com.alibaba.util.ParamInfo;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 查询为读状态
	 * @return
	 */
	public Integer getUnread(String id) {
		return userMapper.getUnread(id);
	}
	
	/**
	 * 修改为读状态
	 * @return
	 */
	public int updateNotUnread(String id) {
		return userMapper.updateNotUnread(id);
	}
	
	/**
	 * 修改为读状态
	 * @return
	 */
	public int updateUnread(String id) {
		return userMapper.updateUnread(id);
	}
	
	/**
	 * 修改登录状态
	 * @return
	 */
	public int updateLoginStatus(User user) {
		return userMapper.updateLoginStatus(user);
	}
	
	/**
	 * 查询登录状态
	 * @return
	 */
	public String getLoginStatus(User user) {
		return userMapper.getLoginStatus(user);
	}
	
	/**
	 * 查询id供终端机列表分页
	 * @return
	 */
	public List<String> getUserIdGroup(ParamInfo pInfo) {
		return userMapper.getUserIdGroup(pInfo);
	}
	
	/**
	 * 分页查询城市
	 * @param user
	 * @return
	 */
	public List<String> getPageInfoCity(ParamInfo paramInfo) {
		return userMapper.getPageInfoCity(paramInfo);
	}
	
	/**
	 * 查询终端机总数
	 * @param user
	 * @return
	 */
	public Integer getMachineCount(ParamInfo paramInfo) {
		return userMapper.getMachineCount(paramInfo);
	}
	
	/**
	 * 根据城市查询总数
	 * @param user
	 * @return
	 */
	public String getStoresCitySum(ParamInfo paramInfo) {
		return userMapper.getStoresCitySum(paramInfo);
	}
	
	/**
	 * 查询总数
	 * @param user
	 * @return
	 */
	public int getCount(ParamInfo paramInfo) {
		return userMapper.getCount(paramInfo);
	}
	
	/**
	 * 终端机管理
	 * @param user
	 * @return
	 */
	public List<Machine> getMachineInfo(ParamInfo paramInfo) {
		return userMapper.getMachineInfo(paramInfo);
	}
	
	/**
	 * 修改用户头像
	 * @param user
	 * @return
	 */
	public int updateUserPic(User user) {
		return userMapper.updateUserPic(user);
	}
	
	/**
	 * 查询要修改用户的头像
	 * @param user
	 * @return
	 */
	public User getUserPic(User user) {
		return userMapper.getUserPic(user);
	}
	
	/**
	 * 查询用户手机号
	 * @param user
	 * @return
	 */
	public String getUserPhone(User user) {
		return userMapper.getUserPhone(user);
	}
	
	/**
	 * 修改用户密码的时候，根据账号查询用户的账号手机号
	 * @param user
	 * @return
	 */
	public User getUserAccountPhoneInfo(User user) {
		return userMapper.getUserAccountPhoneInfo(user);
	}
	
	/**
	 * 用户登陆
	 * @param user
	 * @return
	 */
	public User userLogin(User user){
		return userMapper.userLogin(user);
	}
	
	/**
	 * 根据密码判断用户是否是第一次登陆
	 * @param user
	 * @return
	 */
	public User getUserLastLogin(User user){
		return userMapper.getUserLastLogin(user);
	}
	
	/**
	 * 用户第一次登陆，需设置用户密码
	 * @param user
	 * @return
	 */
	public int changeUserPwd(ParamInfo paramInfo){
		return userMapper.changeUserPwd(paramInfo);
	}
	
	/**
	 * 修改用户密码
	 * @param paramInfo
	 * @return
	 */
	public int updateUserPwd(ParamInfo paramInfo){
		return userMapper.updateUserPwd(paramInfo);
	}
	
	/**
	 * 查询用户密码
	 * @param paramInfo
	 * @return
	 */
	public String getUserPwd(ParamInfo paramInfo){
		return userMapper.getUserPwd(paramInfo);
	}
	
}
