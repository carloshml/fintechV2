package com.teste.salesConsumer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.salesConsumer.entities.Sale;
import com.teste.salesConsumer.service.SaleService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/sale")
public class SaleController {
	private static final Logger logger = LoggerFactory.getLogger(SaleController.class); 

	private final SaleService saleService;

	public SaleController(SaleService ss) {
		this.saleService = ss;
	}
	
	@GetMapping 
	@Operation(summary="Lista as vendas relaizadas")
	public ResponseEntity<List<Sale>> Get() {
		logger.info(">> list sales:" );
		var resp = saleService.findAll();
		return ResponseEntity.ok(resp); 	 
	}

 

}
