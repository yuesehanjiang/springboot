package com.yun.utils;

import java.util.Iterator;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtil {

    /**
     * Post请求
     * 
     * @param url
     * @param params
     * @return
     */
    public static String post(String url, Map<String, String> params) {
        String responseBody = "";
        FormBody.Builder builder = new FormBody.Builder();
        // 添加参数
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        Response response = null;
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }


    public static String getForHeader(String url, Map<String, Object> queries) {
        String responseBody = "";
        StringBuffer sb = new StringBuffer(url);
        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator iterator = queries.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry<String, String>) iterator.next();
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }
        Request request = new Request.Builder().addHeader("key", "value").url(sb.toString()).build();
        Response response = null;
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            response = okHttpClient.newCall(request).execute();
            // int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }

    /**
     * Post请求发送JSON数据....{"name":"zhangsan","pwd":"123456"} 参数一：请求Url
     * 参数二：请求的JSON 参数三：请求回调
     */
    public static String postJsonParams(String url, String jsonParams) {
        String responseBody = "";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Response response = null;
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            response = okHttpClient.newCall(request).execute();
            // int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }

}