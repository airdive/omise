package com.linktai.dao;

import java.util.List;
import java.util.Map;

import com.linktai.pojo.Charges;

public interface ChargesMapper {
    int insert(Charges record);

    int insertSelective(Charges record);
    
    int updatePathAndSign(Charges charges);
    
    List<Charges> findByPage(Map<String, Object> map);
    
    Integer findAllCount(Map<String, Object> map);
    
    Integer updateChargesInfo(Charges charges);
    
    Charges findChargesByChargesId(Map<String, Integer> map) ;
    
    Integer deleteAccount(Map<String, Integer> map);
}