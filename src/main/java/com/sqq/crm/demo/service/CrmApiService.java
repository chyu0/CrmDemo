package com.sqq.crm.demo.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqq.crm.demo.dao.BaseDao;
import com.sqq.crm.demo.enums.PointType;
import com.sqq.crm.demo.model.UserInfo;
import com.sqq.crm.demo.model.UserPoint;

@Service
public class CrmApiService {
	@Autowired
	private BaseDao baseDao;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private UserPointService userPointService;
	
	public UserInfo getUserInfoByMobile(String mobile){
		Map<String,Object> userMap=new HashMap<String,Object>();
		userMap.put("mobile", mobile);
        List<UserInfo> userList = baseDao.getList("userInfoMapper.selectUser", userMap);
        return userList != null ? userList.get(0) : null;
	}

    public String addUser(UserInfo userInfo) {
		Map<String,Object> userMap=new HashMap<String,Object>();
		userMap.put("userId", userInfo.getUserId());
        if (userInfoService.getUserListByUserMap(userMap).size() > 0) {
            return null;
		}
        userMap.remove("userId");
        userMap.put("mobile", userInfo.getMobile());
        if (userInfoService.getUserListByUserMap(userMap).size() > 0) {
            return null;
        }
		userInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		userInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
		//注册送200积分
		userInfo.setPoint(200);
        baseDao.save("userInfoMapper.register", userInfo);
        // 更新积分表
		UserPoint userPoint=new UserPoint();
		userPoint.setUserId(userInfo.getUserId());
		userPoint.setPoint(200);
		userPoint.setPointDesc("注册送积分");
		userPoint.setType(PointType.addPoint.getFlag());
		userPointService.savePointRecord(userPoint);
        return userInfo.getMobile();
	}

	public void updateUser(UserInfo userInfo){
		userInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		baseDao.update("userInfoMapper.modifyUserInfo", userInfo);
	}

	public void addUserPoint(UserPoint userPoint){
		Map<String,Object> userMap=new HashMap<String,Object>();
		userMap.put("userId", userPoint.getUserId());
        List<UserInfo> userList = userInfoService.getUserListByUserMap(userMap);
        if (userList == null || userList.size() <= 0) {
			return;
		}
        Integer point = userList.get(0).getPoint();
		userPointService.savePointRecord(userPoint);
        userMap.put("point", (point != null ? point : 0) + userPoint.getPoint());
		baseDao.update("userInfoMapper.changeUserPoint", userMap);
	}

	public void reduceUserPoint(UserPoint userPoint){
		Map<String,Object> userMap=new HashMap<String,Object>();
		userMap.put("userId", userPoint.getUserId());
        List<UserInfo> userList = userInfoService.getUserListByUserMap(userMap);
        if (userList == null || userList.size() <= 0) {
            return;
        }
		userPointService.savePointRecord(userPoint);
        Integer point = userList.get(0).getPoint();
        if (point != null && point > userPoint.getPoint()) {
            userMap.put("point", point - userPoint.getPoint());
		}else{
			userMap.put("point", 0);
		}
		baseDao.update("userInfoMapper.changeUserPoint", userMap);
	}
}
