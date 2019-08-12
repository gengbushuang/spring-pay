package com.spring.pay.app.message.scheduled;

public interface MessageScheduled {

    /**
     *处理待确认超时消息
     */
    public void handleWaitingConfirmTimeOutMessages();

    /**
     *处理发送中超时没有成功消费确认的信息
     */
    public void handleSendingTimeOutMessage();
}
