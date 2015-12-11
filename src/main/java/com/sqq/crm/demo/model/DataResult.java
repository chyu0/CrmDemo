package com.sqq.crm.demo.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataResult {

    private Map<String, Object> data;
    private Integer code = 200;

    private String msg = "SUCCESS";

    private boolean isSuccess = true;

    public DataResult() {
    }
    
    public DataResult(Integer code, String msg, boolean isSuccess) {
        this.code = code;
        this.msg = msg;
        this.isSuccess = isSuccess;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public DataResult put(String key, Object value) {
        if (data == null) {
            data = new HashMap<String, Object>();
        }
        data.put(key, value);
        return this;
    }

    public DataResult putAll(Map<String, Object> map) {
        data.putAll(map);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T getValue(String key) {
        return (T) this.data.get(key);
    }

    @Override
    public String toString() {

        final int maxLen = 10;
        StringBuilder builder = new StringBuilder();
        builder.append("DataResult [isSuccess=").append(isSuccess);
        builder.append(", code=").append(code).append(", msg=").append(msg);
        builder.append(", data=").append(data != null ? toString(data.entrySet(), maxLen) : null).append("]");
        return builder.toString();
    }

    private String toString(Collection<?> collection, int maxLen) {

        StringBuilder builder = new StringBuilder();
        builder.append("[");
        int i = 0;
        for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
            if (i > 0) builder.append(", ");
            builder.append(iterator.next());
        }
        builder.append("]");
        return builder.toString();
    }
}
