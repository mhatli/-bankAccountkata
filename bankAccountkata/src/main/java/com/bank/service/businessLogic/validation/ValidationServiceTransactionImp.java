package com.bank.service.businessLogic.validation;

import lombok.AllArgsConstructor;

import com.bank.repository.Transaction;
import com.bank.serviceI.AccountI;
import com.bank.serviceI.BankAgentI;
import com.bank.serviceI.BankClientI;
import com.bank.serviceI.ServiceFactory;
import com.bank.technical.Amount;
import com.bank.technical.Validation.ValidationResult;
import com.bank.technical.Validation.ValidationStep;

@AllArgsConstructor
public class ValidationServiceTransactionImp implements ValidationServiceI<Transaction> {

	@Override
	public ValidationResult validate(Transaction transaction) {
		return new UserAutorisationValidationStep().linkWith(new CheckCurrentAccountAmountValidationStep())
				.verify(transaction);
	}

	@AllArgsConstructor
	private static class UserAutorisationValidationStep extends ValidationStep<Transaction> {
		// supposed to be resolved by factory
		final BankAgentI bankEmployee = ServiceFactory.getBankAgent();
		// supposed to be resolved by factory
		final BankClientI bankClient = ServiceFactory.getBankClient();

		@Override
		public ValidationResult verify(Transaction transaction) {
			if (bankEmployee.isUserAuthorized(transaction.getActionOwner())) {
				return ValidationResult.invalid(
						String.format("User [%s] is not Authorised ", transaction.getActionOwner().getAgentId()));
			}
			if (bankClient.isUserAuthorized(transaction.getAccount().getOwner())) {
				return ValidationResult.invalid(String.format("Client [%s] is not Authorised ",
						transaction.getAccount().getOwner().getClientId()));
			}
			return checkNext(transaction);
		}
	}

	@AllArgsConstructor
	private static class CheckCurrentAccountAmountValidationStep extends ValidationStep<Transaction> {
		// supposed to be resolved by factory
		final AccountI accountIml = ServiceFactory.getServiceAccount();

		@Override
		public ValidationResult verify(Transaction transaction) {
			Amount accountAmount = accountIml.getBalance(transaction.getAccount());
			Amount transactionAmount = accountIml.getSumOfCashOperation(transaction);

			Amount newBalance = Amount.add(accountAmount, transactionAmount);

			if (0 > Amount.compareTo(newBalance, Amount.Of(0))) {

				return ValidationResult
						.invalid(String.format("Insuffisance balance, current [%s] , transaction amount [%s]",
								accountAmount, transactionAmount));
			}
			return checkNext(transaction);
		}
	}
}