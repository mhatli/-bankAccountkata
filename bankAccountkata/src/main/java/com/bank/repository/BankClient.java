package com.bank.repository;

import com.bank.technical.Status;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class BankClient extends BankUser {
	@NonNull
	private Integer clientId;

	public BankClient(int systemId, String firstName, String LastName, Status status, Integer clientId) {
		super(systemId, firstName, LastName, status);
		this.clientId = clientId;
	}
	
}
