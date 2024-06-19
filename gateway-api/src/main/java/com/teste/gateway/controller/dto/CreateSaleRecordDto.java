package com.teste.gateway.controller.dto;

import java.math.BigDecimal;

 

import jakarta.validation.constraints.NotNull;

public record CreateSaleRecordDto(@NotNull Long product, @NotNull Long payer, @NotNull BigDecimal price,
		@NotNull BigDecimal quantity) {

	 

}
