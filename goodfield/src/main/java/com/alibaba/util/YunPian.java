package com.alibaba.util;

import static org.hamcrest.CoreMatchers.both;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.yunpian.sdk.model.ResultDO;
import com.yunpian.sdk.model.SendSingleSmsInfo;
import com.yunpian.sdk.service.SmsOperator;
import com.yunpian.sdk.service.YunpianRestClient;

public class YunPian {

	/**
	 * 生成随机6位验证码
	 * @param counts
	 * @return
	 */
	public static String getRandomCode(Integer counts){
		Random random = new Random();
		String result = "";
		for(int i = 0; i < counts; i++){
			result += random.nextInt(10);
		}
		return result;
	}
	
	/**
	 * 使用JDK发送单条短信,智能匹配短信模板
	 * @param apikey 成功注册后登录云片官网,进入后台可查看
	 * @param text 需要使用已审核通过的模板或者默s认模板
	 * @param mobile 接收的手机号,仅支持单号码发送s
	 */

	public static void sendSms(String appkey, String mobile, String text) {
		YunpianRestClient client = new YunpianRestClient("bc61d4a4863694f288adc802534dbc98");// 用apikey生成client,可作为全局静态变量
		SmsOperator smsOperator = client.getSmsOperator();// 获取所需操作类
		ResultDO<SendSingleSmsInfo> result = smsOperator.singleSend(mobile,
				"【古德菲力健身】  您的验证码是" + text + "。有效期为5分钟，请尽快验证");// 发送短信,ResultDO<?>.isSuccess()判断是否成功
		System.out.println(result);
	}
	
	
	
	
	

	/**
	 * 单条短信发送,智能匹配短信模板
	 * @param apikey成功注册后登录云片官网 ,进入后台可查看
	 * @param text需要使用已审核通过的模板或者默认模板
	 * @param mobile接收的手机号 ,仅支持单号码发送 s
	 * @return json格式字符串
	 */
	public static String singleSend(String apikey, String text, String mobile) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("apikey", apikey.toString());
		params.put("text", text.toString());
		params.put("mobile", mobile.toString());
		String httpPostRequest = null;
		try {
			httpPostRequest = HttpClientUtil.httpPostRequest(
					"https://sms.yunpian.com/v2/sms/single_send.json", params);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return httpPostRequest;
	}
	
	
	/**
	 * 更换短信平台
	 * @param apikey成功注册后登录云片官网 ,进入后台可查看
	 * @param text需要使用已审核通过的模板或者默认模板
	 * @param mobile接收的手机号 ,仅支持单号码发送 s
	 * @return json格式字符串
	 */
	public static Boolean singleSendNew(String tel,String content) {		
		  //1. 创建OKHttp对象
	       OkHttpClient client = new OkHttpClient();
	       
	       
	       String url="http://sms.uninets.com.cn/Modules/Interface/http/Iservicesbsjy.aspx?"
	       		+ "flag=sendsms&loginname=szgdfl&password=qP4glCyWxH&p="+tel+""
	       		+ "&c=验证码："+content+"";
	       
	       
	       //2.构建请求,设置要访问的url
	       Request.Builder builder = new Request.Builder().url(url);
	       //增加请求头,模拟Chrome浏览器
	      
	   
	       //3. 发送请求
	       //创建已设置好的请求对象
	       Request  request= builder.build();
	       String retText = null;
	   
	               //发送请求,返回响应对象
	             
				try {
					  Response response = client.newCall(request).execute();
					  retText = response.body().string();
					  System.out.println(retText);
					  
					  String string = retText.split("\\,")[0];
					  
					  return "0".equals(string)?true:false;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	               //获取响应的数据
	               //抓取成功后跳出循环
		      
		  return false;
	    } 
		
		
	public static void main(String[] args) {
		
		//Boolean singleSendNew = singleSendNew("17665326665", "14abc");
	     sendSms("bc61d4a4863694f288adc802534dbc98", "17665326665", "sdfsdf");
		
	}

}
