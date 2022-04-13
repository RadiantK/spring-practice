package com.radiantk.spring;

@SuppressWarnings("serial")
public class DuplicateMemberException extends RuntimeException{

	public DuplicateMemberException(String message) {
		super(message);
	}
}
