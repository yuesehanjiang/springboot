package com.alibaba.common.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.alibaba.common.jpush.JiGunag;
import com.alibaba.service.AdminService;

public class MyJob extends QuartzJobBean{
	
	@Autowired
	private AdminService adminService;
	
    /**
     * 定时推送消息
     */
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		adminService.updateMachineStatusoffLine();	// 把全部终端机修改为离线状态
		JiGunag.pushMessage3();
	}
	
}
