package com.alibaba.controller.jiguang;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyuncs.http.HttpRequest;

@Controller
public class HelloController {


 
	
	
	@ResponseBody
	@RequestMapping("/to1")
	public String too(HttpServletRequest  request2) {
		//HttpServletRequest     request2=(HttpServletRequest) request;
		    
		int serverPort = request2.getLocalPort();
		StringBuffer requestURL = request2.getRequestURL();
		return requestURL.toString();
	}
	
	
	
	
	
	@ResponseBody
	@RequestMapping("/to2")
	public String too1(HttpServletRequest  request2) {
		//HttpServletRequest     request2=(HttpServletRequest) request;
		    
		int serverPort = request2.getLocalPort();
		return "陈朝辉";
	}
	
	

}
