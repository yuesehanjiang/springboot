package com.yun.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ParameterUtil {
	private String id;			// 主键
	private String keyword;		// 关键字
	private String pageNum;		// 页数
	private String pageSize;	// 页码
	private String startTime;	// 开始时间
	private String endTime;		// 结束时间
	private String dateTime;	// 日期
	private String gymAddr;		// 门店id
	private String sex;			// 性别
	private String timeType;    //时间类型：day为日统计、week为周统计、month为月统计、year为年统计
	private String trainType;    //训练类型：1:总卡；2：跑步机；3：动感单车
	private String openId;      //会员openId
	private String code;        //手机
	private String memoryCap;	// 内存容量
	private String version;         // 版本
	private String status;          //状态
	private String productionTime;  //出厂日期
	private String address;         //地址
	private String clubName;        //门店名称
	private String examine;			// 审核状态0待审核1已通过2已拒绝
	
	public String getExamine() {
		return examine;
	}

	public void setExamine(String examine) {
		this.examine = examine;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMemoryCap() {
		return memoryCap;
	}

	public void setMemoryCap(String memoryCap) {
		this.memoryCap = memoryCap;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProductionTime() {
		return productionTime;
	}

	public void setProductionTime(String productionTime) {
		this.productionTime = productionTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

  
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getGymAddr() {
		return gymAddr;
	}

	public void setGymAddr(String gymAddr) {
		this.gymAddr = gymAddr;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
