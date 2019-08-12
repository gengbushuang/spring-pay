package com.spring.pay.common.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public final class StringUtil {

    /**
     * 获取去掉横线的长度为32的UUID串.
     *
     * @return uuid.
     * @author WuShuicheng.
     */
    public static String get32UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取带横线的长度为36的UUID串.
     *
     * @return uuid.
     * @author WuShuicheng.
     */
    public static String get36UUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 判断字符串是否为空，包含空字符串
     * @param value
     * @return
     */
    public static boolean isEmpty(String value){
        return StringUtils.isBlank(value);
    }
}
