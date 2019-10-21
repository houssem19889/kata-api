package com.bank.webservice.services;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.webservice.domain.AccountTransaction;
import com.bank.webservice.domain.MockBank;
import com.bank.webservice.exceptions.AccountNotFoundException;

/**
 * 
 * TransactionsService implementation
 * 
 * @author Ben hassine Houssem
 *
 */

@Service
public class TransactionsServiceImpl implements TransactionsService {

	/*** Object persistence mock ****/
	MockBank bank = MockBank.get();

	/**
	 * Returns the history of transactions linked to an account
	 *
	 * @param accountId the id of the account
	 * @return the List of Account Transactions linked to the account
	 * @see AccountTransaction
	 */
	@Override
	public List<AccountTransaction> getAccountTransactions(Long accountId) {
		List<AccountTransaction> AccountTransactions = bank.getAccountTransactionsByAccount().get(accountId);
		if (AccountTransactions == null) {
			throw new AccountNotFoundException();
		}
		return unmodifiableList(AccountTransactions);
	}

	
	/**
	 * Returns an account transaction witch has been added
	 *
	 * @param accountId      the id of the account 
	 * @param AccountTransaction the account transaction to add
	 * @return the account transaction created
	 * @see AccountTransaction
	 */
	@Override
	public AccountTransaction addAccountTransaction(Long accountId, AccountTransaction AccountTransaction) {
		List<AccountTransaction> AccountTransactions = bank.getAccountTransactionsByAccount().get(accountId);
		if (AccountTransactions == null) {
			AccountTransactions = new ArrayList<AccountTransaction>();
			bank.getAccountTransactionsByAccount().put(accountId, AccountTransactions);
		}
		AccountTransactions.add(AccountTransaction);
		return AccountTransaction;
	}

}
