package com.alibaba.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.alibaba.pojo.Greetings;
import com.alibaba.pojo.MemberShip;
import com.alibaba.pojo.Role;
import com.alibaba.util.ParamInfo;

@Repository
public interface GreetingsMapper {

	String getGreetingsName(String id);	// 查询问候语
	
	String getRoleIds(String id);	// 获取通用问候语绑定的门店	
	
	List<String> getStoresIdByMachine(String stores_id);	// 根据门店id查询终端机id
	
	String getMemberByIdStoresId(String user_id);	// 根据会员id查询门店id
	
	
	String getGreetingsInfos(Greetings greetings);	// 查询某个问候语所有的门店信息
	
	void setGroupConcatMaxLen();	// 设置group_concat存储大小
	
	MemberShip getClusiveGreetingsInfoById(ParamInfo pInfo);	// 查询要修改的专属问候语
	
	int getCountGreetingsInfo(ParamInfo pInfo);	// 查询总数
	
	List<Greetings> getGreetingsInfoList(ParamInfo pInfo); 	// 查询所有问候语
	
	List<Role> getUserRoleRelationInfo(ParamInfo pInfo);	// 查询问候语门店列表信息
	
	List<Greetings> getGreetingRoleName(ParamInfo pInfo);  // 查询门店名称是否已存在
	
	int addGreetingsInfo(ParamInfo pInfo);		// 添加问候语信息
	
	Greetings getGreetingsInfo(Greetings greetings);	// 查询要修改的问候语
	Greetings getGreetingsInfo2(Greetings greetings);	// 查询要修改的问候语
	
	String getOtherRoleId(ParamInfo greetings);		// 超级逻辑判断
	
	Greetings getOtherRoleIdAll(ParamInfo greetings);		// 超级逻辑判断2
	
	int updateGreetingsInfo(ParamInfo pInfo);	// 修改问候语
	
	int deleteGreetingsInfo(Greetings greetings);	// 删除问候语
	
	List<MemberShip> getClusiveGreetingsInfo(ParamInfo pInfo);	// 查询专属问候语
	
	int getCount(ParamInfo pInfo);	// 查询总数
	
	int delClusiveGreetingsInfoById(MemberShip clusiveGreetings);		// 删除专属问候语
	
	int addClusiveGreetingsInfoById(MemberShip clusiveGreetings);		// 添加专属问候语

}
