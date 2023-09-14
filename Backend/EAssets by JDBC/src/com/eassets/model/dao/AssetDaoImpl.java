package com.eassets.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.eassets.model.beans.Asset;
import com.eassets.model.beans.Transaction;
import com.eassets.model.beans.User;
import com.eassets.model.exceptions.AssetTypeNotFoundException;
import com.eassets.model.util.AssetPropertiesManager;
import com.eassets.model.util.AssetType;
import com.eassets.model.util.DBUtility;

public class AssetDaoImpl implements AssetDao {

	public int getAssetTypeId(AssetType assetType) throws AssetTypeNotFoundException {
		Connection dbConnection = null;
		int assetTypeId = -1; // Default value if not found

		try {
			dbConnection = DBUtility.getConnection();
			String selectQuery = "SELECT assetTypeId FROM eassets.assettypes WHERE name = ?";
			PreparedStatement statement = dbConnection.prepareStatement(selectQuery);
			statement.setString(1, assetType.toString()); // Convert AssetType enum to its string representation

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				assetTypeId = resultSet.getInt("assetTypeId");
			} else {
				throw new AssetTypeNotFoundException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtility.closeConnection(dbConnection);
		}

		return assetTypeId;
	}

	@Override
	public boolean addAsset(Asset assetToAdd, User userAddingTheAsset) throws AssetTypeNotFoundException {

		
		Connection dbConnection = null;

		try {
			dbConnection = DBUtility.getConnection();
			String insertQuery = "INSERT INTO eassets.assets (assetName, description, isAvailable, systemAddedDate, assetTypeId, userId) VALUES (?,?,?,?,?,?)";
			PreparedStatement statement = dbConnection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, assetToAdd.getAssetName());
			statement.setString(2, assetToAdd.getAssetDescription());
			statement.setBoolean(3, assetToAdd.isAvailableToBorrow());
			statement.setTimestamp(4, assetToAdd.getDateAdded());
			statement.setInt(5, getAssetTypeId(assetToAdd.getType()));
			statement.setInt(6, userAddingTheAsset.getUserId());

			int rowsInserted = statement.executeUpdate();

			if (rowsInserted > 0) {
				ResultSet generatedKeys = statement.getGeneratedKeys();
				if (generatedKeys.next()) {
					int generatedAssetId = generatedKeys.getInt(1);
					assetToAdd.setAssetId(generatedAssetId);
				}
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DBUtility.closeConnection(dbConnection);
		}
	}

	@Override
	public boolean addAssetType(AssetType assetType) {
		Connection dbConnection = null;

		try {
			dbConnection = DBUtility.getConnection();
			String insertQuery = "INSERT INTO eassets.assettypes (name) VALUES (?)";
			PreparedStatement statement = dbConnection.prepareStatement(insertQuery);

			statement.setString(1, assetType.toString());

			int rowsInserted = statement.executeUpdate();

			return rowsInserted > 0; // Return true if at least one row was inserted
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DBUtility.closeConnection(dbConnection);
		}
	}

	@Override
	public Asset getAsset(Asset assetToSearch) {
		Connection dbConnection = null;
		Asset foundAsset = null;

		try {
			dbConnection = DBUtility.getConnection();
			String selectQuery = "SELECT * FROM eassets.assets WHERE assetId = ?";
			PreparedStatement statement = dbConnection.prepareStatement(selectQuery);
			statement.setInt(1, assetToSearch.getAssetId());

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				int assetId = resultSet.getInt("assetId");
				String assetName = resultSet.getString("assetName");
				String description = resultSet.getString("description");
				boolean isAvailable = resultSet.getBoolean("isAvailable");
				Timestamp systemAddedDate = resultSet.getTimestamp("systemAddedDate");
				int assetTypeId = resultSet.getInt("assetTypeId");

				foundAsset = new Asset(assetId, assetName, AssetPropertiesManager.getAssetTypeFromId(assetTypeId),
						description, systemAddedDate, isAvailable);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtility.closeConnection(dbConnection);
		}

		return foundAsset;
	}

	@Override
	public List<Asset> getAllAsstsByName(String assetName) {
	    Connection dbConnection = null;
	    List<Asset> assets = new ArrayList<>();

	    try {
	        dbConnection = DBUtility.getConnection();
	        String selectQuery = "SELECT * FROM eassets.assets WHERE assetName = ?";
	        PreparedStatement statement = dbConnection.prepareStatement(selectQuery);
	        statement.setString(1, assetName);

	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            // Retrieve values from the result set and create Asset objects
	            int assetId = resultSet.getInt("assetId");
	            String name = resultSet.getString("assetName");
	            String description = resultSet.getString("description");
	            boolean isAvailable = resultSet.getBoolean("isAvailable");
	            Timestamp systemAddedDate = resultSet.getTimestamp("systemAddedDate");
	            int assetTypeId = resultSet.getInt("assetTypeId");

	            Asset asset = new Asset(assetId, name, AssetPropertiesManager.getAssetTypeFromId(assetTypeId), description, systemAddedDate, isAvailable);
	            assets.add(asset);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBUtility.closeConnection(dbConnection);
	    }

	    return assets;
	}

