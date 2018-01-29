package com.linktai.service;

import java.util.Map;

import com.linktai.pojo.Journey;

public interface IJourneyService {
	Journey findJourney(String name);
	
	Map<String, String> updateJourney(Journey journey);
}
