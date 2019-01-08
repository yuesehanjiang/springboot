package com.alibaba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dao.InterfaceInfoMapper;
import com.alibaba.pojo.Admission;
import com.alibaba.pojo.Binding;
import com.alibaba.pojo.File;
import com.alibaba.pojo.Folder;
import com.alibaba.pojo.Greetings;
import com.alibaba.pojo.Machine;
import com.alibaba.pojo.ManagementPic;
import com.alibaba.pojo.MemberShip;
import com.alibaba.pojo.NomemberImageLibrary;
import com.alibaba.pojo.NonMember;
import com.alibaba.pojo.Push;
import com.alibaba.pojo.PushStatus;
import com.alibaba.pojo.Role;
import com.alibaba.pojo.Test;
import com.alibaba.util.ParamInfo;

@Service
public class InterfaceInfoService {

	@Autowired
	private InterfaceInfoMapper membershipMapper;
	
	
	/**
	 * 终端机初始化的时候获取所有门店信息
	 * @return
	 */
	public List<Role> machineInit(){
		return membershipMapper.machineInit();
	}
	/**
	 * 查询终端机内存大小
	 * @return
	 */
	public String getId(String val){
		return membershipMapper.getId(val);
	}
	/**
	 * 查询终端机内存大小
	 * @return
	 */
	public List<Machine> getMemorys(){
		return membershipMapper.getMemorys();
	}
	/**
	 * 查询终端机内存
	 * @return
	 */
	public String getMemory(String value){
		return membershipMapper.getMemory(value);
	}
	/**
	 * 修改终端机内存大小
	 * @return
	 */
	public int updateMachineMemory(Machine machine){
		return membershipMapper.updateMachineMemory(machine);
	}
	
	
	
	
	
	/**
	 * ***
	 * @return
	 */
	public int deleteManagementPicInfo(Binding binding){
		return membershipMapper.deleteManagementPicInfo(binding);
	}
	/**
	 * ***
	 * @return
	 */
	public String getUserInTime(Binding binding){
		return membershipMapper.getUserInTime(binding);
	}
	/**
	 * ***
	 * @return
	 */
	public String getPhotoTime(Binding binding){
		return membershipMapper.getPhotoTime(binding);
	}
	
	/**
	 * ***
	 * @return
	 */
	public void truncateAdmissionBind(){
		membershipMapper.truncateAdmissionBind();
	}
	
	/**
	 * ***
	 * @return
	 */
	public String getAdmissionBindUuid(String user_id){
		return membershipMapper.getAdmissionBindUuid(user_id);
	}
	
	/**
	 * ***
	 * @return
	 */
	public int deleteAdmissionBindUuid(String user_id){
		return membershipMapper.deleteAdmissionBindUuid(user_id);
	}
	
	/**
	 * ***
	 * @return
	 */
	public int saveAdmissionBind(Binding binding){
		return membershipMapper.saveAdmissionBind(binding);
	}
	
	/**
	 * ***
	 * @return
	 */
	public int deleteManagementPicUuid(String uuid){
		return membershipMapper.deleteManagementPicUuid(uuid);
	}
	
	/**
	 * ***
	 * @return
	 */
	public List<String> getManagementPicUuid(){
		return membershipMapper.getManagementPicUuid();
	}
	
	/**
	 * ***
	 * @return
	 */
	public List<String> deleteChao(){
		return membershipMapper.deleteChao();
	}
	
	/**
	 * ***
	 * @return
	 */
	public int getBindingCount(){
		return membershipMapper.getBindingCount();
	}
	
	/**
	 * ***
	 * @return
	 */
	public void truncateBinding(){
		membershipMapper.truncateBinding();
	}
	
	/**
	 * ***
	 * @return
	 */
	public int saveBinding(Binding binding){
		return membershipMapper.saveBinding(binding);
	}
	
	/**
	 * 入场的时候查询uuid
	 * @return
	 */
	public String getAdmissionUuid(String user_id){
		return membershipMapper.getAdmissionUuid(user_id);
	}
	
	/**
	 * 入场的时候查询uuid
	 * @return
	 */
	public int deleteAdmissionUuid(String user_id){
		return membershipMapper.deleteAdmissionUuid(user_id);
	}
	
