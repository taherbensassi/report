package com.ewd.report.dto;

import java.io.Serializable;

// TODO: 30.11.20 change to email and username
public class Credentials implements Serializable {

	private String username;
	private String password;
	

	public Credentials(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}