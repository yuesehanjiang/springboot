package com.yun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yun.pojo.MachineManagement;
import com.yun.utils.ParameterUtil;

@Mapper
public interface MachineManagementMapper {

	List<ParameterUtil> listMachineManagement(ParameterUtil parameter);	// 查询终端机管理
	
	int getMachineManagementCount(String value);	// 查询总数
	
	int saveMachineManagement(MachineManagement management);	// 新增终端机
	
}
