package com.bank.technical;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Amount {
	@NonNull
	AmountValue value;
	
	public static Amount Of(String val) {
		return new Amount(AmountValue.AmountOf(val));
	}

	public static Amount Of(Double val) {
		return new Amount(AmountValue.AmountOf(val));
	}

	public static Amount Of(Integer val) {
		return new Amount(AmountValue.AmountOf(val));
	}
	
	public static Amount Of(Amount val) {
		return Amount.Of(val.getValue().doubleValue());
	}
	
	public static Amount add(Amount val,Amount val2) {
		return Amount.Of(val.getValue().add(val2.getValue()).doubleValue());
	}
	
	public static Amount subtract(Amount val,Amount val2) {
		return Amount.Of(val.getValue().subtract(val2.getValue()).doubleValue());
	}
	
	public static int compareTo(Amount val,Amount val2) {
		return val.getValue().compareTo(val2.getValue());
	}
	
}
