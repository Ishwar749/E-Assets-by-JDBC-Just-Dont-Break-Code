package com.eassets.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.eassets.model.beans.Asset;
import com.eassets.model.beans.User;
import com.eassets.model.util.AssetPropertiesManager;
import com.eassets.model.util.AssetType;
import com.eassets.model.util.DBUtility;
import com.eassets.model.util.UserType;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean registerUser(User userToRegister) {
		Connection dbConnection = null;

		try {
			dbConnection = DBUtility.getConnection();

			// Insert a new user record
			String insertUserQuery = "INSERT INTO eassets.users (name, userType, contactNumber, emailId, loginName, loginPassword, lastLogin, chargedFine, isBanned) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement userStatement = dbConnection.prepareStatement(insertUserQuery);
			userStatement.setString(1, userToRegister.getUserName());
			userStatement.setString(2, userToRegister.getUserType().toString());
			userStatement.setLong(3, userToRegister.getUserContactNumber());
			userStatement.setString(4, userToRegister.getUserEmail());
			userStatement.setString(5, userToRegister.getUserLoginName());
			userStatement.setString(6, userToRegister.getUserLoginPassword());
			userStatement.setTimestamp(7, userToRegister.getLastLogin());
			userStatement.setDouble(8, userToRegister.getChargedFine());
			userStatement.setBoolean(9, userToRegister.isBanned());

			int rowsInserted = userStatement.executeUpdate();

			if (rowsInserted > 0) {
				return true; // User registration was successful
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtility.closeConnection(dbConnection);
		}

		return false; // User registration failed
	}

	@Override
	public boolean loginUser(User userToLogin) {
		Connection dbConnection = null;

		try {
			dbConnection = DBUtility.getConnection();

			// Query to check user login credentials
			String selectUserQuery = "SELECT userId FROM eassets.users WHERE loginName = ? AND loginPassword = ?";
			PreparedStatement userStatement = dbConnection.prepareStatement(selectUserQuery);
			userStatement.setString(1, userToLogin.getUserLoginName());
			userStatement.setString(2, userToLogin.getUserLoginPassword());

			ResultSet resultSet = userStatement.executeQuery();

			if (resultSet.next()) {
				// User with matching credentials found, login successful
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtility.closeConnection(dbConnection);
		}

		return false; // Login failed (no user with matching credentials)
	}

	@Override
	public User getUser(User userToFetch) {
		Connection dbConnection = null;

		try {
			dbConnection = DBUtility.getConnection();

			// Query to fetch a user by username
			String selectUserQuery = "SELECT * FROM eassets.users WHERE loginName = ?";
			PreparedStatement userStatement = dbConnection.prepareStatement(selectUserQuery);
			userStatement.setString(1, userToFetch.getUserLoginName());

			ResultSet resultSet = userStatement.executeQuery();

			if (resultSet.next()) {
				// User with matching username found, create a User object and return it
				int userId = resultSet.getInt("userId");
				String userName = resultSet.getString("name");
				UserType userType = UserType.valueOf(resultSet.getString("userType"));
				long userContactNumber = resultSet.getLong("contactNumber");
				String userEmail = resultSet.getString("emailId");
				String userLoginName = resultSet.getString("loginName");
				String userLoginPassword = resultSet.getString("loginPassword");
				Timestamp lastLogin = resultSet.getTimestamp("lastLogin");
				double chargedFine = resultSet.getDouble("chargedFine");
				boolean isBanned = resultSet.getBoolean("isBanned");

				User fetchedUser = new User(userId, userName, userType, userContactNumber, userEmail, userLoginName,
						userLoginPassword, lastLogin);
				fetchedUser.setChargedFine(chargedFine);
				fetchedUser.setBanned(isBanned);

				return fetchedUser;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			DBUtility.closeConnection(dbConnection);
		}

		return null; // User with the specified username not found
	}

	@Override
	public List<Asset> getAllAssetsBorrowedByUser(User borrowerUser) {
		Connection dbConnection = null;
		List<Asset> borrowedAssets = new ArrayList<>();

		try {
			dbConnection = DBUtility.getConnection();

			// Query to fetch all assets borrowed by a user
			String selectAssetsQuery = "SELECT a.* FROM eassets.assets a "
					+ "INNER JOIN eassets.transactions t ON a.assedId = t.assetId " + "WHERE t.userId = ?";
			PreparedStatement assetsStatement = dbConnection.prepareStatement(selectAssetsQuery);
			assetsStatement.setInt(1, borrowerUser.getUserId());

			ResultSet resultSet = assetsStatement.executeQuery();

			while (resultSet.next()) {
				int assetId = resultSet.getInt("assedId");
				String assetName = resultSet.getString("assetName");
				int assetTypeId = resultSet.getInt("assetTypeId");
				String description = resultSet.getString("description");
				Timestamp systemAddedDate = resultSet.getTimestamp("systemAddedDate");
				boolean isAvailableToBorrow = resultSet.getBoolean("isAvailable");

				Asset asset = new Asset(assetId, assetName, AssetPropertiesManager.getAssetTypeFromId(assetTypeId),
						description, systemAddedDate, isAvailableToBorrow);
				borrowedAssets.add(asset);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			DBUtility.closeConnection(dbConnection);
		}

		return borrowedAssets;
	}

	@Override
	public List<User> getAllUsers() {
		Connection dbConnection = null;
		List<User> allUsers = new ArrayList<>();

		try {
			dbConnection = DBUtility.getConnection();

			// Query to fetch all users
			String selectUsersQuery = "SELECT * FROM eassets.users";
			PreparedStatement usersStatement = dbConnection.prepareStatement(selectUsersQuery);

			ResultSet resultSet = usersStatement.executeQuery();

			while (resultSet.next()) {
				int userId = resultSet.getInt("userId");
				String userName = resultSet.getString("name");
				UserType userType = UserType.valueOf(resultSet.getString("userType"));
				long userContactNumber = resultSet.getLong("contactNumber");
				String userEmail = resultSet.getString("emailId");
				String userLoginName = resultSet.getString("loginName");
				String userLoginPassword = resultSet.getString("loginPassword");
				Timestamp lastLogin = resultSet.getTimestamp("lastLogin");
				double chargedFine = resultSet.getDouble("chargedFine");
				boolean isBanned = resultSet.getBoolean("isBanned");

				User user = new User(userId, userName, userType, userContactNumber, userEmail, userLoginName,
						userLoginPassword, lastLogin);
				user.setChargedFine(chargedFine);
				user.setBanned(isBanned);

				allUsers.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			DBUtility.closeConnection(dbConnection);
		}

		return allUsers;
	}

}
