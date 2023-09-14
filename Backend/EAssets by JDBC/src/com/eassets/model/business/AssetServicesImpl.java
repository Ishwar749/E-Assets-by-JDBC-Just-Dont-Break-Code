package com.eassets.model.business;

import java.sql.Timestamp;
import java.util.List;

import com.eassets.model.beans.Asset;
import com.eassets.model.beans.Transaction;
import com.eassets.model.beans.User;
import com.eassets.model.dao.AssetDao;
import com.eassets.model.exceptions.AssetTypeNotFoundException;
import com.eassets.model.exceptions.PermissionDeniedException;
import com.eassets.model.util.AssetPropertiesManager;
import com.eassets.model.util.AssetType;
import com.eassets.model.util.InstanceFactory;
import com.eassets.model.util.LayerType;
import com.eassets.model.util.ServiceType;
import com.eassets.model.util.UserType;

public class AssetServicesImpl implements AssetServices{

	private AssetDao assetDao = null;
	
	public AssetServicesImpl(){
		assetDao = (AssetDao) InstanceFactory.getInstace(LayerType.DAO, ServiceType.ASSET);
	}
	
	
	@Override
	public boolean addAsset(Asset assetToAdd, User userAddingTheAsset) throws PermissionDeniedException, AssetTypeNotFoundException {
		
		if(userAddingTheAsset.getUserType() != UserType.USER) {
			throw new PermissionDeniedException();
		}
		
		if(!AssetPropertiesManager.checkTypeExists(assetToAdd.getType())) {
			throw new AssetTypeNotFoundException();
		}
		
		return assetDao.addAsset(assetToAdd, userAddingTheAsset);
		
	}

	@Override
	public boolean addAssetType(AssetType assetType) throws AssetTypeNotFoundException {
			
		if(!AssetPropertiesManager.checkTypeExists(assetType)) {
			throw new AssetTypeNotFoundException();
		}
		
		return assetDao.addAssetType(assetType);
	}

	@Override
	public Asset getAsset(Asset assetToSearch) {
		return assetDao.getAsset(assetToSearch);
	}

	@Override
	public List<Asset> getAllAsstsByName(String assetName) {
		return assetDao.getAllAsstsByName(assetName);
	}

	@Override
	public List<Asset> getAllAssetsByType(AssetType searchType) {
		return assetDao.getAllAssetsByType(searchType);
	}

	@Override
	public List<Asset> getAllAssetsByDateAdded(Timestamp dateAdded) {
		return assetDao.getAllAssetsByDateAdded(dateAdded);
	}

	@Override
	public List<Asset> getAllAssetByAvailabilityToBorrow(boolean isAvailableToBorrow) {
		return assetDao.getAllAssetByAvailabilityToBorrow(isAvailableToBorrow);
	}

	@Override
	public List<Asset> getAllBorrowedAssets() {
		return assetDao.getAllBorrowedAssets();
	}


	@Override
	public List<Transaction> getBorrowHistoryForThisAsset(Asset assetForBorrowHistory) {
		return assetDao.getBorrowHistoryForThisAsset(assetForBorrowHistory);
	}

	@Override
	public boolean borrowAsset(Asset assetToBorrow, User borrowerUser) {
		return assetDao.borrowAsset(assetToBorrow, borrowerUser);
	}

	@Override
	public boolean returnAsset(Transaction returnAssetTransaction) {
		return assetDao.returnAsset(returnAssetTransaction);
	}

	
}
