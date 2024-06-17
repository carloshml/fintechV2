package com.teste.gateway.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RabbitMQConfig {
	
	@Bean
	public Jackson2JsonMessageConverter mensageConverter() {
		var objMapper = new ObjectMapper();
		return  new Jackson2JsonMessageConverter(objMapper);
	}

}
