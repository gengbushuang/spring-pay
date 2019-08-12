package com.spring.pay.service.message.enums;

public enum MessageStatusEnum {
    WAITING_CONFIRM(1,"待确认"),

    SENDING(2,"发送中");

    private final int id;

    private final String desc;

    MessageStatusEnum(int id,String desc){
        this.id = id;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }
}
