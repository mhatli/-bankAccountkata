package com.bank.service.businessLogic.validation;

import com.bank.technical.Validation.ValidationResult;

public interface ValidationServiceI<T> {
	ValidationResult validate(T objectToValidate);
}