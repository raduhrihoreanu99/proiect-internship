package com.kronsoft.internship.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kronsoft.internship.dao.AccountRepository;
import com.kronsoft.internship.entities.Account;
import com.kronsoft.internship.exceptions.AccountAlreadyExistException;

@Service
public class AccountService {
	private static Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AccountRepository accountRepository;
	
	public Account registerAccount(Account account) throws AccountAlreadyExistException {
		final String email = account.getEmail();
		if (accountRepository.existsByEmail(email)) {
			throw new AccountAlreadyExistException(email);
		}
		logger.info("Register account with email: {}", email);
		String rawPassword = account.getPassword();
		account.setPassword(passwordEncoder.encode(rawPassword));
		return accountRepository.save(account);
	}
	
}
