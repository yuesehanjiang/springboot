package com.alibaba.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 角色实体类
 * 
 * @author xiangchao
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Relation> relations = new ArrayList<Relation>();
	private String stores_id;
	private String stores_name;
	private String stores_city;
	private String stores_address;
	private String frequency;
	
	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	private List<Machine> machines = new ArrayList<Machine>();
	private Machine machines2 = new Machine();
 
	public Machine getMachines2() {
		return machines2;
	}

	public void setMachines2(Machine machines2) {
		this.machines2 = machines2;
	}

	public Role(List<Relation> relations, String stores_id, String stores_name,
			String stores_city, String stores_address) {
		super();
		this.relations = relations;
		this.stores_id = stores_id;
		this.stores_name = stores_name;
		this.stores_city = stores_city;
		this.stores_address = stores_address;
	}

	public List<Machine> getMachines() {
		return machines;
	}

	public void setMachines(List<Machine> machines) {
		this.machines = machines;
	}

	public Role() {
		super();
	}

	public List<Relation> getRelations() {
		return relations;
	}

	public void setRelations(List<Relation> relations) {
		this.relations = relations;
	}

	public String getStores_id() {
		return stores_id;
	}

	public void setStores_id(String stores_id) {
		this.stores_id = stores_id;
	}

	public String getStores_name() {
		return stores_name;
	}

	public void setStores_name(String stores_name) {
		this.stores_name = stores_name;
	}

	public String getStores_city() {
		return stores_city;
	}

	public void setStores_city(String stores_city) {
		this.stores_city = stores_city;
	}

	public String getStores_address() {
		return stores_address;
	}

	public void setStores_address(String stores_address) {
		this.stores_address = stores_address;
	}

}
