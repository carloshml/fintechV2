package com.teste.gateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.teste.gateway.controller.dto.CreateReversalRecordDto;
import com.teste.gateway.producers.ReversalProducer;

import jakarta.validation.Valid;

@Service
public class ReversalService {
	
	private static final Logger logger = LoggerFactory.getLogger(ReversalService.class); 
	 
	private final ReversalProducer reversalProducer;
	private final SaleService saleService;

	public ReversalService(ReversalProducer rp, SaleService ss) {
		this.saleService = ss;
		this.reversalProducer = rp; 
	}

	public void createReversal(  CreateReversalRecordDto dto) {
		logger.info(">> create Transfer:" + dto);
		var sender = saleService.findById(dto.sale()); 
		reversalProducer.publishReversal(dto);
	}
	 
	
	 

}
