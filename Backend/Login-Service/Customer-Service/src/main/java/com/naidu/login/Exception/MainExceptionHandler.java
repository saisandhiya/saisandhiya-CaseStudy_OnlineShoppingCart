package com.naidu.login.Exception;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class MainExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {

		ErrorResponse exceptionResponse = new ErrorResponse(LocalDate.now(), ex.getMessage(), "Internal Server Error");
		log.info(ex.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(CustomerNotFoundException ex) {
		ErrorResponse err = new ErrorResponse(LocalDate.now(), ex.getMessage(), "Not Found");

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(AdminNotFoundException ex) {
		ErrorResponse err = new ErrorResponse(LocalDate.now(), ex.getMessage(), "Not Found");

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(ProductNotFoundException ex) {
		ErrorResponse err = new ErrorResponse(LocalDate.now(), ex.getMessage(), "Product Not Found");

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(CustomerAlreadyExistException.class)
	public ResponseEntity<ErrorResponse> handleException(CustomerAlreadyExistException ex) {
		ErrorResponse err = new ErrorResponse(LocalDate.now(), ex.getMessage(), "Already Exists");

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}

	@Override

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,

			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		StringBuilder sb = new StringBuilder();

		for (FieldError fe : ex.getBindingResult().getFieldErrors()) {

			sb.append(fe.getField())

					.append(":")

					.append(fe.getDefaultMessage())

					.append(".");

		}

		ErrorResponse exceptionResponse = new ErrorResponse(

				LocalDate.now(),

				"Validation Fails",

				sb.toString());

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);

	}

}
