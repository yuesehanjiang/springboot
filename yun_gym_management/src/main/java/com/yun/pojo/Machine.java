package com.yun.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Machine implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id; //
    private String machineCode;// 终端机机器码
    private String gymAddr;// 地址码
    private String machineName;// 机器名称
    private String status;// 状态:0离线;1在线
    private String machineDelStatus;// 终端机是不是删除了文件或者是文夹夹 0 删除 1没有删除
    private String createTime;//
    private String updateTime;//
    private String emmc_id; // 终端机emmcId
    private String activeStatus;  //激活状态:0未激活,1激活

    private String url; // 小程序url
    private String clubName; // 健身房名称

    
    
    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getGymAddr() {
        return gymAddr;
    }

    public void setGymAddr(String gymAddr) {
        this.gymAddr = gymAddr;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMachineDelStatus() {
        return machineDelStatus;
    }

    public void setMachineDelStatus(String machineDelStatus) {
        this.machineDelStatus = machineDelStatus;
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

    public String getEmmc_id() {
        return emmc_id;
    }

    public void setEmmc_id(String emmc_id) {
        this.emmc_id = emmc_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
