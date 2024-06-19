package com.teste.salesConsumer.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;

public class WalletNotFoundxception extends FintechException {

	private String detail;
	private HttpStatusCode httpStatusCode;

	public WalletNotFoundxception(String detail, HttpStatusCode code) {
		this.detail = detail;
		this.httpStatusCode = code;
	}

	@Override
	public ProblemDetail toProblemDetail() {		 
		var pb = ProblemDetail.forStatus(this.httpStatusCode);
		pb.setTitle("Wallet Warning");
		pb.setDetail(this.detail);
		return pb;
	}

}
