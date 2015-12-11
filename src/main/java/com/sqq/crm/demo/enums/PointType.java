package com.sqq.crm.demo.enums;

public enum PointType {
    addPoint(1, "加积分"), reducePoint(0, "减积分");

	private int flag;
	private String value;

	public int getFlag() {
		return flag;
	}

	public String getValue() {
		return value;
	}

	PointType(int flag, String value) {
		this.flag = flag;
		this.value = value;
	}

	public static PointType getPointTypeByFlag(int flag) {
		for (PointType type : PointType.values()) {
			if (type.getFlag() == flag) {
				return type;
			}
		}
		return null;
	}
}
