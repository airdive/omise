package com.linktai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linktai.dao.ManagersMapper;
import com.linktai.pojo.Managers;
import com.linktai.service.ManagerService;

@Service
public class ManagerServerimpl implements ManagerService {
	@Autowired
	private ManagersMapper managersMapper;

	public Managers login(Managers managers) {
		if (managers != null) {
			Managers login = managersMapper.login(managers);
			return login;
		}
		return null;
	}

}
