package com.linktai.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

import co.omise.Client;
import co.omise.ClientException;
import co.omise.models.OmiseException;
import co.omise.models.Refund;

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
	@ResponseBody
	public Map<String, String> charges(String para, CardOfAc cardOfAc) {
		Map<String, String> map = chargesService.charges(para, cardOfAc);
		return map;
	}

	@RequestMapping(value = "accountInfo")
	@ResponseBody
	public Map<String, String> charges1(Charges charges) {
		System.out.println(charges);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		String substring = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
		hashMap.put("para", substring);
		String string = JSON.toJSONString(charges);
		redisTemplate.expire("account", 30, TimeUnit.MINUTES);
		redisTemplate.opsForHash().put("account", substring, string);
		return hashMap;
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

	/**
	 * 退款
	 */
	@RequestMapping(value = "refound",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> refound(Integer chargesId) {
		HashMap<String,String> hashMap = new HashMap<String, String>();
		Charges refound = chargesService.refound(chargesId);
		Client client;
		try {
			client = new Client(IChargesService.PUBLIC_KEY, IChargesService.PRIVATE_KEY);
			Refund refund = client.charge(refound.getChargesNumberOmise()).refunds()
					.create(new Refund.Create().amount(refound.getChargesRental()));
			hashMap.put("state", "0");
			return hashMap;
		} catch (ClientException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (OmiseException e) {
			e.printStackTrace();
		}
		hashMap.put("state", "1");
		return hashMap;
	}
	
	/**
	 * 删除一条记录
	 */
	
	public Map<String, String> deleteCharge(Integer chargesid){
		return null;
	}

}
