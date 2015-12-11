package com.sqq.crm.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqq.crm.demo.dao.BaseDao;
import com.sqq.crm.demo.model.UserInfo;

@Service
public class UserInfoService {
	@Autowired
	private BaseDao baseDao;
	
	public List<UserInfo> getUserListByUserMap(Map<String,Object> userMap){
		return baseDao.getList("userInfoMapper.selectUser", userMap);
	}

    public UserInfo getUserById(Integer id) {
	    return baseDao.getOne("userInfoMapper.getUserById", id);
	}
}
