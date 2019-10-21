package com.bank.webservice.services;

import com.bank.webservice.domain.Account;

/**
 * 
 *  AccountService Interface
 * 
 * @author Ben hassine Houssem
 *
 */
public interface AccountService {

	/**
	 * Returns an account object by account id
	 *
	 * @param accountId the id of the account to find
	 * @return the account to find
	 * @see Account
	 */
	Account findAccountById(Long id);

	/**
	 * Returns an account object witch is created
	 *
	 * @param accountId      the id of the account to create
	 * @param initialDeposit the initial deposit of the account
	 * @return the account created
	 * @see Account
	 */
	Account createAccount(Long accountId, double initialDeposit);

}
