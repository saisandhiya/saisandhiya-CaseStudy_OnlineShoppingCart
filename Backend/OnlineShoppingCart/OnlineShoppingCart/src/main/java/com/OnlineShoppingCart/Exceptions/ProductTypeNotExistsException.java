package com.OnlineShoppingCart.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ProductTypeNotExistsException extends RuntimeException{
	public ProductTypeNotExistsException(String msg) {
		super(msg);
	}

}
