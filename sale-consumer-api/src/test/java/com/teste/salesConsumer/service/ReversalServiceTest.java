package com.teste.salesConsumer.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.teste.salesConsumer.consumer.dto.CreateReversalRecordDto;
import com.teste.salesConsumer.entities.Product;
import com.teste.salesConsumer.entities.Reversal;
import com.teste.salesConsumer.entities.Sale;
import com.teste.salesConsumer.entities.Status;
import com.teste.salesConsumer.entities.Wallet;
import com.teste.salesConsumer.entities.WalletType;
import com.teste.salesConsumer.repository.ReversalRepository;

@SpringBootTest
public class ReversalServiceTest {

	@InjectMocks
	private ReversalService reversalService;

	@Mock
	private ReversalRepository reversalRepository;

	@Mock
	private SaleService saleService;

	@Mock
	private WalletService walletService;

	@Mock
	private ProductService productService;

	@Test
	@DisplayName("Test creation of a reversal")
	public void testCreateReversal() {
		// Create mock data
		Wallet payer = createWallet();
		Product product = createProduct();
		BigDecimal price = new BigDecimal("100.00");
		BigDecimal quantity = new BigDecimal("100.00");
		CreateReversalRecordDto dto = new CreateReversalRecordDto(2L, price, quantity);
		Sale sale = new Sale(/* fill with relevant data */);
		Reversal expectedReversal = new Reversal(/* fill with relevant data */);

		// Mock behavior
		when(saleService.findById(Mockito.any())).thenReturn(Optional.of(sale));
		when(reversalRepository.save(Mockito.any())).thenReturn(expectedReversal);

		// Call the method
		reversalService.createReversal(dto);

		// Verify interactions		 
		verify(saleService).save(Mockito.any());
		verify(reversalRepository).save(Mockito.any());
		assertThat(product.getQuantity()).isEqualTo(new BigDecimal("1100.00"));
		assertThat(product.getOwner().getBalance()).isEqualTo(new BigDecimal("900.00"));
		assertThat(payer.getBalance()).isEqualTo(new BigDecimal("1100.00"));
		assertThat(sale.getSaleStatus()).isEqualTo(Status.Enum.CANCELED.get());
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
