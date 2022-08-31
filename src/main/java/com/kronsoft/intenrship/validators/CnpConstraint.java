package com.kronsoft.intenrship.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = CnpValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CnpConstraint {
	String message() default "Invalid CNP! It should be not null and 13 digits long!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

