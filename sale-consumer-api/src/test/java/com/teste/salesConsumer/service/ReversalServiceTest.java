package com.teste.salesConsumer.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import com.teste.salesConsumer.entities.Product;
import com.teste.salesConsumer.entities.Reversal;
import com.teste.salesConsumer.entities.Sale;
import com.teste.salesConsumer.entities.Status;
import com.teste.salesConsumer.entities.Wallet;
import com.teste.salesConsumer.entities.WalletType;
import com.teste.salesConsumer.repository.ReversalRepository;

@SpringBootTest
public class ReversalServiceTest {

	BigDecimal price = new BigDecimal("100.00");

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
	@Description("Testando a funcao Process")
	public void testProcess() {

		var quantity = new BigDecimal("100.00");
		var reversal = createReversal();
		var sale = reversal.getSale();
 
		// Simule o comportamento dos serviços
		when(walletService.deposit(sale.getPayer().getId(), price)).thenReturn(sale.getPayer().debit(price));
		when(walletService.withdraw(sale.getProduct().getOwner().getId(), price)).thenReturn(sale.getProduct().getOwner().credit(price));
		when(productService.encrease(createProduct().getId(), quantity)).thenReturn(sale.getProduct());
		when(saleService.save(createSale())).thenReturn(createSale());
		when(reversalRepository.save(createReversal())).thenReturn(createReversal());

		// Chame o método process
		Reversal retorno = reversalService.process(createReversal());

		assertThat(retorno.getStatus()).isEqualTo(Status.Enum.EXECUTED.get());
	}

	private Product createProduct() {
		Product product = new Product();
		// Set properties for the product
		product.setId(2L);
		product.setPrice(price);
		product.setOwner(createWallet(1L));
		product.setQuantity(new BigDecimal("1000.0"));
		product.setName("Sample Product");
		return product;
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

	private Sale createSale() {
		return new Sale(createProduct(), createWallet(2L), new BigDecimal("1000.00"), new BigDecimal("1000.00"));

	}

	private Reversal createReversal() {
		return new Reversal(createSale(), new BigDecimal("1000.00"), new BigDecimal("1000.00"));
	}

}
