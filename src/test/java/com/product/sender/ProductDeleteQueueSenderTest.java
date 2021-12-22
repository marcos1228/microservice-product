package com.product.sender;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import com.product.feature.ScenarioFactory;

import lombok.var;
@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
public class ProductDeleteQueueSenderTest {
	@InjectMocks
	ProductDeleteQueueSender productDeleteQueueSender;

	@Mock
	RabbitTemplate rabbitTemplate;

	@Test
	public void sendMessage_WhenTheMessageIsPosted_ExpectedSucess() {
		var product = ScenarioFactory.newProduct();
		var text = "test";
		var queue = "queue";
		ReflectionTestUtils.setField(productDeleteQueueSender, queue, text);
		productDeleteQueueSender.sendMessage(product);
		verify(rabbitTemplate, times(1)).convertAndSend(text,product);
	}

	@Test
	public void sendMessage_WhenTheMessageIsNotPublished_ExpectedSucess() {
		doThrow(new RuntimeException("TESTE")).when(rabbitTemplate).convertAndSend(null, ScenarioFactory.newProduct());
		productDeleteQueueSender.sendMessage(ScenarioFactory.newProduct());
		verify(rabbitTemplate, times(1)).convertAndSend(null, ScenarioFactory.newProduct());
	}
}
