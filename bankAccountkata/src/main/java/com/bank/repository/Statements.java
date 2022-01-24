package com.bank.repository;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NonNull;

@Data
public class Statements {
	@NonNull
	private SingleStatement lastStatement;
	private List<SingleStatement> Oldstatement = new ArrayList<>();
}
