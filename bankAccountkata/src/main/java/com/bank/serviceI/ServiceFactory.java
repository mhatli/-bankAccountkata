package com.bank.serviceI;

import com.bank.repository.Transaction;
import com.bank.service.businessLogic.validation.ValidationServiceI;
import com.bank.service.businessLogic.validation.ValidationServiceTransactionImp;
import com.bank.serviceIml.stateless.BankClientIml;
import com.bank.serviceIml.stateless.BankAgentIml;
import com.bank.serviceIml.stateless.CashAccountImp;
import com.bank.serviceIml.stateless.TransactionCashImp;

public class ServiceFactory {

	public static AccountI getServiceAccount(){
		return new CashAccountImp();
	}
	
	public static ValidationServiceI<Transaction> getValidationServiceTransaction(){
		return new ValidationServiceTransactionImp();
	}
	public static BankClientI getBankClient(){
		return new BankClientIml();
	}
	
	public static BankAgentI getBankAgent(){
		return new BankAgentIml();
	}
	
	
	public static TransactionI getTransactionCash(){
		return new TransactionCashImp();
	}
	
	
}
