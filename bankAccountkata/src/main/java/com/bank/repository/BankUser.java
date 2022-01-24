package com.bank.repository;

import com.bank.technical.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public abstract class BankUser {
	@NonNull
	private Integer systemId;
	private String firstName;
	private String LastName;
	private Status 	status;
}
