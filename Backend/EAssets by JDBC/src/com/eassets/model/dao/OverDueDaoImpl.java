package com.eassets.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.eassets.model.beans.Transaction;
import com.eassets.model.beans.User;
import com.eassets.model.util.DBUtility;
import com.eassets.model.util.UserType;

public class OverDueDaoImpl implements OverDueDao {

	@Override
	public boolean banUser(User userToBan) {
		Connection dbConnection = null;

		try {
			dbConnection = DBUtility.getConnection();

			// Update the user's banned status
			String updateBannedStatusQuery = "UPDATE eassets.users SET isBanned = ? WHERE userId = ?";
			PreparedStatement userStatement = dbConnection.prepareStatement(updateBannedStatusQuery);
			userStatement.setBoolean(1, true); // Set user as banned
			userStatement.setInt(2, userToBan.getUserId());

			int rowsUpdated = userStatement.executeUpdate();

			if (rowsUpdated > 0) {
				return true; // Banning the user was successful
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			DBUtility.closeConnection(dbConnection);
		}

		return false; // Banning the user failed
	}

	@Override
	public boolean chargeFineToUser(User userToChargeFine, double fineAmount) {
		Connection dbConnection = null;

		try {
			dbConnection = DBUtility.getConnection();

			// Calculate the new total charged fine
			double newChargedFine = userToChargeFine.getChargedFine() + fineAmount;

			// Update the user's charged fine
			String updateChargedFineQuery = "UPDATE eassets.users SET chargedFine = ? WHERE userId = ?";
			PreparedStatement userStatement = dbConnection.prepareStatement(updateChargedFineQuery);
			userStatement.setDouble(1, newChargedFine);
			userStatement.setInt(2, userToChargeFine.getUserId());

			int rowsUpdated = userStatement.executeUpdate();

			if (rowsUpdated > 0) {
				return true; // Charging the fine to the user was successful
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			DBUtility.closeConnection(dbConnection);
		}

		return false; // Charging the fine to the user failed
	}

	@Override
	public boolean sendOverDueMessageToUser(String messageContent, Transaction transactionOfMessage) {
		Connection dbConnection = null;

		try {
			dbConnection = DBUtility.getConnection();

			// Insert a new message into the 'messages' table
			String insertMessageQuery = "INSERT INTO eassets.messages (messageContent) VALUES (?)";
			PreparedStatement messageStatement = dbConnection.prepareStatement(insertMessageQuery,
					PreparedStatement.RETURN_GENERATED_KEYS);
			messageStatement.setString(1, messageContent);

			int rowsInserted = messageStatement.executeUpdate();

			if (rowsInserted > 0) {
				// Get the generated message ID
				ResultSet generatedKeys = messageStatement.getGeneratedKeys();
				int messageId = -1; // Initialize with an invalid value

				if (generatedKeys.next()) {
					messageId = generatedKeys.getInt(1);
				}

				if (messageId != -1) {
					// Insert a new record into the 'transactionmessages' table to associate the
					// message with the transaction
					String insertTransactionMessageQuery = "INSERT INTO eassets.transactionmessages (transactionId, messageId) VALUES (?, ?)";
					PreparedStatement transactionMessageStatement = dbConnection
							.prepareStatement(insertTransactionMessageQuery);
					transactionMessageStatement.setInt(1, transactionOfMessage.getTransactionId());
					transactionMessageStatement.setInt(2, messageId);

					int transactionMessageRowsInserted = transactionMessageStatement.executeUpdate();

					if (transactionMessageRowsInserted > 0) {
						return true; // Sending the overdue message was successful
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			DBUtility.closeConnection(dbConnection);
		}

		return false; // Sending the overdue message failed
	}

	@Override
	public List<User> getAllOverDueUsers() {
		Connection dbConnection = null;
		List<User> overdueUsers = new ArrayList<>();

		try {
			dbConnection = DBUtility.getConnection();

			// Query to fetch all users with overdue transactions
			String selectOverdueUsersQuery = "SELECT DISTINCT u.* FROM eassets.users u "
					+ "INNER JOIN eassets.transactions t ON u.userId = t.userId "
					+ "WHERE t.expReturnDate < NOW() AND t.isTransactionCompleted = 0";
			PreparedStatement overdueUsersStatement = dbConnection.prepareStatement(selectOverdueUsersQuery);

			ResultSet resultSet = overdueUsersStatement.executeQuery();

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

				overdueUsers.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			DBUtility.closeConnection(dbConnection);
		}

		return overdueUsers;
	}

	@Override
	public List<User> getAllBannedUsers() {
		Connection dbConnection = null;
		List<User> bannedUsers = new ArrayList<>();

		try {
			dbConnection = DBUtility.getConnection();

			// Query to fetch all banned users
			String selectBannedUsersQuery = "SELECT * FROM eassets.users WHERE isBanned = 1";
			PreparedStatement bannedUsersStatement = dbConnection.prepareStatement(selectBannedUsersQuery);

			ResultSet resultSet = bannedUsersStatement.executeQuery();

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

				bannedUsers.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			DBUtility.closeConnection(dbConnection);
		}

		return bannedUsers;
	}

	@Override
	public List<String> getAllOverDueMessagesForUser(User overdueUser) {
		Connection dbConnection = null;
		List<String> overdueMessages = new ArrayList<>();

		try {
			dbConnection = DBUtility.getConnection();

			// Query to fetch all overdue messages for a user
			String selectOverdueMessagesQuery = "SELECT m.messageContent FROM eassets.messages m "
					+ "INNER JOIN eassets.transactionmessages tm ON m.Id = tm.messageId "
					+ "INNER JOIN eassets.transactions t ON tm.transactionId = t.id "
					+ "WHERE t.userId = ? AND t.expReturnDate < NOW()";
			PreparedStatement overdueMessagesStatement = dbConnection.prepareStatement(selectOverdueMessagesQuery);
			overdueMessagesStatement.setInt(1, overdueUser.getUserId());

			ResultSet resultSet = overdueMessagesStatement.executeQuery();

			while (resultSet.next()) {
				String messageContent = resultSet.getString("messageContent");
				overdueMessages.add(messageContent);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			DBUtility.closeConnection(dbConnection);
		}

		return overdueMessages;
	}

}
