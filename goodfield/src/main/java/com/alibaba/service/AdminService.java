package com.alibaba.service;

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

@Service
public class AdminService {

	@Autowired
	private AdminMapper adminMapper;
	
	/**
	 * 查询终端机设备id
	 * @return
	 */
	public String getMachineId(String name) {
		return adminMapper.getMachineId(name);
	}
	
	/**
	 * 修改登录状态
	 * @return
	 */
	public int updateLoginStatus(Admin admin) {
		return adminMapper.updateLoginStatus(admin);
	}
	
	/**
	 * 查询登录状态
	 * @return
	 */
	public String getLoginStatus() {
		return adminMapper.getLoginStatus();
	}
	
	/**
	 *	删除用户的时候把权限表的数据一起删除
	 * @return
	 */
	public int deleteRoleRelation(User pInfo) {
		return adminMapper.deleteRoleRelation(pInfo);
	}
	
	/**
	 *	分页
	 * @return
	 */
	public List<String> getlistUserRoleRelationsPages(ParamInfo pInfo) {
		return adminMapper.getlistUserRoleRelationsPages(pInfo);
	}
	
	/**
	 * 查询某个所有门店权限
	 * @return
	 */
	public List<String> getStoresIdRelationInfo(ParamInfo pInfo) {
		return adminMapper.getStoresIdRelationInfo(pInfo);
	}
	
	/**
	 * 查询企业名称
	 * @return
	 */
	public String getEnterpriseNameInfo() {
		return adminMapper.getEnterpriseNameInfo();
	}
	
	/**
	 * 查询所有门店id不重复
	 * @return
	 */
	public Role getRoleId2(Role pInfo) {
		return adminMapper.getRoleId2(pInfo);
	}
	
	/**
	 * 查询id供终端机列表分页
	 * @return
	 */
	public List<String> getUserIdGroup(ParamInfo pInfo) {
		return adminMapper.getUserIdGroup(pInfo);
	}
	
	/**
	 * 修改终端机离线状态
	 * @return
	 */
	public int updateMachineStatusoffLine() {
		return adminMapper.updateMachineStatusoffLine();
	}
	
	/**
	 * 修改终端机在线状态
	 * @return
	 */
	public int updateMachineStatusonLine(ParamInfo pInfo) {
		return adminMapper.updateMachineStatusonLine(pInfo);
	}
	
	/**
	 * 查询所有终端机
	 * @return
	 */
	public List<String> getMachineIds() {
		return adminMapper.getMachineIds();
	}
	
	/**
	 * 分页查询城市
	 * @return
	 */
	public List<String> getPageInfoCity(ParamInfo pInfo) {
		return adminMapper.getPageInfoCity(pInfo);
	}
	
	/**
	 * 查询每个用户下门店终端机的总和
	 * @return
	 */
	public List<String> getAdminMachineCount2(ParamInfo pInfo) {
		return adminMapper.getAdminMachineCount2(pInfo);
	}
	
	/**
	 * 查询总数
	 * @return
	 */
	public int getCount3(ParamInfo pInfo) {
		return adminMapper.getCount3(pInfo);
	}
	
	/**
	 * 查询未分发
	 * @return
	 */
	public List<User> getAdminMachineDistribute(ParamInfo pInfo) {
		return adminMapper.getAdminMachineDistribute(pInfo);
	}
	
	/**
	 * 查询那一段特殊的id
	 * @return
	 */
	public List<String> getNotInStoresId() {
		return adminMapper.getNotInStoresId();
	}
	
	/**
	 * 查询每个用户下门店终端机的总和
	 * @return
	 */
	public List<String> getAdminMachineCount(ParamInfo pInfo) {
		return adminMapper.getAdminMachineCount(pInfo);
	}
	
	/**
	 * 查询终端机总数
	 * @return
	 */
	public Integer getMachineCount(ParamInfo pInfo) {
		return adminMapper.getMachineCount(pInfo);
	}
	
