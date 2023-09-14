package com.eassets.model.business;

import java.util.List;

import com.eassets.model.beans.Transaction;
import com.eassets.model.beans.User;

public interface OverDueServices {
	public boolean banUser(User userToBan);
	public boolean chargeFineToUser(User userToChargeFine, double fineAmount);
	public boolean sendOverDueMessageToUser(String message, Transaction transactionOfMessage);
	public List<User> getAllOverDueUsers();
	public List<User> getAllBannedUsers();
	public List<String> getAllOverDueMessagesForUser(User overdueUser);	
}
