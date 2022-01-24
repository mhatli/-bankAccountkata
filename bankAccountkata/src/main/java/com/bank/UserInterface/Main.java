package com.bank.UserInterface;

import java.util.Date;

import com.bank.repository.Account;
import com.bank.repository.BankClient;
import com.bank.repository.BankAgent;
import com.bank.repository.CashOperationDeposit;
import com.bank.repository.CashOperationWithdrawal;
import com.bank.repository.Operation;
import com.bank.repository.Transaction;
import com.bank.serviceI.AccountI;
import com.bank.serviceI.ServiceFactory;
import com.bank.serviceI.TransactionI;
import com.bank.technical.Amount;
import com.bank.technical.RandomIdGenerator;
import com.bank.technical.Status;
import com.bank.technical.staticContent.StatusBasicEum;

public class Main {

	public static void main(String[] args) {
		Status userStatus = Status.builder().status(StatusBasicEum.Enabled).build();

		BankAgent bankAgent = new BankAgent(RandomIdGenerator.getId(), "Cindy", "R", userStatus,
				RandomIdGenerator.getId());

		BankClient bankClient = new BankClient(RandomIdGenerator.getId(), "Camille", "H", userStatus,
				RandomIdGenerator.getId());

		Account clientCashAccount = new Account(RandomIdGenerator.getId(), Amount.Of(0), bankClient);

		// supposed to be resolved by factory
		TransactionI transactionService = ServiceFactory.getTransactionCash();
		
		AccountI accountI = ServiceFactory.getServiceAccount();

		System.out.println("SETP1 INIT: ->\n" + clientCashAccount);
		System.out.println();

		
		Operation operation = new CashOperationDeposit(RandomIdGenerator.getId(), new Date(),  Amount.Of(109));

		Transaction transaction = transactionService.registerOperation(bankAgent, clientCashAccount, operation);
		
		operation = new CashOperationDeposit(RandomIdGenerator.getId(), new Date(),  Amount.Of(1));
		
		transaction = transactionService.addOperation(transaction, operation);

		transaction = transactionService.validateAndSettelTransaction(transaction);

		
		System.out.println("SETP2  After Deposit : ->\n" + clientCashAccount);
		System.out.println("SETP2 Transaction Detail -->\n" + "Current Account Balance :"+accountI.getBalance(clientCashAccount)+", Transaction ID = "+transaction.getTransactionId()+", Status = "+transaction.getStatus());
		System.out.println();

		
		
		operation = new CashOperationWithdrawal(RandomIdGenerator.getId(), new Date(), Amount.Of(99));

		transaction = transactionService.registerOperation(bankAgent, clientCashAccount, operation);

		transaction = transactionService.validateAndSettelTransaction(transaction);
		
		System.out.println("SETP3  After Withdrawal : ->\n" + clientCashAccount);
		System.out.println("SETP3 Transaction Detail -->\n" + "Current Account Balance :"+accountI.getBalance(clientCashAccount)+", Transaction ID = "+transaction.getTransactionId()+", Status = "+transaction.getStatus());
		System.out.println();
		
		
		operation = new CashOperationWithdrawal(RandomIdGenerator.getId(), new Date(), Amount.Of(655));

		transaction = transactionService.registerOperation(bankAgent, clientCashAccount, operation);

		transaction = transactionService.validateAndSettelTransaction(transaction);
		
		System.out.println("SETP4  After Withdrawal : ->\n" + clientCashAccount);
		System.out.println("SETP4 Transaction Detail -->\n" + "Current Account Balance :"+accountI.getBalance(clientCashAccount)+", Transaction ID = "+transaction.getTransactionId()+", Status = "+transaction.getStatus()+", Reason = "+transaction.getValidationrResult().getErrorMsg());
		System.out.println();

	}

}
