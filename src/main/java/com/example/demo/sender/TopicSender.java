package com.example.demo.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.config.RabbitConfig;
import com.example.demo.entity.User;

@Component
public class TopicSender {
	@Autowired
	private AmqpTemplate rabbitTemplate;

	// 第一个参数：TopicExchange名字
	// 第二个参数：Route-Key
	// 第三个参数：要发送的内容
	public void send(User user) {
		this.rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE, "lzc.message", user);
		this.rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE, "lzc.lzc", user);
	}
	
	public void sendMsg(String msg) {
		this.rabbitTemplate.convertAndSend("hcj.topic", "hcj.message", msg);
	}
}
