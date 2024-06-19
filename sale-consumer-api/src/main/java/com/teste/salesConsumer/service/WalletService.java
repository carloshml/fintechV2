package com.teste.salesConsumer.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.teste.salesConsumer.entities.Wallet;
import com.teste.salesConsumer.exception.WalletNotFoundxception;
import com.teste.salesConsumer.repository.WalletRepository;

 

@Service
public class WalletService {

	private static final Logger logger = LoggerFactory.getLogger(WalletService.class); 
	
	private final WalletRepository walletRepository;
 

	public WalletService(WalletRepository wr) {
		this. walletRepository = wr;
	}

	public Optional<Wallet> findById(Long idWallet) {
		var resp = walletRepository.findById(idWallet);
		if (!resp.isPresent())
			throw new WalletNotFoundxception("Wallet id:" + idWallet + " doesn't exist ",
					HttpStatus.UNPROCESSABLE_ENTITY);
		return resp;

	}

	public Wallet save(Wallet wallet) {
		return walletRepository.save(wallet);		
	}

}
