package com.teste.fintech.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class WalletNotFoundxception extends   FintechException {

	private Long walletId;
	private HttpStatus statusCode;

	public WalletNotFoundxception(Long walletId, HttpStatus statusCode) {
		this.walletId = walletId;
		this.statusCode = statusCode;
	}

	@Override
	public ProblemDetail toProblemDetail() {
		// status 422
		var pb = ProblemDetail.forStatus(this.statusCode);
		pb.setTitle("Wallet Not Found");
		pb.setDetail("There is no wallet with the id " + this.walletId);
		return pb;
	}

}
