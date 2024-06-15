package com.teste.fintech.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RestController;

import com.teste.fintech.controller.dto.TransferDto;
import com.teste.fintech.service.TransferService;

@RestController
public class TransferConsumer {

	private final TransferService transferService;

	public TransferConsumer(TransferService transferService) {
		this.transferService = transferService;
	}

	@RabbitListener(queues = "${broker.queue.transfer.name}")
	public void listEmailQueues(@Payload TransferDto dto) {
		var resp = transferService.transfer(dto);

	}

}
