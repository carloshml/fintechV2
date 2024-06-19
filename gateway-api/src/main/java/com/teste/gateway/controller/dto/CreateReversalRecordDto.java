package com.teste.gateway.controller.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;


/**
 * @param Long sale
 * @param BigDecimal price
 * @param BigDecimal quantity  
 * */  
public record CreateReversalRecordDto(@NotNull Long sale, @NotNull BigDecimal price, @NotNull BigDecimal quantity) {

	 

}
