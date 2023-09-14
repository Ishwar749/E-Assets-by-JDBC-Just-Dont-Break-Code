package com.eassets.model.business;

import java.sql.Timestamp;
import java.util.List;

import com.eassets.model.beans.Asset;
import com.eassets.model.beans.Transaction;
import com.eassets.model.beans.User;
import com.eassets.model.exceptions.AssetTypeNotFoundException;
import com.eassets.model.exceptions.PermissionDeniedException;
import com.eassets.model.util.AssetType;

public interface AssetServices {
	
	public boolean addAsset(Asset assetToAdd, User userAddingTheAsset) throws AssetTypeNotFoundException, PermissionDeniedException; 
	public boolean addAssetType(AssetType assetType) throws AssetTypeNotFoundException; 
	
	
	public Asset getAsset(Asset assetToSearch);
	public List<Asset> getAllAsstsByName(String AssetName);
	public List<Asset> getAllAssetsByType(AssetType searchType);
	public List<Asset> getAllAssetsByDateAdded(Timestamp dateAdded);
	public List<Asset> getAllAssetByAvailabilityToBorrow(boolean isAvailableToBorrow);
	public List<Asset> getAllBorrowedAssets();
	public List<Transaction> getBorrowHistoryForThisAsset(Asset assetForBorrowHistory);
	
	public boolean borrowAsset(Asset assetToBorrow, User borrowerUser);
	public boolean returnAsset(Transaction returnAssetTransaction);
}
