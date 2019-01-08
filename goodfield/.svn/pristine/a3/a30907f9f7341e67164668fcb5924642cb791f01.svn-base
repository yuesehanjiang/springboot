package com.alibaba.pojo;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class Machine implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String machine_id;
	private String stores_id;
	private String machine_name;
	private String file_path;
	private String screen;
	private String status;
	private String start_time;
	private String end_time;
	private String type;
	private String stores_name;
	private String frequency;
	private String stores_city;
	private String memory;
	
	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getStores_city() {
		return stores_city;
	}

	public void setStores_city(String stores_city) {
		this.stores_city = stores_city;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStores_name() {
		return stores_name;
	}

	public void setStores_name(String stores_name) {
		this.stores_name = stores_name;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStart_time() {
		return start_time == null?null: start_time.replace(".0", "");
	}

	
	public void setStart_time(String start_time) {
		this.start_time = start_time ==null?null: start_time.replace(".0", "");
	}

	public String getEnd_time() {
		return end_time ==null?null: end_time.replace(".0", "");
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time==null?null: end_time.replace(".0", "");
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMachine_id() {
		return machine_id == null ? "" : machine_id;
	}

	public void setMachine_id(String machine_id) {
		this.machine_id = machine_id == null ? null : machine_id.trim();
	}

	public String getStores_id() {
		return stores_id;
	}

	public void setStores_id(String stores_id) {
		this.stores_id = stores_id == null ? null : stores_id.trim();
	}

	public String getMachine_name() {
		return machine_name == null ? "" : machine_name;
	}

	public void setMachine_name(String machine_name) {
		this.machine_name = machine_name == null ? null : machine_name.trim();
	}

}
