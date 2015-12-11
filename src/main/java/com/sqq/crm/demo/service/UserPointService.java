package com.sqq.crm.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqq.crm.demo.dao.BaseDao;
import com.sqq.crm.demo.model.UserPoint;

@Service
public class UserPointService {
	@Autowired
	private BaseDao baseDao;
	
	public List<UserPoint> getAllUserPoint(){
		return baseDao.getList("userPointMapper.getAllUserPoint");
	}
	
	public List<UserPoint> getOneUserPoint(String userId){
		return baseDao.getList("userPointMapper.getOneUserPoint", userId);
	}
	
	public void savePointRecord(UserPoint userPoint){
        userPoint.setCreateTime(new Date());
        baseDao.save("userPointMapper.insertPoint", userPoint);
	}
}
