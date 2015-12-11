package com.sqq.crm.demo.enums;

public enum Sex {

                 unkown(0, "未知"), male(1, "男"), female(2, "女");

    private int flag;

    private String value;

    public int getFlag() {

        return flag;
    }

    public String getValue() {

        return value;
    }

    Sex(int flag, String value) {
        this.flag = flag;
        this.value = value;
    }

    public static String getSexValueByFlag(int flag) {

        for (Sex sex : Sex.values()) {
            if (sex.getFlag() == flag) {
                return sex.getValue();
            }
        }
        return null;
    }
}
