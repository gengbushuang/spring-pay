package com.spring.pay.service.message.dao;

import com.spring.pay.common.core.dao.BaseMapper;
import com.spring.pay.service.message.model.RpTransactionMessage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface RpTransactionMessageMapper extends BaseMapper<RpTransactionMessage> {

}
