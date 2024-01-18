package com.transaction.management.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "mtransaction")
public class Transaction {
	
	@Id
	@Column(name = "RECORD_ID")
	@SequenceGenerator(name = "userID", sequenceName = "TRANS_SEQ_RECORD_ID", allocationSize = 1)
	@GeneratedValue(generator = "userID", strategy = GenerationType.AUTO)
	private long record_id;
	
	@Column(name = "TRANSACTION_TYPE")
	private String transactionType;
	
	@Column(name= "TRANSACTION_AMOUNT")
	private Double transactionMoney;
	
	@Column(name = "TRANSACTION_DATE")
	@Temporal(TemporalType.DATE)
	private Date transactionDate;
	
	@Column(name= "TRANSACTION_REASON")
	private String reasonOfTransaction;
	
	@Transient
	private String currencyType;

	public long getRecord_id() {
		return record_id;
	}

	public void setRecord_id(long record_id) {
		this.record_id = record_id;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Double getTransactionMoney() {
		return transactionMoney;
	}

	public void setTransactionMoney(Double transactionMoney) {
		this.transactionMoney = transactionMoney;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getReasonOfTransaction() {
		return reasonOfTransaction;
	}

	public void setReasonOfTransaction(String reasonOfTransaction) {
		this.reasonOfTransaction = reasonOfTransaction;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

}
