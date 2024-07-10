package com.teste.gateway.producers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.teste.gateway.controller.dto.CreateSaleRecordDto;

 

@Component
public class SaleProducer {
	private static final Logger logger = LoggerFactory.getLogger(SaleProducer.class);
	final RabbitTemplate rabbitTemplate;

	public SaleProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Value("${broker.queue.sale.name}")
	private String routingSale;
	
	


	public void publishSale(CreateSaleRecordDto dto) {  
		logger.info(">> publishSale sale:" + dto); 
		rabbitTemplate.convertAndSend("", routingSale, dto);
	}
	
	 

}
