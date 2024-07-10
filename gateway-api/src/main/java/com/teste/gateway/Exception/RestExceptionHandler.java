package com.teste.gateway.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import feign.FeignException;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(FintechException.class)
	public ProblemDetail handleFintechException(FintechException e) {
		return e.toProblemDetail();
	}
	
	@ExceptionHandler(feign.FeignException.class)
	public String handleFeignException(FeignException e) {
		return e.contentUTF8();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

		var fieldErrors = e.getFieldErrors().stream().map(f -> new InvalidParam(f.getField(), f.getDefaultMessage()))
				.toList();

		var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

		pb.setTitle("Your request parameters didn't validate. ");
		pb.setProperty("invalid-params", fieldErrors);
		return pb;
	}

	private record InvalidParam(String name, String reason) {
	}

}
