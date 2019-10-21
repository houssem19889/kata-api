package com.bank.webservice.enums;

/** 
 * @author Ben hassine Houssem
 *
 */
public enum TransactionType {

	DEPOSIT(1), WITHDRAWAL(2);
	int id;

	private TransactionType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
