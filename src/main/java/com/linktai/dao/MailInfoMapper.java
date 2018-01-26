package com.linktai.dao;

import com.linktai.pojo.MailInfo;

public interface MailInfoMapper {
    int insert(MailInfo record);

	int insertSelective(MailInfo record);

	MailInfo selectMail(String name);
	
	Integer updateMailContent(MailInfo mailInfo);
}