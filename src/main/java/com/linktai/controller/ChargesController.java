package com.linktai.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.linktai.pojo.CardOfAc;
import com.linktai.pojo.Charges;
import com.linktai.service.IChargesService;
import com.linktai.utils.PageUtil;

@Controller
@RequestMapping("charges")
public class ChargesController {
	@Autowired
	private StringRedisTemplate redisTemplate;
	@Autowired
	private IChargesService chargesService;

	/**
	 * 
	 * @param arg
	 *            前端携带过来的数据
	 * @param cardOfAc
	 * @return
	 */
	@RequestMapping(value = "pay")
	public void charges(String para, CardOfAc cardOfAc, HttpServletResponse response) {
		Map<String, String> map = chargesService.charges(para, cardOfAc);
		String string = JSON.toJSONString(map);
		response.setHeader("Access-Control-Allow-origin", "*");
		try {
			response.getWriter().println(string);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "accountInfo")
	public void charges1(Charges charges, HttpServletResponse response) {
		System.out.println(charges);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		String substring = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
		hashMap.put("para", substring);
		String string = JSON.toJSONString(charges);
		response.setHeader("Access-Control-Allow-origin", "*");
		redisTemplate.expire("account", 30, TimeUnit.MINUTES);
		redisTemplate.opsForHash().put("account", substring, string);

		try {
			PrintWriter writer = response.getWriter();
			writer.println(JSON.toJSONString(hashMap));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "a")
	@ResponseBody
	public Map<String, String> charges2() {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		String substring = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
		hashMap.put("arg", substring);
		redisTemplate.expire("account1", 10, TimeUnit.SECONDS);
		redisTemplate.opsForValue().append("account1", "qw");
		redisTemplate.opsForHash().put("account", substring, "111");
		System.out.println("连接成功");
		return hashMap;
	}

	/**
	 * 分页
	 */
	@RequestMapping("pageSpilt")
	@ResponseBody
	public PageUtil<Charges> pageSpilt(Integer cp, Integer ps, @RequestParam(required = false) String select) {
		PageUtil<Charges> pageUtil = chargesService.listPage(cp, ps, select);
		return pageUtil;
	}

	/**
	 * 修改信息功能
	 */
	@RequestMapping(value = "updateInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateChargesInfo(Charges charges) {
		Map<String, String> info = chargesService.updateChargesInfo(charges);
		return info;
	}

	/**
	 * 赠票,返回票号
	 */
	@RequestMapping(value = "giveTicket", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Integer> giveTicket(Charges charges) {
		Map<String, Integer> map = chargesService.charges(charges);
		return map;
	}

}
