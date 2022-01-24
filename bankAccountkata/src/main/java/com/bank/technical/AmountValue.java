package com.bank.technical;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class AmountValue extends BigDecimal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AmountValue(String val) {
		super(val);
		setScale(2, RoundingMode.CEILING);
	}

	private AmountValue(Double val) {
		super(val);
		setScale(2, RoundingMode.CEILING);
	}

	private AmountValue(Integer val) {
		super(val);
		setScale(2, RoundingMode.CEILING);
	}

	static AmountValue AmountOf(String val) {
		return new AmountValue(val);
	}

	static AmountValue AmountOf(Double val) {
		return new AmountValue(val);
	}

	static AmountValue AmountOf(Integer val) {
		return new AmountValue(val);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
