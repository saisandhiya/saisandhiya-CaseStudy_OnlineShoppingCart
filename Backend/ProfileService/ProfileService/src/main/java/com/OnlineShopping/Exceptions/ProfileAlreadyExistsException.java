package com.OnlineShopping.Exceptions;

public class ProfileAlreadyExistsException extends RuntimeException{
	public ProfileAlreadyExistsException(String msg){
		super(msg);
	}

}
