package com.alibaba.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.alibaba.pojo.Role;
import com.alibaba.util.ParamInfo;

@Repository
public interface AdmissionMapper {

	List<String> getSemberSize(ParamInfo paramInfo);   // 查询入场次数
	
	ParamInfo getIdentityInfo(ParamInfo paramInfo);   // 查询入场人数统计
	
	List<Role> getUserRoleRelationInfo(ParamInfo paramInfo);	// 查询入场人数统计门店列表信息
	
	List<ParamInfo> getIdentityInfoDetails(ParamInfo list); // 查询入场人数统计详情
	
	List<ParamInfo> getnonMember(ParamInfo list);	// 查询非会员信息
	
	int getCount2(ParamInfo list); // 查询总数
	
	int getCount(ParamInfo list); // 查询总数
	
}
