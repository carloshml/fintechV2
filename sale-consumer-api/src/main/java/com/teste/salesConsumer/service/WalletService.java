package com.teste.salesConsumer.service;

import java.math.BigDecimal;
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

import com.teste.salesConsumer.controller.dto.UpdateWalletDto;
import com.teste.salesConsumer.entities.Wallet;
import com.teste.salesConsumer.exception.WalletNotFoundxception;

 

@Service
public class WalletService {

	private static final Logger logger = LoggerFactory.getLogger(WalletService.class);

	@Value("${url.wallet.api.host}")
	private String urlWalletApi;
	private RestTemplate restTemplate = new RestTemplate();

	public WalletService() {
		
	}  

	public Wallet findById(Long idWallet) {
		String fidByIdURL = urlWalletApi + "/ById/" + idWallet;
		logger.info(">> findById Wallet urlWalletApi:" + fidByIdURL);
		Wallet wallets = null;
		try {
			ResponseEntity<Wallet> response = restTemplate.exchange(fidByIdURL, HttpMethod.GET, null, Wallet.class);
			 
			
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

	public Wallet deposit(Long idWallet, BigDecimal value) {
		String fidByIdURL = urlWalletApi + "/deposit?id=" + idWallet+"&value="+value;
		logger.info(">> deposit Wallet urlWalletApi:" + fidByIdURL);
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

	public Wallet withdraw(Long idWallet, BigDecimal value) {
		String fidByIdURL = urlWalletApi + "/withdraw?id=" + idWallet+"&value="+value;
		logger.info(">> withdraw Wallet urlWalletApi:" + fidByIdURL);
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
