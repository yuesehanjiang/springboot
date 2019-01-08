package com.alibaba.common.result;

import java.util.HashMap;
public class JsonResult2{
	
	  // 还没有用户，请先去创建用户
    public static HashMap<String, Object> build(String code, String message, String uuid) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("message", message);
    	map.put("code", code);
    	map.put("uuid", uuid);
    	return map;
    }
    
    // 还没有用户，请先去创建用户
    public static HashMap<String, Object> build(String code, String message, Integer uuid) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("message", message);
    	map.put("code", code);
    	map.put("data", uuid);
    	return map;
    }
    
    public static HashMap<String, Object> build(String code, String message, String uuid, String user_id) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("message", message);
    	map.put("code", code);
    	map.put("uuid", uuid);
    	map.put("user_id", user_id);
    	return map;
    }
    
}
