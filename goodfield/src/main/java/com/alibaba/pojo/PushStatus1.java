package com.alibaba.pojo;

public class PushStatus1 {
	private String machine_is_delete;
	private String is_ting;

	@Override
	public String toString() {
		return "PushStatus1 [machine_is_delete=" + machine_is_delete
				+ ", is_ting=" + is_ting + "]";
	}

	public String getMachine_is_delete() {
		return machine_is_delete;
	}

	public void setMachine_is_delete(String machine_is_delete) {
		this.machine_is_delete = machine_is_delete;
	}

	public String getIs_ting() {
		return is_ting;
	}

	public void setIs_ting(String is_ting) {
		this.is_ting = is_ting;
	}

}
