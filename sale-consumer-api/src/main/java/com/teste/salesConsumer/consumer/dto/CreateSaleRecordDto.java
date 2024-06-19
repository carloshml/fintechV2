package com.teste.salesConsumer.consumer.dto;

import java.math.BigDecimal;

import com.teste.salesConsumer.entities.Product;
import com.teste.salesConsumer.entities.Sale;
import com.teste.salesConsumer.entities.Wallet;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

public record CreateSaleRecordDto(@NotNull Long product, @NotNull Long payer, @NotNull BigDecimal price,
		@NotNull BigDecimal quantity) {

	public Sale createSale(Product product, Wallet payer, BigDecimal price, BigDecimal quantity) {
		return new Sale(product, payer, price, quantity);
	}

}
