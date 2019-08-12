package com.spring.pay.common.core.utils;

import com.spring.pay.common.core.model.Result;

public class ResultUtils<D> {

    public static <D> Result<D> success(D data) {
        return new Result("", 1, "success", data);
    }

    public static <D> Result<D> success() {
        return new Result("", 1, "success");
    }

    public static <D> Result<D> failed(int errorCode, String errorMsg) {
        return new Result("", errorCode, errorMsg);
    }

    public static <D> Result<D> failed(ResultCode resultCode) {
        return new Result("", resultCode.getCode(), resultCode.getMessage());
    }

}
