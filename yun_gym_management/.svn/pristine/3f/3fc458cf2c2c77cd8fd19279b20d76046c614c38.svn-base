package com.yun.utils;

import java.util.HashMap;

public class ServerResponse {

	// 查询集合数据
    public static HashMap<String, Object> build(String code, String message, Object data, String total, String pageNum, String pageSize) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("code", code);
		map.put("data", data);
		map.put("total", total);
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		return map;
	}
    
    // 查询单条数据
    public static HashMap<String, Object> build(String code, String message, Object data) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("message", message);
    	map.put("code", code);
    	map.put("data", data);
    	return map;
    }
    
    // 错误提示
    public static HashMap<String, Object> build(String code, String message) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("code", code);
		return map;
	}
    
}
