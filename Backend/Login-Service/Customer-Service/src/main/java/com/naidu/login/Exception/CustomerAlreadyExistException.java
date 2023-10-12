package com.naidu.login.Exception;

public class CustomerAlreadyExistException extends RuntimeException {
	public CustomerAlreadyExistException(String msg){
		super(msg);
	}

}
