package com.teste.gateway.entity;

import java.math.BigDecimal;
import java.util.UUID;

public   record TransferRecordDto( 
		  BigDecimal value,
		  Long payee,
		  Long payer
		) {

}
