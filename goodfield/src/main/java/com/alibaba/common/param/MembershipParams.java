package com.alibaba.common.param;

import java.util.ArrayList;
import java.util.List;

public class MembershipParams {

	private List<Membership> message = new ArrayList<Membership>();

	public List<Membership> getMessage() {
		return message;
	}

	public void setMessage(List<Membership> message) {
		this.message = message;
	}
	
}
