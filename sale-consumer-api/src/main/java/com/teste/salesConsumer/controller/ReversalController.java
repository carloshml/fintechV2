package com.teste.salesConsumer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.salesConsumer.entities.Reversal; 
import com.teste.salesConsumer.service.ReversalService;
 

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/reversal")
public class ReversalController {
	private static final Logger logger = LoggerFactory.getLogger(ReversalController.class); 

	private final ReversalService reversalService;

	public ReversalController(ReversalService ss) {
		this.reversalService = ss;
	}
	
	@GetMapping 
	@Operation(summary="Lista as reversal relaizadas")
	public ResponseEntity<List<Reversal>> Get() {
		logger.info(">> list sales:" );
		var resp = reversalService.findAll();
		return ResponseEntity.ok(resp); 	 
	}

 

}
