package com.spring.pay.service.message.api;

import com.github.pagehelper.PageInfo;
import com.spring.pay.common.core.model.PageParam;
import com.spring.pay.service.message.exceptions.MessageBizException;
import com.spring.pay.service.message.model.RpTransactionMessage;

import java.util.Map;

public interface RpTransactionMessageService {

    /**
     * 预先存储要发送的消息信息
     *
     * @param rpTransactionMessage
     * @return
     * @throws MessageBizException
     */
    public int saveMessageWaitingConfirm(RpTransactionMessage rpTransactionMessage) throws MessageBizException;

    /**
     * 确认后发送消息
     *
     * @param messageId
     * @throws MessageBizException
     */
    public void confirmAndSendMessage(String messageId) throws MessageBizException;

    /**
     * 保持消息后发送消息
     *
     * @param rpTransactionMessage
     * @return
     * @throws MessageBizException
     */
    public int saveAndSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException;

    /**
     * 直接发送消息
     *
     * @param rpTransactionMessage
     * @throws MessageBizException
     */
    public void directSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException;

    /**
     * 重发消息
     *
     * @param rpTransactionMessage
     * @throws MessageBizException
     */
    public void reSendMessage(RpTransactionMessage rpTransactionMessage) throws MessageBizException;

    /**
     * 重发messageId的消息
     *
     * @param messageId
     * @throws MessageBizException
     */
    public void reSendMessageByMessageId(String messageId) throws MessageBizException;

    /**
     * 标记messageId的消息死亡
     *
     * @param messageId
     * @throws MessageBizException
     */
    public void setMessageToAreadlyDead(String messageId) throws MessageBizException;

    /**
     * 根据messageId删除消息
     *
     * @param messageId
     * @throws MessageBizException
     */
    public void deleteMessageByMessageId(String messageId) throws MessageBizException;

    /**
     * @param messageId
     * @return
     * @throws MessageBizException
     */
    public RpTransactionMessage getMessageByMessageId(String messageId) throws MessageBizException;


    public PageInfo<RpTransactionMessage> listPage(PageParam<RpTransactionMessage> pageParam) throws MessageBizException;


    public PageInfo<RpTransactionMessage> listPageMap(PageParam<Map<String,Object>> pageParam) throws MessageBizException;
}
