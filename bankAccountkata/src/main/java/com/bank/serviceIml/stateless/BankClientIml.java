package com.bank.serviceIml.stateless;

import com.bank.repository.BankClient;
import com.bank.serviceI.BankClientI;

public class BankClientIml implements BankClientI{

	@Override
	public boolean isUserAuthorized(BankClient User) {
		return true;
	}

}
