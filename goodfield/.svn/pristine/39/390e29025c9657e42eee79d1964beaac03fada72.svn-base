package com.alibaba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dao.AndroidMapper;
import com.alibaba.pojo.MemberInfo;
import com.alibaba.util.ParamInfo;

@Service
public class AndroidService {

	@Autowired
	private AndroidMapper androidMapper;

	/**
	 * 查询门店名称
	 * @param pInfo
	 * @return
	 */
	public String storesName(ParamInfo pInfo){
		return androidMapper.storesName(pInfo);
	}
	
	/**
	 * 查询门店总人数
	 * @param pInfo
	 * @return
	 */
	public int getAdnroidCount(ParamInfo pInfo){
		return androidMapper.getAdnroidCount(pInfo);
	}
	
	/**
	 * 查询是哪一家门店的信息
	 * @param pInfo
	 * @return
	 */
	public String getStoresName(ParamInfo pInfo){
		return androidMapper.getStoresName(pInfo);
	}
	
	/**
	 * 查询安卓需要统计的数据
	 * @param pInfo
	 * @return
	 */
	public ParamInfo getAdnroidStores(ParamInfo pInfo){
		return androidMapper.getAdnroidStores(pInfo);
	}
	
	/**
	 * 询每一天要统计的数据
	 * @param pInfo
	 * @return
	 */
	public MemberInfo getdateTimeStores(ParamInfo pInfo){
		return androidMapper.getdateTimeStores(pInfo);
	}
	
}
