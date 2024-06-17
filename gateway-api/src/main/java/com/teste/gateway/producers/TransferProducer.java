package com.teste.gateway.producers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.teste.gateway.entity.TransferRecordDto;

 

@Component
public class TransferProducer {
	private static final Logger logger = LoggerFactory.getLogger(TransferProducer.class);
	final RabbitTemplate rabbitTemplate;

	public TransferProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Value("${broker.queue.email.name}")
	private String routingKey;

	public void publishTransfer(TransferRecordDto dto) {  
		logger.info(">> publishTransfer Transfer:" + dto); 
		rabbitTemplate.convertAndSend("", routingKey, dto);
	}

}
