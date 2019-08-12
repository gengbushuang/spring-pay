package com.spring.pay.controller.exception;

import com.spring.pay.common.core.enums.ErrorCode;
import com.spring.pay.common.core.exception.BizException;
import com.spring.pay.common.core.model.Result;
import com.spring.pay.common.core.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class WebExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(WebExceptionHandler.class);

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public Result<String> handleBizException(BizException bizex) {
        log.error("handleBizException->", bizex);
        return ResultUtils.failed(bizex.getCode(), bizex.getMsg());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<String> handleException(Exception ex) {
        log.error("handleException->", ex);
        return ResultUtils.failed(ErrorCode.SYSTEM_ERROR.getCode(), "未知异常:" + ex.getMessage());
    }
}
