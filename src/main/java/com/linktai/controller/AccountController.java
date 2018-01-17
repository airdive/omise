package com.linktai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.linktai.pojo.AccountInfo;
import com.linktai.pojo.Card;

@Controller
@RequestMapping("account")
public class AccountController {
	/**
	 * 输入个人信息,银行卡信息交易
	 */
	public String charges(AccountInfo accountInfo,Card card) {
		
		return null;
	}
}