	/**
	 * 查询总数
	 * @return
	 */
	public int getCount2(ParamInfo pInfo) {
		return adminMapper.getCount2(pInfo);
	}
	
	/**
	 * 终端机管理
	 * @return
	 */
	public List<User> getAdminMachine(ParamInfo pInfo) {
		return adminMapper.getAdminMachine(pInfo);
	}
	
	/**
	 * 查询图像存储个数
	 * @return
	 */
	public String getPicSize() {
		return adminMapper.getPicSize();
	}
	
	/**
	 * 查询企业信息
	 * @return
	 */
	public Admin getEnterpriseInfo() {
		return adminMapper.getEnterpriseInfo();
	}
	
	/**
	 * 查询用户id
	 * @return
	 */
	public User getUserIdInfo(User user) {
		return adminMapper.getUserIdInfo(user);
	}
	
	/**
	 * 在修改密码的时候查询管理员账号和手机号
	 * @return
	 */
	public Admin getAdminAccountPhoneInfo() {
		return adminMapper.getAdminAccountPhoneInfo();
	}
	
	/**
	 * 查询所有门店数据
	 * @return
	 */
	public List<Role> getStoresList() {
		return adminMapper.getStoresList();
	}
	
	/**
	 * 查询所有用户的门店权限信息
	 * @return
	 */
	public Admin adminLogin(Admin admin){
		return adminMapper.adminLogin(admin);
	}
	
	/**
	 * 查询所有用户的门店权限信息
	 * @return
	 */
	public List<User> listUserRoleRelationsInfo(ParamInfo pInfo){
		return adminMapper.listUserRoleRelationsInfo(pInfo);
	}
	
	/**
	 * 查询总数
	 * @return
	 */
	public int getCount(ParamInfo pInfo){
		return adminMapper.getCount(pInfo);
	}
	
	/**
	 * 查询设置权限总数
	 * @return
	 */
	public int getUserRoleRelationCount(ParamInfo pInfo){
		return adminMapper.getUserRoleRelationCount(pInfo);
	}
	
	/**
	 * 设置权限
	 * @param user
	 * @return
	 */
	public List<Role> getUserRoleRelationInfo(ParamInfo user){
		return adminMapper.getUserRoleRelationInfo(user);
	}
	
	/**
	 * 查询要编辑门店信息
	 * @param user
	 * @return
	 */
	public List<Role> getRoleNameInfo(ParamInfo pInfo){
		return adminMapper.getRoleNameInfo(pInfo);
	}
	
	/**
	 * 编辑门店之前先删除原来的门店数据 
	 * @param user
	 * @return
	 */
	public int deleteUserRoleName(ParamInfo pInfo) {
		return adminMapper.deleteUserRoleName(pInfo);
	}
	
	/**
	 * 编辑门店之前先删除原来的门店数据 
	 * @param user
	 * @return
	 */
	public int deleteUserRoleName2(ParamInfo pInfo) {
		return adminMapper.deleteUserRoleName2(pInfo);
	}
	
	/**
	 * 查询用户下的所有门店信息
	 * @param user
	 * @return
	 */
	public List<UserRoleRelation> getUserRoleNameInfo(ParamInfo pInfo) {
		return adminMapper.getUserRoleNameInfo(pInfo);
	}
	
	/**
	 * 查询门店的权限信息
	 * @param user
	 * @return
	 */
	public List<UserRoleRelation> getRoleRelationInfo(ParamInfo pInfo) {
		return adminMapper.getRoleRelationInfo(pInfo);
	}
	
	/**
	 * 查询门店所有id
	 * @param user
	 * @return
	 */
	public List<Role> getRoleId()	{
		return adminMapper.getRoleId();
	}
	
	/**
	 * 编辑门店信息
	 * @param user
	 * @return
	 */
	public int updateRoleNameInfo(List<UserRoleInfo> list)	{
		return adminMapper.updateRoleNameInfo(list);
	}
	
	/**
	 * 判断用户账号是否已存在
	 * @param user
	 * @return
	 */
	public User getAccountInfo(User user){
		return adminMapper.getAccountInfo(user);
	}
	
