package com.eassets.model.business;

import java.util.List;

import com.eassets.model.beans.Transaction;
import com.eassets.model.beans.User;
import com.eassets.model.dao.OverDueDao;
import com.eassets.model.util.InstanceFactory;
import com.eassets.model.util.LayerType;
import com.eassets.model.util.ServiceType;

public class OverDueServicesImpl implements OverDueServices{

	private OverDueDao overDueDao = null;
	
	public OverDueServicesImpl() {
		overDueDao = (OverDueDao) InstanceFactory.getInstace(LayerType.DAO, ServiceType.OVERDUE);
	}
	
	
	@Override
	public boolean banUser(User userToBan) {
		return overDueDao.banUser(userToBan);
	}

	@Override
	public boolean chargeFineToUser(User userToChargeFine, double fineAmount) {
		return overDueDao.chargeFineToUser(userToChargeFine, fineAmount);
	}

	@Override
	public boolean sendOverDueMessageToUser(String message, Transaction transactionOfMessage) {
		return overDueDao.sendOverDueMessageToUser(message, transactionOfMessage);
	}

	@Override
	public List<User> getAllOverDueUsers() {
		return overDueDao.getAllOverDueUsers();
	}

	@Override
	public List<User> getAllBannedUsers() {
		return overDueDao.getAllBannedUsers();
	}

	@Override
	public List<String> getAllOverDueMessagesForUser(User overdueUser) {
		return overDueDao.getAllOverDueMessagesForUser(overdueUser);
	}

}
