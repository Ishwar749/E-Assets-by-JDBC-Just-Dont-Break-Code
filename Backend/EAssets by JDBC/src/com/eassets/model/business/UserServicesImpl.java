package com.eassets.model.business;

import java.util.List;

import com.eassets.model.beans.Asset;
import com.eassets.model.beans.User;
import com.eassets.model.dao.UserDao;
import com.eassets.model.util.InstanceFactory;
import com.eassets.model.util.LayerType;
import com.eassets.model.util.ServiceType;

public class UserServicesImpl implements UserServices{

	UserDao userDao = null;
	
	public UserServicesImpl() {
		userDao = (UserDao) InstanceFactory.getInstace(LayerType.DAO, ServiceType.USER);
		
	}
	@Override
	public boolean registerUser(User userToRegister) {
		return userDao.registerUser(userToRegister);
	}

	@Override
	public boolean loginUser(User userToLogin) {
		return userDao.loginUser(userToLogin);
	}

	@Override
	public User getUser(User userToFetch) {
		return userDao.getUser(userToFetch);
	}

	@Override
	public List<Asset> getAllAssetsBorrowedByUser(User borrowerUser) {
		return userDao.getAllAssetsBorrowedByUser(borrowerUser);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

}
