package com.teste.salesConsumer.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.teste.salesConsumer.entities.Product;
import com.teste.salesConsumer.exception.WalletNotFoundxception;

@Service
public class ProductService {

	private static final Logger logger = LoggerFactory.getLogger(WalletService.class);

	@Value("${url.produto.api.host}")
	private String urlProdutoApi;
	private RestTemplate restTemplate = new RestTemplate();

	public List<Product> findAll() {
		logger.info(">> findAll Products urlWalletApi:" + urlProdutoApi);
		List<Product> product = new ArrayList<>();
		try {
			ResponseEntity<List> response = restTemplate.exchange(urlProdutoApi, HttpMethod.GET, null, List.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				product = response.getBody();
				// Now you can work with the List<Wallet> returned by the API
				// (assuming the response is in JSON or another suitable format)
				System.out.println("Received " + product.size() + " wallets.");
			} else {
				new WalletNotFoundxception("wallets Vazia", response.getStatusCode());
			}
		} catch (HttpClientErrorException e) {
			throw new WalletNotFoundxception(e.getMessage(), e.getStatusCode());
		} catch (Exception e) {
			e.printStackTrace();
			throw new WalletNotFoundxception("Wallets API não encontrada", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return product;

	}

	public Product findById(Long id) {
		String fidByIdURL = urlProdutoApi + "/byId/" + id;
		logger.info(">>  Product   findById:" + fidByIdURL);
		Product produto = null;
		try {
			ResponseEntity<Product> response = restTemplate.exchange(fidByIdURL, HttpMethod.GET, null, Product.class);

			if (response.getStatusCode() == HttpStatus.OK) {
				produto = response.getBody();
			} else {
				throw new WalletNotFoundxception("Product id:" + id + " doesn't exist ",
						HttpStatus.UNPROCESSABLE_ENTITY);
			}
		} catch (HttpClientErrorException e) {
			throw new WalletNotFoundxception(e.getMessage(), e.getStatusCode());
		} catch (Exception e) {
			throw new WalletNotFoundxception("Product-API não encontrada", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return produto;
	}
 
	public Product reduce(Long id, BigDecimal value) {
		String fidByIdURL = urlProdutoApi + "/reduce?id=" + id + "&quantity=" + value;
		logger.info(">> Product  reduce urlWalletApi:" + fidByIdURL);
		Product produto = null;
		try {
			ResponseEntity<Product> response = restTemplate.exchange(fidByIdURL, HttpMethod.GET, null, Product.class);
			logger.info(">> response.getStatusCode()" + response.getStatusCode());

			if (response.getStatusCode() == HttpStatus.OK) {
				produto = response.getBody();
			} else {
				throw new WalletNotFoundxception("Produto id:" + id + " doesn't exist ",
						HttpStatus.UNPROCESSABLE_ENTITY);
			}
		} catch (HttpClientErrorException e) {
			throw new WalletNotFoundxception(e.getMessage(), e.getStatusCode());
		} catch (Exception e) {
			throw new WalletNotFoundxception("Produtuo-API não encontrada", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return produto;
	}

	public Product encrease(Long id, BigDecimal value) {
		String fidByIdURL = urlProdutoApi + "/encrease?id=" + id + "&quantity=" + value;
		logger.info(">> Product  encrease urlWalletApi:" + fidByIdURL);
		Product wallets = null;
		try {
			ResponseEntity<Product> response = restTemplate.exchange(fidByIdURL, HttpMethod.GET, null, Product.class);
			logger.info(">> response.getStatusCode()" + response.getStatusCode());

			if (response.getStatusCode() == HttpStatus.OK) {
				wallets = response.getBody();
			} else {
				throw new WalletNotFoundxception("Produtuo id:" + id + " doesn't exist ",
						HttpStatus.UNPROCESSABLE_ENTITY);
			}
		} catch (HttpClientErrorException e) {
			throw new WalletNotFoundxception(e.getMessage(), e.getStatusCode());
		} catch (Exception e) {
			throw new WalletNotFoundxception("Produtuo-API não encontrada", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return wallets;
	}

}
