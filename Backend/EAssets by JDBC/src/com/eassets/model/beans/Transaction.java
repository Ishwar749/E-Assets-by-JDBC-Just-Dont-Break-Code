package com.eassets.model.beans;

import java.sql.Timestamp;

public class Transaction {

	private int transactionId;
	private int borrowerId;
	private int assetId;
	private Timestamp borrowDateAndTime;
	private Timestamp expectedReturnDateAndTime;
	private Timestamp actualReturnDateAndTime;
	private boolean isTransactionCompleted;
	
	
	public Transaction(int transactionId, int borrowerId, int assetId, Timestamp borrowDateAndTime) {
		super();
		this.transactionId = transactionId;
		this.borrowerId = borrowerId;
		this.assetId = assetId;
		this.borrowDateAndTime = borrowDateAndTime;
		this.isTransactionCompleted = false;
	}


	public int getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}


	public int getBorrowerId() {
		return borrowerId;
	}


	public void setBorrowerId(int borrowerId) {
		this.borrowerId = borrowerId;
	}


	public int getAssetId() {
		return assetId;
	}


	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}


	public Timestamp getBorrowDateAndTime() {
		return borrowDateAndTime;
	}


	public void setBorrowDateAndTime(Timestamp borrowDateAndTime) {
		this.borrowDateAndTime = borrowDateAndTime;
	}

	public Timestamp getExpectedReturnDateAndTime() {
		return expectedReturnDateAndTime;
	}


	public void setExpectedReturnDateAndTime(Timestamp expectedReturnDateAndTime) {
		this.expectedReturnDateAndTime = expectedReturnDateAndTime;
	}


	public Timestamp getActualReturnDateAndTime() {
		return actualReturnDateAndTime;
	}


	public void setActualReturnDateAndTime(Timestamp actualReturnDateAndTime) {
		this.actualReturnDateAndTime = actualReturnDateAndTime;
	}


	public boolean isTransactionCompleted() {
		return isTransactionCompleted;
	}


	public void setTransactionCompleted(boolean isTransactionCompleted) {
		this.isTransactionCompleted = isTransactionCompleted;
	}
	
	
}
