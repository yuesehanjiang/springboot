package com.yun.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserShow implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id; // 会员秀id(自增)
	private String nickname; // 用户昵称
	private String sex; // 性别：0:女，1：男
	private String image; // 上传图片
	private String signature; // 个性签名
	private String createTime; // 创建时间
	private String updateTime; // 修改时间
	private String gymAddr; // 健身房地址码：16进制方式配置
	private String type;	// 类型0会员1管理员
	private String examine;	// 审核状态0待审核1已通过2已拒绝
	
	public String getExamine() {
		return examine;
	}

	public void setExamine(String examine) {
		this.examine = examine;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
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

}
