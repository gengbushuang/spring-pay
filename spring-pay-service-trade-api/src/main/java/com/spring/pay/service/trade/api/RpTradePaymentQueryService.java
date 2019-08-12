package com.spring.pay.service.trade.api;

import com.github.pagehelper.PageInfo;
import com.spring.pay.common.core.model.PageParam;
import com.spring.pay.service.trade.model.RpTradePaymentOrder;
import com.spring.pay.service.trade.model.RpTradePaymentRecord;
import com.spring.pay.service.trade.vo.PaymentOrderQueryVo;

import java.util.List;
import java.util.Map;

/**
 * 交易模块查询
 */
public interface RpTradePaymentQueryService {

    /**
     * 交易模块查询
     * @param paremMap
     * @return
     */
    public List<RpTradePaymentRecord> listPaymentRecord(Map<String, Object> paremMap);


    /**
     * 分页查询支付订单
     * @param pageParam
     * @return
     */
    public PageInfo<RpTradePaymentOrder> listPaymentOrderPage(PageParam<PaymentOrderQueryVo> pageParam);
}
