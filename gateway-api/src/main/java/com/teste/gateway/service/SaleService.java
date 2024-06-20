package com.teste.gateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.teste.gateway.Exception.WalletNotFoundxception;
import com.teste.gateway.controller.dto.CreateSaleRecordDto;
import com.teste.gateway.entity.Sale;
import com.teste.gateway.producers.SaleProducer;

@Service
public class SaleService {

	private static final Logger logger = LoggerFactory.getLogger(WalletService.class);

	@Value("${url.sale.api.host}")
	private String urlSaleApi;

	private final WalletService walletService;
	private final ProductService productService;
	private final SaleProducer saleProducer;
	private RestTemplate restTemplate = new RestTemplate();
	
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

	public Sale findById(Long id) {
		String fidByIdURL = urlSaleApi + "/byId/" + id;
		logger.info(">>  Product   findById:" + fidByIdURL);
		Sale sale = null;
		try {
			ResponseEntity<Sale> response = restTemplate.exchange(fidByIdURL, HttpMethod.GET, null, Sale.class);

			if (response.getStatusCode() == HttpStatus.OK) {
				sale = response.getBody();
			} else {
				throw new WalletNotFoundxception("Sale id:" + id + " doesn't exist ",
						HttpStatus.UNPROCESSABLE_ENTITY);
			}
		} catch (HttpClientErrorException e) {
			throw new WalletNotFoundxception(e.getMessage(), e.getStatusCode());
		} catch (Exception e) {
			throw new WalletNotFoundxception("Sale-API não encontrada", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return sale;
	}

}
