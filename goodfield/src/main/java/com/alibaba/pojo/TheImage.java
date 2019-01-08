package com.alibaba.pojo;

import java.io.Serializable;

public class TheImage implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id; // 主键
	private String imageName; // 图像名
	private String picPath; // 图像路径
	private String createTime; // 创建时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