	@Override
	public List<Asset> getAllAssetsByType(AssetType searchType) {
	    Connection dbConnection = null;
	    List<Asset> assets = new ArrayList<>();

	    try {
	        dbConnection = DBUtility.getConnection();
	        String selectQuery = "SELECT * FROM eassets.assets WHERE assetTypeId = ?";
	        PreparedStatement statement = dbConnection.prepareStatement(selectQuery);
	        statement.setInt(1, AssetPropertiesManager.getAssetIdFromType(searchType));

	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int assetId = resultSet.getInt("assetId");
	            String assetName = resultSet.getString("assetName");
	            String description = resultSet.getString("description");
	            boolean isAvailable = resultSet.getBoolean("isAvailable");
	            Timestamp systemAddedDate = resultSet.getTimestamp("systemAddedDate");
	            int assetTypeId = resultSet.getInt("assetTypeId");

	            Asset asset = new Asset(assetId, assetName, searchType, description, systemAddedDate, isAvailable);
	            assets.add(asset);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBUtility.closeConnection(dbConnection);
	    }

	    return assets;
	}

	@Override
	public List<Asset> getAllAssetsByDateAdded(Timestamp dateAdded) {
	    Connection dbConnection = null;
	    List<Asset> assets = new ArrayList<>();

	    try {
	        dbConnection = DBUtility.getConnection();
	        String selectQuery = "SELECT * FROM eassets.assets WHERE systemAddedDate = ?";
	        PreparedStatement statement = dbConnection.prepareStatement(selectQuery);
	        statement.setTimestamp(1, dateAdded);

	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int assetId = resultSet.getInt("assetId");
	            String assetName = resultSet.getString("assetName");
	            int assetTypeId = resultSet.getInt("assetTypeId");
	            String description = resultSet.getString("description");
	            boolean isAvailable = resultSet.getBoolean("isAvailable");
	            Timestamp systemAddedDate = resultSet.getTimestamp("systemAddedDate");

	            Asset asset = new Asset(assetId, assetName, AssetPropertiesManager.getAssetTypeFromId(assetTypeId), description, systemAddedDate, isAvailable);
	            assets.add(asset);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBUtility.closeConnection(dbConnection);
	    }

	    return assets;
	}

	@Override
	public List<Asset> getAllAssetByAvailabilityToBorrow(boolean isAvailableToBorrow) {
	    Connection dbConnection = null;
	    List<Asset> assets = new ArrayList<>();

	    try {
	        dbConnection = DBUtility.getConnection();
	        String selectQuery = "SELECT * FROM eassets.assets WHERE isAvailable = ?";
	        PreparedStatement statement = dbConnection.prepareStatement(selectQuery);
	        statement.setBoolean(1, isAvailableToBorrow);

	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int assetId = resultSet.getInt("assetId");
	            String assetName = resultSet.getString("assetName");
	            int assetTypeId = resultSet.getInt("assetTypeId");
	            String description = resultSet.getString("description");
	            Timestamp systemAddedDate = resultSet.getTimestamp("systemAddedDate");

	            Asset asset = new Asset(assetId, assetName, AssetPropertiesManager.getAssetTypeFromId(assetTypeId), description, systemAddedDate, isAvailableToBorrow);
	            assets.add(asset);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBUtility.closeConnection(dbConnection);
	    }

	    return assets;
	}

