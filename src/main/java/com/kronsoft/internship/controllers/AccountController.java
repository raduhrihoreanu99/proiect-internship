package com.kronsoft.internship.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kronsoft.internship.entities.Account;
import com.kronsoft.internship.exceptions.AccountAlreadyExistException;
import com.kronsoft.internship.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	private static Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Account registerAccount(@Valid @RequestBody Account account) throws AccountAlreadyExistException {
		logger.info("[REST] POST to /account/register");
		return accountService.registerAccount(account);
	}
}
