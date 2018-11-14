package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.sender.DirectSender;
import com.example.demo.sender.FanoutSender;
import com.example.demo.sender.TopicSender;

@RestController
@RequestMapping("/")
public class SendController {
	@Autowired
	private FanoutSender fanoutSender;
	@Autowired
	private TopicSender topicSender;
	@Autowired
	private DirectSender directSender;

	
	/**
	 * Fanout模式 Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息。
	 * @param user
	 * @author huangchangjin
	 * @date 2018年11月14日 上午10:28:57
	 */
	@GetMapping("sendFanout")
	public void sendFanout(User user) {
		System.err.println(user);
		if (user.getId() == null) {
			user = new User();
			user.setId("1");
			user.setName("pwl");
		}
		fanoutSender.send(user);
	}
	
	/**
	 * direct模式  消息中的路由键（routing key）如果和 Binding 中的 binding key 一致，
	 * 交换器就将消息发到对应的队列中。路由键与队列名完全匹配
	 * @param user
	 * @author huangchangjin
	 * @date 2018年11月14日 上午10:33:40
	 */
	@GetMapping("sendDirect")
	public void sendDirect(User user) {
		System.err.println(user);
		if (user.getId() == null) {
			user = new User();
			user.setId("2");
			user.setName("missjin---->direct");
		}
		directSender.send(user);
	}
	
	/**
	 * topic模式  基本思想和路由模式是一样的，只不过路由键支持模糊匹配，符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词
	 * @param msg
	 * @author huangchangjin
	 * @date 2018年11月14日 上午10:33:56
	 */
	@GetMapping("sendTopic")
	public void sendTopic(String msg) {
		System.err.println("msg is "+msg);
		topicSender.sendMsg(msg);
	}
}
