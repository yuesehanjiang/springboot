package org.fkjava.test;

import java.io.IOException;
import java.util.UUID;

import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class TestMaven {

	private static RequestConfig requestConfig = null;
	static {
		// 设置超时时间
		requestConfig = RequestConfig.custom().setSocketTimeout(2000)
				.setConnectTimeout(2000).build();
	}

	public static void main(String[] args) {
		 String json="{\"user_id\":\"12\", \"stores_id\":\"12\", \"machine_id\":\"12\"}";
		
	     JSONObject jsonParam=JSONObject.parseObject(json);
		
		  for (int i = 0; i < 10000; i++) {
			
		
		  JSONObject httpPost = httpPost("http://47.106.33.137:9096/goodfield/web/ship/saveManUserId",  jsonParam);
		 
          System.err.println(httpPost);
          
	           try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
          
		  }
	}

	public static JSONObject httpPost(String url, JSONObject jsonParam) {
		// post请求返回结果
		CloseableHttpClient httpClient = HttpClients.createDefault();
		JSONObject jsonResult = null;
		HttpPost httpPost = new HttpPost(url);
		// 设置请求和传输超时时间
		httpPost.setConfig(requestConfig);
		try {
			// 设置参数解决中文乱码
			if (null != jsonParam) {
				StringEntity entity = new StringEntity(jsonParam.toString(),
						"utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
			// 发送请求
			CloseableHttpResponse result = httpClient.execute(httpPost);

			if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = "";
				try {
					// 读取服务器返回的json数据（然后解析）
					str = EntityUtils.toString(result.getEntity(), "utf-8");
					// 把json字符串转换成json对象
					jsonResult = JSONObject.parseObject(str);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpPost.releaseConnection();
		}
		return jsonResult;
	}
}
