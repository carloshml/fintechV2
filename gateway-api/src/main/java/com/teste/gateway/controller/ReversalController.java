package com.teste.gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.gateway.controller.dto.CreateReversalRecordDto;
import com.teste.gateway.controller.dto.CreateSaleRecordDto;
import com.teste.gateway.entity.Reversal;
import com.teste.gateway.service.ReversalService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/reversal")
public class ReversalController {

	private final ReversalService reversalService;

	public ReversalController(ReversalService ss) {
		this.reversalService = ss;
	}

	@PostMapping
	@Operation(summary = "Criar  uma venda no sistema")
	public ResponseEntity<Reversal> createSale(@RequestBody @Valid CreateReversalRecordDto dto) {
		reversalService.createReversal(dto);
		return ResponseEntity.ok(null );
	}

}
