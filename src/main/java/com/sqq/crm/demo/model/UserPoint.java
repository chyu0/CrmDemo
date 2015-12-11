package com.sqq.crm.demo.model;

import java.io.Serializable;
import java.util.Date;

public class UserPoint implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String userId;
    private Integer type;
    private Integer point;
    private String pointDesc;
    private Date createTime;
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public String getPointDesc() {
		return pointDesc;
	}
	public void setPointDesc(String pointDesc) {
		this.pointDesc = pointDesc;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
}
