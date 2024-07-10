package com.teste.gateway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.teste.gateway.clients.ProductApiClient;
import com.teste.gateway.controller.dto.CreateProductDto;
import com.teste.gateway.entity.Product;

import jakarta.validation.Valid;

@Service
public class ProductService {

    private final ProductApiClient productApiClient;

    @Autowired
    public ProductService(ProductApiClient productApiClient) {
        this.productApiClient = productApiClient;
    }

    public ResponseEntity<Product> createProduct(@Valid CreateProductDto dto) {
        return productApiClient.createProduct(dto);
    }

    public ResponseEntity<List<Product>> findAll() {
        return productApiClient.getAllProducts();
    }

    public ResponseEntity<Product> findById(Long id) {
        return productApiClient.getProductById(id);
    }
}
