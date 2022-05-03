package com.radiantk.command;

public class ErrorResponse {

	private String message;

	public ErrorResponse(String message) {
		this.message = message;
	}

	public final String getMessage() {
		return message;
	}
	
}
