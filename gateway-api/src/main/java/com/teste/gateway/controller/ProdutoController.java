package com.teste.gateway.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.gateway.controller.dto.CreateProductDto;
import com.teste.gateway.entity.Product;
import com.teste.gateway.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProdutoController {

	private final ProductService productService;

	public ProdutoController(ProductService ps) {
		this.productService = ps;
	}

	@PostMapping 
	@Operation(summary="Criar um produto no sistema")
	public ResponseEntity<Product> creatProduto(@RequestBody @Valid CreateProductDto dto) {
		return productService.createProduct(dto);	 
	}
	
	@GetMapping 
	@Operation(summary="Lista os produtos do sistema")
	public ResponseEntity<List<Product>> Get() {
		return  productService.findAll();
	 
	}
	
	 

}
