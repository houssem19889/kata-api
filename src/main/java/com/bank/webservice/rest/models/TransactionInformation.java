package com.bank.webservice.rest.models;

import java.util.Date;

/**
 * 
 * Informations linked to the transaction
 * 
 * @author Ben hassine Houssem
 *
 */
public class TransactionInformation {

	private String type;
	private double amount;
	private Date date;
	
	public TransactionInformation(String type, double amount, Date date) {
		this.type = type;
		this.amount = amount;
		this.date = date;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}


}
