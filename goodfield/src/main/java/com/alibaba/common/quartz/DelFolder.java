package com.alibaba.common.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.alibaba.service.isservice.FileUploadService;

public class DelFolder extends QuartzJobBean{
	
	@Autowired
	FileUploadService  fileUploadService;
	
	
	//删除过期半个月的oos 数据           delexpireDateFolder  pushstatus 表中数据status为1 的也删除
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		
		    System.err.println("我 五秒中执行  一次");
		     fileUploadService.delexpireDateFolder(); 
		
	}

}
