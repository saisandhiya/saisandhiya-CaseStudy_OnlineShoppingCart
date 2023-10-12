package com.OnlineShoppingCart.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ProductNotFoundException extends RuntimeException{
	public ProductNotFoundException (String msg) {
		super(msg);
	}

}
