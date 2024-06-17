package com.teste.fintech;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.teste.fintech.repository.TransferRepository;
import com.teste.fintech.repository.WalletRepository;
import com.teste.fintech.service.TransferService;

@SpringBootTest
public class TransferServiceTest {

	 

	@Mock
	private TransferRepository transferRepository;

	@Mock
	private WalletRepository walletRepository;

 
	@InjectMocks
	private TransferService transferService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

 

 

	// Add more test cases for other scenarios (e.g., unauthorized transfer, wallet
	// not found, etc.)
}
