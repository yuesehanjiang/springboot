package com.alibaba.util;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ParamInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id; // 主键
	private String enterprise_name;
	private String stores_id; // 门店id
	private String phone; // 手机号
	private String code; // 验证码
	private String account; // 账号
	private String password; // 密码
	private String newPassword; // 新密码
	private String stores_name; // 门店名
	private String user_id; // 用户id
	private String u_id; // 用户id
	private String relationId; // 权限id
	private String cityContent;
	private String roleId; // 门店id
	private String original; // 旧密码
	private String nonmember; // 非会员
	private String member; // 会员
	private String memberformer;// 曾会员
	private String startTime; // 开始时间
	private String endTime; // 结束时间
	private String datetime; // 时间
	private String memberId; // 会员id
	private String einlass; // 入场时间
	private String identity; // 0:非会员--1:会员--2曾会员
	private String card; // 卡号
	private String name; // 姓名
	private String stores_city; // 城市
	private String address; // 地址
	private String nonmemberName; // 非会员问候语
	private String positive; // 正序
	private String reverse; // 倒序
	private String pageNum; // 页码
	private String pageSize; // 页数
	private String startPage;
	private String endPage;
	private String pic; // 头像
	private String keyword; // 关键字
	private String isNull; // 是空
	private String isNotNull; // 不是空
	private String type; // 类型
	private String content; // 门店id内容
	private String user_in_time;
	private String user_name;
	private String user_type;
	private String user_card;
	private String appoint;
	private String valid_member;
	private String running_water;
	private String user_pic;
	private String pic_path;
	private String distinguish_id;
	private String photo_time;
	private String datetime1;
	private String datetime2;
	private String datetime3;
	private String frequency;
	private String userId;
	private String status;
	private String content_type;
	private String content2;
	private String machine_id;
	private String userToken;
	private String folderId; // 关联文件夹id
	private String member_id;
	private String storesId;
	private String loginStatus;
	private String pushIsAll;
	private String flag;
	private String uuid;

	private String image_name;

	private String stores_names;
	private String stores_ids;

	private String rePlayTime;
	private String rePlayDate;

	private String screenType;
	private String   liveStatus;
	private String   gymAddr;
	private String  contentType;
	
	

}
