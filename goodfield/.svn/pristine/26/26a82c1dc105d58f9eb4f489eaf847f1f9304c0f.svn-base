package com.alibaba.common.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.common.result.JsonResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.service.AdminService;
import com.alibaba.util.RedisUtils;

@Component
public class AdminLoginInterceptor implements HandlerInterceptor {

	@Autowired
	private AdminService adminService;
	
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}


	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String loginStatus = adminService.getLoginStatus();	// 查询登录状态
		String adminToken = RedisUtils.getKV("adminToken_"+loginStatus);
		if (adminToken == null) {
			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(JSON.toJSONString(JsonResult.build("210", "登陆超时，请重新登陆！")));
			return false;
		}
		return true;
	}

}
