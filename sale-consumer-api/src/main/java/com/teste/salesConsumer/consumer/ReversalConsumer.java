package com.teste.salesConsumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.teste.salesConsumer.consumer.dto.CreateReversalRecordDto;
import com.teste.salesConsumer.service.ReversalService;


@Component 
public class ReversalConsumer {

	private static final Logger logger = LoggerFactory.getLogger(ReversalConsumer.class);

	private final ReversalService reversalService;

	public ReversalConsumer(ReversalService ss) {
		this.reversalService = ss;
	}

	@RabbitListener(queues = "${broker.queue.reversal.name}")
	public void createReversal(@Payload CreateReversalRecordDto dto) {
		logger.info(">> create sale:" + dto);
		reversalService.createReversal(dto);
	}

}
