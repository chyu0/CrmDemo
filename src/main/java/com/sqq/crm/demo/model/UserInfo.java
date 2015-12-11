
package com.sqq.crm.demo.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String userId;

    private String mobile;

    private String nickName;

    private String realName;

    private Integer sex;

    private String birthday;

    private String headPic;

    private Integer point;

    private String idCardNo;

    private String extFields;

    private Timestamp createTime;

    private Timestamp updateTime;

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {

        this.userId = userId;
    }

    public String getMobile() {

        return mobile;
    }

    public void setMobile(String mobile) {

        this.mobile = mobile;
    }

    public String getNickName() {

        return nickName;
    }

    public void setNickName(String nickName) {

        this.nickName = nickName;
    }

    public String getRealName() {

        return realName;
    }

    public void setRealName(String realName) {

        this.realName = realName;
    }

    public Integer getSex() {

        return sex;
    }

    public void setSex(Integer sex) {

        this.sex = sex;
    }

    public String getBirthday() {

        return birthday;
    }

    public void setBirthday(String birthday) {

        this.birthday = birthday;
    }

    public String getHeadPic() {

        return headPic;
    }

    public void setHeadPic(String headPic) {

        this.headPic = headPic;
    }

    public Integer getPoint() {

        return point;
    }

    public void setPoint(Integer point) {

        this.point = point;
    }

    public String getIdCardNo() {

        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {

        this.idCardNo = idCardNo;
    }

    public String getExtFields() {

        return extFields;
    }

    public void setExtFields(String extFields) {

        this.extFields = extFields;
    }

    public Timestamp getCreateTime() {

        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {

        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {

        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {

        this.updateTime = updateTime;
    }
}
