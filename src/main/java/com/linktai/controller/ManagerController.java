package com.linktai.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.linktai.pojo.Charges;
import com.linktai.pojo.MailInfo;
import com.linktai.pojo.Managers;
import com.linktai.service.IChargesService;
import com.linktai.service.ManagerService;
import com.linktai.utils.PageUtil;
import com.linktai.utils.rsa.RsaUtils;

@Controller
@RequestMapping("manager")
public class ManagerController {
	@Autowired
	private StringRedisTemplate redisTemplate;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private IChargesService chargesService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> login(Managers managers, HttpServletRequest request) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		Managers login = managerService.login(managers);
		if (login != null) {
			// 登陆成功之前去把别的客户端的信息清除
			// Map<Object, Object> entries =
			// redisTemplate.opsForHash().entries(managers.getLoginname());
			// if(entries!=null) {
			// Set<Entry<Object,Object>> entrySet = entries.entrySet();
			// for (Entry<Object, Object> entry : entrySet) {
			// entry.setValue(1);
			// }
			// }
			// 登陆成功
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(15 * 60);
			session.setAttribute("user", managers);
			// String token = UUID.randomUUID().toString().replaceAll("-", "");
			// redisTemplate.expire(managers.getLoginname(), 15, TimeUnit.MINUTES);
			// //0表示登陆状态
			// redisTemplate.opsForHash().put(managers.getLoginname(), token, 0);
			// hashMap.put("token", token);
			// hashMap.put("loginName", managers.getLoginname());
			hashMap.put("state", "0");
			return hashMap;
		}
		hashMap.put("state", "1");
		return hashMap;
	}

	/**
	 * 修改信息功能
	 */
	@RequestMapping(value = "updateInfo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateChargesInfo(Charges charges) {
		Object json = JSON.toJSON(charges);
		try {
			String str = new String(json.toString().getBytes("ISO-8859-1"), "UTF-8");
			Charges parse = JSON.parseObject(str, new TypeReference<Charges>() {
			});
			Map<String, String> info = chargesService.updateChargesInfo(parse);
			return info;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 赠票,返回票号
	 */
	@RequestMapping(value = "giveTicket")
	@ResponseBody
	public Map<String, Integer> giveTicket(Charges charges) {
		Object json = JSON.toJSON(charges);
		try {
			String str = new String(json.toString().getBytes("ISO-8859-1"), "UTF-8");
			Charges parse = JSON.parseObject(str, new TypeReference<Charges>() {
			});
			Map<String, Integer> map = chargesService.charges(parse);
			return map;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 退出登录
	 */
	@RequestMapping(value = "logout", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> checkLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("state", "0");
		return hashMap;
	}

	/**
	 * 修改用户密码
	 */
	@RequestMapping("updatePWD")
	@ResponseBody
	public Map<String, String> updatePWD(String oldPWD, String newPWD, HttpServletRequest request) {
		HashMap<String, String> map = new HashMap<String, String>();
		Managers managers = new Managers();
		HttpSession session = request.getSession();
		Managers attribute = (Managers) session.getAttribute("user");
		if (attribute != null) {
			managers.setLoginname(attribute.getLoginname());
			managers.setLoginpsw(oldPWD);
			map.put("oldpwd", oldPWD);
			map.put("newpwd", newPWD);
			if (oldPWD == null || newPWD == null) {
				return map;
			}
			if (!oldPWD.equals(attribute.getLoginpsw())) {
				map.put("state", "1");
				map.put("attr", attribute.toString());
				return map;
			}
			managers.setLoginpsw(newPWD);
			boolean updatePWD = managerService.updatePWD(managers);
			if (updatePWD) {
				map.put("state", "0");
				map.put("newLogin", managers.toString());
				session.removeAttribute("user");
			} else {
				map.put("state", "1");
			}
		}
		return map;
	}

	/**
	 * 签到分页
	 */
	@RequestMapping("pageSpiltQD")
	@ResponseBody
	public PageUtil<Charges> pageSpiltQD(PageUtil<Charges> page, @RequestParam(required = false) String select) {
		PageUtil<Charges> pageUtil = managerService.pageListQD(page.getCp(), page.getPs(), select);
		return pageUtil;
	}

	/**
	 * 统计总门票数和签到人数
	 */
	@RequestMapping("accountQD")
	@ResponseBody
	public Map<String, Integer> accountQD() {
		Map<String, Integer> accountQD = managerService.accountQD();
		return accountQD;
	}
	
	
	/**
	 * 获取邮件内容
	 */
	@RequestMapping("findMailInfo")
	@ResponseBody
	public MailInfo findMailInfo(String name) {
		try {
			name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MailInfo info = managerService.findMailInfo(name);
		return info;
	}

	/**
	 * 修改邮件内容
	 */
	@RequestMapping("updateMailContent")
	@ResponseBody
	public Map<String, String> updateMailContent(MailInfo mailInfo) {
		Map<String, String> map = managerService.updateMailContent(mailInfo);
		return map;
	}

	/**
	 * 未读错误信息条数
	 */
	@RequestMapping("countNotRead")
	@ResponseBody
	public Map<String, Integer> countNotRead() {
		Map<String, Integer> notRead = managerService.countNotRead();
		return notRead;
	}
	
	
	/**
	 * 签到
	 */
	public Map<String, String> verifySign(String sign){
		HashMap<String,String> hashMap = new HashMap<String, String>();
		int indexOf = sign.indexOf(',');
		if(indexOf==-1) {
			hashMap.put("state", "0");
			return hashMap;
		}
		String chargesid = sign.substring(0, indexOf);
		Integer valueOf = Integer.valueOf(chargesid);
		sign = sign.substring(indexOf,sign.length());
		
		
		return null;
	}
	
	
	
	/**
	 * 将错误信息的用户 加入交易表
	 * @param id    失败信息id
	 * @return
	 */
	@RequestMapping("addFailInfoToCharges")
	@ResponseBody
	public Map<String, String> addFailInfoToCharges(Integer id,String name,String email){
		try {
			name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, String> map = managerService.addFailInfoToCharges(id,name,email);
		return map;
	}

}
