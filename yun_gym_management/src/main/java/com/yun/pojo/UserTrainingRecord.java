package com.yun.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserTrainingRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id; // 记录id
    private String nickname; // 用户昵称
    private String head; // 用户头像
    private String gymAddr; // 健身房地址码：16进制方式配置
    private String equipmentType; // 运动类型:1：总卡路里2：跑步3：动感单车
    private String mileage; // 里程
    private String calorie; // 卡路里消耗
    private String createTime; // 创建时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getGymAddr() {
        return gymAddr;
    }

    public void setGymAddr(String gymAddr) {
        this.gymAddr = gymAddr;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
