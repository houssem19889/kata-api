package com.bank.webservice.rest.models;

/**
 * 
 * 
 * User transaction
 * 
 * @author Ben hassine Houssem
 *
 */
public class UserTransaction {

	private double amount;
	
	private Long accountId;


	public UserTransaction() {
	}

	public UserTransaction(Long accountId, double amount) {
		this.amount = amount;
		this.accountId = accountId;

	}
	
	public UserTransaction(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the accountId
	 */
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
}
