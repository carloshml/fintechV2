package com.teste.gateway.producers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.teste.gateway.controller.dto.CreateReversalRecordDto;

import jakarta.validation.Valid;

@Component
public class ReversalProducer {
	private static final Logger logger = LoggerFactory.getLogger(SaleProducer.class);
	
	final RabbitTemplate rabbitTemplate;
	
	public ReversalProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Value("${broker.queue.reversal.name}")
	private String routingreversal;

	public void publishReversal(@Valid CreateReversalRecordDto dto) {
		logger.info(">> publishSale reversal:" + dto);
		rabbitTemplate.convertAndSend("", routingreversal, dto);
	}

}
