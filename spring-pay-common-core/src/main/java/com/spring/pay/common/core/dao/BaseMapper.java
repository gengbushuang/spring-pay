package com.spring.pay.common.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseMapper<T> {

    /**
     * 插入一条记录
     * @param entity
     * @return
     */
    Integer insert(T entity);

    /**
     * 更新一条数据
     * @param entity
     * @return
     */
    Integer update(T entity);

    /**
     * 物理删除一条数据
     * @param entity
     * @return
     */
    Integer delete(T entity);

    /**
     * 根据ID获取对应数据
     * @param id
     * @return
     */
    T getById(String id);

    /**
     * 根据map参数信息获取对应数据
     * @param paramMap
     * @return
     */
    T getByMap(Map<String, Object> paramMap);


    List<T> listPage(Map<String, Object> paramMap);

}
