package com.spring.pay.service.message.send.impl;

import com.spring.pay.service.message.send.MessageSend;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqMessageSend implements MessageSend, RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void sendMessage(String queue, String routingKey, String message) {
//        Message msg = new Message(message.getBytes(),null);
        rabbitTemplate.convertAndSend(queue, routingKey, message);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("********************************************************");
        System.out.println("exChange确认" + ack + "   " + cause);
        System.out.println("********************************************************");
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("********************************************************");
        System.out.println("失败确认:" + message + " | " + replyCode + " | " + replyText + " | " + exchange + " | " + routingKey);
        System.out.println("********************************************************");

    }
}
