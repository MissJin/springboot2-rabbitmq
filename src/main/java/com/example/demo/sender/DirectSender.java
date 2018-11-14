package com.example.demo.sender;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.config.RabbitConfig;
import com.example.demo.entity.User;
 
@Component
public class DirectSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
 
    public void send(User user) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.DIRECT_EXCHANGE, "direct.pwl", user);
    }
}

