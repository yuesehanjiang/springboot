package com.alibaba.util;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.pojo.MemberShip;
import com.alibaba.pojo.Role;

public class MemberShipList {

	private List<MemberShip> lists = new ArrayList<MemberShip>();
	
	private List<Role> roles = new ArrayList<Role>();

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<MemberShip> getLists() {
		return lists;
	}

	public void setLists(List<MemberShip> lists) {
		this.lists = lists;
	}
	
}
