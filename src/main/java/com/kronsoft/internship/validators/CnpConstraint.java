package com.kronsoft.internship.validators;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target({ ElementType.FIELD })
@Constraint(validatedBy = { CnpValidator.class })
public @interface CnpConstraint {
	String message() default "CNP must not be null and contain exactly 13 digits";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}
