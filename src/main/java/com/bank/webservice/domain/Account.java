package com.bank.webservice.domain;

/**
 * @author Ben hassine Houssem
 * 
 */
public class Account {

	private Long id;
	private double amount;

	protected Account() {
	}

	public Account(double amount) {
		this.amount = amount;
	}

	public Account(Long id) {
		this.id = id;
	}

	public Account(Long id, double amount) {
		this.id = id;
		this.amount = amount;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
