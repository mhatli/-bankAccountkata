package com.bank.serviceI;

import com.bank.repository.Account;
import com.bank.repository.BankAgent;
import com.bank.repository.Operation;
import com.bank.repository.Transaction;

public interface TransactionI {

	Transaction registerOperation(BankAgent actionOwner, Account account, Operation operation);

	Transaction addOperation(Transaction transaction, Operation operation);

	Transaction validateAndSettelTransaction(Transaction transaction);
		
}
