package com.alibaba.common.jpush;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;

public class JiGunag {
	
	
	
    private static final String appKey = "2fb43ca4407b075987afed77";
    private static final String masterSecret = "9e63fa80ca7644ec1f515e6e";
    private static JPushClient jpushClient = null;

    private static synchronized JPushClient getJPushClient() {
        if (jpushClient == null) {
            jpushClient = new JPushClient(masterSecret, appKey);
        }
        return jpushClient;
    }
	
	public static void pushMessage(String [] machine,String text) {
		  try {
		    	  
			     
			  getJPushClient().sendPush(pushCustomMessage(machine,text, null));
			} catch (APIConnectionException e) {
				e.printStackTrace();
			} catch (APIRequestException e) {
				e.printStackTrace();
			}
	}
	public static void main(String[] args) {
		
		String [] machine= {"0xbe1ff498_0xbe1ff465","0xbe1ff4b4_0xbe1bf43f","0xbe1ff4b4_0xbe1bf43f",
				"0xbe1ff4b4_0xbe1bf43f1","0xbe1ff4b4_0xbe1bf43f2",
				"0xbe1ff4b4_0xbe1bf43f3","0xbe1ff498_0000","0xbe1ff498_0xbe1ff481","0xbe1ff498_0xbe1ff481"
				,"0xbe1ff41e_0xbe1bf43f","0xbe1ff4b4_0xbe1bf43f"};
		
		pushMessage(machine,"213");
		
		
	}
	
	
	
	
	
	
	
	
	public static void pushMessage2(String [] machine,String text, String title) {
		try {
			
			//JPushClient jpushClient = new JPushClient(masterSecret, appKey);
			getJPushClient().sendPush(pushCustomMessage(machine,text, title));
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
	public static void pushMessage3() {
		try {
			
			//JPushClient jpushClient = new JPushClient(masterSecret, appKey);
			getJPushClient().sendPush(pushCustomMessage2("发起心跳", "200"));
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
	public static PushPayload pushCustomMessage(String [] machine,String text, String title) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.tag(machine))
                .setMessage(Message.newBuilder()
                		.setTitle(title)
                        .setMsgContent(text)
                        .build())
                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(false)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(0)
                        .build()
                     )
                .build();
    }
	
	/**
	 * t
	 * @return
	 */
	public static PushPayload pushCustomMessage2(String text, String title) {
		return PushPayload.newBuilder()
				.setPlatform(Platform.android_ios())
				.setAudience(Audience.all())
				.setMessage(Message.newBuilder()
						.setTitle(title)
						.setMsgContent(text)
						.build())
						.setOptions(Options.newBuilder()
								//此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
								.setApnsProduction(false)
								//此字段是给开发者自己给推送编号，方便推送者分辨推送记录
								.setSendno(1)
								//此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
								.setTimeToLive(0)
								.build()
								)
								.build();
	}
	
	
	

}
