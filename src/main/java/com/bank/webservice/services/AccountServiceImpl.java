package com.bank.webservice.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.webservice.domain.Account;
import com.bank.webservice.domain.AccountTransaction;
import com.bank.webservice.domain.MockBank;
import com.bank.webservice.enums.TransactionType;
import com.bank.webservice.exceptions.AccountExistException;
import com.bank.webservice.exceptions.AccountNotFoundException;

/**
 * 
 * AccountService implementation
 * 
 * @author Ben hassine Houssem
 *
 */

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	TransactionsService transactionsService;
	
	/***Object persistence mock****/
	MockBank bank = MockBank.get();

	/**
	 * Returns an account object by account id
	 *
	 * @param accountId the id of the account to find
	 * @return the account to find
	 * @see Account
	 */
	@Override
	public Account findAccountById(Long accountId) {
		Account account = bank.getAccountById().get(accountId);
		if (account == null) {
			throw new AccountNotFoundException();
		}
		return account;
	}

	/**
	 * Returns an account object witch is created
	 *
	 * @param accountId      the id of the account to create
	 * @param initialDeposit the initial deposit of the account
	 * @return the account created
	 * @see Account
	 */
	@Override
	public Account createAccount(Long accountId, double initialDeposit) {
		Account accountExists = bank.getAccountById().get(accountId);
		if (accountExists != null) {
			throw new AccountExistException();
		}
		Account account = new Account(accountId, initialDeposit);
		bank.getAccountById().put(accountId, account);
		transactionsService.addAccountTransaction(accountId,
				new AccountTransaction(TransactionType.DEPOSIT.getId(), initialDeposit, new Date()));
		return account;
	}

}
