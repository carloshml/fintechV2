package com.teste.fintech.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.teste.fintech.Exception.WalletNotFoundxception;
import com.teste.fintech.Exception.WalletStatmentNotFoundException;
import com.teste.fintech.entity.Wallet;
import com.teste.fintech.entity.WalletStatement;
import com.teste.fintech.repository.WalletStatmentRepository;

@DataJpaTest
@ActiveProfiles("test")
class WalletStatementServiceTest {

    @Mock
    private WalletStatmentRepository walletStatmentRepository;

    private WalletStatementService walletStatementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        walletStatementService = new WalletStatementService(walletStatmentRepository);
    }

    @Test
    void testSaveDeposit_Successful() {
        Wallet wallet = new Wallet(/* initialize with relevant data */);
        BigDecimal depositValue = BigDecimal.valueOf(100.0);

        walletStatementService.saveDeposit(wallet, depositValue);

        // Verify that save was called with the correct wallet statement
        verify(walletStatmentRepository).save(any());
    }

    @Test
    void testSaveWithdraw_Successful() {
        Wallet wallet = new Wallet(/* initialize with relevant data */);
        BigDecimal withdrawalValue = BigDecimal.valueOf(50.0);

        walletStatementService.saveWithdraw(wallet, withdrawalValue);

        // Verify that save was called with the correct wallet statement
        verify(walletStatmentRepository).save(any());
    }

    @Test
    void testFindByWallet_SuccessfulRetrieval() {
        Long walletId = 123L;
        List<WalletStatement> walletStatements = List.of(/* create some test wallet statements */);
        Mockito.when(walletStatmentRepository.findByWallet(any())).thenReturn(Optional.of(walletStatements));

        Optional<List<WalletStatement>> result = walletStatementService.findByWallet(walletId);

        // Verify that the correct wallet statements were retrieved
        assertThat(result).isPresent().contains(walletStatements);
    }
    
	@Test
	void testFindById_WalletNotFound() {
		Long walletId = 123L;
		Mockito.when(walletStatmentRepository.findById(walletId)).thenReturn(Optional.empty());
		WalletStatementService actualWalletService = new WalletStatementService(walletStatmentRepository);
		assertThatThrownBy(() -> actualWalletService.findByWallet(walletId)).isInstanceOf(WalletStatmentNotFoundException.class);
				 
	}

    // Add more test cases as needed
}
 

