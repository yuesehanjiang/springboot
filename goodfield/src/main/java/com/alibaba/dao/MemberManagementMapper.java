package com.alibaba.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.alibaba.pojo.ManagementPic;
import com.alibaba.pojo.MemberManagement;
import com.alibaba.pojo.Role;
import com.alibaba.util.ParamInfo;

@Repository
public interface MemberManagementMapper {
	
	String getUserPic(ParamInfo pInfo); // 查询总数
	
	int getCount(ParamInfo pInfo); // 查询总数
	
	int getCount2(ParamInfo pInfo); // 查询总数
	
	List<Role> getUserRoleRelationInfo(ParamInfo pInfo);	// 查询会员门店列表信息
	
	List<MemberManagement> getMemberManagementInfo(ParamInfo pInfo);	// 查询会员信息
	
	List<ManagementPic> getMemberManagementPicInfo(ParamInfo pInfo);	// 查询会员图像库

}
