package com.teste.productApi.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class FintechException extends RuntimeException {

	
	private HttpStatus statusCode;
	private String msg;
	
	public FintechException() {
	 
	}
	
	public FintechException(String detail) {
		this.msg = detail;
	}

	public FintechException(String msg, HttpStatus statusCode) {
		this.msg = msg;
		this.statusCode = statusCode;
	}

	public ProblemDetail toProblemDetail() {	 
		var pb = ProblemDetail.forStatus(this.statusCode);
		pb.setTitle("Not Found");
		pb.setDetail(this.msg);
		return pb;
	}

}
