package com.bank.repository;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public abstract class Operation {
	@NonNull
	Integer operationId;
	private Date date;
}
