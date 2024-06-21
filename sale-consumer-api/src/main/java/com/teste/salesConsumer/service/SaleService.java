package com.teste.salesConsumer.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

 
import com.teste.salesConsumer.consumer.dto.CreateSaleRecordDto;
import com.teste.salesConsumer.entities.Sale;
import com.teste.salesConsumer.exception.FintechException;
import com.teste.salesConsumer.exception.WalletNotFoundxception;
import com.teste.salesConsumer.repository.SaleRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

@Service
public class SaleService {

	private static final Logger logger = LoggerFactory.getLogger(SaleService.class);

	private final SaleRepository saleRepository;
	private final WalletService walletService;
	private final ProductService productService;

	public SaleService(SaleRepository sp, WalletService ws, ProductService ps) {
		this.saleRepository = sp;
		this.walletService = ws;
		this.productService = ps;
	}

	@Transactional
	public void createASale(CreateSaleRecordDto dto) {
		logger.info(">> begin create  sales:");
		var product = productService.findById(dto.product());
		var payer = walletService.findById(dto.payer());
		walletService.withdraw(payer.getId(), dto.price());
		walletService.deposit(product.getOwner().getId(),dto.price());		 
		productService.reduce(product.getId(),dto.quantity());
		this.save(dto.createSale(product, payer, dto.price(), dto.quantity()));
		logger.info(">> end create  sales:");
	}

	public List<Sale> findAll() {
		return saleRepository.findAll();
	}

	public Sale save(Sale sale) {
		return saleRepository.save(sale);
	}

	public Optional<Sale>  findById(Long id) {
		Optional<Sale> sale = saleRepository.findById(id);
		if (!sale.isPresent())
			throw new WalletNotFoundxception("Sale  não existe", HttpStatus.NOT_FOUND);
		return sale;
	}

}