	@Override
	public List<Asset> getAllBorrowedAssets() {
	    Connection dbConnection = null;
	    List<Asset> borrowedAssets = new ArrayList<>();

	    try {
	        dbConnection = DBUtility.getConnection();
	        String selectQuery = "SELECT * FROM eassets.assets WHERE isAvailable = ?";
	        PreparedStatement statement = dbConnection.prepareStatement(selectQuery);
	        statement.setBoolean(1, false);

	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int assetId = resultSet.getInt("assetId");
	            String assetName = resultSet.getString("assetName");
	            int assetTypeId = resultSet.getInt("assetTypeId");
	            String description = resultSet.getString("description");
	            Timestamp systemAddedDate = resultSet.getTimestamp("systemAddedDate");
	            boolean isAvailableToBorrow = resultSet.getBoolean("isAvailable");

	            Asset asset = new Asset(assetId, assetName, AssetPropertiesManager.getAssetTypeFromId(assetTypeId), description, systemAddedDate, isAvailableToBorrow);
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
	public List<Transaction> getBorrowHistoryForThisAsset(Asset assetForBorrowHistory) {
	    Connection dbConnection = null;
	    List<Transaction> borrowHistory = new ArrayList<>();

	    try {
	        dbConnection = DBUtility.getConnection();
	        String selectQuery = "SELECT * FROM eassets.transactions WHERE assetId = ?";
	        PreparedStatement statement = dbConnection.prepareStatement(selectQuery);
	        statement.setInt(1, assetForBorrowHistory.getAssetId());

	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int transactionId = resultSet.getInt("id");
	            int borrowerId = resultSet.getInt("userId");
	            int assetId = resultSet.getInt("assetId");
	            Timestamp borrowDateAndTime = resultSet.getTimestamp("borrowDate");
	            Timestamp expectedReturnDateAndTime = resultSet.getTimestamp("expReturnDate");
	            Timestamp actualReturnDateAndTime = resultSet.getTimestamp("actualReturnDate");
	            boolean isTransactionCompleted = resultSet.getBoolean("isTransactionCompleted");

	            Transaction transaction = new Transaction(transactionId, borrowerId, assetId, borrowDateAndTime);
	            transaction.setExpectedReturnDateAndTime(expectedReturnDateAndTime);
	            transaction.setActualReturnDateAndTime(actualReturnDateAndTime);
	            transaction.setTransactionCompleted(isTransactionCompleted);

	            borrowHistory.add(transaction);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBUtility.closeConnection(dbConnection);
	    }

	    return borrowHistory;
	}

	@Override
	public boolean borrowAsset(Asset assetToBorrow, User borrowerUser) {
	    Connection dbConnection = null;

	    try {
	        dbConnection = DBUtility.getConnection();

	        // Check if the asset is available for borrowing
	        if (!assetToBorrow.isAvailableToBorrow()) {
	            return false; // Asset is not available for borrowing
	        }

	        // Create a new transaction record
	        String insertTransactionQuery = "INSERT INTO eassets.transactions (assetId, borrowDate, userId) VALUES (?, ?, ?)";
	        PreparedStatement transactionStatement = dbConnection.prepareStatement(insertTransactionQuery);
	        transactionStatement.setInt(1, assetToBorrow.getAssetId());
	        transactionStatement.setTimestamp(2, new Timestamp(new Date(0).getTime())); // Current date and time
	        transactionStatement.setInt(3, borrowerUser.getUserId());

	        int rowsInserted = transactionStatement.executeUpdate();

	        if (rowsInserted > 0) {
	            // Update the asset's availability status
	            String updateAssetQuery = "UPDATE eassets.assets SET isAvailable = ? WHERE assedId = ?";
	            PreparedStatement assetStatement = dbConnection.prepareStatement(updateAssetQuery);
	            assetStatement.setBoolean(1, false); // Set asset as not available
	            assetStatement.setInt(2, assetToBorrow.getAssetId());

	            int assetRowsUpdated = assetStatement.executeUpdate();

	            if (assetRowsUpdated > 0) {
	                return true; // Borrowing successful
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBUtility.closeConnection(dbConnection);
	    }

	    return false; // Borrowing failed
	}

	@Override
	public boolean returnAsset(Transaction returnAssetTransaction) {
	    Connection dbConnection = null;

	    try {
	        dbConnection = DBUtility.getConnection();

	        // Check if the transaction is already completed
	        if (returnAssetTransaction.isTransactionCompleted()) {
	            return false; // Transaction is already completed, cannot return the asset again
	        }

	        // Update the transaction record with the return date and mark it as completed
	        String updateTransactionQuery = "UPDATE eassets.transactions SET actualReturnDate = ?, isTransactionCompleted = ? WHERE id = ?";
	        PreparedStatement transactionStatement = dbConnection.prepareStatement(updateTransactionQuery);
	        transactionStatement.setTimestamp(1, new Timestamp(new Date(0).getTime())); // Current date and time as return date
	        transactionStatement.setBoolean(2, true); // Mark transaction as completed
	        transactionStatement.setInt(3, returnAssetTransaction.getTransactionId());

	        int rowsUpdated = transactionStatement.executeUpdate();

	        if (rowsUpdated > 0) {
	            // Update the asset's availability status to mark it as available
	            String updateAssetQuery = "UPDATE eassets.assets SET isAvailable = ? WHERE assedId = ?";
	            PreparedStatement assetStatement = dbConnection.prepareStatement(updateAssetQuery);
	            assetStatement.setBoolean(1, true); // Set asset as available
	            assetStatement.setInt(2, returnAssetTransaction.getAssetId());

	            int assetRowsUpdated = assetStatement.executeUpdate();

	            if (assetRowsUpdated > 0) {
	                return true; // Returning the asset was successful
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBUtility.closeConnection(dbConnection);
	    }

	    return false; // Returning the asset failed
	}

}
