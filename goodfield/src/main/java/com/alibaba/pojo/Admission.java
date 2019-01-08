package com.alibaba.pojo;

import java.io.Serializable;

public class Admission implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;			// 主键
	private String stores_id;	// 门店id
	private String user_id;		// 会员id
	private String user_card;	// 会员卡号
	private String user_in_time;// 入场时间
	private String user_type;	// 会员类型 0：单店 1：通店
	private String appoint;		// 是否约课 0：否  1：是
	private String valid_member; // 是否有效会员 0：否  1：是
	private String machine_id;
	private String uuid;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMachine_id() {
		return machine_id;
	}

	public void setMachine_id(String machine_id) {
		this.machine_id = machine_id;
	}

	private  String  machineId;
	
	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
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

	public String getUser_card() {
		return user_card;
	}

	public void setUser_card(String user_card) {
		this.user_card = user_card;
	}

	public String getUser_in_time() {
		return user_in_time;
	}

	public void setUser_in_time(String user_in_time) {
		this.user_in_time = user_in_time;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getAppoint() {
		return appoint;
	}

	public void setAppoint(String appoint) {
		this.appoint = appoint;
	}

	public String getValid_member() {
		return valid_member;
	}

	public void setValid_member(String valid_member) {
		this.valid_member = valid_member;
	}

	@Override
	public String toString() {
		return "Admission [id=" + id + ", stores_id=" + stores_id + ", user_id=" + user_id + ", user_card=" + user_card
				+ ", user_in_time=" + user_in_time + ", user_type=" + user_type + ", appoint=" + appoint
				+ ", valid_member=" + valid_member + ", machineId=" + machineId + "]";
	}




}
