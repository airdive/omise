package com.linktai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.linktai.pojo.AccountInfo;
import com.linktai.pojo.CardOfAc;

@Controller
@RequestMapping("account")
public class AccountController {
	/**
	 * ���������Ϣ,���п���Ϣ����
	 */
	public String charges(AccountInfo accountInfo,CardOfAc card) {
		
		return null;
	}
}
