package com.eassets.model.beans;

import java.sql.Timestamp;

import com.eassets.model.util.UserType;

public class User {
	private int userId;
	private String userName;
	private UserType userType;
	private long userContactNumber;
	private String userEmail;
	private String userLoginName;
	private String userLoginPassword;
	private Timestamp lastLogin;
	private double chargedFine;
	private boolean isBanned;
	
	public User() {
		super();
	}

	public User(int userId, String userName, UserType userType, long userContactNumber, String userEmail,
			String userLoginName, String userLoginPassword, Timestamp lastLogin) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userType = userType;
		this.userContactNumber = userContactNumber;
		this.userEmail = userEmail;
		this.userLoginName = userLoginName;
		this.userLoginPassword = userLoginPassword;
		this.lastLogin = lastLogin;
		this.chargedFine = (double)0;
		this.isBanned = false;
	}

	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public UserType getUserType() {
		return userType;
	}


	public void setUserType(UserType userType) {
		this.userType = userType;
	}


	public long getUserContactNumber() {
		return userContactNumber;
	}


	public void setUserContactNumber(long userContactNumber) {
		this.userContactNumber = userContactNumber;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getUserLoginName() {
		return userLoginName;
	}


	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}


	public String getUserLoginPassword() {
		return userLoginPassword;
	}


	public void setUserLoginPassword(String userLoginPassword) {
		this.userLoginPassword = userLoginPassword;
	}
	
	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	public double getChargedFine() {
		return chargedFine;
	}

	public void setChargedFine(double chargedFine) {
		this.chargedFine = chargedFine;
	}

	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}
	
	
}
