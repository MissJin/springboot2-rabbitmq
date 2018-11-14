package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.User;
import com.example.demo.sender.DirectSender;
import com.example.demo.sender.FanoutSender;
import com.example.demo.sender.TopicSender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {


	@Autowired
	private FanoutSender fanoutSender;
	@Autowired
	private TopicSender topicSender;
	@Autowired
	private DirectSender directSender;

	/**
	 * Fanout测试
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFanout() throws Exception {
		User user = new User();
		user.setId("1");
		user.setName("pwl");
		fanoutSender.send(user);
	}

	/**
	 * TOPIC测试
	 * 
	 * @throws Exception
	 */
	@Test
	public void testTopic() throws Exception {
		User user = new User();
		user.setId("1");
		user.setName("pwl");
		topicSender.send(user);
	}

	/**
	 * DIRECT测试
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDirect() throws Exception {
		User user = new User();
		user.setId("1");
		user.setName("pwl");
		directSender.send(user);
	}

}
