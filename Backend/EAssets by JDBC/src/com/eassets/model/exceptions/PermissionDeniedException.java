package com.eassets.model.exceptions;

public class PermissionDeniedException extends Exception{

	public PermissionDeniedException() {
		super("Current user doesn't have the permisson to perform this operation");
	}

	public PermissionDeniedException(String message) {
		super(message);
	}
	
	
}
