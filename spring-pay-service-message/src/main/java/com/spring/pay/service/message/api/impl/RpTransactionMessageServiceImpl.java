package com.spring.pay.service.message.api.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.pay.common.core.enums.PublicEnum;
import com.spring.pay.common.core.model.PageParam;
import com.spring.pay.common.core.utils.StringUtil;
import com.spring.pay.service.message.api.RpTransactionMessageService;
import com.spring.pay.service.message.dao.RpTransactionMessageMapper;
import com.spring.pay.service.message.enums.MessageCode;
import com.spring.pay.service.message.enums.MessageStatusEnum;
import com.spring.pay.service.message.exceptions.MessageBizException;
import com.spring.pay.service.message.model.RpTransactionMessage;
import com.spring.pay.service.message.send.MessageSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RpTransactionMessageServiceImpl implements RpTransactionMessageService {

    @Autowired
    private RpTransactionMessageMapper rpTransactionMessageMapper;

    @Autowired
    private MessageSend messageSend;

    @Override
    public int saveMessageWaitingConfirm(RpTransactionMessage message) throws MessageBizException {
        if (message == null) {
            throw new MessageBizException(MessageCode.SAVA_MESSAGE_IS_NULL);
        }

        if (StringUtil.isEmpty(message.getConsumerQueue())) {
            throw new MessageBizException(MessageCode.MESSAGE_CONSUMER_QUEUE_IS_NULL);
        }
        //消息重发次数
        message.setMessageSendTimes(0);
        //消息状态
        message.setStatus(MessageStatusEnum.WAITING_CONFIRM.getId());
        //是否死亡
        message.setAreadlyDead(PublicEnum.NO.getId());

        return rpTransactionMessageMapper.insert(message);
    }

    @Override
    public void confirmAndSendMessage(String messageId) throws MessageBizException {
        //先查找对应的消息信息
        RpTransactionMessage message = getMessageByMessageId(messageId);
        if (message == null) {
            throw new MessageBizException(MessageCode.SAVA_MESSAGE_IS_NULL.getCode(), "根据消息id查找的消息为空");
        }
        //消息转变为发送中状态
        message.setStatus(MessageStatusEnum.SENDING.getId());
        message.setEditTime(new Date());
        //更新数据
        rpTransactionMessageMapper.update(message);
        //更新后发送消息
        messageSend.sendMessage(message.getConsumerQueue(), "*", message.getMessageBody());
    }

    @Override
    public int saveAndSendMessage(RpTransactionMessage message) throws MessageBizException {
        if (message == null) {
            throw new MessageBizException(MessageCode.SAVA_MESSAGE_IS_NULL);
        }

        if (StringUtil.isEmpty(message.getConsumerQueue())) {
            throw new MessageBizException(MessageCode.MESSAGE_CONSUMER_QUEUE_IS_NULL);
        }

        //消息重发次数
        message.setMessageSendTimes(0);
        //消息状态
        message.setStatus(MessageStatusEnum.SENDING.getId());
        //是否死亡
        message.setAreadlyDead(PublicEnum.NO.getId());
        int result = rpTransactionMessageMapper.insert(message);
        //保存完后发送消息
        messageSend.sendMessage(message.getConsumerQueue(), "*", message.getMessageBody());
        return result;
    }

    @Override
    public void directSendMessage(RpTransactionMessage message) throws MessageBizException {
        if (message == null) {
            throw new MessageBizException(MessageCode.SAVA_MESSAGE_IS_NULL);
        }

        if (StringUtil.isEmpty(message.getConsumerQueue())) {
            throw new MessageBizException(MessageCode.MESSAGE_CONSUMER_QUEUE_IS_NULL);
        }
        //发送消息
        messageSend.sendMessage(message.getConsumerQueue(), "*", message.getMessageBody());
    }

    @Override
    public void reSendMessage(RpTransactionMessage message) throws MessageBizException {
        if (message == null) {
            throw new MessageBizException(MessageCode.SAVA_MESSAGE_IS_NULL);
        }

        if (StringUtil.isEmpty(message.getConsumerQueue())) {
            throw new MessageBizException(MessageCode.MESSAGE_CONSUMER_QUEUE_IS_NULL);
        }
        //更新修改时间
        message.setEditTime(new Date());
        //重发次数加1
        message.setMessageSendTimes(message.getMessageSendTimes() + 1);
        //更新数据
        rpTransactionMessageMapper.update(message);
        //发送消息
        messageSend.sendMessage(message.getConsumerQueue(), "*", message.getMessageBody());
    }

    @Override
    public void reSendMessageByMessageId(String messageId) throws MessageBizException {
        //先查找对应的消息信息
        RpTransactionMessage message = getMessageByMessageId(messageId);
        if (message == null) {
            throw new MessageBizException(MessageCode.SAVA_MESSAGE_IS_NULL.getCode(), "根据消息id查找的消息为空");
        }
        int maxTimes = 3;
        if (message.getMessageSendTimes() >= maxTimes) {
            message.setAreadlyDead(PublicEnum.YES.getId());
        }
        //更新修改时间
        message.setEditTime(new Date());
        //重发次数加1
        message.setMessageSendTimes(message.getMessageSendTimes() + 1);
        //更新数据
        rpTransactionMessageMapper.update(message);
        //发送消息
        messageSend.sendMessage(message.getConsumerQueue(), "*", message.getMessageBody());
    }

    @Override
    public void setMessageToAreadlyDead(String messageId) throws MessageBizException {
        //先查找对应的消息信息
        RpTransactionMessage message = getMessageByMessageId(messageId);
        if (message == null) {
            throw new MessageBizException(MessageCode.SAVA_MESSAGE_IS_NULL.getCode(), "根据消息id查找的消息为空");
        }
        //标记死亡
        message.setAreadlyDead(PublicEnum.YES.getId());
        //更新修改时间
        message.setEditTime(new Date());
        //更新数据
        rpTransactionMessageMapper.update(message);
    }

    @Override
    public void deleteMessageByMessageId(String messageId) throws MessageBizException {

    }

    /**
     * 查询messageId对应的数据信息
     *
     * @param messageId
     * @return
     */
    public RpTransactionMessage getMessageByMessageId(String messageId) {
        Map<String, Object> paramMap = new HashMap<>(2);
        paramMap.put("messageId", messageId);
        return rpTransactionMessageMapper.getByMap(paramMap);
    }

    @Override
    public PageInfo<RpTransactionMessage> listPage(PageParam<RpTransactionMessage> pageParam) throws MessageBizException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        RpTransactionMessage rpTransactionMessage = pageParam.getData();
        if (rpTransactionMessage != null) {
            paramMap.put("areadlyDead", rpTransactionMessage.getAreadlyDead());
            paramMap.put("messageId", rpTransactionMessage.getMessageId());
            paramMap.put("consumerQueue", rpTransactionMessage.getConsumerQueue());
            paramMap.put("status", rpTransactionMessage.getStatus());
        }
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<RpTransactionMessage> rpTransactionMessages = rpTransactionMessageMapper.listPage(paramMap);
        PageInfo<RpTransactionMessage> rpTransactionMessagePageInfo = new PageInfo<>(rpTransactionMessages);
        return rpTransactionMessagePageInfo;
    }

    @Override
    public PageInfo<RpTransactionMessage> listPageMap(PageParam<Map<String, Object>> pageParam) throws MessageBizException {
        Map<String, Object> paramMap = pageParam.getData();
        if (paramMap == null) {
            paramMap = Collections.EMPTY_MAP;
        }
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<RpTransactionMessage> rpTransactionMessages = rpTransactionMessageMapper.listPage(paramMap);
        PageInfo<RpTransactionMessage> rpTransactionMessagePageInfo = new PageInfo<>(rpTransactionMessages);
        return rpTransactionMessagePageInfo;
    }
}
