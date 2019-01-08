package com.alibaba.pojo;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 文件夹实体
 * 
 * @author mayn
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain=true)  
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Folder implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id; // 主键
	private String folderName; // 文件夹名
	private String roleId; // 门店id
	private String startTime; // 播放开始时间
	private String endTime; // 播放结束时间
	private String userId; // 用户id
	private String type; // 0:上屏 1中屏 2下屏
	private String status; // 0停播1正常
	private String content_type; // 0图片1视频
	private String machine_ids;
	private String stores_name;
	private String stores_id;
	private String user_id;
	private String machine_name;
	private String machine_id;
	private String machine_name_all;
	private String machine_id_all;
	private String machineName;
	private String machineId;
	private String unlimited_time; // 0：不限时间 1：限时间
	private String progress;
	private String userToken;
	private String received; // 0：未收到 1已收到
	private String pushIsAll;
	private String phone;
	private String update_time;
	private String aaaa;
	private String loginStatus;
	private String replay;
	private String live_status;

	private PushStatus1 pushStatus;

	private String empty;

	private String rePlayDate; // 重复播放的日期

	private String rePlayTime; // 重复播放的时间  rePlayTime
   
	
	private String gymAddr;
	
	
	private String  screenType;
	
	private String contentType;
	
	private String machineIds;
	
	private String unlimitedTime;
	
	private String pushIs;  //有没有推送
	
	
	private String machineCode;  //机器吗
	
	private List<File> files; 
	
	private String clubName; //门店id
	
	
	
	
}
