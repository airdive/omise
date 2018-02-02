package com.linktai.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
	public Map<String, String> charges(String para, String tokenId) {
		Map<String, String> map = chargesService.charges(para, tokenId);
		return map;
	}
	
	/**
	 * 换以太币支付方式，说明之前支付失败，传入txhash更新失败信息，后台客服验证是否转账成功，成功发送邮件
	 * @param para
	 * @param txhash
	 * @return
	 */
	@RequestMapping(value="payByETH")
	@ResponseBody
	public Map<String, String> chargesByETH(String para,String txhash){
		Map<String, String> chargesByETH = chargesService.chargesByETH(para, txhash);
		return chargesByETH;
	}

	@RequestMapping(value = "accountInfo")
	@ResponseBody
	public Map<String, String> charges1(Charges charges) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		String substring = UUID.randomUUID().toString().replaceAll("-", "");
		hashMap.put("para", substring);
		hashMap.put("lang", charges.getLang());
		String string = JSON.toJSONString(charges);
		redisTemplate.expire("account", 30, TimeUnit.MINUTES);
		redisTemplate.opsForHash().put("account", substring, string);
		return hashMap;
	}

	/**
	 * 分页
	 */
	@RequestMapping(value = "pageSpilt1")
	@ResponseBody
	public PageUtil<Charges> pageSpilt1(PageUtil<Charges> page, @RequestParam(required = false) String select) {
		if (select != null) {
			try {
				select = new String(select.toString().getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		PageUtil<Charges> pageUtil = chargesService.listPage(page.getCp(), page.getPs(), select);
		return pageUtil;
	}

	@RequestMapping(value = "pageSpiltZP")
	@ResponseBody
	public PageUtil<Charges> pageListZP(PageUtil<Charges> page, @RequestParam(required = false) String select) {
		if (select != null) {
			try {
				select = new String(select.toString().getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		PageUtil<Charges> pageUtil = chargesService.listPageZP(page.getCp(), page.getPs(), select);
		return pageUtil;
	}

	/**
	 * 退款
	 */
	@RequestMapping(value = "refound", method = RequestMethod.POST, produces = { "application/json;charset=utf-8" })
	@ResponseBody
	public Map<String, String> refound(Integer chargesId) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
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
	@RequestMapping(value = "deleteCharge")
	@ResponseBody
	public Map<String, String> deleteCharge(Integer chargesId) {
		System.out.println(chargesId);
		boolean delete = chargesService.delete(chargesId);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		String hashmap = delete ? hashMap.put("state", "0") : hashMap.put("state", "1");
		return hashMap;
	}

	@RequestMapping(value = "sendMail")
	@ResponseBody
	public Map<String, String> sendMail(Integer chargesId) {
		Map<String, String> map = chargesService.sendMail(chargesId);
		return map;
	}

	@RequestMapping(value = "sendMailZP")
	@ResponseBody
	public Map<String, String> sendMailZP(Integer chargesId, String lang) {
		Map<String, String> map = chargesService.sendMailZP(chargesId, lang);
		return map;
	}

	/**
	 * 统计总票数，交易票数，赠票数
	 * 
	 * @return
	 */
	@RequestMapping(value = "findAllCount")
	@ResponseBody
	public Map<String, Integer> findAllCount() {
		Map<String, Integer> map = chargesService.findAllCount();
		return map;
	}

}
