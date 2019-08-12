package com.spring.pay.service.message.enums;

import com.spring.pay.common.core.utils.ResultCode;

public enum MessageCode implements ResultCode {

    SAVA_MESSAGE_IS_NULL(8001,"保存的消息为空"),
    MESSAGE_CONSUMER_QUEUE_IS_NULL(8002,"消息的消费队列不能为空")
    ;
    private final int code;

    private final String message;

    MessageCode(int code,String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
