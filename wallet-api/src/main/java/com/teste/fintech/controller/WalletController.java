package com.teste.fintech.controller;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teste.fintech.controller.dto.CreateWalletDto;
import com.teste.fintech.entity.Wallet;
import com.teste.fintech.service.WalletService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/wallets")
public class WalletController {
	private static final Logger logger = LoggerFactory.getLogger(WalletController.class); 
	private final WalletService walletService;

	public WalletController(WalletService walletService) {
		this.walletService = walletService;
	}

	@PostMapping 
	@Operation(summary="Criar uma nova wallet no sistema")
	public ResponseEntity<Wallet> creatWallet(@RequestBody @Valid CreateWalletDto dto) {
		var wallet = walletService.createWallet(dto);
		return ResponseEntity.ok(wallet);
	}
	
	@GetMapping 
	@Operation(summary="Lista as wallets disponíveis no sistema")
	public ResponseEntity<List<Wallet>> Get() {
		var resp = walletService.findAll();
		return ResponseEntity.ok(resp); 	 
	}
	
	@GetMapping("deposit") 
	@Operation(summary="Depositar valor na Carteira")
	public ResponseEntity<Wallet> deposit(
			@RequestParam Long id,
			@RequestParam BigDecimal value
			) {
		var resp = walletService.deposit(id,value);
		return ResponseEntity.ok(resp); 	 
	}
	
	@GetMapping("withdraw") 
	@Operation(summary="Sacar valor da Carteira")
	public ResponseEntity<Wallet> withdraw(
			@RequestParam Long id,
			@RequestParam BigDecimal value
			) {
		var resp = walletService.withdraw(id,value);
		return ResponseEntity.ok(resp); 	 
	} 
	
	@GetMapping("ById/{id}")
	public ResponseEntity<Wallet> GetById(@PathVariable(value = "id") Long idWallet) {
		logger.info(">> findById id:" + idWallet); 
		var resp = walletService.findById(idWallet);
		return new ResponseEntity<Wallet>(resp.get(), HttpStatus.OK); 
	}

}
