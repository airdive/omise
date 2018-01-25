package com.linktai.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linktai.service.ITicketService;

@Controller
@RequestMapping("ticket")
public class TicketController {
	@Autowired
	private ITicketService ticketService;
	@ResponseBody
	@RequestMapping("findNumber")
	/**
	 * 查询票的数量
	 * @return
	 */
	public Map<String, Integer> findNumber() {
		Integer ticketNumber = ticketService.findCount();
		HashMap<String,Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("ticketNumber", ticketNumber);
		return hashMap;
	}
}
