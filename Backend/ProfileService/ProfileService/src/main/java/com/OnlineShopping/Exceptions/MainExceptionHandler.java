package com.OnlineShopping.Exceptions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

 

import java.time.LocalDate;

@RestControllerAdvice
public class MainExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(Exception.class)
	    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
	        ErrorResponse response = new ErrorResponse(LocalDate.now(), ex.getMessage(), request.getDescription(false), "Internal Server Error");
//	        log.error(ex.getMessage());
	        return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	 

	    @ExceptionHandler(ProfileNotFoundException.class)
	    public final ResponseEntity<ErrorResponse> handleNotFoundException(ProfileNotFoundException ex, WebRequest request) {

	 
	    	 ErrorResponse response = new  ErrorResponse(LocalDate.now(), ex.getMessage(), request.getDescription(false), "Not Found");
//	        log.error(ex.getMessage());

	 

	        return new ResponseEntity< ErrorResponse>(response, HttpStatus.NOT_FOUND);
	    }


	    @ExceptionHandler(ProfileAlreadyExistsException.class)
	    public final ResponseEntity<ErrorResponse> handleNotFoundException(ProfileAlreadyExistsException ex, WebRequest request) {

	 
	    	 ErrorResponse response = new  ErrorResponse(LocalDate.now(), ex.getMessage(), request.getDescription(false), "Not Found");
//	        log.error(ex.getMessage());

	 

	        return new ResponseEntity< ErrorResponse>(response, HttpStatus.NOT_FOUND);
	    }

	 

	    @Override
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
 HttpHeaders headers, HttpStatusCode status, WebRequest request) {
	        StringBuilder details= new StringBuilder();
	        for(FieldError error:ex.getBindingResult().getFieldErrors()) {
	            details.append(error.getDefaultMessage()).append(", ");
	        }
	        ErrorResponse response =new  ErrorResponse(LocalDate.now(),details.toString(),request.getDescription(false),"Bad Request");
//	        log.error("Validation fails "+details.toString());
	        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	    }
	}


