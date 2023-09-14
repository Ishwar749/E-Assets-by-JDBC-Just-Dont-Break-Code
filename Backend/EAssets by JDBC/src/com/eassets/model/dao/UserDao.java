package com.eassets.model.dao;

import java.util.List;

import com.eassets.model.beans.Asset;
import com.eassets.model.beans.User;

public interface UserDao {
	public boolean registerUser(User userToRegister); // Store Password in encrypted format
	public boolean loginUser(User userToLogin);
	public User getUser(User userToFetch);
	public List<Asset> getAllAssetsBorrowedByUser(User borrowerUser); // Show latest borrowed up
	public List<User> getAllUsers();
}
