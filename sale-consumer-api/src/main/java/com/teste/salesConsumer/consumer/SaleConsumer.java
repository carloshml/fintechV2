package com.teste.salesConsumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.teste.salesConsumer.consumer.dto.CreateSaleRecordDto;
import com.teste.salesConsumer.service.SaleService;


@Component 
public class SaleConsumer {

	private static final Logger logger = LoggerFactory.getLogger(SaleConsumer.class);

	private final SaleService saleService;

	public SaleConsumer(SaleService ss) {
		this.saleService = ss;
	}

	@RabbitListener(queues = "${broker.queue.sale.name}")
	public void listEmailQueues(@Payload CreateSaleRecordDto dto) {
		logger.info(">> create sale:" + dto);
		 saleService.createASale(dto);
	}

}
