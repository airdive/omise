package com.linktai.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linktai.pojo.CardOfAc;
import com.linktai.pojo.Charges;
import com.linktai.service.IChargesService;
import com.linktai.utils.PageUtil;
@Controller
@RequestMapping("charges")
public class ChargesController {
	@Autowired
	private IChargesService chargesService;
	@RequestMapping(value="pay")
	@ResponseBody
	public Map<String, String> charges(Charges charges,CardOfAc cardOfAc){
		Map<String, String> map = chargesService.charges(charges, cardOfAc);
		return map;
	}
	
	/**
	 * 分页
	 */
	@RequestMapping("pageSpilt")
	@ResponseBody
	public PageUtil<Charges> pageSpilt(Integer cp,Integer ps,@RequestParam(required=false)String select){
		PageUtil<Charges> pageUtil = chargesService.listPage(cp, ps, select);
		return pageUtil;
	}
	/**
	 * 修改信息功能
	 */
	
	/**
	 * 赠票,返回票号
	 */
	@RequestMapping(value="giveTicket",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Integer> giveTicket(Charges charges){
		Map<String, Integer> map = chargesService.charges(charges);
		return map;
	}
	
}
