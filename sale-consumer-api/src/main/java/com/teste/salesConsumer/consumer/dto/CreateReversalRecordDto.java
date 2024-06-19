package com.teste.salesConsumer.consumer.dto;

import java.math.BigDecimal;

import com.teste.salesConsumer.entities.Reversal;
import com.teste.salesConsumer.entities.Sale;

import jakarta.validation.constraints.NotNull;


/**
 * @param Long sale
 * @param BigDecimal price
 * @param BigDecimal quantity  
 * */  
public record CreateReversalRecordDto(@NotNull Long sale, @NotNull BigDecimal price, @NotNull BigDecimal quantity) {

	public Reversal createReversal(Sale sale, BigDecimal price, BigDecimal quantity) {
		return new Reversal(sale, price, quantity);
	}

}
