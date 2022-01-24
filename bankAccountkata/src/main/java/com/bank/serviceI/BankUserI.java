package com.bank.serviceI;

import com.bank.repository.BankUser;

public interface BankUserI<U extends BankUser> {

	boolean isUserAuthorized(U User);
}
