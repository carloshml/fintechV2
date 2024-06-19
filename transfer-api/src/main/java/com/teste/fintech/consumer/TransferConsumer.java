package com.teste.fintech.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.teste.fintech.controller.dto.TransferRecordDto;
import com.teste.fintech.service.TransferService;

@Component 
public class TransferConsumer {

	private static final Logger logger = LoggerFactory.getLogger(TransferConsumer.class);

	private final TransferService transferService;

	public TransferConsumer(TransferService transferService) {
		this.transferService = transferService;
	}

	@RabbitListener(queues = "${broker.queue.transfer.name}")
	public void listEmailQueues(@Payload TransferRecordDto dto) {
		logger.info(">> create Transfer:" + dto);
		transferService.transfer(dto);

	}

}
