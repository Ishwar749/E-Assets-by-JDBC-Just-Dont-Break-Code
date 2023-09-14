package com.eassets.model.exceptions;

public class AssetTypeNotFoundException extends Exception {

	public AssetTypeNotFoundException() {
		super("No Such Asset Type Found");
	}

	public AssetTypeNotFoundException(String message) {
		super(message);
	}
	
}
