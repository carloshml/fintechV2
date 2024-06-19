package com.teste.gateway.controller.dto;

 

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;

public record UpdateWalletDto(
		@NotBlank Long idWallet,
		@NotBlank String fullName,
		@NotBlank String cpfCnpj,
		@NotBlank String email,
		@NotBlank String password,
		@NotBlank BigDecimal balance
	 
		) {
	
 

}
