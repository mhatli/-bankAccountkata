package com.bank.repository;

import java.util.Date;

import com.bank.technical.Amount;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data
@ToString
public class Account {
	@NonNull
	private Integer accountId;

	@NonNull
	private BankClient owner;

	private Statements statements;

	public Account(Integer accountId, Amount amount, BankClient client) {
		super();
		this.accountId = accountId;
		this.owner=client;
		statements = new Statements(new SingleStatement(amount, new Date()));
	}
}
