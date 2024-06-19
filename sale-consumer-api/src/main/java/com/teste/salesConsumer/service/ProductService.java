package com.teste.salesConsumer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.teste.salesConsumer.entities.Product;
import com.teste.salesConsumer.entities.Wallet;
import com.teste.salesConsumer.exception.WalletNotFoundxception;
import com.teste.salesConsumer.repository.ProducRepository;

@Service
public class ProductService {

	private final ProducRepository producRepository;

	public ProductService(ProducRepository pr) {
		this.producRepository = pr;

	}

	public Optional<Product> findById(Long idWallet) {
		var resp = producRepository.findById(idWallet);
		if (!resp.isPresent())
			throw new WalletNotFoundxception("Product id:" + idWallet + " doesn't exist ",
					HttpStatus.UNPROCESSABLE_ENTITY);
		return resp;

	}
	
	public Product save(Product wallet) {
		return producRepository.save(wallet);		
	}

}
