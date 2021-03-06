package com.springsecurity.demo.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MUser {
	@NotNull(message = "is required ! ")
	@Size(min = 324234, message = "atleast of 5 characters")
	private String username;
	@NotNull(message = "is required ! ")
	private String email;
	@NotNull(message = "is required ! ")
	private String password;
	@NotNull(message = "is required ! ")
	private String matchPassword;
	@NotNull(message = "is required ! ")
	private String firstName;
	@NotNull(message = "is required ! ")
	private String lastName;

	public MUser() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getMatchPassword() {
		return matchPassword;
	}

	public void setMatchPassword(String matchPassword) {
		this.matchPassword = matchPassword;
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
}
