package com.alibaba.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

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

@Repository
public interface InterfaceInfoMapper {

	List<Role> machineInit();
	
	String getId(String val);
	
	List<Machine> getMemorys();	// 查询终端机内存大小
	
	String getMemory(String value);	// 查询终端机内存
	int updateMachineMemory(Machine machine);	// 修改终端机内存
	
	List<MemberShip> getExclusives(String stores_id);	// 
	String getStoresId(String machine_id);	// 查询门店id
	String getStoresIdByName(String stores_id);	// 根据门店id查询欢迎词
	
	
	
	int deleteNomemberImageLibrary();
	
	
	
	/* 问候语推送 */
	Greetings getNonmemberNamePush(NonMember nonMember);	// 查询推送的问候语
	
	Greetings getNamePush(Admission nonMember);	// 查询会员的问候语
	
	String getExclusive(Admission nonMember);	// 会员优先推的问候语
	
	String getMemberName(Admission nonMember);	// 查询会员名称

	
	
	int updateMembershipPic(MemberShip memberShip);	// 修改会员头像
	int updateMembership(MemberShip memberShip);	// 修改会员未曾会员
	
	
	
	/* 终端机的操作和推送 start */
	int savePush(Push push);	// 添加解绑的终端机
	
	int updatePushStatus(Push push);	// 添加解绑的终端机
	
	String delPush(Push push);	// 判断解绑的终端机是否符合删除条件
	
	int delPushInfos(Push push);	// 删除解绑的终端机
	
	
	
	
	
	
	
	
	/* 终端机的操作和推送 end */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/* 三屏推送 */
	
	List<String> getdeviceIdNull(PushStatus folder);	// 如果没数据就不推送
	
	List<String> getIsDeleteInfo(PushStatus folder);	// 查询设备表里的删除状态是否符合删除
	
	int updateIsDetete(PushStatus folder);	// 修改删除状态
	
	String getMacFolderInfos(Folder folder);	// 查询文件夹下的所有终端机
	
	int updateMacStatus0(PushStatus pushStatus);	// 将数据库中某个文件夹id的终端机重置为离线
	
	String getMacStatusInfos0(PushStatus pushStatus);	// 查询终端机是不是符合清0的条件
	
	String getMacStatusLast(PushStatus pushStatus);	// 查询第一次推送的终端机
	String getMacStatusLast2(PushStatus pushStatus);	// 查询第一次推送的终端机
	
	String getMacStatus(PushStatus pushStatus);	// 查询可以推送的终端机
	String getMacStatus2(PushStatus pushStatus);	// 查询可以推送的终端机
	
	String getMacStatus0(PushStatus pushStatus);	// 查询终端机状态是不是都为0的是就是第一次推送
	
	
	
	
	
	
	
	
	
	
	
	
	String getTestInfo(Test test);	// 查询有没有终端机
	
	int saveTest(Test test);	// 牛逼
	
	String getFolderMachineIds(File file);	// 查询没收到的文件
	
	List<File> selectThatStatus(File file);	// 查询没收到的文件
	
	int updateThatStatus(File file);	// 收到再一次修改状态

	int terminalMachineUploadStatus(File file);	// 修改推送给终端机收到修改状态
	int terminalMachineUploadStatus2(File file);	// 修改推送给终端机收到修改状态
	
	List<File> getFolderFilePushIs(ParamInfo pInfo);	// 查询文件夹下的文件是否已全部推送
	
	int updateFilepushIs(ParamInfo pInfo);	// 推送成功修改文件推送状态

	int getFolderCount(File file);	// 判断页面新增了文件还是删除了文件
	
	List<String> getFolderMachines(String str);	// 创建文件夹的时候获取该文件夹所有的终端机
	List<String> getFolderMachines2(String str);	// 创建文件夹的时候获取该文件夹所有的终端机
	List<String> getFolderMachines3(String str);	// 创建文件夹的时候获取该文件夹所有的终端机
	
	Folder getFolderStatus(Machine machine);	// 查询已停播的文件夹
	
	int updateFolderReceived(String id);	// 终端机收到文件夹修改状态
	
	Folder getFolderInfos(ParamInfo pInfo);	// 查询要推送的文件夹数据
	Folder getFolderInfos2(ParamInfo pInfo);	// 查询要推送的文件夹数据
	
	String[] getMachineNameInfo2(Machine machine); // 查询设置终端机名称2
	
	String getMachineNameInfo(Machine machine);	// 查询设置终端机名称
	
	String getMachineInfo(Machine machine);	// 查询终端机id是否存在
	
	List<Machine> getMachineMachineNameByStoresId(Machine machine);		// 根据门店id查询终端机名
	
	Machine getMachineById(Machine machine);	// 根据id查询终端机的信息
	
	int saveMachine(Machine machine);	// 添加门店下面终端机的信息
	
	int deleteMembership(String user_id);	// 删除之前的会员信息，添加最新的
	
	int saveMemberShip(List<MemberShip> message);	// 批量添加会员信息
	int saveMemberShip2(MemberShip message);	// 添加会员信息
	
	int saveStores(Role role);	// 批量添加门店信息
	
	int saveNonMember(NonMember member);	// 添加非会员数据
	String getUserIdInfo(MemberShip message);	// 查询会员是否存在
	
	int saveAdmission(Admission admission);	// 添加入场人数统计会员信息
	
	int saveNomemberImageLibrary(NomemberImageLibrary nomemberImageLibrary);	// 添加非会员图像库
	
	int saveManagementPic(ManagementPic managementPic);	// 添加会员图像库
	
	int getManagementPicCount(ManagementPic managementPic);	// 查询会员图像个数
	
	int saveBinding(Binding binding);	// 添加绑定的uuid
	
	int saveAdmissionBind(Binding binding);	// 添加绑定的uuid入场
	
	String getAdmissionUuid(String user_id);	// 入场的时候查询uuid
	
	int deleteAdmissionUuid(String user_id);	// 入场的时候查询uuid
	
	int getBindingCount();
	
	void truncateBinding();
	
	List<String> deleteChao();
	
	List<String> getManagementPicUuid();
	
	int deleteManagementPicUuid(String uuid);
	
	String getAdmissionBindUuid(String user_id);	// 查询入场的uuid
		
	int deleteAdmissionBindUuid(String user_id);	// 删除询入场的uuid
	
	void truncateAdmissionBind();	
	
	String getPhotoTime(Binding binding);	// 查询拍照时间
	String getUserInTime(Binding binding);	// 查询入场时间
	int deleteManagementPicInfo(Binding binding);	// 删除拍照数据
	
	
}
