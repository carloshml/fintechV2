package com.teste.fintech.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class WalletStatmentNotFoundException extends   FintechException {

	private Long walletId;
	private HttpStatus statusCode;

	public WalletStatmentNotFoundException(Long walletId, HttpStatus statusCode) {
		this.walletId = walletId;
		this.statusCode = statusCode;
	}

	@Override
	public ProblemDetail toProblemDetail() {
		// status 422
		var pb = ProblemDetail.forStatus(this.statusCode);
		pb.setTitle("Wallet Statment Not Found");
		pb.setDetail("There is no wallet stament with the id " + this.walletId);
		return pb;
	}

}
