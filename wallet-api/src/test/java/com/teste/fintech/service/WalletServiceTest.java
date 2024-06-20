package com.teste.fintech.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.teste.fintech.Exception.WalletDataAlreadyExistisException;
import com.teste.fintech.controller.dto.CreateWalletDto;
import com.teste.fintech.entity.Wallet;
import com.teste.fintech.entity.WalletType;
import com.teste.fintech.repository.WalletRepository;
import com.teste.fintech.Exception.WalletNotFoundxception;

@DataJpaTest
@ActiveProfiles("test")
public class WalletServiceTest {

	@Mock
	private WalletRepository walletRepository;

	@Mock
	private WalletStatementService walletStatementService;

	@Mock
	private WalletService walletService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateWallet_AlreadyExists() {
		CreateWalletDto dto = new CreateWalletDto("John Doe", "12345678900", "john.doe@example.com", "securePassword",
				WalletType.Enum.USER);
		WalletService actualWalletService = new WalletService(walletRepository, walletStatementService);
		Mockito.when(walletRepository.findByCpfCnpjOrEmail(any(), any())).thenReturn(Optional.of(new Wallet()));
		assertThatThrownBy(() -> actualWalletService.createWallet(dto))
				.isInstanceOf(WalletDataAlreadyExistisException.class);

	}

	@Test
	void testFindAll() {
		List<Wallet> wallets = new ArrayList<>();
		Mockito.when(walletRepository.findAll()).thenReturn(wallets);
		List<Wallet> result = walletService.findAll();
		assertThat(wallets.size()).isEqualTo(result.size());
	}

	@Test
	void testFindById() {
		Long idWallet = 1L;
		Wallet expectedWallet = createWallet(1L);
		Mockito.when(walletService.findById(Mockito.any())).thenReturn(Optional.of(expectedWallet));
		Optional<Wallet> actualWallet = walletService.findById(idWallet);
		assertThat(Optional.of(expectedWallet)).isEqualTo(actualWallet);
	}

	@Test
	void testFindById_WalletNotFound() {
		Long walletId = 123L;
		Mockito.when(walletRepository.findById(walletId)).thenReturn(Optional.empty());
		WalletService actualWalletService = new WalletService(walletRepository, walletStatementService);
		assertThatThrownBy(() -> actualWalletService.findById(walletId)).isInstanceOf(WalletNotFoundxception.class);
	}

	@Test
	void test_deposit() {
	    Long idWallet = 1L;
	    Wallet expectedWallet = createWallet(1L);
	    Mockito.when(walletRepository.findById(Mockito.any())).thenReturn(Optional.of(expectedWallet));
	    Mockito.when(walletRepository.save(Mockito.any())).thenReturn(expectedWallet);	 
		WalletService actualWalletService = new WalletService(walletRepository, walletStatementService);
	    Wallet actualWallet = actualWalletService.deposit(idWallet, new BigDecimal("1000.00"));	 
	    assertThat(actualWallet).isEqualTo(expectedWallet);
	}

	@Test
	void test_withdraw() {
		Long idWallet = 1L;
		Wallet expectedWallet = createWallet(1L);
		Mockito.when(walletRepository.findById(Mockito.any())).thenReturn(Optional.of(expectedWallet));
		Mockito.when(walletRepository.save(Mockito.any())).thenReturn(expectedWallet);
		WalletService actualWalletService = new WalletService(walletRepository, walletStatementService);
		Wallet actualWallet = actualWalletService.withdraw(idWallet, new BigDecimal("1000.00"));
		assertThat(expectedWallet).isEqualTo(actualWallet);
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
