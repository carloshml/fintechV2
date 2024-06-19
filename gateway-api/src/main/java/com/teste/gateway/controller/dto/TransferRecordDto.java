package com.teste.gateway.controller.dto;

import java.math.BigDecimal;
import java.util.UUID;

public   record TransferRecordDto( 
		  BigDecimal value,
		  Long payee,
		  Long payer
		) {

}
