package com.alibaba.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 用户实体类
 * @author mayn
 *
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;			// 主键
	private String account;		// 账号
	private String password;	// 密码
	private String username;	// 用户名
	private Integer status;		// 0:删除 1未删除
	private String phone;		// 手机号
	private String pic;			// 头像
	private String userToken;
	private String adminPic;
	private String enterpriseName;  // 企业名称
	private String create_time;
	private String user_id;
	private String loginStatus;

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getAdminPic() {
		return adminPic;
	}

	public void setAdminPic(String adminPic) {
		this.adminPic = adminPic;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	private List<Role> roles = new ArrayList<Role>();
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account == null ? null : account.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", password="
				+ password + ", username=" + username + ", status=" + status
				+ ", phone=" + phone + ", pic=" + pic + ", userToken="
				+ userToken + ", adminPic=" + adminPic + ", enterpriseName="
				+ enterpriseName + ", roles=" + roles + "]";
	}
	
	

}
