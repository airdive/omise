package com.linktai.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linktai.pojo.Managers;
import com.linktai.service.ManagerService;

@Controller
@RequestMapping("manager")
public class ManagerController {
	@Autowired
	private StringRedisTemplate redisTemplate;
	@Autowired
	private ManagerService managerService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> login(Managers managers,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		HashMap<String, String> hashMap = new HashMap<String, String>();
		Managers login = managerService.login(managers);
		if (login != null) {
			// 登陆成功之前去把别的客户端的信息清除
			Map<Object, Object> entries = redisTemplate.opsForHash().entries(managers.getLoginname());
			if(entries!=null) {
				Set<Entry<Object,Object>> entrySet = entries.entrySet();
				for (Entry<Object, Object> entry : entrySet) {
					entry.setValue(1);
				}
			}
			// 登陆成功
			String token = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
			redisTemplate.expire(managers.getLoginname(), 1, TimeUnit.DAYS);
			//0表示登陆状态
			redisTemplate.opsForHash().put(managers.getLoginname(), token, 0);
			hashMap.put("state","0");
		}
		hashMap.put("state", "1");
		return hashMap;
	}
}
