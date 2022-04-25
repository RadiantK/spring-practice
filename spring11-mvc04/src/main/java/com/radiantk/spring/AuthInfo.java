package com.radiantk.spring;

// 로그인 성공 후 인증 상태 정보를 세션에 보관할 때 사용할 객체
public class AuthInfo {

	private long id;
	private String eamil;
	private String name;
	
	public AuthInfo(long id, String eamil, String name) {
		this.id = id;
		this.eamil = eamil;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getEamil() {
		return eamil;
	}

	public String getName() {
		return name;
	}
	
}
