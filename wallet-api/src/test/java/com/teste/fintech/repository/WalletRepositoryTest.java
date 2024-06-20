package com.teste.fintech.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.teste.fintech.controller.dto.CreateWalletDto;
import com.teste.fintech.entity.Wallet;
import com.teste.fintech.entity.WalletType;

@DataJpaTest
@ActiveProfiles("test")
class WalletRepositoryTest {

	@Autowired
	WalletRepository walletRepository;
	
	@Autowired
	WalletTypeRepository walletTypeRepository; 

	@Test
	@DisplayName("Should return an wallet sucessfully ")
	void findWalletSucess() {
		CreateWalletDto dto = new CreateWalletDto("Carlos", "205.881.120-89", "email@mock.com", "1234",
				WalletType.Enum.USER);
		createWalletType();
		createWallet(dto.toWallet());
		Optional<Wallet> result = walletRepository.findByCpfCnpjOrEmail("205.881.120-89", "");
		assertThat(result.isPresent()).isTrue();
	}
	
	@Test
	@DisplayName("Should not return an wallet  ")
	void findWalletError() {  
		Optional<Wallet> result = walletRepository.findByCpfCnpjOrEmail("205.881.120-89", "");
		assertThat(result.isEmpty()).isTrue();
	}

	private Wallet createWallet(Wallet wallet) {
		return walletRepository.save(wallet);
	}
	
	private void createWalletType() {
		Arrays.stream(WalletType.Enum.values())
		 .forEach(walletType -> walletTypeRepository.save(walletType.get()));	
	}

}
