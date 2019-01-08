package com.yun.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yun.constant.ResponseMessage;
import com.yun.mapper.MachineManagementMapper;
import com.yun.pojo.MachineManagement;
import com.yun.service.MachineManagementService;
import com.yun.utils.ParameterUtil;
import com.yun.utils.ServerResponse;
import com.yun.utils.StringUtils;

@Service
@Transactional
public class MachineManagementServiceImpl implements MachineManagementService {

	@Autowired
	private MachineManagementMapper machineManagementMapper;
	
	/**
	 * 查询终端机管理
	 */
	@Override
	public HashMap<String, Object> listMachineManagement(ParameterUtil parameter) {
		if(StringUtils.isHasEmpty(parameter.getPageNum(), parameter.getPageSize(), parameter.getGymAddr())){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		HashMap<String, Object> calculationPage = StringUtils.calculationPage(parameter.getPageNum(), parameter.getPageSize());
		parameter.setPageNum(calculationPage.get("pageNum")+"");
		parameter.setPageSize(calculationPage.get("pageSize")+"");
		List<ParameterUtil> listMachineManagement = machineManagementMapper.listMachineManagement(parameter);
		if(listMachineManagement.size() < 1){
			return ServerResponse.build("204", ResponseMessage.isNull);
		}
		int machineManagementCount = machineManagementMapper.getMachineManagementCount(parameter.getGymAddr());
		return ServerResponse.build("200", ResponseMessage.success, listMachineManagement, machineManagementCount+"", calculationPage.get("pNum")+"", parameter.getPageSize());
	}

	/**
	 * 新增终端机
	 */
	@Override
	public HashMap<String, Object> saveMachineManagement(
			MachineManagement management) {
		if(StringUtils.isHasEmpty(management.getMachineId(), management.getMemoryCap(), 
				management.getProductionTime(), management.getVersion())){
			return ServerResponse.build("202", ResponseMessage.paramIsNull);
		}
		int saveMachineManagement = machineManagementMapper.saveMachineManagement(management);
		if(saveMachineManagement > 0){
			return ServerResponse.build("200", ResponseMessage.success);
		} else {
			return ServerResponse.build("203", ResponseMessage.exception);
		}
		
	}

}
