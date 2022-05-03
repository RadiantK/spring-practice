package com.radiantk.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.radiantk.command.ErrorResponse;
import com.radiantk.exception.MemberNotFoundException;

@RestControllerAdvice("controller")
public class ApiExceptionAdvice {
	
	@ExceptionHandler(MemberNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoData(){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse("no member"));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleBindException(
			MethodArgumentNotValidException ex) {
		String errorCodes = ex.getBindingResult().getAllErrors() // List<ObjectError>
				.stream()
				.map(error -> error.getCodes()[0]) // error는 ObjectError
				.collect(Collectors.joining(","));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse("errorCodes= " + errorCodes));
	}
}
