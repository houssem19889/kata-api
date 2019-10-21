package com.bank.webservice.domain;

import java.util.Date;

/**
 * @author Ben hassine Houssem
 *
 */
public class AccountTransaction {

	private Long id;
	private int type;
	private double amount;
	private Date date;

	protected AccountTransaction() {
	}

	public AccountTransaction(int type, double amount, Date date) {
		this.type = type;
		this.amount = amount;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
