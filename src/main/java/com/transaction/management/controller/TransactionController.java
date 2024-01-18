package com.transaction.management.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.transaction.management.entity.Transaction;
import com.transaction.management.service.TransactionService;
import com.transaction.management.web.RequestWSE;

@CrossOrigin
@RestController
@RequestMapping("/transaction/management")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@GetMapping("/transactions")
	public List<Transaction> findAll(@RequestBody(required = false) RequestWSE requestWSE) {
		try {
			return transactionService.findAll(requestWSE);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
					"Unable to get Transaction at the Time");
		}
	}


	@PostMapping("/transaction/create")
	public ResponseEntity<Object> createUser(@RequestBody Transaction transaction) {
		transaction.setTransactionDate(new Date());
		try {
			Transaction savedTransaction = transactionService.createUser(transaction);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedTransaction.getRecord_id()).toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
					"Unable to add Money in USD as currency convertor is not available");
		}
	}

}
