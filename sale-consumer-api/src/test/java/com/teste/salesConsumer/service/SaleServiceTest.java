package com.teste.salesConsumer.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.teste.salesConsumer.consumer.dto.CreateSaleRecordDto;
import com.teste.salesConsumer.entities.Product;
import com.teste.salesConsumer.entities.Sale;
import com.teste.salesConsumer.entities.Wallet;
import com.teste.salesConsumer.entities.WalletType;
import com.teste.salesConsumer.repository.SaleRepository;

@SpringBootTest
public class SaleServiceTest {

	@InjectMocks
	private SaleService saleService;

	@Mock
	private SaleRepository saleRepository;

	@Mock
	private WalletService walletService;

	@Mock
	private ProductService productService;

	@Test
	@DisplayName("Test creation of a sale record")
	public void testCreateASale() {
		CreateSaleRecordDto dto = new CreateSaleRecordDto(2L, 2L, new BigDecimal("100.00"), // Set the price
				new BigDecimal("5.0") 
		);
		Wallet payer = createWallet();
		Product product = createProduct();
		Mockito.when(productService.findById(Mockito.any())).thenReturn(product);
		Mockito.when(walletService.findById(Mockito.any())).thenReturn(payer);
		saleService.createASale(dto); 
		Mockito.verify(saleRepository).save(Mockito.any());		 
		assertThat(product.getQuantity()).isEqualTo(new BigDecimal("995.0"));
		assertThat(product.getOwner().getBalance()).isEqualTo(new BigDecimal("1100.00"));
		assertThat(payer.getBalance()).isEqualTo(new BigDecimal("900.00"));
	}

	private Product createProduct() {
		Product product = new Product();
		// Set properties for the product
		product.setId(2L);
		product.setPrice(new BigDecimal("100.00"));
		product.setOwner(createWallet());
		product.setQuantity(new BigDecimal("1000.0"));
		product.setName("Sample Product");
		return product;
	}

	private Wallet createWallet() {
		Wallet wallet = new Wallet();
		wallet.setId(2L);
		wallet.setFullName("John Doe");
		wallet.setCpfCnpj("12345678900");
		wallet.setEmail("john.doe@example.com");
		wallet.setPassword("securePassword");
		wallet.setBalance(new BigDecimal("1000.00"));
		wallet.setWalletType(WalletType.Enum.USER.get());
		return wallet;
	}
}
