package com.naidu.login.Exception;

@SuppressWarnings("serial")
public class CustomerIdNotFoundException extends RuntimeException {
	
	public CustomerIdNotFoundException() {
		
	}
	public CustomerIdNotFoundException(String msg) {
		super(msg);
	}

}
