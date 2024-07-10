package com.teste.productApi.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ProdutctDataAlreadyExistisException extends FintechException {
	
	
	private String detail;

	public ProdutctDataAlreadyExistisException(String detail) {
		this.detail = detail;
	}

	@Override
	public ProblemDetail toProblemDetail() {
		// status 422
		var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
		pb.setTitle("Data Existis");
		pb.setDetail(this.detail);
		return pb;
	}

}
