package com.teste.gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.gateway.controller.dto.CreateSaleRecordDto;
import com.teste.gateway.entity.Sale;
import com.teste.gateway.service.SaleService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/sale")
public class SaleController {

	private final SaleService saleService;

	public SaleController(SaleService ss) {
		this.saleService = ss;
	}

	@PostMapping
	@Operation(summary = "Criar  uma venda no sistema")
	public ResponseEntity<Sale> createSale(@RequestBody @Valid CreateSaleRecordDto dto) {
		saleService.createSale(dto);
		return ResponseEntity.ok(null );
	}

}
