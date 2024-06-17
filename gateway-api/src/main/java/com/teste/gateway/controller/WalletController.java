package com.teste.gateway.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teste.gateway.controller.dto.CreateWalletDto;
import com.teste.gateway.entity.Wallet;
import com.teste.gateway.service.WalletService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
public class WalletController {

	private final WalletService walletService;

	public WalletController(WalletService walletService) {
		this.walletService = walletService;
	}

	@PostMapping("/wallets")
	@Operation(summary="Criar uma nova wallet no sistema")
	public ResponseEntity<Wallet> creatWallet(@RequestBody @Valid CreateWalletDto dto) {
		var wallet = walletService.createWallet(dto);
		return ResponseEntity.ok(wallet);
	}
	
	@GetMapping("/wallets")
	@Operation(summary="Lista as wallets disponíveis no sistema")
	public ResponseEntity<List<Wallet>> Get() {
		var resp = walletService.findAll();
		return ResponseEntity.ok(resp); 	 
	}
	
	@GetMapping("/wallets/deposit") 
	@Operation(summary="Depositar valor na Carteira")
	public ResponseEntity<Wallet> deposit(
			@RequestParam Long id,
			@RequestParam BigDecimal value
			) {
		var resp = walletService.deposit(id,value);
		return ResponseEntity.ok(resp); 	 
	}
	
	@GetMapping("/wallets/withdraw") 
	@Operation(summary="Sacar valor da Carteira")
	public ResponseEntity<Wallet> withdraw(
			@RequestParam Long id,
			@RequestParam BigDecimal value
			) {
		var resp = walletService.withdraw(id,value);
		return ResponseEntity.ok(resp); 	 
	}

}
