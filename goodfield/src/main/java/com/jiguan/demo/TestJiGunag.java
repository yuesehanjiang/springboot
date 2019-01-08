package com.jiguan.demo;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class TestJiGunag {
	
	public static void main(String[] args) {
	      
	   try {
	    	String appKey = "2fb43ca4407b075987afed77"; 
		    String masterSecret = "9e63fa80ca7644ec1f515e6e";
		     //创建JPushClient(极光推送的实例)
		     JPushClient jpushClient = new JPushClient(masterSecret, appKey);
			jpushClient.sendPush(pushCustomMessage());
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}

	}
	
	
	
	/**
	 * t
	 * @return
	 */
	public static PushPayload pushCustomMessage() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.tag(new String[]{"0xbe1ff4b1_0xbe1bf4d1"}))
                .setMessage(Message.newBuilder()
                        .setMsgContent("aasdfsdfaaaaaa")
                        .build())
                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(false)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(86400)
                        .build()
                )
                .build();
    }
 


}

