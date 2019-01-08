package com.alibaba.common.jpush;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;


public class JiGuangUtil {
	private static final String appKey = "2fb43ca4407b075987afed77";
	private static final String masterSecret = "9e63fa80ca7644ec1f515e6e";
	private static JPushClient jpushClient = null;
	
	private static synchronized JPushClient getJPushClient() {
		if (jpushClient == null) {
			jpushClient = new JPushClient(masterSecret, appKey);
		}
		return jpushClient;
	}
	
	 public static void jpushAndroid(String [] machieid,String text) {
		 
        // JPushClient jpushClient = new JPushClient(masterSecret, appKey);
         PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android())
//               .setAudience(Audience.all())
                 .setAudience(Audience.tag(machieid))
                 .setNotification(Notification.android(text, "文件夹",null))
                 .setOptions(Options.newBuilder().setApnsProduction(false).build()).build();
         try {
             @SuppressWarnings("unused")
             PushResult pu = getJPushClient().sendPush(payload);
         } catch (Exception e) {
             e.printStackTrace();
         }
    }
	 
	 
	 
	 
	 
	 
	 
	 
	
	  
	  
	    
	  public static void main(String[] args) throws Exception {
		  
		  String[] arrString={"0xbe1ff4b1_0xbe1bf4d1"};
		  
		
		  ObjectMapper  objectMapper=new ObjectMapper();
		  
		  String writeValueAsString = objectMapper.writeValueAsString("ksjdfiieijweriopew");
		  jpushAndroid(arrString,writeValueAsString);
		  
		 // PushResult push = push("0xbe1ff4b1_0xbe1bf4d1","我们的 世界");
		  
		 // System.out.println(push);
	}
	    
	    
}
