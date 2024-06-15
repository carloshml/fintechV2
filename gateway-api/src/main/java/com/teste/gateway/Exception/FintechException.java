package com.teste.gateway.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class FintechException extends RuntimeException {
	
	public ProblemDetail toProblemDetail() {
		var pb =  ProblemDetail.forStatus(
				HttpStatus.INTERNAL_SERVER_ERROR);
		pb.setTitle("Internal Server Error");
		return pb;				
	}
	
	public ProblemDetail toProblemDetail(String detail) {
		var pb =  ProblemDetail.forStatus(
				HttpStatus.INTERNAL_SERVER_ERROR);
		pb.setTitle("Warning");
		pb.setDetail(detail);
		return pb;				
	}

}
