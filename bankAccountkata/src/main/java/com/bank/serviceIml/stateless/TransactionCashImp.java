package com.bank.serviceIml.stateless;

import java.util.ArrayList;
import java.util.List;

import com.bank.repository.Account;
import com.bank.repository.BankAgent;
import com.bank.repository.Operation;
import com.bank.repository.Transaction;
import com.bank.service.businessLogic.validation.ValidationServiceI;
import com.bank.serviceI.AccountI;
import com.bank.serviceI.ServiceFactory;
import com.bank.serviceI.TransactionI;
import com.bank.technical.RandomIdGenerator;
import com.bank.technical.Validation.ValidationResult;
import com.bank.technical.staticContent.TransactionStatusEum;

public class TransactionCashImp implements TransactionI {

	@Override
	public Transaction registerOperation(BankAgent actionOwner, Account account, Operation operation) {
		List<Operation> operations = new ArrayList<Operation>(); 
		operations.add(operation);
		
		return new Transaction(RandomIdGenerator.getId(), account, actionOwner, operations, null,
				TransactionStatusEum.MODIFICATION);
	}

	@Override
	public Transaction addOperation(Transaction transaction, Operation operation) {
		transaction.getOperations().add(operation);
		return transaction;
	}

	@Override
	public Transaction validateAndSettelTransaction(Transaction transaction) {
		// supposed to be resolved by factory
		ValidationServiceI<Transaction> validator = ServiceFactory.getValidationServiceTransaction();
		
		// supposed to be resolved by factory
		AccountI accountImp = ServiceFactory.getServiceAccount();

		ValidationResult validationrResult = validator.validate(transaction);
		transaction.setValidationrResult(validationrResult);

		accountImp.checkAndExecuteCashTransaction(transaction);

		return transaction;
	}

}
