package com.eassets.model.beans;

import java.sql.Timestamp;

import com.eassets.model.util.AssetType;

public class Asset {
	private int assetId;
	private String assetName;
	private AssetType type;
	private String assetDescription;
	private Timestamp dateAdded;
	private boolean isAvailableToBorrow;
	
	public Asset() {
		super();
	}

	public Asset(int assetId, String assetName, AssetType type, String assetDescription, Timestamp dateAdded,
			boolean isAvailableToBorrow) {
		super();
		this.assetId = assetId;
		this.assetName = assetName;
		this.type = type;
		this.assetDescription = assetDescription;
		this.dateAdded = dateAdded;
		this.isAvailableToBorrow = isAvailableToBorrow;
	}

	public int getAssetId() {
		return assetId;
	}

	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public AssetType getType() {
		return type;
	}

	public void setType(AssetType type) {
		this.type = type;
	}

	public String getAssetDescription() {
		return assetDescription;
	}

	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}

	public Timestamp getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Timestamp dateAdded) {
		this.dateAdded = dateAdded;
	}

	public boolean isAvailableToBorrow() {
		return isAvailableToBorrow;
	}

	public void setAvailableToBorrow(boolean isAvailableToBorrow) {
		this.isAvailableToBorrow = isAvailableToBorrow;
	}
}
