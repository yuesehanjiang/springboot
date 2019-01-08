package com.alibaba.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.alibaba.pojo.Role;
import com.alibaba.util.ParamInfo;

@Repository
public interface EntranceReportMapper {

	List<ParamInfo> getEntranceReportInfo(ParamInfo pInfo);	// 查询入场报告
	
	List<Role> getUserRoleRelationInfo(ParamInfo pInfo);	// 查询入场报告门店列表信息
	
	List<ParamInfo> getEntranceReportNonMemberInfo(ParamInfo pInfo);	// 筛选非会员数据
	
	ParamInfo getEntranceReportNonMemberDetails(ParamInfo paramInfo);	// 筛选非会员详情数据
	
	int getCount(ParamInfo pInfo);	// 查询总数
	
	int getCount2(ParamInfo pInfo);	// 查询总数
	
	int getCount3(ParamInfo pInfo);	// 查询总数
	
	List<ParamInfo> getgetEntranceReportDetails(ParamInfo pInfo);	// 查询入场次数详情
	
}
