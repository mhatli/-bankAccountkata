package test.bank.userInterface;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.bank.repository.Account;
import com.bank.repository.BankAgent;
import com.bank.repository.BankClient;
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
import com.bank.technical.staticContent.TransactionStatusEum;

/**
 * 
 * @author user
 *
 * this class has the same scenario in Main.java
 */
public class TestMainFlow {

	private AccountI accountI;
	private TransactionI transactionService;

	private Status userStatus;
	private BankAgent bankAgent;
	private BankClient bankClient;
	private Account clientCashAccount;
	private Amount amountWhenAcountCreated;

	@Before
	public void setUp() throws Exception {
		//init services
		accountI = ServiceFactory.getServiceAccount();
		transactionService = ServiceFactory.getTransactionCash();

		//init reusable test values
		userStatus = Status.builder().status(StatusBasicEum.Enabled).build();
		bankAgent = new BankAgent(RandomIdGenerator.getId(), "Cindy", "R", userStatus, RandomIdGenerator.getId());
		bankClient = new BankClient(RandomIdGenerator.getId(), "Camille", "H", userStatus, RandomIdGenerator.getId());
		
		//Account Creation Operation
		amountWhenAcountCreated = Amount.Of(0);
		clientCashAccount = new Account(RandomIdGenerator.getId(), amountWhenAcountCreated, bankClient);

	}

	@Test
	public void test() {

		assertTrue("initial account amount is not correct, need to be equal to " + amountWhenAcountCreated,
				amountWhenAcountCreated.equals(accountI.getBalance(clientCashAccount)));

		// ---------Deposit Operation
		Amount amountOfFirstDeposit = Amount.Of(109);

		Operation operation = new CashOperationDeposit(RandomIdGenerator.getId(), new Date(),
				Amount.Of(amountOfFirstDeposit));

		Transaction transaction = transactionService.registerOperation(bankAgent, clientCashAccount, operation);

		Amount amountOfSecondDeposit = Amount.Of(1);

		operation = new CashOperationDeposit(RandomIdGenerator.getId(), new Date(), amountOfSecondDeposit);

		transaction = transactionService.addOperation(transaction, operation);

		transaction = transactionService.validateAndSettelTransaction(transaction);

		Amount expectedAccountBalance = Amount.add(amountOfFirstDeposit, amountOfSecondDeposit);
		assertTrue(
				"After first cash deposit of tow Cash Operation inside same transaction, Account Balance slould be equal to the sum Of both Operation : "
						+ expectedAccountBalance,
				expectedAccountBalance.equals(accountI.getBalance(clientCashAccount)));

		// ---------Withdrawal Operation : success

		Amount amountOfFirstWithdrawal = Amount.Of(99);

		operation = new CashOperationWithdrawal(RandomIdGenerator.getId(), new Date(), amountOfFirstWithdrawal);

		transaction = transactionService.registerOperation(bankAgent, clientCashAccount, operation);

		transaction = transactionService.validateAndSettelTransaction(transaction);

		expectedAccountBalance = Amount.subtract(expectedAccountBalance, amountOfFirstWithdrawal);
		assertTrue(
				"After first cash Withdrawal, Account amount slould be equal to the the account balance minus withdrawal amount : "
						+ expectedAccountBalance,
				expectedAccountBalance.equals(accountI.getBalance(clientCashAccount)));

		// ---------Withdrawal Operation : failure

		Amount amountOfSecondWithdrawal = Amount.Of(655);

		operation = new CashOperationWithdrawal(RandomIdGenerator.getId(), new Date(), amountOfSecondWithdrawal);

		transaction = transactionService.registerOperation(bankAgent, clientCashAccount, operation);

		expectedAccountBalance = accountI.getBalance(clientCashAccount);

		transaction = transactionService.validateAndSettelTransaction(transaction);

		assertTrue("After second cash Withdrawal, Account amount should be unchanged " + expectedAccountBalance,
				expectedAccountBalance.equals(accountI.getBalance(clientCashAccount)));

		assertTrue("After second cash Withdrawal, Transaction status should be in " + TransactionStatusEum.ERROR,
				transaction.getValidationrResult() != null && transaction.getValidationrResult().notValid()
						&& TransactionStatusEum.ERROR.equals(transaction.getStatus()));

		assertTrue("After second cash Withdrawal, Transaction Error Msg should contain  " + "Insuffisance balance",
				transaction.getValidationrResult().getErrorMsg() != null
						&& transaction.getValidationrResult().getErrorMsg().contains("Insuffisance balance"));

		System.out.println("Done!");

	}

}
