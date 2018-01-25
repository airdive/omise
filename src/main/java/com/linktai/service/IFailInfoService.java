package com.linktai.service;

import java.util.Map;

import com.linktai.pojo.FailInfo;
import com.linktai.utils.PageUtil;

public interface IFailInfoService {
	Map<String, Integer> countFailNuber();
	PageUtil<FailInfo> pagespilt(Integer cp,Integer ps,String failCode);
}
