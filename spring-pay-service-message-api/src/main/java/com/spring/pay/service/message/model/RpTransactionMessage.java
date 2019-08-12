package com.spring.pay.service.message.model;

import com.spring.pay.common.core.model.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Data
public class RpTransactionMessage extends BaseEntity {

    private static final long serialVersionUID = 1757377457814546156L;

    private String messageId;

    private String messageBody;

    private String messageDataType;

    private String consumerQueue;

    private Integer messageSendTimes;

    private Integer areadlyDead;

    private String field1;

    private String field2;

    private String field3;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
