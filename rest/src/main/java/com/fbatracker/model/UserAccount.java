package com.fbatracker.model;

import java.io.Serializable;

public class UserAccount implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6557118782206168566L;
	
	private int id;
	private String username;
	private String email;
	private String password;
	
	public UserAccount() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	
}
