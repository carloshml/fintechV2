package com.teste.productApi.controller;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teste.productApi.controller.dto.CreateProductDto;
import com.teste.productApi.entity.Product;
import com.teste.productApi.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class); 
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping 
	@Operation(summary="Criar um   produto  t no sistema")
	public ResponseEntity<Product> creatProduct(@RequestBody @Valid CreateProductDto dto) {
		var product = productService.creatProduct(dto);
		return ResponseEntity.ok(product);
	}
	
	@GetMapping 
	@Operation(summary="Lista os  produtos  disponíveis no sistema")
	public ResponseEntity<List<Product>> Get() {
		var resp = productService.findAll();
		return ResponseEntity.ok(resp); 	 
	}
	
	@GetMapping("byId/{id}")
	@Operation(summary="Procura produto pelo id")
	public ResponseEntity<Product> findById(@PathVariable(value = "id") Long id) {
		logger.info(">> findById id:" + id); 
		var resp = productService.findById(id);
		return new ResponseEntity<Product>(resp, HttpStatus.OK); 
	} 
	
	@GetMapping("reduce") 
	@Operation(summary="reduz quandidade do produto")
	public ResponseEntity<Product> reduce(
			@RequestParam Long id,
			@RequestParam BigDecimal quantity
			) {	 
		logger.info(">> reduce id:" + id); 
		var resp = productService.reduce(id,quantity );
		return ResponseEntity.ok(resp); 
	}
	
	@GetMapping("encrease")
	@Operation(summary="aumenta quantidade do produto")
	public ResponseEntity<Product> encrease(@RequestParam Long id,
			@RequestParam BigDecimal quantity) { 
		logger.info(">> encrease id:" + id); 
		var resp = productService.encrease(id,quantity );
		return ResponseEntity.ok(resp); 
	}

}
