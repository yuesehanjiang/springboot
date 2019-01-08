package com.yun.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yun.constant.ResponseMessage;
import com.yun.mapper.GuidePeopleMapper;
import com.yun.pojo.GuidePeople;
import com.yun.service.GuidePeopleService;
import com.yun.utils.ParameterUtil;
import com.yun.utils.ServerResponse;
import com.yun.utils.StringUtils;

@Service
@Transactional
public class GuidePeopleServiceImpl implements GuidePeopleService {

	@Autowired
	private GuidePeopleMapper guidePeopleMapper;

	/**
	 * 查询指引人
	 */
	@Override
	public HashMap<String, Object> listGuidePeople(ParameterUtil parameterUtil) {
		if (StringUtils.isHasEmpty(parameterUtil.getGymAddr())) {
            return ServerResponse.build("202", ResponseMessage.paramIsNull); 
        }
		List<GuidePeople> listGuidePeople = guidePeopleMapper.listGuidePeople(parameterUtil);
		if(listGuidePeople.size() < 1){
			return ServerResponse.build("201", "暂无数据");
		}
		return ServerResponse.build("200", "查询成功!", listGuidePeople);
	}

	/**
	 * 删除指引人
	 */
	@Override
	public HashMap<String, Object> deleteGuidePeopleById(
			ParameterUtil parameterUtil) {
		if (StringUtils.isHasEmpty(parameterUtil.getGymAddr(), parameterUtil.getId())) {
			return ServerResponse.build("202", ResponseMessage.paramIsNull); 
		}
		int deleteGuidePeopleById = guidePeopleMapper.deleteGuidePeopleById(parameterUtil);
		if(deleteGuidePeopleById > 0){
			return ServerResponse.build("200", "删除成功!");
		} else {
			return ServerResponse.build("205", "删除失败!");
		}
	}

	/**
	 * 新增指引人
	 */
	@Override
	public HashMap<String, Object> saveGuidePeople(GuidePeople guidePeople) {
		if (StringUtils.isHasEmpty(guidePeople.getGymAddr(), guidePeople.getCode(), guidePeople.getName(), guidePeople.getSex())) {
			return ServerResponse.build("202", ResponseMessage.paramIsNull); 
		}
		String codeisNull = guidePeopleMapper.getCodeisNull(guidePeople);
		if(codeisNull != null){
			return ServerResponse.build("205", "编号已存在!");
		}
		int saveGuidePeople = guidePeopleMapper.saveGuidePeople(guidePeople);
		if(saveGuidePeople > 0){
			return ServerResponse.build("200", "新增成功!");
		} else {
			return ServerResponse.build("205", "新增失败!");
		}
	}

	/**
	 * 查询要修改的指引人信息
	 */
	@Override
	public HashMap<String, Object> getGuidePeopleById(
			ParameterUtil parameterUtil) {
		if (StringUtils.isHasEmpty(parameterUtil.getGymAddr(), parameterUtil.getId())) {
			return ServerResponse.build("202", ResponseMessage.paramIsNull); 
		}
		GuidePeople guidePeopleById = guidePeopleMapper.getGuidePeopleById(parameterUtil);
		if(guidePeopleById != null){
			return ServerResponse.build("200", "查询成功!", guidePeopleById);
		} else {
			return ServerResponse.build("201", "暂无数据!");
		}
	}

	/**
	 * 修改指引人信息
	 */
	@Override
	public HashMap<String, Object> updateGuidePeopleById(GuidePeople guidePeople) {
		if (StringUtils.isHasEmpty(guidePeople.getGymAddr(), guidePeople.getCode(), guidePeople.getName(), guidePeople.getSex())) {
			return ServerResponse.build("202", ResponseMessage.paramIsNull); 
		}
		String codeisNull = guidePeopleMapper.getCodeisNull2(guidePeople);
		if(codeisNull != null){
			return ServerResponse.build("205", "编号已存在!");
		}
		guidePeopleMapper.updateGuidePeopleById(guidePeople);
		return ServerResponse.build("200", "修改成功!");
	}

	/**
	 * 提供终端机的指引人
	 */
	@Override
	public HashMap<String, Object> listGuidePeopleTerminal(GuidePeople guidePeople) {
		if (StringUtils.isHasEmpty(guidePeople.getGymAddr())) {
			return ServerResponse.build("202", ResponseMessage.paramIsNull); 
		}
		List<GuidePeople> guidePeopleById = guidePeopleMapper.listGuidePeopleTerminal(guidePeople);
		if(guidePeopleById.size() < 1){
			return ServerResponse.build("201", "暂无数据!");
		}
		return ServerResponse.build("200", "查询成功!", guidePeopleById);
	}

}
