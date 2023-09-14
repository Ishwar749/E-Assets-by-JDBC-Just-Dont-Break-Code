package com.eassets.model.exceptions;

public class AuthenticationException extends Exception{
	
	public AuthenticationException() {
		super("Username or Password is incorrect. Please try again.");
	}

	public AuthenticationException(String msg) {
		super(msg);
	}
}
