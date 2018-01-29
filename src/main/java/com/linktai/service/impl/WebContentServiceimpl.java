package com.linktai.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linktai.dao.WebContentMapper;
import com.linktai.pojo.WebContent;
import com.linktai.service.WebContentService;
@Service
public class WebContentServiceimpl implements WebContentService{
	@Autowired
	private WebContentMapper webContentMapper;
	public WebContent selectByName(String name) {
		WebContent webContent = webContentMapper.selectByName(name);
		return webContent;
	}

	public Map<String, String> updateWebContent(WebContent webContent) {
		HashMap<String,String> hashMap = new HashMap<String, String>();
		Integer integer = webContentMapper.updateWebContent(webContent);
		
		if(integer>0) {
			hashMap.put("state", "0");
			return hashMap;
		}
		hashMap.put("state", "1");
		return hashMap;
	}

}
