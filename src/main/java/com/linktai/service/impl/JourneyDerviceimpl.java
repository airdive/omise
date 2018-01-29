package com.linktai.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linktai.dao.JourneyMapper;
import com.linktai.pojo.Journey;
import com.linktai.service.IJourneyService;

@Service
public class JourneyDerviceimpl implements IJourneyService {
	@Autowired
	private JourneyMapper journeyMapper;

	public Journey findJourney(String name) {
		Journey journey = journeyMapper.selectJourneyByName(name);
		return journey;
	}

	public Map<String, String> updateJourney(Journey journey) {
		Integer integer = journeyMapper.updateJourney(journey);
		HashMap<String, String> hashMap = new HashMap<String, String>();

		if (integer > 0) {
			hashMap.put("state", "0");
			return hashMap;
		}
		hashMap.putIfAbsent("state", "1");
		return hashMap;
	}

}
