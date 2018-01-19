package com.linktai.utils;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginIntercepter extends HandlerInterceptorAdapter{
	@Autowired
	private StringRedisTemplate redisTemplate;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getParameter("token");
		HashMap<String,String> hashMap = new HashMap<String, String>();
		if(token==null) {
			hashMap.put("state", "1");
			return false;
		}
		return true;
	}
}
