package com.linktai.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.linktai.pojo.FailInfo;

public interface FailInfoMapper {
    int insert(FailInfo record);
    List<FailInfo> findFailInfo(Map<String, Object> map);
    Integer findAllCount(String failCode);
    
    Integer findCountNotRead();
    
    Integer updateIsReadState();
    
    FailInfo findFailByID(Integer id);
    
    Integer updateStateOfHandle(HashMap<String, String> map);
    
    Integer updateTxHashAndIsPayCoin(HashMap<String, String> map);
}