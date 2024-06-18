package com.teste.fintech.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.teste.fintech.entity.WalletAction;
import com.teste.fintech.entity.WalletType;
import com.teste.fintech.repository.WalletActionRepository;
import com.teste.fintech.repository.WalletTypeRepository;


@Configuration
public class Dataloader implements CommandLineRunner {
 
	private final WalletTypeRepository  walletTypeRepository; 
	private final WalletActionRepository  walletActionRepository; 
	
	public Dataloader(WalletTypeRepository wtr, WalletActionRepository  war) {
		this.walletTypeRepository = wtr;
		this.walletActionRepository = war;
	}
	
	@Override
	public void run(String... args) throws Exception {
		Arrays.stream(WalletType.Enum.values())
		 .forEach(walletType -> walletTypeRepository.save(walletType.get()));	
		
		Arrays.stream(WalletAction.Enum.values())
		 .forEach(walletType -> walletActionRepository.save(walletType.get()));	 
	}

}
