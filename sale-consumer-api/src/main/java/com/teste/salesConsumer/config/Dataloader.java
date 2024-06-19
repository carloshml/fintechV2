package com.teste.salesConsumer.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.teste.salesConsumer.entities.SaleStatus; 
import com.teste.salesConsumer.repository.SaleStatusRepository;
 


@Configuration
public class Dataloader implements CommandLineRunner {
 
	private final SaleStatusRepository  saleStatusRepository; 
 
	
	public Dataloader(SaleStatusRepository ssr ) {
		this.saleStatusRepository = ssr;		 
	}
	
	@Override
	public void run(String... args) throws Exception { 
		Arrays.stream(SaleStatus.Enum.values())
		 .forEach(saleStatus -> saleStatusRepository.save(saleStatus.get()));	 
	}

}
