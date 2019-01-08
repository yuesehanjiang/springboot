package com.alibaba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dao.MemberManagementMapper;
import com.alibaba.pojo.ManagementPic;
import com.alibaba.pojo.MemberManagement;
import com.alibaba.pojo.Role;
import com.alibaba.util.ParamInfo;

@Service
public class MemberManagementService {

	@Autowired
	private MemberManagementMapper managementMapper;
	
	/**
	 * 查询总数
	 * @param pInfo
	 * @return
	 */
	public String getUserPic(ParamInfo pInfo) {
		return managementMapper.getUserPic(pInfo);
	}
	
	/**
	 * 查询总数
	 * @param pInfo
	 * @return
	 */
	public int getCount(ParamInfo pInfo) {
		return managementMapper.getCount(pInfo);
	}
	
	/**
	 * 查询总数
	 * @param pInfo
	 * @return
	 */
	public int getCount2(ParamInfo pInfo) {
		return managementMapper.getCount2(pInfo);
	}
	
	/**
	 * 查询会员门店列表信息
	 * @param pInfo
	 * @return
	 */
	public List<Role> getUserRoleRelationInfo(ParamInfo pInfo){
		return managementMapper.getUserRoleRelationInfo(pInfo);
	}
	
	/**
	 * 查询会员信息
	 * @param pInfo
	 * @return
	 */
	public List<MemberManagement> getMemberManagementInfo(ParamInfo pInfo){
		return managementMapper.getMemberManagementInfo(pInfo);
	}
	
	/**
	 * 查询会员图像库
	 * @param pInfo
	 * @return
	 */
	public List<ManagementPic> getMemberManagementPicInfo(ParamInfo pInfo){
		return managementMapper.getMemberManagementPicInfo(pInfo);
	}
	
}
