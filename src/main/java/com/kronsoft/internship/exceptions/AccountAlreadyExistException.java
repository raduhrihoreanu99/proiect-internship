package com.kronsoft.internship.exceptions;

public class AccountAlreadyExistException extends Exception {
	private static final long serialVersionUID = 7001746976620554755L;
	
	public AccountAlreadyExistException(String email) {
		super("Account with email: " + email + " already exits");
	}
}
