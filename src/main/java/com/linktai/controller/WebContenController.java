package com.linktai.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.linktai.pojo.WebContent;
import com.linktai.service.WebContentService;

@Controller
@RequestMapping("webContent")
public class WebContenController {
	@Autowired
	private WebContentService webContentService;
	@RequestMapping("findWeb")
	@ResponseBody
	public WebContent findWebContentByName(String name) {
		WebContent webContent = webContentService.selectByName(name);
		return webContent;
	}
	@RequestMapping("updateWeb")
	@ResponseBody
	public Map<String, String> updateWeb(WebContent webContent){
		Object json = JSON.toJSON(webContent);
		try {
			String str = new String(json.toString().getBytes("ISO-8859-1"), "UTF-8");
			WebContent webContent2 = JSON.parseObject(str, new TypeReference<WebContent>() {
			});
			Map<String, String> map = webContentService.updateWebContent(webContent2);
			return map;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
