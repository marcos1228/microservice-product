package com.product;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.product.convert.CategoryEnumToIntegerConvet;
import com.product.convert.ClassificationEnumToIntgerConveter;
import com.product.convert.IntegerToCategoryEnumConveter;
import com.product.convert.IntegerToClassificationEnumConveter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
		log.info("Microservice de produto pronto para receber requisições!");
	}

	@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource
	      = new ReloadableResourceBundleMessageSource();
	    
	    messageSource.setBasename("classpath:messages");
	    messageSource.setDefaultEncoding("UTF-8");
	    messageSource.setCacheSeconds(10);
	    return messageSource;
	}
	
	@Bean
	public LocalValidatorFactoryBean getValidator() {
	    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	    bean.setValidationMessageSource(messageSource());
	    return bean;
	}
	@Bean
	public ModelMapper mapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(new CategoryEnumToIntegerConvet());
		modelMapper.addConverter(new IntegerToCategoryEnumConveter());
		modelMapper.addConverter(new ClassificationEnumToIntgerConveter());
		modelMapper.addConverter(new IntegerToClassificationEnumConveter());
		return modelMapper;
	}
}

