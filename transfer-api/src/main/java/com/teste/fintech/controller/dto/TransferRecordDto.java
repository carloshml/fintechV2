package com.teste.fintech.controller.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferRecordDto( 
		  BigDecimal value,
		  Long payee,
		  Long payer
		) {
	
}

	
	

	
	 
