package com.eassets.model.util;

import java.util.HashMap;
import java.util.Map;

public class AssetPropertiesManager {
	
	static final Map<AssetType, AssetTypeProperties> assetTypePropertiesMap = new HashMap<>();
	static final Map<Integer, AssetType> assetTypeIDMapping= new HashMap<>();
	static final Map<AssetType, Integer> assetIdTypeMapping = new HashMap<>();
	
    static {
        assetTypePropertiesMap.put(AssetType.LAPTOP, new AssetTypeProperties(10, 100.0, 14));
        assetTypePropertiesMap.put(AssetType.BOOK, new AssetTypeProperties(7, 15, 0));
        assetTypePropertiesMap.put(AssetType.MOBILE, new AssetTypeProperties(15,75.0,10));
    }
    
    public static AssetTypeProperties getAssetTypeProperties(AssetType assetType) {
        return assetTypePropertiesMap.get(assetType);
    }
    
    public static void mapIdToAssetType(int assetTypeId, AssetType assetType) {
    	assetTypeIDMapping.put(assetTypeId, assetType);
    	assetIdTypeMapping.put(assetType, assetTypeId);
    }
    
    public static AssetType getAssetTypeFromId(int assetTypeId) {
    	return assetTypeIDMapping.get(assetTypeId);
    }
    
    public static int getAssetIdFromType(AssetType assetType) {
    	return assetIdTypeMapping.get(assetType);
    }
    
    public static boolean checkTypeExists(AssetType userProvidedAssetType) {
    	return assetTypePropertiesMap.containsKey(userProvidedAssetType);
    }
    
}