	/**
	 * 删除之前的会员信息，添加最新的
	 * @return
	 */
	public int saveMemberShip2(MemberShip message){
		return membershipMapper.saveMemberShip2(message);
	}

	/**
	 * 删除之前的会员信息，添加最新的
	 * @return
	 */
	public int deleteMembership(String user_id){
		return membershipMapper.deleteMembership(user_id);
	}
	
	/**
	 * 查询门店id
	 * @return
	 */
	public List<MemberShip> getExclusives(String stores_id){
		return membershipMapper.getExclusives(stores_id);
	}
	
	/**
	 * 查询门店id
	 * @return
	 */
	public String getStoresId(String machine_id){
		return membershipMapper.getStoresId(machine_id);
	}
	
	/**
	 * 根据门店id查询欢迎词
	 * @return
	 */
	public String getStoresIdByName(String stores_id){
		return membershipMapper.getStoresIdByName(stores_id);
	}
	
	
	
	
	public int deleteNomemberImageLibrary(){
		return membershipMapper.deleteNomemberImageLibrary();
	}
	
	/* 终端机的操作和推送 start */
	
	public int savePush(Push push){
		return membershipMapper.savePush(push);
	}
	
	/**
	 * 添加解绑的终端机
	 * @param push
	 * @return
	 */
	public int updatePushStatus(Push push){
		return membershipMapper.updatePushStatus(push);
	}
	
	/**
	 * 判断解绑的终端机是否符合删除条件
	 * @param push
	 * @return
	 */
	public String delPush(Push push){
		return membershipMapper.delPush(push);
	}
	
	/**
	 * 删除解绑的终端机
	 * @param push
	 * @return
	 */
	public int delPushInfos(Push push) {
		return membershipMapper.delPushInfos(push);
	}
	
	/* 终端机的操作和推送 end */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 修改会员头像
	 * @param message
	 * @return
	 */
	public int updateMembershipPic(MemberShip memberShip) {
		return membershipMapper.updateMembershipPic(memberShip);
	}
	
	/**
	 * 修改会员未曾会员
	 * @param message
	 * @return
	 */
	public int updateMembership(MemberShip memberShip) {
		return membershipMapper.updateMembership(memberShip);
	}
	
	/**
	 * 会员优先推的问候语
	 * @param message
	 * @return
	 */
	public String getExclusive(Admission nonMember) {
		return membershipMapper.getExclusive(nonMember);
	}
	
	/**
	 * 查询推送的问候语
	 * @param message
	 * @return
	 */
	public Greetings getNonmemberNamePush(NonMember nonMember) {
		return membershipMapper.getNonmemberNamePush(nonMember);
	}
	
	/**
	 * 查询会员问候语
	 * @param message
	 * @return
	 */
	public Greetings getNamePush(Admission nonMember) {
		return membershipMapper.getNamePush(nonMember);
	}
	
