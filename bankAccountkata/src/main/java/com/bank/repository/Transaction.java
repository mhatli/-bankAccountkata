package com.bank.repository;

import java.util.ArrayList;
import java.util.List;

import com.bank.technical.Validation.ValidationResult;
import com.bank.technical.staticContent.TransactionStatusEum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Transaction {

	@NonNull
	private Integer transactionId;
	@NonNull
	private Account account;
	@NonNull
	private BankAgent actionOwner;
	@NonNull
	private List<Operation> operations = new ArrayList<Operation>();

	private ValidationResult validationrResult;
	
	private TransactionStatusEum status = TransactionStatusEum.MODIFICATION;
}
