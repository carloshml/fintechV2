package com.teste.gateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teste.gateway.entity.TransferRecordDto;
import com.teste.gateway.service.TransferService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
public class TransferController {

	private static final Logger logger = LoggerFactory.getLogger(TransferController.class);
	private final TransferService transferService;

	public TransferController(TransferService ts) {
		transferService = ts;
	}

	@PostMapping("/transfer")
	@Operation(summary = "create a  Transfer")
	public ResponseEntity createTransfer(@RequestBody @Valid TransferRecordDto transferDto) {
		transferService.createTransfer(transferDto);
		return ResponseEntity.ok(null);

	} 
}
