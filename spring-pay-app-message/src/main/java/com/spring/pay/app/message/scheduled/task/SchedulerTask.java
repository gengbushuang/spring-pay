package com.spring.pay.app.message.scheduled.task;

import com.spring.pay.app.message.scheduled.MessageScheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTask {

    private static final Logger log = LoggerFactory.getLogger(SchedulerTask.class);

    @Autowired
    private MessageScheduled messageScheduled;

    /**
     * 处理状态为“待确认”但已超时的消息.
     */
    @Scheduled(fixedRate = 60000)
    public void waitingConfirmTimeTasks() {
        log.info("执行(处理[waiting_confirm]状态的消息)任务开始");
        try {
            messageScheduled.handleWaitingConfirmTimeOutMessages();
        } catch (Exception e) {
            log.error("waitingConfirmTimeTasks异常",e);
        }
        log.info("执行(处理[waiting_confirm]状态的消息)任务结束");
    }

    /**
     * 处理状态为“发送中”但超时没有被成功消费确认的消息
     */
    @Scheduled(fixedRate = 60000)
    public void sendingTimeTasks() {
        log.info("执行(处理[SENDING]的消息)任务开始");
        try {
            messageScheduled.handleSendingTimeOutMessage();
        } catch (Exception e) {
            log.error("sendingTimeTasks异常",e);
        }
        log.info("执行(处理[SENDING]的消息)任务结束");
    }
}
