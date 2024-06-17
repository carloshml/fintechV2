package com.teste.fintech.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.teste.fintech.Exception.TransferNotAllowerForWalletTypeException;
import com.teste.fintech.Exception.TrasnferNotAuthorizedException;
import com.teste.fintech.Exception.InsufitientBalanceException;
import com.teste.fintech.Exception.WalletNotFoundxception; 
import com.teste.fintech.controller.dto.TransferRecordDto;
import com.teste.fintech.entity.Transfer;
import com.teste.fintech.entity.Wallet;
import com.teste.fintech.repository.TransferRepository;
import com.teste.fintech.repository.WalletRepository;

import jakarta.transaction.Transactional;

@Service
public class TransferService {

	private final TransferRepository transferRepository;
	private final WalletRepository walletRepository;

	public TransferService(TransferRepository trasnferRepository, WalletRepository walletRepository) {

		this.transferRepository = trasnferRepository;
		this.walletRepository = walletRepository;
	}

	// @Transactional para garantir que o commit no banco só será feito se não
	// ocorrer nenhuma exceção
	@Transactional
	public Transfer transfer(TransferRecordDto transferDto) {
		var sender = walletRepository.findById(transferDto.payer())
				.orElseThrow(() -> new WalletNotFoundxception(transferDto.payer()));
		var receiver = walletRepository.findById(transferDto.payee())
				.orElseThrow(() -> new WalletNotFoundxception(transferDto.payee()));
	
		sender.debit(transferDto.value());
		receiver.credit(transferDto.value());
		var transfer = new Transfer(sender, receiver, transferDto.value());
		walletRepository.save(sender);
		walletRepository.save(receiver);
		var transferResult = transferRepository.save(transfer);

		CompletableFuture.runAsync(() -> {
			 // enviar algum tipo de notificação
			 // e-mail ou chamar o serviço de notificação 
		});

		return transferResult;
	}

	

	public List<Transfer> findAll() {
		return transferRepository.findAll();
	}

}
