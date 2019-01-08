package com.alibaba.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface CountMapper {

	Integer selectAllcount(@Param("machineId")String machineId);  //查找公司的 所有数据
      //查询15天以内 的会员和非会员 信息
	 List<Map> selectMebers(@Param("status")Integer status, @Param("machineId")String machineId);
	
	//查询非 会员 信息
	 List<Map> slectNonMember(@Param("machineId")String machineId);
	 
	 
	 
	String selectGreetings(@Param("status")int status,@Param("stores_id") String stores_id);//查找问候语
	String selectGreetingsnon(@Param("status")int status,@Param("stores_id") String stores_id);  //非会员问候与

	
	//终端机是不是 有用户关联
	List<Map> selectMaps(@Param("account")String account, @Param("machine_id")String machine_id);

}

