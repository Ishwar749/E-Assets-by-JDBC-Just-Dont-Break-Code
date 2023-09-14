package com.eassets.model.util;

import com.eassets.model.business.AssetServicesImpl;
import com.eassets.model.business.OverDueServicesImpl;
import com.eassets.model.business.UserServicesImpl;
import com.eassets.model.dao.AssetDaoImpl;
import com.eassets.model.dao.OverDueDaoImpl;
import com.eassets.model.dao.UserDaoImpl;

public class InstanceFactory {

	public static Object getInstace(LayerType layerType, ServiceType serviceType) {
		
		Object objectInstance = null;
		
		switch(layerType) {
			
			case SERVICE: 
				objectInstance = getServiceLayerInstace(serviceType);
				break;
				
			case DAO: 
				objectInstance = getDAOLayerInstace(serviceType);
				break;
		}
		
		return objectInstance;
	}
	
	
	
	public static Object getServiceLayerInstace(ServiceType serviceType) {
		
		Object objectInstance = null;
		
		switch(serviceType) {
			
		case ASSET: 
			objectInstance = new AssetServicesImpl();
			break;
			
		case OVERDUE: 
			objectInstance = new OverDueServicesImpl();
			break;
		
		
		case USER: 
			objectInstance = new UserServicesImpl();
			break;
		}
		
		return objectInstance;
	}
	
	public static Object getDAOLayerInstace(ServiceType serviceType) {
		
		Object objectInstance = null;
		
		switch(serviceType) {
			
		case ASSET: 
			objectInstance = new AssetDaoImpl();
			break;
			
		case OVERDUE: 
			objectInstance = new OverDueDaoImpl();
			break;
		
		
		case USER: 
			objectInstance = new UserDaoImpl();
			break;
		}
		
		return objectInstance;
	}
}
