package com.alibaba.common.quartz;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.alibaba.pojo.PushStatus;
import com.alibaba.service.isservice.FileUploadService;
import com.alibaba.service.isservice.JiGuangService;

public class Push extends QuartzJobBean{
	
	@Autowired
	FileUploadService  fileUploadService;
	
	@Autowired
	JiGuangService  jiGuangService;
	
    /**
     * 定时推送文件夹
     */
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		    List<PushStatus> ops =
		    		jiGuangService.selectPushStatusByfloderId(null);
	
		     for (int i = 0; i < ops.size(); i++) {
				   String status = ops.get(i).getStatus();
				   String  floderId=ops.get(i).getFolder_id();
				   if(null !=status  || "0".equalsIgnoreCase(status)) {
					   try {
						jiGuangService.pushFile(floderId);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					   
				   }
				   
				   
			}
		
	}
}
