package com.alibaba.service.serviceiml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dao.InterfaceInfoMapper;
import com.alibaba.pojo.MemberShip;
import com.alibaba.service.InterfaceInfoService;
import com.alibaba.service.isservice.InterfaceService;

@Service
public class InterfaceServiceImpl implements InterfaceService {

	@Autowired
	private InterfaceInfoMapper infoMapper;
	@Autowired
	private InterfaceInfoService membershipService;
	
	@Override
	public int saveMemberShip(List<MemberShip> memberShip, String content) {
		membershipService.deleteMembership(content);
		membershipService.saveMemberShip(memberShip);
		return 1;
	}

}
