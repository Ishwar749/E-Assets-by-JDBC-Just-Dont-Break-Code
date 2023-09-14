package com.eassets.model.business;

import java.util.List;

import com.eassets.model.beans.Asset;
import com.eassets.model.beans.User;

public interface UserServices {
	public boolean registerUser(User userToRegister); 
	public boolean loginUser(User userToLogin);
	public User getUser(User userToFetch);
	public List<Asset> getAllAssetsBorrowedByUser(User borrowerUser);
	public List<User> getAllUsers();
}
