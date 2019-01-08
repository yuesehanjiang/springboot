package com.yun.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Coach implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id; // id(自增)
	private String name; // 教练名称
	private String sex; // 性别：0:女，1：男
	private String image; // 上传图片
	private String introduction; // 个人介绍
	private String trainContent; // 训练内容
	private String createTime; // 创建时间
	private String updateTime; // 更新时间
	private String gymAddr; // 健身房地址码：16进制方式配置
	private String rank; // 等级：10个等级
	private String vote; // 点赞数

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getTrainContent() {
		return trainContent;
	}

	public void setTrainContent(String trainContent) {
		this.trainContent = trainContent;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getGymAddr() {
		return gymAddr;
	}

	public void setGymAddr(String gymAddr) {
		this.gymAddr = gymAddr;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getVote() {
		return vote;
	}

	public void setVote(String vote) {
		this.vote = vote;
	}

}