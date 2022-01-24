package com.bank.repository;

import java.util.Date;

import com.bank.technical.Amount;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class CashOperation extends Operation{
	private Amount amount;

	public CashOperation(int operationId, Date date, Amount amount) {
		super(operationId, date);
		this.amount = amount;
	}
}
