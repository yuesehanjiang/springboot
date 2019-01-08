package com.alibaba.pojo;

import java.io.Serializable;

/**
 * 会员管理
 * 
 * @author mayn
 *
 */
public class MemberManagement implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id; // 主键
	private String stores_id;
	private String user_id;
	private String user_name;
	private String user_cards;
	private String user_pic;
	private String user_type;
	private String member_type;
	private String stores_name;
	
	public String getStores_name() {
		return stores_name;
	}

	public void setStores_name(String stores_name) {
		this.stores_name = stores_name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStores_id() {
		return stores_id;
	}

	public void setStores_id(String stores_id) {
		this.stores_id = stores_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_cards() {
		return user_cards;
	}

	public void setUser_cards(String user_cards) {
		this.user_cards = user_cards;
	}

	public String getUser_pic() {
		return user_pic;
	}

	public void setUser_pic(String user_pic) {
		this.user_pic = user_pic;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getMember_type() {
		return member_type;
	}

	public void setMember_type(String member_type) {
		this.member_type = member_type;
	}

}
