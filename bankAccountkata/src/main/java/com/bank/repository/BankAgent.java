package com.bank.repository;

import com.bank.technical.Status;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class BankAgent extends BankUser {
	@NonNull
	private Integer agentId;

	public BankAgent(int systemId, String firstName, String LastName, Status status, Integer agentId) {
		super(systemId, firstName, LastName, status);
		this.agentId = agentId;
	}

	
}
