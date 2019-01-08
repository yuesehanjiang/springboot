package com.alibaba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dao.PushStatusMapper;
import com.alibaba.pojo.PushStatus;

@Service
public class PushStatusService {

	@Autowired
	private PushStatusMapper pushStatusMapper;
	
	/**
	 * 如果终端机修改了，先删除之前某个文件夹的id
	 * @param a
	 * @return
	 */
	public int deletePushStatus(PushStatus a){
		return pushStatusMapper.deletePushStatus(a);
	}
	
	/**
	 * 添加文件设备信息
	 * @param a
	 * @return
	 */
	public int savePushStatus(PushStatus a){
		return pushStatusMapper.savePushStatus(a);
	}
	
	/**
	 * 修改终端机状态
	 * @param a
	 * @return
	 */
	public int updatePushStatus(PushStatus a){
		return pushStatusMapper.updatePushStatus(a);
	}
	
	/**
	 * 修改终端机状态
	 * @param a
	 * @return
	 */
	public int updatePushStatus0(PushStatus a){
		return pushStatusMapper.updatePushStatus0(a);
	}
	
}
