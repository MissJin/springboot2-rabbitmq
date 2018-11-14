package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
	// 管理后台的安装：
	// 1.先安装erlang 下载地址【http://www.erlang.org/downloads】 ,配置环境变量 
	// 2.下载并安装RabbitMQ【可以下载window二进制文件，解压就可以用了】
	// 启动后台管理：
	// 1.先安装后台管理界面——>进入sbin目录——>执行【rabbitmq-plugins enable rabbitmq_management】安装管理后台——>再启动服务【rabbitmq-server.bat】
	// 2.访问【localhost:15672】[账号：guest 密码：guest]
	
	
	// topic 【通配符模式 】【#代表1个或多个， *代表一个】
	public static final String TOPIC_EXCHANGE = "topic.exchange";
	public static final String TOPIC_QUEUE1 = "topic.queue1";
	public static final String TOPIC_QUEUE2 = "topic.queue2";

	// fanout 【广播模式 】【所有的交换机中的队列都会收到消息】
	public static final String FANOUT_EXCHANGE = "fanout.exchange";
	public static final String FANOUT_QUEUE1 = "fanout.queue1";
	public static final String FANOUT_QUEUE2 = "fanout.queue2";

	// redirect 【路由模式】【完全匹配交换内的路由键值】
	public static final String DIRECT_EXCHANGE = "direct.exchange";
	public static final String DIRECT_QUEUE1 = "direct.queue1";
	public static final String DIRECT_QUEUE2 = "direct.queue2";

	/**
	 * Topic模式
	 *
	 * @return
	 */
	@Bean
	public Queue topicQueue1() {
		return new Queue(TOPIC_QUEUE1);
	}

	@Bean
	public Queue topicQueue2() {
		return new Queue(TOPIC_QUEUE2);
	}
	@Bean
	public Queue topicQueue3() {
		return new Queue("hcj.test-queue");
	}

	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(TOPIC_EXCHANGE);
	}
	
	@Bean
	public TopicExchange topicTestExchange() {
		return new TopicExchange("hcj.topic");
	}

	@Bean
	public Binding topicBinding1() {
		return BindingBuilder.bind(topicQueue1())
				.to(topicExchange())
				.with("lzc.message");
	}

	@Bean
	public Binding topicBinding2() {
		return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("lzc.#");
	}
	
	@Bean
	public Binding topicBinding3() {
		return BindingBuilder.bind(topicQueue3())
				.to(topicTestExchange())
				.with("hcj.#");
	}

	/**
	 * Fanout模式 Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息。
	 * 
	 * @return
	 */
	@Bean
	public Queue fanoutQueue1() {
		return new Queue(FANOUT_QUEUE1);
	}

	@Bean
	public Queue fanoutQueue2() {
		return new Queue(FANOUT_QUEUE2);
	}

	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange(FANOUT_EXCHANGE);
	}

	@Bean
	public Binding fanoutBinding1() {
		return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
	}

	@Bean
	public Binding fanoutBinding2() {
		return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
	}

	/**
	 * direct模式 消息中的路由键（routing key）如果和 Binding 中的 binding key 一致，
	 * 交换器就将消息发到对应的队列中。路由键与队列名完全匹配
	 * 
	 * @return
	 */
	@Bean
	public Queue directQueue1() {
		return new Queue(DIRECT_QUEUE1);
	}

	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange(DIRECT_EXCHANGE);
	}

	@Bean
	public Binding directBinding1() {
		return BindingBuilder.bind(directQueue1()).to(directExchange()).with("direct.pwl");
	}

}
