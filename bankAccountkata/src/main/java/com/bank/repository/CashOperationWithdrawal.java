package com.bank.repository;

import java.util.Date;

import com.bank.technical.Amount;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CashOperationWithdrawal extends CashOperation {
	public CashOperationWithdrawal(int operationId, Date date, Amount amount) {
		super(operationId, date, amount);
		if (getAmount().getValue().signum() > 0) {
			setAmount(Amount.Of(getAmount().getValue().negate().doubleValue()));
		}
	}

}
