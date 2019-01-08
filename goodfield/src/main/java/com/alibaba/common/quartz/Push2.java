package com.alibaba.common.quartz;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.alibaba.service.InterfaceInfoService;

public class Push2 extends QuartzJobBean {

	@Autowired
	private InterfaceInfoService membershipService;
	
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		List<String> managementPicUuid = membershipService.getManagementPicUuid();
		if(managementPicUuid.size() > 0){
			String content = "";
			for (String string : managementPicUuid) {
				content += "'"+string+"'"+",";
			}
			membershipService.deleteManagementPicUuid(content.substring(0, content.length()-1));
		}
		membershipService.truncateBinding();
		membershipService.truncateAdmissionBind();
		membershipService.deleteNomemberImageLibrary();
	}
	
}
