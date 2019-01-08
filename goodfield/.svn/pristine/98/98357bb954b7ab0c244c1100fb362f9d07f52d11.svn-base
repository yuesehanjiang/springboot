package org.fkjava.test;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Duanxin {
   
   public static void main(String[] args) {
	   //1. 创建OKHttp对象
       OkHttpClient client = new OkHttpClient();
       
       
       String url="http://sms.uninets.com.cn/Modules/Interface/http/Iservicesbsjy.aspx?"
       		+ "flag=sendsms&loginname=szgdfl&password=qP4glCyWxH&p=17665326665"
       		+ "&c=验证码：123456";
       
       
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
               //获取响应的数据
               //抓取成功后跳出循环
	      
	
    } 
}
