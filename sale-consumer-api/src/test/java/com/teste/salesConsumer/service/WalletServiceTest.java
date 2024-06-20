package com.teste.salesConsumer.service;
 

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.teste.salesConsumer.entities.Wallet;
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
        // Arrange
        Long idWallet = 1L;
        Wallet expectedWallet = new Wallet(/* initialize with relevant data */);
        
        Mockito.when(walletService.findById(Mockito.any())).thenReturn(expectedWallet);
        
      

        // Act
        Wallet actualWallet = walletService.findById(idWallet);

     
        assertEquals(expectedWallet, actualWallet);
    }

     

} 