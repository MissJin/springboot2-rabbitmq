package com.example.demo.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.demo.config.RabbitConfig;
import com.example.demo.entity.User;

@Component
public class TopicReceiver {
	// queues是指要监听的队列的名字
	@RabbitListener(queues = RabbitConfig.TOPIC_QUEUE1)
	public void receiveTopic1(User user) {
		System.out.println("【receiveTopic1监听到消息】" + user.toString());
	}

	@RabbitListener(queues = RabbitConfig.TOPIC_QUEUE2)
	public void receiveTopic2(User user) {
		System.out.println("【receiveTopic2监听到消息】" + user.toString());
	}
	
	@RabbitListener(queues = "hcj.test-queue")
	public void receiveTopic3(String msg) {
		System.out.println("【receiveTopic3监听到消息】" + msg);
	}
}
