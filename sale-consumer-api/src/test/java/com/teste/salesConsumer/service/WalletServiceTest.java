package com.teste.salesConsumer.service;
 

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import com.teste.salesConsumer.entities.Wallet;
import com.teste.salesConsumer.entities.WalletType;
import com.teste.salesConsumer.exception.WalletNotFoundxception;
 
@SpringBootTest
class WalletServiceTest {

	
	@Mock
	private WalletService walletService; 
	
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class); 
    }



    @Test
    void testFindById_WalletNotFound() { 
        Long idWallet = 1L; 
        Mockito.when(walletService.findById(Mockito.any())).thenThrow(new WalletNotFoundxception("", HttpStatus.NOT_FOUND));
        assertThrows(WalletNotFoundxception.class, () -> walletService.findById(idWallet));
    }
    
    @Test
    void testFindById() {        
        Long idWallet = 1L;
        Wallet expectedWallet = createWallet(1L);        
        Mockito.when(walletService.findById(Mockito.any())).thenReturn(expectedWallet);       
        Wallet actualWallet = walletService.findById(idWallet);     
        assertEquals(expectedWallet, actualWallet);
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