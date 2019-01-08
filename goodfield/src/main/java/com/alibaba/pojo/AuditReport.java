package com.alibaba.pojo;

import java.io.Serializable;

public class AuditReport implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;				// 主键
	private String cardTime;		// 刷卡时间
	private String roleId;			// 门店id
	private String name;			// 用户姓名
	private String memberid;		// 会员id
	private String card;			// 卡号
	private String pic;				// 头像
	private String entryNumber;		// 入场流水号
	private String distinguishid;	// 识别id
	private String image;			// 图像
	private String about;			// 约课情况 0：否  1：是
	private String effective;		// 是否有效会员  0：否  1：是
	private String running_water;
	
	public String getRunning_water() {
		return running_water;
	}

	public void setRunning_water(String running_water) {
		this.running_water = running_water;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCardTime() {
		return cardTime.replace(".0", "");
	}

	public void setCardTime(String cardTime) {
		this.cardTime = cardTime;
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

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getEntryNumber() {
		return entryNumber;
	}

	public void setEntryNumber(String entryNumber) {
		this.entryNumber = entryNumber;
	}

	public String getDistinguishid() {
		return distinguishid;
	}

	public void setDistinguishid(String distinguishid) {
		this.distinguishid = distinguishid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getEffective() {
		return effective;
	}

	public void setEffective(String effective) {
		this.effective = effective;
	}

}
