package com.bank.webservice.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class is put in place to replace the persistence of objects. it is a
 * thread-safe singleton.
 * 
 * @author Ben hassine Houssem
 *
 */

public class MockBank {

	private Map<Long, List<AccountTransaction>> AccountTransactionsByAccount = new HashMap<Long, List<AccountTransaction>>();
	private Map<Long, Account> AccountById = new HashMap<Long, Account>();

	private MockBank() {
	}

	/** Holder */
	private static class SingletonHolder {
		/** Preinitialized unique Instance */
		private final static MockBank instance = new MockBank();
	}

	public static MockBank get() {
		return SingletonHolder.instance;
	}

	/**
	 * @return the accountTransactionsByAccount
	 */
	public Map<Long, List<AccountTransaction>> getAccountTransactionsByAccount() {
		return AccountTransactionsByAccount;
	}

	/**
	 * @param accountTransactionsByAccount the accountTransactionsByAccount to set
	 */
	public void setAccountTransactionsByAccount(Map<Long, List<AccountTransaction>> accountTransactionsByAccount) {
		AccountTransactionsByAccount = accountTransactionsByAccount;
	}

	/**
	 * @return the accountById
	 */
	public Map<Long, Account> getAccountById() {
		return AccountById;
	}

	/**
	 * @param accountById the accountById to set
	 */
	public void setAccountById(Map<Long, Account> accountById) {
		AccountById = accountById;
	}

}