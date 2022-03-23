package com.radiantk.spring;

public class User {

	private String user;
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public void init() {
		System.out.println("컨테이너 초기화");
	}
	
	public void send() {
		System.out.println(user + " to send message");
	}
	
	public void destroy() {
		System.out.println("컨테이너 소멸");
	}
}
