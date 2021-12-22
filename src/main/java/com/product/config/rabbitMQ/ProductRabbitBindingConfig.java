package com.product.config.rabbitMQ;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.var;

@Configuration
public class ProductRabbitBindingConfig {
	@Value("${product.inative.queue}")
	private String queue;


	@Resource(name = "productRabbitAdmin")
	private RabbitAdmin productRabbitAdmin;

	@PostConstruct
	public void rabbitInit() {
		this.registerQueue(this.queue);
	}

	private void registerQueue(String queueName) {
		var queue = new Queue(queueName);
		productRabbitAdmin.declareQueue(queue);

	}
}
