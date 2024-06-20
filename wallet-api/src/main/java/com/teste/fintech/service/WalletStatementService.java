package com.teste.fintech.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.teste.fintech.Exception.WalletStatmentNotFoundException;
import com.teste.fintech.entity.Wallet;
import com.teste.fintech.entity.WalletAction;
import com.teste.fintech.entity.WalletStatement;
import com.teste.fintech.repository.WalletStatmentRepository;

@Service
public class WalletStatementService {
	private static final Logger logger = LoggerFactory.getLogger(WalletStatementService.class);

	private final WalletStatmentRepository walletStatmentRepository; 

	public WalletStatementService(WalletStatmentRepository wsr) {
		this.walletStatmentRepository = wsr;
	}

	public void saveDeposit(Wallet w, BigDecimal value) {
		var walletStatement = new WalletStatement(w, WalletAction.Enum.DEPOSIT.get(), w.getBalance(),
				w.getBalance().add(value), value);
		walletStatmentRepository.save(walletStatement);
	}

	public void saveWithdraw(Wallet w, BigDecimal value) {
		var walletStatement = new WalletStatement(w, WalletAction.Enum.WITHDRAW.get(), w.getBalance(),
				w.getBalance().subtract(value), value);
		walletStatmentRepository.save(walletStatement);
	}

	public Optional<List<WalletStatement>> findByWallet(Long walletId) {
		var wallet = new Wallet();
		wallet.setId(walletId);
		Optional<List<WalletStatement>> wsList = walletStatmentRepository.findByWallet(wallet);
		if (!wsList.isPresent())
			throw new WalletStatmentNotFoundException(walletId, HttpStatus.NOT_FOUND);		
		return wsList;
	}

}
