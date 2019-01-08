package com.alibaba.common.jpush;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

import com.alibaba.pojo.File;
import com.alibaba.pojo.Folder;
import com.google.gson.Gson;

public class ScreenPushUtil {

	public static void main(String[] args) {
		jpushFolderStatus("123","device001");
	}
	
	
	private static final String appKey = "2fb43ca4407b075987afed77";
	private static final String masterSecret = "9e63fa80ca7644ec1f515e6e";
	private static JPushClient jpushClient = null;
	
	private static synchronized JPushClient getJPushClient() {
		if (jpushClient == null) {
			jpushClient = new JPushClient(masterSecret, appKey);
		}
		return jpushClient;
	}
	
	/**
	 * 前端上传文件推送给终端机
	 */
	public static void jpushFolderAndroid(Folder folder,String tage) {
	    
		PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android())
//				.setAudience(Audience.all())
				.setAudience(Audience.tag(new String[] { "","" }))
				.setNotification(Notification.android(new Gson().toJson(folder), "1", null))
				.setOptions(Options.newBuilder().setApnsProduction(false).build()).build();
		try {
			@SuppressWarnings("unused")
			PushResult pu = getJPushClient().sendPush(payload);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 前端删除文件推送给终端机
	 */
	public static void jpushFolderAndroid2(File f, String tage) {
		
		PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android())
//				.setAudience(Audience.all())
				.setAudience(Audience.tag(new String[] { tage }))
				.setNotification(Notification.android(new Gson().toJson(f), "0",null))
				.setOptions(Options.newBuilder().setApnsProduction(false).build()).build();
		try {
			@SuppressWarnings("unused")
			PushResult pu =  getJPushClient().sendPush(payload);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改文件夹状态的时候通知终端机
	 * @param id
	 */
	public static void jpushFolderStatus(String folder,String tage) {
	
		PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android())
				.setAudience(Audience.tag(new String[] { tage }))
				.setNotification(Notification.android(new Gson().toJson(folder), "1",null))
				.setOptions(Options.newBuilder().setApnsProduction(false).build()).build();
		try {
			@SuppressWarnings("unused")
			PushResult pu = getJPushClient().sendPush(payload);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
