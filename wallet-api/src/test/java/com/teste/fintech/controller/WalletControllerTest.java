package com.teste.fintech.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.teste.fintech.controller.dto.CreateWalletDto;
import com.teste.fintech.entity.Wallet;
import com.teste.fintech.entity.WalletType;
import com.teste.fintech.service.WalletService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class WalletControllerTest {

	@Mock
	private WalletService walletService;

	private WalletController walletController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		walletController = new WalletController(walletService);
	}

	@Test
	void testCreateWallet_SuccessfulCreation() {
		CreateWalletDto dto = new CreateWalletDto("John Doe", "12345678900", "john.doe@example.com", "securePassword",
				WalletType.Enum.USER);
		Wallet createdWallet = createWallet(1L);
		when(walletService.createWallet(dto)).thenReturn(createdWallet);

		ResponseEntity<Wallet> response = walletController.creatWallet(dto);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(createdWallet);
	}

	@Test
	void test_getAllWallets() {
		List<Wallet> wallets = new ArrayList<>();
		when(walletService.findAll()).thenReturn(wallets);
		ResponseEntity<List<Wallet>> response = walletController.Get();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(wallets);
	}

	@Test
	void test_deposit() {
		Wallet wallet = createWallet(1L);
		when(walletService.deposit(any(), any())).thenReturn(wallet);
		ResponseEntity<Wallet> response = walletController.deposit(1L, new BigDecimal("10.00"));
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(wallet);
	}

	@Test
	void test_withdraw() {
		Wallet wallet = createWallet(1L);
		when(walletService.withdraw(any(), any())).thenReturn(wallet);
		ResponseEntity<Wallet> response = walletController.withdraw(1L, new BigDecimal("10.00"));
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(wallet);
	}
	
	@Test
	void test_GetById() {
		Wallet wallet = createWallet(1L);
		when(walletService.findById(any())).thenReturn(Optional.of(wallet));
		ResponseEntity<Wallet> response = walletController.GetById(1L);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(wallet);
	}

	private Wallet createWallet(Long id) {
		Wallet wallet = new Wallet();
		wallet.setId(id);
		wallet.setFullName("John Doe");
		wallet.setCpfCnpj("12345678900");
		wallet.setEmail("john.doe@example.com");
		wallet.setPassword("securePassword");
		wallet.setBalance(new BigDecimal("1000.00"));
		wallet.setWalletType(WalletType.Enum.USER.get());
		return wallet;
	}
}
