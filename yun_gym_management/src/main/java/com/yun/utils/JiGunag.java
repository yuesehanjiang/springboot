package com.yun.utils;

import java.util.HashMap;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;

import com.alibaba.fastjson.JSONObject;

/**
 * 
*项目名称：企智云_终端机
*创建作者：瞿黑子
*创建日期：2018年11月16日
*详细描述：极光推送
 */
public class JiGunag {

    public static void main(String[] args) {
        String[] machine = { "0xbe1ff4b1_0xbe1bf4d1" };
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", "content");
        map.put("type", "appUrl");
        String result = JSONObject.toJSONString(map); 
        pushMessage2(machine, result, "");
    }
    
    private static final String appKey = "19003d20b63bda84019b0282";
    private static final String masterSecret = "6d1deddec7042f8b560cfcbb";
    private static JPushClient jpushClient = null;

    private static synchronized JPushClient getJPushClient() {
        if (jpushClient == null) {
            jpushClient = new JPushClient(masterSecret, appKey);
        }
        return jpushClient;
    }

    public static void pushMessage(String[] machine, String text) {
        try {
            getJPushClient().sendPush(pushCustomMessage(machine, text, null));
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }

    public static void pushMessage2(String[] machine, String text, String title) {
        try {
            PushResult result =  getJPushClient().sendPush(pushCustomMessage(machine, text, title));
            System.out.println(result.toString());
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }

    public static void pushMessage3() {
        try {
            getJPushClient().sendPush(pushCustomMessage2("发起心跳", "200"));
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }

    /**
     * t
     * 
     * @return
     */
    public static PushPayload pushCustomMessage(String[] machine, String text, String title) {
        return PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.tag(machine))
                .setMessage(Message.newBuilder().setTitle(title).setMsgContent(text).build())
                .setOptions(Options.newBuilder()
                        // 此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(false)
                        // 此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        // 此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(0).build())
                .build();
    }

    /**
     * 
     * @return
     */
    public static PushPayload pushCustomMessage2(String text, String title) {
        return PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.all())
                .setMessage(Message.newBuilder().setTitle(title).setMsgContent(text).build())
                .setOptions(Options.newBuilder()
                        // 此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(false)
                        // 此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        // 此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(0).build())
                .build();
    }
    
    /**
     * 推送自定义消息
     * @return
     */
    public static PushPayload pushCustomMessageTest(String text,String title) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                //.setAudience(Audience.tag(new String[]{"0xbe1ff4b1_0xbe1bf4d1"}))
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
    
    
    public static void pushMessageTest(String text,String title) {
        try {
            getJPushClient().sendPush(pushCustomMessageTest(text,title));
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }
    
    

}