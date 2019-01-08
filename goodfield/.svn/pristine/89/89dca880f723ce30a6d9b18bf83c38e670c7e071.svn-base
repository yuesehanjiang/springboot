package com.alibaba.common.interceptor;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

public class RequestUtil {

	public static String CHARSET = "UTF-8";

	/**
	 * @详细
	 * @param request
	 * @return 返回JSON数据包
	 */
	public static String getJSONString(HttpServletRequest request) {
		String json = "";
		try {
			ServletInputStream in = request.getInputStream();
			String content = IOUtils.toString(in, CHARSET);
			json = URLDecoder.decode(content, CHARSET);
			json = json.substring(json.indexOf("=") + 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

}
