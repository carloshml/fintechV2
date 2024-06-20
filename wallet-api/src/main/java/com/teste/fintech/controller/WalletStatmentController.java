package com.teste.fintech.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.fintech.entity.WalletStatement;
import com.teste.fintech.service.WalletStatementService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/walletStatment")
public class WalletStatmentController {
	private static final Logger logger = LoggerFactory.getLogger(WalletStatmentController.class); 
	private final WalletStatementService walletStatementService;

	public WalletStatmentController(WalletStatementService wss) {
		this.walletStatementService = wss;
	}
 
	
	@GetMapping("/byWallet/{walletId}")
	@Operation(summary="Lista as wallets disponíveis no sistema")
	public ResponseEntity<List<WalletStatement>> GetById(@PathVariable(value = "walletId") Long walletId) {
		logger.info(">> view all statments from walletId:" + walletId);
		var resp = walletStatementService.findByWallet(walletId);
		return ResponseEntity.ok(resp.get()); 	 
	}
	
 
	
	 

}
