package com.alibaba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dao.EntranceReportStoreMapper;
import com.alibaba.pojo.Role;
import com.alibaba.util.ParamInfo;

@Service
public class EntranceReportStoreService {

	@Autowired
	private EntranceReportStoreMapper entranceReportStoreMapper;

	/**
	 * 查询入场报告-会员
	 * @param entranceReport
	 * @return
	 */
	public List<ParamInfo> getEntranceReportInfo(ParamInfo pInfo){
		return entranceReportStoreMapper.getEntranceReportInfo(pInfo);
	}
	
	/**
	 * 查询入场报告-筛选非会员数据
	 * @param entranceReport
	 * @return
	 */
	public List<ParamInfo> getEntranceReportNonMemberInfo(ParamInfo pInfo){
		return entranceReportStoreMapper.getEntranceReportNonMemberInfo(pInfo);
	}
	
	/**
	 * 查询入场报告-筛选非会员详情数据
	 * @param entranceReport
	 * @return
	 */
	public ParamInfo getEntranceReportNonMemberDetails(ParamInfo paramInfo) {
		return entranceReportStoreMapper.getEntranceReportNonMemberDetails(paramInfo);
	}
	
	/**
	 * 查询入场报告门店列表信息
	 * @param entranceReport
	 * @return
	 */
	public List<Role> getUserRoleRelationInfo(ParamInfo pInfo){
		return entranceReportStoreMapper.getUserRoleRelationInfo(pInfo);
	}
	
	/**
	 * 查询总数
	 * @param entranceReport
	 * @return
	 */
	public int getCount(ParamInfo pInfo){
		return entranceReportStoreMapper.getCount(pInfo);
	}
	
	/**
	 * 查询总数
	 * @param entranceReport
	 * @return
	 */
	public int getCount2(ParamInfo pInfo){
		return entranceReportStoreMapper.getCount2(pInfo);
	}
	
	/**
	 * 查询总数
	 * @param entranceReport
	 * @return
	 */
	public int getCount3(ParamInfo pInfo){
		return entranceReportStoreMapper.getCount3(pInfo);
	}
	
	/**
	 * 查询入场次数详情
	 * @param entranceReport
	 * @return
	 */
	public List<ParamInfo> getgetEntranceReportDetails(ParamInfo pInfo){
		return entranceReportStoreMapper.getgetEntranceReportDetails(pInfo);
	}
	
}
