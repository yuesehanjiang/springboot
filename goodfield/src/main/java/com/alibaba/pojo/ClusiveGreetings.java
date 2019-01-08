package com.alibaba.pojo;

import java.io.Serializable;

/**
 * 专属问候语
 * @author mayn
 *
 */
public class ClusiveGreetings implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id; // 主键
	private String roleId; // 门店Id
	private String name; // 姓名
	private String memberid; // 会员id
	private String cart; // 卡号
	private String pic; // 头像
	private String exclusive; // 专属问候语

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getCart() {
		return cart;
	}

	public void setCart(String cart) {
		this.cart = cart;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getExclusive() {
		return exclusive;
	}

	public void setExclusive(String exclusive) {
		this.exclusive = exclusive;
	}

}
