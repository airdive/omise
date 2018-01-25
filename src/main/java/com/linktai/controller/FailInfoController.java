package com.linktai.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linktai.pojo.FailInfo;
import com.linktai.service.IFailInfoService;
import com.linktai.utils.PageUtil;

@RequestMapping("failInfo")
@Controller
public class FailInfoController {
	@Autowired
	private IFailInfoService failInfoService;
	@RequestMapping(value = "listPageFail")
	@ResponseBody
	public PageUtil<FailInfo> listPageFail(PageUtil<FailInfo> page,String failCode){
		PageUtil<FailInfo> pageUtil = failInfoService.pagespilt(page.getCp(), page.getPs(), failCode);
		return pageUtil;
	}
	
	@RequestMapping(value = "countFailNumber")
	@ResponseBody
	public Map<String, Integer> countFailNumber(){
		Map<String, Integer> map = failInfoService.countFailNuber();
		return map;
	}
}
