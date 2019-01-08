package com.alibaba.service.isservice;

import java.util.List;

import com.alibaba.pojo.MemberShip;

public interface InterfaceService {

	int saveMemberShip(List<MemberShip> message, String content);	// 批量添加会员信息
	
}
