package com.radiantk.controller;

// Form에 입력값을 전달받기 위한 객체
public class LoginCommand {

	private String email;
	private String password;
	private boolean rememberEmail;

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

	public boolean isRememberEmail() {
		return rememberEmail;
	}

	public void setRememberEmail(boolean rememberEmail) {
		this.rememberEmail = rememberEmail;
	}
	
	
}