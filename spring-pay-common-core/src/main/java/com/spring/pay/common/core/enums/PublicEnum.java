package com.spring.pay.common.core.enums;

public enum PublicEnum {
    YES(1, "是"),

    NO(0, "否");

    private final int id;

    private final String desc;

    PublicEnum(int id, String desc) {
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
