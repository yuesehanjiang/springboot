package com.yun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yun.pojo.GuidePeople;
import com.yun.utils.ParameterUtil;

@Mapper
public interface GuidePeopleMapper {

	List<GuidePeople> listGuidePeople(ParameterUtil parameterUtil);	// 查询指引人
	List<GuidePeople> listGuidePeopleTerminal(GuidePeople guidePeople);	// 提供终端机的指引人
	
	int deleteGuidePeopleById(ParameterUtil parameterUtil);		// 删除指引人
	
	int saveGuidePeople(GuidePeople guidePeople);	// 新增指引人
	
	GuidePeople getGuidePeopleById(ParameterUtil parameterUtil);	// 查询要修改的指引人信息
	
	String getCodeisNull(GuidePeople guidePeople);	// 查询编号是否存在
	String getCodeisNull2(GuidePeople guidePeople);	// 查询编号是否存在
	
	int updateGuidePeopleById(GuidePeople guidePeople);	// 修改指引人信息
	
}
