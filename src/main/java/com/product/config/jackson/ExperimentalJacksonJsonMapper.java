package com.product.config.jackson;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.type.LogicalType;

@Configuration
public class ExperimentalJacksonJsonMapper extends Jackson2JsonMessageConverter {
	@Override
	public Object fromMessage(Message message, Object conversionHint) {
		message.getMessageProperties().setContentType("application/json");
		return super.fromMessage(message, conversionHint);
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
		return builder -> builder.postConfigurer(objectMapper -> {
			objectMapper.coercionConfigFor(LogicalType.Enum).setCoercion(CoercionInputShape.EmptyString,
					CoercionAction.AsNull);
		});
	}

}
