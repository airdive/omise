package com.linktai.dao;

import com.linktai.pojo.Charges;

public interface ChargesMapper {
    int insert(Charges record);

    int insertSelective(Charges record);
}