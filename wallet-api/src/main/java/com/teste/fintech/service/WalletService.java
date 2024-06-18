package com.teste.fintech.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.teste.fintech.Exception.WalletDataAlreadyExistisException;
import com.teste.fintech.Exception.WalletNotFoundxception;
import com.teste.fintech.controller.dto.CreateWalletDto;
import com.teste.fintech.entity.Wallet;
import com.teste.fintech.repository.WalletRepository;

import jakarta.transaction.Transactional;

@Service
public class WalletService {
	private static final Logger logger = LoggerFactory.getLogger(WalletService.class);
	private final WalletRepository walletRepository;
	private final WalletStatementService walletStatementService;
 

	public WalletService(WalletRepository wr, WalletStatementService wss) {
		this.walletRepository = wr;	 
		this.walletStatementService = wss;
	}

	public Wallet createWallet(CreateWalletDto dto) {
		var walletDb = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());

		if (walletDb.isPresent()) {
			throw new WalletDataAlreadyExistisException("CPF CNPJ or E-mail Exists");
		}
		return walletRepository.save(dto.toWallet());
	}

	public List<Wallet> findAll() {
		return walletRepository.findAll();
	}

	public Optional<Wallet> findById(Long id) {
		var resp = walletRepository.findById(id);
		if (!resp.isPresent())
			throw new WalletNotFoundxception(id, HttpStatus.NOT_FOUND);
		return resp;

	}

	@Transactional
	public Wallet deposit(Long id, BigDecimal value) {
		logger.info(">> deposit " + value + "fom wallet id" + id);
		try {
			var resp = walletRepository.findById(id);
			if (!resp.isPresent())
				throw new WalletNotFoundxception(id, HttpStatus.NOT_FOUND);	 
			walletStatementService.saveDeposit(resp.get(), value);
			resp.get().credit(value);
			return walletRepository.save(resp.get());
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public Wallet withdraw(Long id, BigDecimal value) {
		logger.info(">> withdraw " + value + "fom wallet id" + id);
		try {
			var resp = walletRepository.findById(id);
			if (!resp.isPresent())
				throw new WalletNotFoundxception(id, HttpStatus.NOT_FOUND);			
			walletStatementService.saveWithdraw(resp.get(), value);
			resp.get().debit(value);
			return walletRepository.save(resp.get());
		} catch (Exception e) {
			throw e;
		}
	}

}
