package com.alibaba.pojo;

import java.io.Serializable;

public class NonMember implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;			// 主键
	private String stores_id;	// 门店id
	private String user_name;	// 顾客名
	private String user_phone;	// 顾客手机
	private String user_in_time;// 入场时间
	private String machine_id;
	
	public String getMachine_id() {
		return machine_id;
	}

	public void setMachine_id(String machine_id) {
		this.machine_id = machine_id;
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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_in_time() {
		return user_in_time.toString();
	}

	public void setUser_in_time(String user_in_time) {
		this.user_in_time = user_in_time;
	}

	@Override
	public String toString() {
		return "NonMember [id=" + id + ", stores_id=" + stores_id
				+ ", user_name=" + user_name + ", user_phone=" + user_phone
				+ ", user_in_time=" + user_in_time + "]";
	}
	
}
