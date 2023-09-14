package com.eassets.model.exceptions;

public class AssetNotFoundException extends Exception{

	public AssetNotFoundException() {
		super("Asset Not Found");
	}

	public AssetNotFoundException(String message) {
		super(message);
	}
	
}