	/**
	 * 删除用户
	 * @param user
	 * @return
	 */
	public int deleteUserInfo(User user){
		return adminMapper.deleteUserInfo(user);
	}
	
	/**
	 * 管理员创建用户
	 * @param user
	 * @return
	 */
	public int createUserInfo(User user){
		return adminMapper.createUserInfo(user);
	}
	
	/**
	 * 查询用户手机号，保证在添加的时候手机号不能重复
	 * @param user
	 * @return
	 */
	public User getUserPhoneInfo(User user){
		return adminMapper.getUserPhoneInfo(user);
	}
	
	/**
	 * 查询用户手机号，保证在添加的时候手机号不能重复
	 * @param user
	 * @return
	 */
	public User getUserPhoneInfoup(User user){
		return adminMapper.getUserPhoneInfoup(user);
	}
	
	/**
	 * 根据用户手机号查询账号
	 * @param user
	 * @return
	 */
	public User getUserAccountInfo(User user){
		return adminMapper.getUserAccountInfo(user);
	}
	
	/**
	 * 查询用户列表
	 * @return
	 */
	public List<User> getUserInfo(ParamInfo pInfo){
		return adminMapper.getUserInfo(pInfo);
	}
	
	/**
	 * 根据id查询要修改的用户列表
	 * @param user
	 * @return
	 */
	public User getUserInfoById(User user){
		return adminMapper.getUserInfoById(user);
	}
	
	/**
	 * 修改用户列表信息
	 * @param user
	 * @return
	 */
	public int updateUserInfo(User user){
		return adminMapper.updateUserInfo(user);
	}
	
	/**
	 * 根据密码判断用户是否是第一次登陆
	 * @param admin
	 * @return
	 */
	public Admin getAdminLastLogin(Admin admin){
		return adminMapper.getAdminLastLogin(admin);
	}
	
	/**
	 * 第一次登陆，设置管理员密码
	 * @param admin
	 * @return
	 */
	public int updateAdminPwd(Admin admin){
		return adminMapper.updateAdminPwd(admin);
	}
	
	/**
	 * 修改企业信息
	 * @param admin
	 * @return
	 */
	public int updateEnterpriseInfo(Admin admin){
		return adminMapper.updateEnterpriseInfo(admin);
	}
	
	/**
	 * 查询管理员密码
	 * @param admin
	 * @return
	 */
	public String getAdminPwd(){
		return adminMapper.getAdminPwd();
	}
	
	/**
	 * 修改管理员绑定的手机号
	 * @return
	 */
	public int updateAdminPhone(Admin admin){
		return adminMapper.updateAdminPhone(admin);
	} 
	
	/**
	 * 修改管理员密码
	 * @param paramInfo
	 * @return
	 */
	public int changeAdminPwd(ParamInfo paramInfo){
		return adminMapper.changeAdminPwd(paramInfo);
	}
	
	/**
	 * 分配之前先删除原来的权限数据
	 * @param list
	 * @return
	 */
	public int deleteUserRoleRelation(List<UserRoleInfo> list){	
		return adminMapper.deleteUserRoleRelation(list);
	}
	
	/**
	 * 批量分配权限数据
	 * @param list
	 * @return
	 */
	public int addUserRoleRelation(List<UserRoleInfo> list){
		return adminMapper.addUserRoleRelation(list);
	}
	
	/**
	 * 查询企业头像
	 * @param list
	 * @return
	 */
	public String getPicInfo(){
		return adminMapper.getPicInfo();
	}
	
	/**
	 * 查询管理员手机号
	 * @param list
	 * @return
	 */
	public String getAdminPhone(Admin admin){
		return adminMapper.getAdminPhone(admin);
	}
	
	/**
	 * 修改图像存储量
	 * @param admin
	 * @return
	 */
	public int updatePicSize(Admin admin) {
		return adminMapper.updatePicSize(admin);
	}
	
}
