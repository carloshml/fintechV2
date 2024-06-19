package com.teste.salesConsumer.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.teste.salesConsumer.consumer.dto.CreateSaleRecordDto;
import com.teste.salesConsumer.entities.Sale;
import com.teste.salesConsumer.repository.SaleRepository;

import jakarta.transaction.Transactional;

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

		payer.get().debit(dto.price());
		product.get().getOwner().credit(dto.price());
		walletService.save(payer.get());
		walletService.save(product.get().getOwner());

		product.get().debit(dto.quantity());
		productService.save(product.get());

		saleRepository.save(dto.createSale(product.get(), payer.get(), dto.price(), dto.quantity()));
		logger.info(">> end create  sales:");
	}

	public List<Sale> findAll() {
		return saleRepository.findAll();
	}

}
