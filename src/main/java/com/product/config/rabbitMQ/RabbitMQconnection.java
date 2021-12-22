package com.product.config.rabbitMQ;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.product.config.jackson.ExperimentalJacksonJsonMapper;

import lombok.var;

@Configuration
public class RabbitMQconnection {

	@Bean(name = "productConnectionFactory")
	@Primary
	public CachingConnectionFactory ProductConnectFactory(@Value("${product.rabbitmq.host}") String host,
			@Value("${product.rabbitmq.port}") int port, @Value("${product.rabbitmq.username}") String username,
			@Value("${product.rabbitmq.password}") String password) {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(host);
		connectionFactory.setPort(port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		return connectionFactory;
	}

	@Bean(name = "productContainerFactory")
	public SimpleRabbitListenerContainerFactory productContainerFactory(
			@Qualifier("productConnectionFactory") ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setAutoStartup(true);
		factory.setMissingQueuesFatal(false);
		factory.setStartConsumerMinInterval(3000L);
		factory.setRecoveryInterval(15000L);
		factory.setChannelTransacted(true);
		factory.setMessageConverter(new ExperimentalJacksonJsonMapper());
		return factory;
	}

	@Bean(name = "productRabbitTemplate")
	@Primary
	public RabbitTemplate orderRabbitTemplate(
			@Qualifier("productConnectionFactory") ConnectionFactory connectionFactory) {
		final var rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(new ExperimentalJacksonJsonMapper());
		return rabbitTemplate;
	}

	@Bean(name = "productRabbitAdmin")
	@Primary
	public RabbitAdmin productRabbitAdmin(@Qualifier("productConnectionFactory") ConnectionFactory connectionFactory) {
		RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
		rabbitAdmin.setAutoStartup(true);
		return rabbitAdmin;
	}
}
