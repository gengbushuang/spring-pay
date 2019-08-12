package com.spring.pay.common.core.enums;

import com.spring.pay.common.core.utils.ResultCode;

public enum ErrorCode implements ResultCode {
    SYSTEM_ERROR(-1, "system error"),
    DB_INSERT_RESULT_0(10040001, "数据库操作,insert返回0"),
    DB_UPDATE_RESULT_0(10040002, "数据库操作,update返回0"),
    DB_SELECTONE_IS_NULL(10040003, "数据库操作,selectOne返回null"),
    DB_LIST_IS_NULL(10040004, "数据库操作,list返回null"),
    TOKEN_IS_ILLICIT(10040005, "Token 验证非法"),
    SESSION_IS_OUT_TIME(10040006, "会话超时"),
    DB_GET_SEQ_NEXT_VALUE_ERROR(10040007, "序列生成超时");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
