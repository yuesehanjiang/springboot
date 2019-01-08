package com.alibaba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dao.AdmissionMapper;
import com.alibaba.pojo.Role;
import com.alibaba.util.ParamInfo;

@Service
public class AdmissionService {

	@Autowired
	private AdmissionMapper admissionMapper;
	
	/**
	 * 查询入场次数
	 * @return
	 */
	public List<String> getSemberSize(ParamInfo paramInfo){
		return admissionMapper.getSemberSize(paramInfo);
	}
	
	/**
	 * 查询入场人数统计
	 * @return
	 */
	public ParamInfo getIdentityInfo(ParamInfo paramInfo){
		return admissionMapper.getIdentityInfo(paramInfo);
	}
	
	/**
	 * 查询入场人数统计门店列表信息
	 * @return
	 */
	public List<Role> getUserRoleRelationInfo(ParamInfo paramInfo){
		return admissionMapper.getUserRoleRelationInfo(paramInfo);
	}
	
	/**
	 * 查询入场人数统计详情
	 * @return
	 */
	public List<ParamInfo> getIdentityInfoDetails(ParamInfo paramInfo){
		return admissionMapper.getIdentityInfoDetails(paramInfo);
	}
	
	/**
	 * 查询非会员信息
	 * @return
	 */
	public List<ParamInfo> getnonMember(ParamInfo list){
		return admissionMapper.getnonMember(list);
	}
	
	/**
	 * 查询总数
	 * @return
	 */
	public int getCount(ParamInfo paramInfo){
		return admissionMapper.getCount(paramInfo);
	}
	
	/**
	 * 查询总数
	 * @return
	 */
	public int getCount2(ParamInfo paramInfo){
		return admissionMapper.getCount2(paramInfo);
	}
	
}
