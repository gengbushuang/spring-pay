package com.spring.pay.common.core.model;

import lombok.Data;

@Data
public class PageParam<T> {

    private int pageNum;// 当前页数
    private int pageSize;// 页面大小

    private T data;

    public PageParam(int pageNum,int pageSize,T data){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.data = data;
    }

}
