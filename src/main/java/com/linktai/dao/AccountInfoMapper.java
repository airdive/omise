package com.linktai.dao;

import com.linktai.pojo.AccountInfo;

public interface AccountInfoMapper {
    int insert(AccountInfo record);

    int insertSelective(AccountInfo record);
}