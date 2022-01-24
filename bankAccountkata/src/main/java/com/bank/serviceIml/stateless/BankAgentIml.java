package com.bank.serviceIml.stateless;

import com.bank.repository.BankAgent;
import com.bank.serviceI.BankAgentI;

public class BankAgentIml implements BankAgentI {

	@Override
	public boolean isUserAuthorized(BankAgent User) {
		return true;
	}

}
