package com.alibaba.common.result;

import java.util.HashMap;
import java.util.List;

import com.alibaba.pojo.Relation;
import com.alibaba.pojo.Role;
public class JsonResult{
	
	// 查询返回值
    public static HashMap<String, Object> build(String code, String message, Object data) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("data", data);
		map.put("code", code);
		return map;
	}
    // 查询返回值
    public static HashMap<String, Object> build(String code, String message, List<Object> data) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("message", message);
    	map.put("data", data);
    	map.put("code", code);
    	return map;
    }
 // 查询返回值
    public static HashMap<String, Object> build(String code, String message, Object data, String storesId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("data", data);
		map.put("code", code);
		map.put("storesId", storesId);
		return map;
	}
    
    public static HashMap<String, Object> build(String code, String message, Object data, int total, Object pageNum, String pageSize) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("message", message);
    	map.put("data", data);
    	map.put("code", code);
    	map.put("total", total);
    	map.put("pageNum", pageNum);
    	map.put("pageSize", pageSize);
    	return map;
    }
    
    public static HashMap<String, Object> build(String code, String message, Object data, int total, String pageNum, String pageSize, 
    		int machineCount, List<String> machineList,Object object) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("message", message);
    	map.put("data", data);
    	map.put("machineCount", machineCount);
    	map.put("machineList", machineList);
    	map.put("code", code);
    	map.put("total", total);
    	map.put("pageNum", pageNum);
    	map.put("pageSize", pageSize);
    	
    	map.put("delmachinme", object);
    	return map;
    }
    
    public static HashMap<String, Object> build(String code, String message, Object data, int total, String pageNum, String pageSize,Object machineCount) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("message", message);
    	map.put("data", data);
    	map.put("code", code);
    	map.put("total", total);
    	map.put("pageNum", pageNum);
    	map.put("pageSize", pageSize);
    	map.put("machineCount", machineCount);
    	return map;
    }
    
    public static HashMap<String, Object> build(String code, String message, Object data, int total, String pageNum, String pageSize,String user_pic) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("message", message);
    	map.put("data", data);
    	map.put("code", code);
    	map.put("total", total);
    	map.put("pageNum", pageNum);
    	map.put("pageSize", pageSize);
    	map.put("pic", user_pic);
    	return map;
    }
    public static HashMap<String, Object> build(String code, String message, Object data, int total, String pageNum, String pageSize,List<Role> machineCount) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("message", message);
    	map.put("data", data);
    	map.put("code", code);
    	map.put("total", total);
    	map.put("pageNum", pageNum);
    	map.put("pageSize", pageSize);
    	map.put("roles", machineCount);
    	return map;
    }
    public static HashMap<String, Object> build(String code, String message, Object data, int total, String pageNum, String pageSize,List<Role> machineCount,List<String> size) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("message", message);
    	map.put("data", data);
    	map.put("code", code);
    	map.put("total", total);
    	map.put("pageNum", pageNum);
    	map.put("pageSize", pageSize);
    	map.put("roles", machineCount);
    	map.put("size", size);
    	return map;
    }
    
    public static HashMap<String, Object> build(String machineList,String code, String message, Object data, int total, String pageNum, String pageSize,Integer machineCount) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("machineList", "10");
    	map.put("message", message);
    	map.put("data", data);
    	map.put("code", code);
    	map.put("total", total);
    	map.put("pageNum", pageNum);
    	map.put("pageSize", pageSize);
    	map.put("machineCount", machineCount);
    	return map;
    }
    
    // 还没有用户，请先去创建用户
    public static HashMap<String, Object> build(String code, String message, String pic) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("message", message);
    	map.put("code", code);
    	map.put("pic", pic);
    	return map;
    }
    
    // 查询返回值门店信息
    public static HashMap<String, Object> build(String code, String message, Object data, int storesCount) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("message", message);
    	map.put("data", data);
    	map.put("code", code);
    	map.put("storesCount", storesCount);
    	return map;
    }
    
    // 查询返回值门店信息
    public static HashMap<String, Object> build(String code, String message, Object data, int storesCount, String storesName) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("message", message);
    	map.put("data", data);
    	map.put("code", code);
    	map.put("storesCount", storesCount);
    	map.put("storesName", storesName);
    	return map;
    }
    
    // 查询返回值门店信息
    public static HashMap<String, Object> build(String code, String message, Object data, Object roles) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("message", message);
    	map.put("data", data);
    	map.put("code", code);
    	map.put("roles", roles);
    	return map;
    }
    
    // 查询返回值权限信息
    public static HashMap<String, Object> build(String code, String message, Object data, List<Relation> relations) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("message", message);
    	map.put("data", data);
    	map.put("code", code);
    	map.put("relations", relations);
    	return map;
    }
    
    // 查询数据分页返回
    public static HashMap<String, Object> build(String code, String message, Object data, long total, int pageNum, int pageSize) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("message", message);
    	map.put("data", data);
    	map.put("code", code);
    	map.put("total", total);
    	map.put("pageNum", pageNum);
    	map.put("pageSize", pageSize);
    	return map;
    }
    
    // 新增，删除，修改返回值
    public static HashMap<String, Object> build(String code, String message) {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("message", message);
    	map.put("code", code);
    	return map;
    }
    
}
