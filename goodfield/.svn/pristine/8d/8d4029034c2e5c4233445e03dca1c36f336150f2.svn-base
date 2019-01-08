package com.alibaba.dao;

import org.springframework.stereotype.Repository;

import com.alibaba.pojo.PushStatus;

@Repository
public interface PushStatusMapper {

	int deletePushStatus(PushStatus a);	// 如果终端机修改了，先删除之前某个文件夹的id
	
	int savePushStatus(PushStatus a);	// 添加文件设备信息
	
	int updatePushStatus(PushStatus a);		// 修改终端机状态
	
	int updatePushStatus0(PushStatus a);		// 修改终端机状态
	
}
