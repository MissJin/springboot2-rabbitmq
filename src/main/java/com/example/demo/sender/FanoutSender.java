package com.example.demo.sender;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.config.RabbitConfig;
import com.example.demo.entity.User;
 
@Component
public class FanoutSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;
 
    public void send(User user) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.FANOUT_EXCHANGE, "", user);
    }
}

