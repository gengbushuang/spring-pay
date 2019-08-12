package com.spring.pay.app.message.scheduled.impl;

import com.github.pagehelper.PageInfo;
import com.spring.pay.app.message.scheduled.MessageScheduled;
import com.spring.pay.common.core.enums.PublicEnum;
import com.spring.pay.common.core.model.PageParam;
import com.spring.pay.service.message.api.RpTransactionMessageService;
import com.spring.pay.service.message.enums.MessageStatusEnum;
import com.spring.pay.service.message.model.RpTransactionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MessageScheduledImpl implements MessageScheduled {

    @Autowired
    private RpTransactionMessageService rpTransactionMessageService;


    @Override
    public void handleWaitingConfirmTimeOutMessages() {

        int numPerPage = 2000; // 每页条数
        int maxHandlePageCount = 3; // 一次最多处理页数
        Map<String, Object> paramMap = new HashMap<String, Object>(); // 查询条件
        String dateStr = getCreateTimeBefore();

        paramMap.put("createTimeBefore", dateStr);
        //状态为待确认的
        paramMap.put("status", MessageStatusEnum.WAITING_CONFIRM.name());
        //
        Map<String, RpTransactionMessage> messageMap = getMessageMap(numPerPage, maxHandlePageCount, paramMap);
        
    }

    @Override
    public void handleSendingTimeOutMessage() {

        int numPerPage = 2000; // 每页条数
        int maxHandlePageCount = 3; // 一次最多处理页数

        Map<String, Object> paramMap = new HashMap<String, Object>(); // 查询条件

        String dateStr = getCreateTimeBefore();
        paramMap.put("createTimeBefore", dateStr);// 取存放了多久的消息
        paramMap.put("status", MessageStatusEnum.SENDING.name());// 取状态为发送中的消息
        paramMap.put("areadlyDead", PublicEnum.NO.name());// 取存活的发送中消息

        Map<String, RpTransactionMessage> messageMap = getMessageMap(numPerPage, maxHandlePageCount, paramMap);

    }


    Map<String, RpTransactionMessage> getMessageMap(int numPerPage, int maxHandlePageCount, Map<String, Object> paramMap) {

        Map<String, RpTransactionMessage> messageMap = new HashMap<String, RpTransactionMessage>();
        //取第一页数据
        PageParam<Map<String, Object>> pageParam = new PageParam<>(1, numPerPage, paramMap);
        PageInfo<RpTransactionMessage> pageInfo = rpTransactionMessageService.listPageMap(pageParam);

        List<RpTransactionMessage> list = pageInfo.getList();
        //如果第一页数据都没有就直接返回
        if (list == null || list.isEmpty()) {
            return messageMap;
        }
        //转换map
        for (RpTransactionMessage rpTransactionMessage : list) {
            messageMap.put(rpTransactionMessage.getMessageId(), rpTransactionMessage);
        }
        //重新设置要获取的总页数
        long pageCount = pageInfo.getTotal();
        if (maxHandlePageCount < pageCount) {
            pageCount = maxHandlePageCount;
        }
        //循环获取剩下页数的数据
        for (int pageNum = 2; pageNum <= pageCount; ++pageNum) {
            pageParam.setPageNum(pageNum);
            pageInfo = rpTransactionMessageService.listPageMap(pageParam);
            list = pageInfo.getList();
            if (list == null || list.isEmpty()) {
                break;
            }
            //转换map
            for (RpTransactionMessage rpTransactionMessage : list) {
                messageMap.put(rpTransactionMessage.getMessageId(), rpTransactionMessage);
            }
        }
        return messageMap;
    }

    /**
     * 获取当前时间往前的数据
     *
     * @return
     */
    private String getCreateTimeBefore() {
        long currentTimeInMillis = 180;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateStr = LocalDateTime.now().minusSeconds(currentTimeInMillis).format(formatter);
        return dateStr;
    }
}
