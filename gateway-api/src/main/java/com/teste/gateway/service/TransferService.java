package com.teste.gateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.teste.gateway.Exception.InsufitientBalanceException;
import com.teste.gateway.Exception.TransferNotAllowerForWalletTypeException;
import com.teste.gateway.controller.dto.TransferRecordDto;
import com.teste.gateway.entity.Wallet;
import com.teste.gateway.producers.TransferProducer;
 

@Service
public class TransferService {
	
	private static final Logger logger = LoggerFactory.getLogger(TransferService.class); 
	
	private final TransferProducer transferProducer;
	private final WalletService walletService;
	

	public TransferService( TransferProducer tp, WalletService wp ) {
		transferProducer = tp;
		walletService = wp;
	}

	public void createTransfer(TransferRecordDto dto) {
		logger.info(">> create Transfer:" + dto); 
		var sender = walletService.findById(dto.payer());
		var receiver = walletService.findById(dto.payee());		
		validateTransfer(dto, sender); 
		transferProducer.publishTransfer(dto);	 
	}
	
	private void validateTransfer(TransferRecordDto transferDto, Wallet sender) {

		if (!sender.isTransferAllawedForWalletType()) {
			throw new TransferNotAllowerForWalletTypeException();
		}

		if (!sender.isBalanceGreaterOrEqualThan(transferDto.value())) {
			throw new InsufitientBalanceException(transferDto.value());
		} 
	}

}
