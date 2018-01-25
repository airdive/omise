package com.linktai.dao;

import com.linktai.pojo.Managers;

public interface ManagersMapper {
    int insert(Managers record);

    int insertSelective(Managers record);
    
    Managers login(Managers managers);
    
    Integer updatePWD(Managers managers);
}