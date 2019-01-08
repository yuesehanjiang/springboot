package com.yun.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserFace implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id; // 人脸数据表id
	private String openId; // 用户
	private String gymAddr; // 健身房地址码：16进制方式配置
	private String face; // 人脸特征码
	private String createTime; // 创建时间
	private String updateTime; // 更新时间
	private String faceImg;  //人脸图
	
	
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getOpenId() {
        return openId;
    }
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    public String getGymAddr() {
        return gymAddr;
    }
    public void setGymAddr(String gymAddr) {
        this.gymAddr = gymAddr;
    }
    public String getFace() {
        return face;
    }
    public void setFace(String face) {
        this.face = face;
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
    public String getFaceImg() {
        return faceImg;
    }
    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }
	
	

}
