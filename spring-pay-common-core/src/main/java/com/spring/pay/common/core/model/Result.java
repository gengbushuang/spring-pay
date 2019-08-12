package com.spring.pay.common.core.model;

import com.spring.pay.common.core.utils.ResultCode;

public class Result<D> implements ResultCode {


    private String logId;

    private int code;

    private String message;

    private D data;

    public Result(){}

    public Result(String logId, int code, String message){
        this(logId,code,message,null);
    }

    public Result(String logId, int code, String message, D data){
        this.logId = logId;
        this.code = code;
        this.message = message;
        this.data = data;
    }


    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }
}
