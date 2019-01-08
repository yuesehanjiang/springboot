package com.yun.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 用户实体类
 * 
 * @author qzy005
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id; // 用户表
    private String openId; // 用户openid
    private String nickname; // 昵称
    private String img; // 头像
    private String createTime; // 创建时间
    private String updateTime; // 修改时间
    private String status; // 用户状态 0.禁用 1.正常
    private String machineCode; // 登陆的终端机机器码
    private String bracelet; // 手环编码

    private String faceImg; // 人脸图
    private String gymAddr; //健身房地址码 
    private String face;    //人脸特征码
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
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMachineCode() {
        return machineCode;
    }
    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }
    public String getBracelet() {
        return bracelet;
    }
    public void setBracelet(String bracelet) {
        this.bracelet = bracelet;
    }
    public String getFaceImg() {
        return faceImg;
    }
    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
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
}
