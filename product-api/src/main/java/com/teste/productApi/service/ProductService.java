package com.teste.productApi.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.teste.productApi.Exception.FintechException;
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
		// validaria se o nome é único
		var product = dto.create(wallet.get());	 
		return producRepository.save(product);
	}

	public List<Product> findAll() {
		return producRepository.findAll();
	}

	public Product findById(Long id) {
		var resp = producRepository.findById(id);
		if (!resp.isPresent())
			throw new FintechException("", HttpStatus.NOT_FOUND);
		return resp.get(); 
	}

	public Product encrease(Long id, BigDecimal quantity) {
		var resp = producRepository.findById(id);
		resp.get().encrease(quantity);
		if (!resp.isPresent())
			throw new FintechException("", HttpStatus.NOT_FOUND);
		return  producRepository.save(resp.get()); 
	}

	public Product reduce(Long id, BigDecimal quantity) {
		var resp = producRepository.findById(id);
		resp.get().reduce(quantity);
		if (!resp.isPresent())
			throw new FintechException("", HttpStatus.NOT_FOUND);
		return  producRepository.save(resp.get()); 
	}

}
