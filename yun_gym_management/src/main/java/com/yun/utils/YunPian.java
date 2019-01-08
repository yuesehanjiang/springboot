package com.yun.utils;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.yunpian.sdk.model.ResultDO;
import com.yunpian.sdk.model.SendSingleSmsInfo;
import com.yunpian.sdk.service.SmsOperator;
import com.yunpian.sdk.service.YunpianRestClient;

/**
 * 
 * 项目名称：企智云_终端机 创建作者：瞿黑子 创建日期：2018年11月6日 详细描述：云片短信验证
 */
public class YunPian {

    private final static Logger logger = LoggerFactory.getLogger(YunPian.class);

    /**
     * 生成随机6位验证码
     * 
     * @param counts
     * @return
     */
    public static String getRandomCode(Integer counts) {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < counts; i++) {
            result += random.nextInt(10);
        }
        return result;
    }

    /**
     * 使用JDK发送单条短信,智能匹配短信模板
     * 
     * @param apikey
     *            成功注册后登录云片官网,进入后台可查看
     * @param text
     *            需要使用已审核通过的模板或者默s认模板
     * @param mobile
     *            接收的手机号,仅支持单号码发送s
     */

    public static void sendSms(String mobile, String text) {
        YunpianRestClient client = new YunpianRestClient("bc61d4a4863694f288adc802534dbc98");// 用apikey生成client,可作为全局静态变量
        SmsOperator smsOperator = client.getSmsOperator();// 获取所需操作类
        ResultDO<SendSingleSmsInfo> result = smsOperator.singleSend(mobile,
                "【企智云】  您的验证码是" + text + "。有效期为2分钟，请尽快验证");// 发送短信,ResultDO<?>.isSuccess()判断是否成功
        logger.info("短信发送={}", new Gson().toJson(result));
    }
    
    public static String sendMessage(String mobile, String text) {
        YunpianRestClient client = new YunpianRestClient("bc61d4a4863694f288adc802534dbc98");// 用apikey生成client,可作为全局静态变量
        SmsOperator smsOperator = client.getSmsOperator();// 获取所需操作类
        ResultDO<SendSingleSmsInfo> result = smsOperator.singleSend(mobile,
                "【企智云】  您的验证码是" + text + "。有效期为2分钟，请尽快验证");// 发送短信,ResultDO<?>.isSuccess()判断是否成功
        logger.info("短信发送={}", new Gson().toJson(result));
        return new Gson().toJson(result);
    }

}