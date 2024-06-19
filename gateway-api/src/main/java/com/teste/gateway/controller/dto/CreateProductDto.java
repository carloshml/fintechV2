package com.teste.gateway.controller.dto;

import java.math.BigDecimal;

import com.teste.gateway.entity.Product;
import com.teste.gateway.entity.Wallet;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProductDto( 
		@NotNull @Min(  (long) 0.1)  BigDecimal price, 
		@NotNull Long owner,  
		@NotNull Integer quantity, 
		@NotNull @NotBlank String name		
		) {
	
	public  Product create(Wallet owner) {		
		return new Product(
				    price, 
				    owner,
				    quantity, 
				    name	
				);
		
	}
}
