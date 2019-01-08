package com.alibaba.pojo;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 会员图像库
 * 
 * @author xiangchao
 *
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ManagementPic implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id; // 主键
	private String image_name; // 图片名
	private String pic_path; // 图片路径
	private String photo_time; // 拍照时间
	private String user_id; // 会员id
	private String running_water; // 入场流水号
	private String user_type;	// 会员类型0：曾会员 1：会员
	private String member_type;
	private String user_pic;
	private String stores_id;
	private String machine_id;
	private String uuid;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMachine_id() {
		return machine_id;
	}

	public void setMachine_id(String machine_id) {
		this.machine_id = machine_id;
	}

	public String getStores_id() {
		return stores_id;
	}

	public void setStores_id(String stores_id) {
		this.stores_id = stores_id;
	}

	public String getUser_pic() {
		return user_pic;
	}

	public void setUser_pic(String user_pic) {
		this.user_pic = user_pic;
	}

	public String getMember_type() {
		return member_type;
	}

	public void setMember_type(String member_type) {
		this.member_type = member_type;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getRunning_water() {
		return running_water;
	}

	public void setRunning_water(String running_water) {
		this.running_water = running_water;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImage_name() {
		return image_name;
	}

	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	public String getPic_path() {
		return pic_path;
	}

	public void setPic_path(String pic_path) {
		this.pic_path = pic_path;
	}

	public String getPhoto_time() {
		return photo_time == null ? photo_time : photo_time.replace(".0", "");
	}

	public void setPhoto_time(String photo_time) {
		this.photo_time = photo_time;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

}
