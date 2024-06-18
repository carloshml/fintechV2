package com.teste.productApi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.teste.productApi.controller.dto.CreateProductDto;
import com.teste.productApi.entity.Product;
import com.teste.productApi.repository.ProducRepository;
import com.teste.productApi.repository.WalletRepository;

@Service
public class ProductService {
	
	
	private final ProducRepository producRepository;
	private final WalletRepository walletRepository;
	
	public ProductService(ProducRepository pr, WalletRepository wr) {
		this.producRepository = pr;
		this.walletRepository = wr;
	}

	public Product creatProduct(CreateProductDto dto) {
		var wallet = walletRepository.findById(dto.owner());
		// validaria se a carteira existe
		var product = dto.create(wallet.get());	 
		return producRepository.save(product);
	}

	public List<Product> findAll() {
		return producRepository.findAll();
	}

}
