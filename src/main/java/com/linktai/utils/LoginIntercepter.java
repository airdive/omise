package com.linktai.utils;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.linktai.pojo.Managers;

public class LoginIntercepter extends HandlerInterceptorAdapter {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setHeader("Access-Control-Allow-Credentials", "true");
		// String token = request.getParameter("token");
		// String loginName = request.getParameter("loginName");
		// 若未登录则没有token
		HashMap<String, String> hashMap = new HashMap<String, String>();
		// if(token==null) {
		// hashMap.put("loginState", "1");
		// response.getWriter().println(JSON.toJSON(hashMap));
		// return false;
		// }
		// stringRedisTemplate.expire(loginName, 15, TimeUnit.MINUTES);
		// stringRedisTemplate.opsForHash().put(loginName, token, 0);
		Managers attribute = (Managers) request.getSession().getAttribute("user");
		if (attribute == null) {
			response.setStatus(401);
			return false;
		}
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(15*60);
		session.setAttribute("user", attribute);
		return true;
	}
}
