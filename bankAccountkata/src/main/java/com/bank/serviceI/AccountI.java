package com.bank.serviceI;

import com.bank.repository.Account;
import com.bank.repository.Transaction;
import com.bank.technical.Amount;

public interface AccountI {

	Amount getSumOfCashOperation(Transaction transaction);

	void checkAndExecuteCashTransaction(Transaction transaction);

	Amount getBalance(Account account);

}
