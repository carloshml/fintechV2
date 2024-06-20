package com.teste.salesConsumer.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.teste.salesConsumer.consumer.dto.CreateReversalRecordDto;
import com.teste.salesConsumer.entities.Reversal;
import com.teste.salesConsumer.entities.Sale;
import com.teste.salesConsumer.entities.Status;
import com.teste.salesConsumer.repository.ReversalRepository;

import jakarta.transaction.Transactional;

@Service
public class ReversalService {

	private static final Logger logger = LoggerFactory.getLogger(SaleService.class);

	private final ReversalRepository reversalRepository;
	private final SaleService saleService;
	private final WalletService walletService;
	private final ProductService productService;

	public ReversalService(SaleService sp, ReversalRepository rr, WalletService ws, ProductService ps) {
		this.reversalRepository = rr;
		this.saleService = sp;
		this.walletService = ws;
		this.productService = ps;
	}

	public void createReversal(CreateReversalRecordDto dto) {
		logger.info(">> begin create  reversal:");
		var sale = saleService.findById(dto.sale());
		var reversal = reversalRepository.save(dto.createReversal(sale.get(), dto.price(), dto.quantity()));
		logger.info(">> end create  reversal:");
		process(reversal);
	}

	@Transactional
	public Reversal process(Reversal dto) {
		logger.info(">> begin process  Reversal:");
		Sale sale = dto.getSale();
		walletService.deposit(sale.getPayer().getId(), dto.getPrice());
		walletService.withdraw(sale.getProduct().getOwner().getId(), dto.getPrice());
		productService.encrease(sale.getProduct().getId(), dto.getQuantity());		
		sale.setSaleStatus(Status.Enum.CANCELED.get());
		saleService.save(sale);
		dto.setStatus(Status.Enum.EXECUTED.get());
		reversalRepository.save(dto);
		logger.info(">> end  process  Reversal:");
		return dto;
	}

	public List<Reversal> findAll() {
		return reversalRepository.findAll();
	}

}
