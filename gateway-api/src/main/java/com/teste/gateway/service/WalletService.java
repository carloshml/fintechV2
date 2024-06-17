package com.teste.gateway.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.teste.gateway.Exception.WalletNotFoundxception;
import com.teste.gateway.controller.dto.CreateWalletDto;
import com.teste.gateway.entity.Wallet;

@Service
public class WalletService {

	private static final Logger logger = LoggerFactory.getLogger(WalletService.class);

	@Value("${url.wallet.api.host}")
	private String urlWalletApi;
	private RestTemplate restTemplate = new RestTemplate();

	public WalletService() {
	}

	public Wallet createWallet(CreateWalletDto dto) {
		logger.info(">> createWallet:" + dto);
		Wallet createdWallet = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<CreateWalletDto> requestEntity = new HttpEntity<>(dto, headers);

		try {
			ResponseEntity<Wallet> response = restTemplate.postForEntity(urlWalletApi + "wallets", requestEntity,
					Wallet.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				createdWallet = response.getBody();
			} else {
				throw new WalletNotFoundxception("Error creating wallet. Status code: " + response.getStatusCode(),
						HttpStatus.UNPROCESSABLE_ENTITY);
			}
		} catch (HttpClientErrorException e) {
			throw new WalletNotFoundxception(e.getMessage(), e.getStatusCode());
		} catch (Exception e) {
			throw new WalletNotFoundxception("Wallets API não encontrada", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return createdWallet;
	}

	public List<Wallet> findAll() {
		logger.info(">> findAll Wallets urlWalletApi:" + urlWalletApi);
		List<Wallet> wallets = new ArrayList<>();
		try {
			ResponseEntity<List> response = restTemplate.exchange(urlWalletApi + "wallets", HttpMethod.GET, null,
					List.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				wallets = response.getBody();
				// Now you can work with the List<Wallet> returned by the API
				// (assuming the response is in JSON or another suitable format)
				System.out.println("Received " + wallets.size() + " wallets.");
			} else {
				new WalletNotFoundxception("wallets Vazia", response.getStatusCode());
			}
		} catch (HttpClientErrorException e) {
			throw new WalletNotFoundxception(e.getMessage(), e.getStatusCode());
		} catch (Exception e) {
			e.printStackTrace();
			throw new WalletNotFoundxception("Wallets API não encontrada", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return wallets;
	}

	public Wallet findById(Long idWallet) {
		String fidByIdURL = urlWalletApi + "walletById/" + idWallet;
		logger.info(">> findById Wallet urlWalletApi:" + fidByIdURL);
		Wallet wallets = null;
		try {
			ResponseEntity<Wallet> response = restTemplate.exchange(fidByIdURL, HttpMethod.GET, null, Wallet.class);
			logger.info(">> response.getStatusCode()" + response.getStatusCode());
			
			if (response.getStatusCode() == HttpStatus.OK) {
				wallets = response.getBody();
			} else {
				throw new WalletNotFoundxception("Wallet id:" + idWallet + " doesn't exist ",
						HttpStatus.UNPROCESSABLE_ENTITY);
			}
		} catch (HttpClientErrorException e) {
			throw new WalletNotFoundxception(e.getMessage(), e.getStatusCode());
		} catch (Exception e) {
			throw new WalletNotFoundxception("Wallets API não encontrada", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return wallets;
	}

}
