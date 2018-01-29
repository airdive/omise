package com.linktai.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.linktai.pojo.Journey;
import com.linktai.service.IJourneyService;

@Controller
@RequestMapping("journey")
public class JourneyController {
	@Autowired
	private IJourneyService journeyService;
	
	
	@RequestMapping("findJourney")
	@ResponseBody
	public Journey findByJourneyName(String name) {
		try {
			name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(name);
		Journey journey = journeyService.findJourney(name);
		return journey;
	}
	
	
	@RequestMapping("updateJourney")
	@ResponseBody
	public Map<String, String> updateJourney(Journey journey){
		Object json = JSON.toJSON(journey);
		try {
			String str = new String(json.toString().getBytes("ISO-8859-1"), "UTF-8");
			Journey journey2 = JSON.parseObject(str, new TypeReference<Journey>() {
			});
			Map<String, String> map = journeyService.updateJourney(journey2);
			return map;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
