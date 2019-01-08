package com.alibaba.common.jpush;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

public class PushUtil {

	public static void main(String[] args) {
		jpushAndroid();
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
	 * 开始推送
	 */
	public static void jpushAndroid() {
		/*String appKey = "2fb43ca4407b075987afed77";
	    String masterSecret = "9e63fa80ca7644ec1f515e6e";
		JPushClient jpushClient = new JPushClient(masterSecret, appKey);*/
		PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android())
//				.setAudience(Audience.all())
				.setAudience(Audience.tag(new String[] {"0xbe1ff4b1_0xbe1bf4d1"}))
				.setNotification(Notification.android("发起心跳", "200", null))
				.setOptions(Options.newBuilder().setApnsProduction(false).build()).build();
		try {
			@SuppressWarnings("unused")
			PushResult pu = getJPushClient().sendPush(payload);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 推送问候语
	 */
	public static void jpushGreetings(String content, String tag, String title) throws Exception {
		
		JPushClient jpushClient = new JPushClient(masterSecret, appKey);
		PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android())
				.setAudience(Audience.tag(new String[] { tag }))
				.setNotification(Notification.android(content, title, null))
				.setOptions(Options.newBuilder().setApnsProduction(false).build()).build();
		@SuppressWarnings("unused")
		PushResult pu = getJPushClient().sendPush(payload);
	}
	
}
