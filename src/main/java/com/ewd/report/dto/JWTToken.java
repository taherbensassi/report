package com.ewd.report.dto;

import java.io.Serializable;

public class JWTToken implements Serializable {

	private final String token;

	public JWTToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}
}