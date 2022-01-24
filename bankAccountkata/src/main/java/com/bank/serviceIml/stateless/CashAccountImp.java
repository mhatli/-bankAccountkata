package com.bank.serviceIml.stateless;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.bank.repository.Account;
import com.bank.repository.CashOperation;
import com.bank.repository.SingleStatement;
import com.bank.repository.Transaction;
import com.bank.serviceI.AccountI;
import com.bank.technical.Amount;
import com.bank.technical.staticContent.TransactionStatusEum;

public class CashAccountImp implements AccountI {

	private List<CashOperation> getCashOperation(Transaction transaction) {
		return transaction.getOperations().stream().filter(op -> op instanceof CashOperation)
				.map(op -> (CashOperation) op).collect(Collectors.toList());
	}

	private void setAccountNewBalance(Transaction transaction) {
		Account account = transaction.getAccount();
		account.getStatements().getOldstatement().add(account.getStatements().getLastStatement());

		Amount newBalance = Amount.add(getBalance(account), getSumOfCashOperation(transaction));

		SingleStatement newStatement = new SingleStatement(newBalance, new Date());
		newStatement.setTransaction(transaction);
		
		account.getStatements().setLastStatement(newStatement);

	}

	private void validateCashTransaction(Transaction transaction) {
		if (transaction.getValidationrResult().isValid()
				&& (transaction.getStatus().equals(TransactionStatusEum.MODIFICATION))) {

			transaction.setStatus(TransactionStatusEum.VALID);
		}
	}

	
	private void settelCashTransaction(Transaction transaction) {

		if (transaction.getStatus().equals(TransactionStatusEum.VALID)) {
			setAccountNewBalance(transaction);
			transaction.setStatus(TransactionStatusEum.SETTLED);
		}
	}

	
	@Override
	public Amount getBalance(Account account) {
		return account.getStatements().getLastStatement().getAmount();
	}

	@Override
	public Amount getSumOfCashOperation(Transaction transaction) {
		return getCashOperation(transaction).stream().map(op -> op.getAmount())
				.reduce((t, u) -> Amount.Of(t.getValue().add(u.getValue()).doubleValue())).orElse(Amount.Of(0));
	}


	@Override
	public void checkAndExecuteCashTransaction(Transaction transaction) {
		validateCashTransaction(transaction);
		settelCashTransaction(transaction);
	}
}
