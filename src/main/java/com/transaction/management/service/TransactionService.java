package com.transaction.management.service;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;

import com.transaction.management.entity.Transaction;
import com.transaction.management.web.RequestWSE;

public interface TransactionService {

	
	public List<Transaction> findAll(RequestWSE requestWSE) throws JSONException, InterruptedException, IOException;
	
	public Transaction createUser(Transaction user) throws Exception;
}
