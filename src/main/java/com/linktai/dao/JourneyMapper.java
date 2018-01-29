package com.linktai.dao;

import com.linktai.pojo.Journey;

public interface JourneyMapper {
    int insert(Journey record);

    int insertSelective(Journey record);
    
    Journey selectJourneyByName(String name);
    
    Integer updateJourney(Journey journey);
}