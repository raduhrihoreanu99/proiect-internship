package com.kronsoft.internship.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "accounts")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ACCOUNT_ID")
	private Long accountId;
	
	@NotBlank(message = "First name cannot be blank!")
	@Column(name = "FIRST_NAME", length = 20, nullable = false)
	private String firstName;

	@NotBlank(message = "Last name cannot be blank!")
	@Column(name = "LAST_NAME", length = 40, nullable = false)
	private String lastName;
	
	@NotBlank(message = "Email cannot be blank!")
	@Column(name = "EMAIL", length = 30, nullable = false, unique = true)
	private String email;
	
	@NotBlank(message = "Password cannot be blank!")
	@Column(name = "PASSWORD", nullable = false)
	private String password;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", password=" + password + "]";
	}
	
}
