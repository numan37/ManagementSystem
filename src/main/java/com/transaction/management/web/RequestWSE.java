package com.transaction.management.web;

import java.util.Date;

public class RequestWSE {
	
	private String requestListType;
	
	private Date dateOfTransaction;
	
	private Date startDateOfTransaction;
	
	private Date endDateOfTransaction;
	
	private String currencyType;

	public String getRequestListType() {
		return requestListType;
	}

	public void setRequestListType(String requestListType) {
		this.requestListType = requestListType;
	}

	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public Date getStartDateOfTransaction() {
		return startDateOfTransaction;
	}

	public void setStartDateOfTransaction(Date startDateOfTransaction) {
		this.startDateOfTransaction = startDateOfTransaction;
	}

	public Date getEndDateOfTransaction() {
		return endDateOfTransaction;
	}

	public void setEndDateOfTransaction(Date endDateOfTransaction) {
		this.endDateOfTransaction = endDateOfTransaction;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	
}
