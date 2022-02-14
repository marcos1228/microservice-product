package com.product.sender;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.product.domain.model.Product;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class ProductDeleteQueueSender {
	
	@Resource(name = "productRabbitTemplate")
	private RabbitTemplate rabbitTemplate;

	@Value("${product.inative.queue}")
	private String queue;

	public void sendMessage(Product product) {
		try {
			rabbitTemplate.convertAndSend(this.queue ,product);
			log.info("Method={} IdProduct={}", "sendMessage", product.getId());
		} catch (Exception e) {
			log.info("Method={} cause={} message={}", "sendMessage", e.getCause(), e.getMessage());
		}
	}
}