	/**
	 * 询会员名称
	 * @param message
	 * @return
	 */
	public String getMemberName(Admission nonMember) {
		return membershipMapper.getMemberName(nonMember);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 如果没数据就不推送
	 * @param message
	 * @return
	 */
	public List<String> getdeviceIdNull(PushStatus folder) {
		return membershipMapper.getdeviceIdNull(folder);
	}
	
	/**
	 * 查询设备表里的删除状态是否符合删除
	 * @param message
	 * @return
	 */
	public List<String> getIsDeleteInfo(PushStatus folder) {
		return membershipMapper.getIsDeleteInfo(folder);
	}
	
	/**
	 * 修改删除状态
	 * @param message
	 * @return
	 */
	public int updateIsDetete(PushStatus a) {
		return membershipMapper.updateIsDetete(a);
	}
	
	/**
	 * 查询文件夹下的所有终端机
	 * @param message
	 * @return
	 */
	public String getMacFolderInfos(Folder folder) {
		return membershipMapper.getMacFolderInfos(folder);
	}
	
	/**
	 * 将数据库中某个文件夹id的终端机重置为离线
	 * @param message
	 * @return
	 */
	public int updateMacStatus0(PushStatus pushStatus) {
		return membershipMapper.updateMacStatus0(pushStatus);
	}
	
	/**
	 * 查询终端机是不是符合清0的条件
	 * @param message
	 * @return
	 */
	public String getMacStatusInfos0(PushStatus pushStatus) {
		return membershipMapper.getMacStatusInfos0(pushStatus);
	}
	
	/**
	 * 查询第一次推送的终端机
	 * @param message
	 * @return
	 */
	public String getMacStatusLast(PushStatus pushStatus) {
		return membershipMapper.getMacStatusLast(pushStatus);
	}
	/**
	 * 查询第一次推送的终端机
	 * @param message
	 * @return
	 */
	public String getMacStatusLast2(PushStatus pushStatus) {
		return membershipMapper.getMacStatusLast2(pushStatus);
	}
	
	/**
	 * 查询终端机状态是不是都为0的是就是第一次推送
	 * @param message
	 * @return
	 */
	public String getMacStatus0(PushStatus pushStatus) {
		return membershipMapper.getMacStatus0(pushStatus);
	}
	
	/**
	 * 查询可以推送的终端机
	 * @param message
	 * @return
	 */
	public String getMacStatus(PushStatus pushStatus) {
		return membershipMapper.getMacStatus(pushStatus);
	}
	
	/**
	 * 查询可以推送的终端机
	 * @param message
	 * @return
	 */
	public String getMacStatus2(PushStatus pushStatus) {
		return membershipMapper.getMacStatus2(pushStatus);
	}
	
	
	
	/**
	 * 查询有没有终端机
	 * @param message
	 * @return
	 */
	public String getTestInfo(Test test) {
		return membershipMapper.getTestInfo(test);
	}
		
	/**
	 * 牛逼
	 * @param message
	 * @return
	 */
	public int saveTest(Test test) {
		return membershipMapper.saveTest(test);
	}
	
	/**
	 * 查询未收到文件的终端机
	 * @param message
	 * @return
	 */
	public String getFolderMachineIds(File file) {
		return membershipMapper.getFolderMachineIds(file);
	}
	
	/**
	 * 查询没收到的文件
	 * @param message
	 * @return
	 */
	public List<File> selectThatStatus(File file) {
		return membershipMapper.selectThatStatus(file);
	}
	
	/**
	 * 收到再一次修改状态
	 * @param message
	 * @return
	 */
	public int updateThatStatus(File file) {
		return membershipMapper.updateThatStatus(file);
	}
	
	/**
	 * 修改推送给终端机收到修改状态
	 * @param message
	 * @return
	 */
	public int terminalMachineUploadStatus(File file) {
		return membershipMapper.terminalMachineUploadStatus(file);
	}
	
	/**
	 * 修改推送给终端机收到修改状态
	 * @param message
	 * @return
	 */
	public int terminalMachineUploadStatus2(File file) {
		return membershipMapper.terminalMachineUploadStatus2(file);
	}
	
	/**
	 * 查询文件夹下的文件是否已全部推送
	 * @param message
	 * @return
	 */
	public List<File> getFolderFilePushIs(ParamInfo pInfo) {
		return membershipMapper.getFolderFilePushIs(pInfo);
	}
	
	/**
	 * 推送成功修改文件推送状态
	 * @param message
	 * @return
	 */
	public int updateFilepushIs(ParamInfo pInfo) {
		return membershipMapper.updateFilepushIs(pInfo);
	}
	
	/**
	 * 判断页面新增了文件还是删除了文件
	 * @param message
	 * @return
	 */
	public int getFolderCount(File file) {
		return membershipMapper.getFolderCount(file);
	}
	
	/**
	 * 创建文件夹的时候获取该文件夹所有的终端机
	 * @param message
	 * @return
	 */
	public List<String> getFolderMachines(String str) {
		return membershipMapper.getFolderMachines(str);
	}
	
	/**
	 * 创建文件夹的时候获取该文件夹所有的终端机
	 * @param message
	 * @return
	 */
	public List<String> getFolderMachines3(String str) {
		return membershipMapper.getFolderMachines(str);
	}
	
	/**
	 * 创建文件夹的时候获取该文件夹所有的终端机
	 * @param message
	 * @return
	 */
	public List<String> getFolderMachines2(String str) {
		return membershipMapper.getFolderMachines2(str);
	}
	
	/**
	 * 查询已停播的文件夹
	 * @param message
	 * @return
	 */
	public Folder getFolderStatus(Machine machine) {
		return membershipMapper.getFolderStatus(machine);
	}
	
	/**
	 * 终端机收到文件夹修改状态
	 * @param message
	 * @return
	 */
	public int updateFolderReceived(String id) {
		return membershipMapper.updateFolderReceived(id);
	}
	
	/**
	 * 查询要推送的文件夹数据
	 * @param message
	 * @return
	 */
	public Folder getFolderInfos(ParamInfo pInfo) {
		return membershipMapper.getFolderInfos(pInfo);
	}
	/**
	 * 查询要推送的文件夹数据
	 * @param message
	 * @return
	 */
	public Folder getFolderInfos2(ParamInfo pInfo) {
		return membershipMapper.getFolderInfos2(pInfo);
	}
	
	/**
	 * 查询设置终端机名称2
	 * @param message
	 * @return
	 */
	public String[] getMachineNameInfo2(Machine machine) {
		return membershipMapper.getMachineNameInfo2(machine);
	}
	
	/**
	 * 查询设置终端机名称
	 * @param message
	 * @return
	 */
	public String getMachineNameInfo(Machine machine) {
		return membershipMapper.getMachineNameInfo(machine);
	}
	
	/**
	 * 查询终端机id是否存在
	 * @param message
	 * @return
	 */
	public String getMachineInfo(Machine machine) {
		return membershipMapper.getMachineInfo(machine);
	}
	
	/**
	 * 根据门店id查询终端机名
	 * @param message
	 * @return
	 */
	public List<Machine> getMachineMachineNameByStoresId(Machine machine) {
		return membershipMapper.getMachineMachineNameByStoresId(machine);
	}
	
	/**
	 * 根据id查询终端机的信息
	 * @param message
	 * @return
	 */
	public Machine getMachineById(Machine machine) {
		return membershipMapper.getMachineById(machine);
	}
	
	/**
	 * 添加门店下面终端机的信息 
	 * @param message
	 * @return
	 */
	public int saveMachine(Machine machine) {
		return membershipMapper.saveMachine(machine);
	}
	
	/**
	 * 批量添加会员信息
	 * @param message
	 * @return
	 */
	public int saveMemberShip(List<MemberShip> message){
		return membershipMapper.saveMemberShip(message);
	}
	
	/**
	 * 批量添加门店信息
	 * @param message
	 * @return
	 */
	public int saveStores(Role role) {
		return membershipMapper.saveStores(role);
	}
	
	/**
	 * 添加非会员数据
	 * @param message
	 * @return
	 */
	public int saveNonMember(NonMember member) {
		return membershipMapper.saveNonMember(member);
	}
	
	/**
	 * 查询会员是否存在
	 * @param message
	 * @return
	 */
	public String getUserIdInfo(MemberShip message) {
		return membershipMapper.getUserIdInfo(message);
	}
	
	/**
	 * 添加入场人数统计会员信息
	 * @param message
	 * @return
	 */
	public int saveAdmission(Admission admission) {
		return membershipMapper.saveAdmission(admission);
	}
	
	/**
	 * 添加非会员图像库
	 * @param message
	 * @return
	 */
	public int saveNomemberImageLibrary(NomemberImageLibrary nomemberImageLibrary) {
		return membershipMapper.saveNomemberImageLibrary(nomemberImageLibrary);
	}
	
	/**
	 * 添加会员图像库
	 * @param message
	 * @return
	 */
	public int saveManagementPic(ManagementPic managementPic) {
		return membershipMapper.saveManagementPic(managementPic);
	}
	
	/**
	 * 查询会员图像个数
	 * @param message
	 * @return
	 */
	public int getManagementPicCount(ManagementPic managementPic) {
		return membershipMapper.getManagementPicCount(managementPic);
	}
	
}
