package com.spring.pay.service.message.send;

public interface MessageSend {

    public void sendMessage(String queue,String routingKey,String message);
}
