package com.yun.pojo;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id; // 课程表id
    private String gymAddr; // 健身房地址码：16进制方式配置
    private String content; // 课程信息
    private String createTime; // 创建时间
    private String updateTime; // 更新时间
    private String useDate; // 开始使用日期

    private LinkedHashMap<String, Object> contentMap;

    public void setContentMap(LinkedHashMap<String, Object> con) {
        this.contentMap = con;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGymAddr() {
        return gymAddr;
    }

    public void setGymAddr(String gymAddr) {
        this.gymAddr = gymAddr;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getUseDate() {
        return useDate;
    }

    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }

    public LinkedHashMap<String, Object> getContentMap() {
        return contentMap;
    }

}