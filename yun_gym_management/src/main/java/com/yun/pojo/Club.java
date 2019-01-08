package com.yun.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 健身房实体类
 * 
 * @author qzy005
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Club implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer clubId; // 健身房id
    private Integer clubPid; // 企业id
    private String clubName; // 健身房名称
    private String gymAddr; // 健身房地址码：16进制方式配置
    private String address; // 地址备注
    private String gymLogo; // 健身房logo
    private String companyName; // 公司名称
    private String contact; // 联系方式	
	private String gymAbout; // 健身房描述
	public Integer getClubId() {
		return clubId;
	}
	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}
	public Integer getClubPid() {
		return clubPid;
	}
	public void setClubPid(Integer clubPid) {
		this.clubPid = clubPid;
	}
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	public String getGymAddr() {
		return gymAddr;
	}
	public void setGymAddr(String gymAddr) {
		this.gymAddr = gymAddr;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGymLogo() {
		return gymLogo;
	}
	public void setGymLogo(String gymLogo) {
		this.gymLogo = gymLogo;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getGymAbout() {
		return gymAbout;
	}
	public void setGymAbout(String gymAbout) {
		this.gymAbout = gymAbout;
	}

}