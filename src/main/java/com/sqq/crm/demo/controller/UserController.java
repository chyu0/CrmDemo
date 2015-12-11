
package com.sqq.crm.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sqq.crm.demo.base.view.BaseModelAndView;
import com.sqq.crm.demo.enums.PointType;
import com.sqq.crm.demo.model.DataResult;
import com.sqq.crm.demo.model.UserInfo;
import com.sqq.crm.demo.model.UserPoint;
import com.sqq.crm.demo.service.CrmApiService;
import com.sqq.crm.demo.service.UserInfoService;
import com.sqq.crm.demo.util.FileUploadUtil;
import com.sqq.crm.demo.util.PaginationUtil;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private CrmApiService crmApiService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("userList")
    public BaseModelAndView userList(HttpServletRequest request,
                                     @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                     @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {

        BaseModelAndView mv = new BaseModelAndView("user_info");
        Map<String, Object> userMap = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(request.getParameter("userId"))) {
            userMap.put("userId", "%" + request.getParameter("userId") + "%");
        }
        if (StringUtils.isNotBlank(request.getParameter("mobile"))) {
            userMap.put("mobile", "%" + request.getParameter("mobile") + "%");
        }
        PaginationUtil<UserInfo> page = new PaginationUtil<UserInfo>(pageSize, pageNo);
        page.setTotalCount(userInfoService.getUserListByUserMap(userMap).size());
        userMap.put("startNo", (page.getPageNo() - 1) * page.getPageSize());
        userMap.put("pageSize", page.getPageSize());
        page.setItems(userInfoService.getUserListByUserMap(userMap));
        userMap.put("page", page);
        mv.addAllObjects(userMap);
        return mv;
    }

    @RequestMapping("toEdit")
    public BaseModelAndView toEditUser(@RequestParam(value = "id", required = false) Integer id) {
        UserInfo userInfo = null;
        if (id != null) {
            userInfo = userInfoService.getUserById(id);
        }
        return new BaseModelAndView("edit_user", "userInfo", userInfo);
    }

    @ResponseBody
    @RequestMapping("saveOrUpdate")
    public DataResult saveOrUpdate(@ModelAttribute("userInfo") UserInfo userInfo,
                                   @RequestParam(value = "file") MultipartFile file,
                                   HttpServletRequest request) throws Exception {

        if (userInfo == null) {
            logger.error("更新失败，用户信息为空");
            return new DataResult(500, "用户信息为空，更新或注册失败", true);
        }
        String fileName = "";
        if (file.getSize() > 0) {
            logger.info("上传文件：" + file);
            fileName = FileUploadUtil.fileUpload(file, request);
            userInfo.setHeadPic(fileName);
        }
        try {
            UserInfo user = userInfoService.getUserById(userInfo.getId());
            if (user != null) {
                crmApiService.updateUser(userInfo);
            } else {
                if (crmApiService.addUser(userInfo) == null) {
                    logger.error("注册失败");
                    return new DataResult(500, "注册失败", true);
                }
            }
        } catch (Exception e) {
            if (file.getSize() > 0) {
                FileUploadUtil.deleteFile(request, fileName);
            }
            logger.error(e.getMessage());
            return new DataResult(500, "操作失败", true);
        }
        // 删除更新前的图片
        if (file.getSize() > 0) {
            FileUploadUtil.deleteFile(request, request.getParameter("originPic"));
        }
        return new DataResult();
    }

    @ResponseBody
    @RequestMapping("changePoint")
    public DataResult changePoint(@ModelAttribute("userPoint") UserPoint userPoint) {
        DataResult dataResult = new DataResult();
        if (userPoint != null && userPoint.getPoint() != null && userPoint.getType() != null) {
            if (userPoint.getType() != PointType.addPoint.getFlag()) {
                crmApiService.addUserPoint(userPoint);
            } else {
                crmApiService.reduceUserPoint(userPoint);
            }
            dataResult.setMsg("修改积分成功");
            return dataResult;
        }
        dataResult.setCode(500);
        dataResult.setMsg("修改积分失败");
        dataResult.setSuccess(false);
        return dataResult;
    }
}
