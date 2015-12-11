package com.sqq.crm.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sqq.crm.demo.model.DataResult;
import com.sqq.crm.demo.model.UserInfo;
import com.sqq.crm.demo.model.UserPoint;
import com.sqq.crm.demo.service.CrmApiService;

@Controller
@RequestMapping("/api")
public class CrmApiController {
	
    @Autowired
    private CrmApiService crmApiService;

    @ResponseBody
	@RequestMapping("/addUser")
    public DataResult addUser(@ModelAttribute("userInfo") UserInfo userInfo) {
        if (userInfo == null) {
            return new DataResult(500, "用户信息为空无法更新或注册", false).put("userInfo", null);
        }
        if (crmApiService.addUser(userInfo) == null) {
            return new DataResult(500, "注册失败", false).put("userInfo", null);
        }
        return new DataResult().put("userInfo", userInfo);
	}

    @ResponseBody
	@RequestMapping("/updateUser")
    public DataResult updateUser(@ModelAttribute("userInfo") UserInfo userInfo) {
        if (userInfo == null) {
            return new DataResult(500, "用户信息为空无法更新", false).put("userInfo", null);
        }
        if (userInfo.getId() != null) {
            crmApiService.updateUser(userInfo);
        }
        return new DataResult().put("userInfo", userInfo);
	}

    @ResponseBody
	@RequestMapping("/addPoint")
    public DataResult addPoint(@ModelAttribute("userPoint") UserPoint userPoint) {
        if (userPoint != null && userPoint.getPoint() != null && userPoint.getType() != null) {
            crmApiService.addUserPoint(userPoint);
            return new DataResult().put("userPoint", userPoint);
        }
        return new DataResult(500, "加积分失败", false).put("userPoint", null);
	}

    @ResponseBody
	@RequestMapping("/reducePoint")
    public DataResult reducePoint(@ModelAttribute("userPoint") UserPoint userPoint) {
        if (userPoint != null && userPoint.getPoint() != null && userPoint.getType() != null) {
            crmApiService.reduceUserPoint(userPoint);
            return new DataResult().put("userPoint", userPoint);
        }
        return new DataResult(500, "减积分失败", false).put("userPoint", null);
	}
}
