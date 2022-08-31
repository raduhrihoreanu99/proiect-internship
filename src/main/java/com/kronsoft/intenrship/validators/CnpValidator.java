package com.kronsoft.intenrship.validators;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CnpValidator implements ConstraintValidator<CnpConstraint, String> {
	private static final int CNP_LENGTH = 13;
	private static final String CONTAINS_ONLY_NUMBERS_REGEX = "[0-9]+";
	
	@Override
	public boolean isValid(String cnp, ConstraintValidatorContext context) {
		if (Objects.isNull(cnp)) {
			return false;
		}
		if (cnp.length() != CNP_LENGTH) {
			return false;
		}
		if (!cnp.matches(CONTAINS_ONLY_NUMBERS_REGEX)) {
			return false;
		}
		return true;
	}

}
