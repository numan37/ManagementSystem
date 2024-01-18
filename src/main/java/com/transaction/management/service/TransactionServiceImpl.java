package com.transaction.management.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transaction.management.DAO.TransactionDAO;
import com.transaction.management.commons.CommonConstants;
import com.transaction.management.commons.CommonHelperClass;
import com.transaction.management.entity.Transaction;
import com.transaction.management.web.RequestWSE;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private TransactionDAO transactionDAO;

	@Override
	public List<Transaction> findAll(RequestWSE requestWSE) throws JSONException, InterruptedException, IOException {
		List<Transaction> transactions = null;
		if(CommonHelperClass.isNotNullAndEmpty(requestWSE.getRequestListType())) {
			if(CommonConstants.DEFULTLISTING.equalsIgnoreCase(requestWSE.getRequestListType())) {
				transactions=transactionDAO.findAll();
			} else if(CommonConstants.ADVANCELISTING.equalsIgnoreCase(requestWSE.getRequestListType())) {
				if(requestWSE.getDateOfTransaction()!=null) {
					transactions= transactionDAO.transactionsOnDate(requestWSE.getDateOfTransaction());
				} else if(requestWSE.getStartDateOfTransaction()!=null &&requestWSE.getEndDateOfTransaction()!=null) {
					transactions= transactionDAO.transactionsBetweenDate(requestWSE.getStartDateOfTransaction(), requestWSE.getEndDateOfTransaction());
				}
			}
		}
		if(transactions!=null && transactions.size()>0) {
			if(CommonConstants.USACurrency.equalsIgnoreCase(requestWSE.getCurrencyType())) {
				Double curencyVal = Double.valueOf(getCurrencyRates(CommonConstants.INDCurrency));
				for(Transaction transaction:transactions) {
					transaction.setTransactionMoney(transaction.getTransactionMoney()/curencyVal);
					transaction.setCurrencyType(CommonConstants.USACurrency);
				}
			} else {
				for(Transaction transaction:transactions) {
					transaction.setCurrencyType(CommonConstants.INDCurrency);
				}
			}
		} else {
			transactions=new ArrayList<>();
		}
		return transactions;
		
	}

	@Override
	public Transaction findById(Long id) {
		return transactionDAO.getReferenceById(id);
	}

	@Override
	public Transaction createUser(Transaction transaction) throws Exception {
		String currencyType=CommonConstants.INDCurrency;
		if(CommonHelperClass.isNotNullAndEmpty(transaction.getCurrencyType())) {
			currencyType=transaction.getCurrencyType();
		}
		if(CommonConstants.USACurrency.equalsIgnoreCase(currencyType)) {
			String curencyVal = getCurrencyRates(CommonConstants.INDCurrency);
			transaction.setTransactionMoney(transaction.getTransactionMoney()*Double.valueOf(curencyVal));
		}
		return transactionDAO.save(transaction);
	}

	private String getCurrencyRates(String currency) throws InterruptedException, IOException, JSONException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.fxratesapi.com/latest"))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("Unable to add Money in USD as currency convertor is not available");
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new InterruptedException("Unable to add Money in USD as currency convertor is not available");
		}
		if(CommonHelperClass.isNotNullAndEmpty(response.body())) {
			JSONObject apiResponse=new JSONObject(String.valueOf(response.body()));
			if(CommonConstants.USACurrency.equalsIgnoreCase(String.valueOf(apiResponse.get("base")))) {
				JSONObject currencyConversion=new JSONObject(String.valueOf(apiResponse.get("rates")));
				return String.valueOf(currencyConversion.get(currency));
			} else {
				throw new JSONException("Unable to add Money in USD as currency convertor is not available");
			}
		} else {
			throw new JSONException("Unable to add Money in USD as currency convertor is not available");
		}
		
	}

}
