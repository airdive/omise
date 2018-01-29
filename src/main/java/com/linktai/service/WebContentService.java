package com.linktai.service;

import java.util.Map;

import com.linktai.pojo.WebContent;

public interface WebContentService {
	WebContent selectByName(String name);
	
	Map<String, String> updateWebContent(WebContent webContent);
}
