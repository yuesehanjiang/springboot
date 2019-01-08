package com.alibaba.common.param;

import java.io.Serializable;

public class UserRoleInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String rid;
	private String userId;
	private Integer relationId;
	private String roleName;
	private String city;
	private String address;
	private String roleId;
	private String stores_id;
	private String user_id;
	
    private String  phone;
    private String userToken;
    
    
    
	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getUserToken() {
		return userToken;
	}


	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}


	public String getStores_id() {
		return stores_id;
	}
	

	public void setStores_id(String stores_id) {
		this.stores_id = stores_id == null ? null : stores_id.trim();
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id == null ? null : user_id.trim();
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRelationId() {
		return relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId == null ? null : Integer.parseInt(String.valueOf(relationId).trim());
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
