package com.naidu.login.Exception;

@SuppressWarnings("serial")
public class CustomerNotFoundException extends RuntimeException {
	
	public CustomerNotFoundException() {
		
	}
	
	public CustomerNotFoundException(String msg) {
		super(msg);
	}

}
