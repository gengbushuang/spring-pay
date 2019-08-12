package com.spring.pay.common.core.model;

import com.spring.pay.common.core.utils.StringUtil;
import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {

    private String id = StringUtil.get32UUID();// 主键ID.
    private Integer version = 0;// 版本号默认为0
    private Integer status;// 状态 PublicStatusEnum
    private String creater;// 创建人.
    private Date createTime = new Date();// 创建时间.
    private String editor;// 修改人.
    private Date editTime;// 修改时间.
    private String remark;// 描述

}
