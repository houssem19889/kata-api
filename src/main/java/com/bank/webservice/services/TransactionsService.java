package com.bank.webservice.services;

import java.util.List;

import com.bank.webservice.domain.AccountTransaction;

/**
 * 
 * TransactionsService interface
 * 
 * @author Ben hassine Houssem
 *
 */

public interface TransactionsService {
	/**
	 * Returns the history of transactions linked to an account
	 *
	 * @param accountId      the id of the account to create
	 * @return the List of Account Transactions linked to the account
	 * @see AccountTransaction
	 */
	AccountTransaction addAccountTransaction(Long accountId, AccountTransaction AccountTransaction);
	
	/**
	 * Returns an account transaction witch has been added
	 *
	 * @param accountId      the id of the account 
	 * @param AccountTransaction the account transaction to add
	 * @return the account transaction created
	 * @see AccountTransaction
	 */
	List<AccountTransaction> getAccountTransactions(Long accountId);

}
