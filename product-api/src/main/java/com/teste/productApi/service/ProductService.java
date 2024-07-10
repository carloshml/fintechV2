package com.teste.productApi.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.teste.productApi.Exception.FintechException;
import com.teste.productApi.Exception.ProdutctDataAlreadyExistisException;
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
		
		var productData = producRepository.findByName(dto.name());

		if (productData.isPresent()) {
			throw new ProdutctDataAlreadyExistisException("Produto Já Existe");
		}
		
		
		var wallet = walletRepository.findById(dto.owner());
		if (!wallet.isPresent())
			throw new FintechException("Walet do produto não existe", HttpStatus.NOT_FOUND);
	 
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
