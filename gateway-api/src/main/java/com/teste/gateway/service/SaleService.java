package com.teste.gateway.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.teste.gateway.Exception.WalletNotFoundxception;

import com.teste.gateway.controller.dto.CreateSaleRecordDto;
import com.teste.gateway.controller.dto.TransferRecordDto;

import jakarta.validation.Valid;

import com.teste.gateway.entity.Sale;
import com.teste.gateway.producers.TransferProducer;
import com.teste.gateway.producers.SaleProducer;

@Service
public class SaleService {

	private static final Logger logger = LoggerFactory.getLogger(WalletService.class);

	@Value("${url.produto.api.host}")
	private String urlProdutoApi;

	private final WalletService walletService;
	private final ProductService productService;
	private final SaleProducer saleProducer;

	public SaleService(WalletService wp, SaleProducer sp, ProductService ps) {
		this.walletService = wp;
		this.productService = ps;
		this.saleProducer = sp;
	}

	public void createSale(CreateSaleRecordDto dto) {
		logger.info(">> create Transfer:" + dto);
		var sender = walletService.findById(dto.payer());	 
		var product = productService.findById(dto.product());	 
		saleProducer.publishSale(dto);
	}

}
