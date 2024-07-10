package com.teste.gateway.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.teste.gateway.controller.dto.CreateProductDto;
import com.teste.gateway.entity.Product;

@FeignClient(name = "product-api", url = "${url.produto.api.host}")
public interface ProductApiClient {

    @PostMapping("/product")
    ResponseEntity<Product> createProduct(@RequestBody CreateProductDto dto);

    @GetMapping("/product")
    ResponseEntity<List<Product>>   getAllProducts();

    @GetMapping("/product/{id}")
    ResponseEntity<Product>  getProductById(@PathVariable("id") Long id);
}
