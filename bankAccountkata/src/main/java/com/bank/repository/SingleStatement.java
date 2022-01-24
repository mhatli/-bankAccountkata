package com.bank.repository;

import java.util.Date;

import com.bank.technical.Amount;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data
public class SingleStatement {
	@NonNull
	private Amount amount;
	@NonNull
	private Date date;
	
	@ToString.Exclude
	private Transaction transaction;
}
