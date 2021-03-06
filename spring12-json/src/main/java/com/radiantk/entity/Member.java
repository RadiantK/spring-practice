package com.radiantk.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.radiantk.exception.WrongIdPasswordException;

public class Member {
	
	private Long id;
	private String email;
	@JsonIgnore // Json응답 포함대상에서 제외
	private String password;
	private String name;
	// @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss") // 날짜 포멧형식 지정
	private LocalDateTime regDateTime;
	
	public Member(String email, String password, String name, LocalDateTime regDateTime) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.regDateTime = regDateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getRegDateTime() {
		return regDateTime;
	}

	public void setRegDateTime(LocalDateTime regDateTime) {
		this.regDateTime = regDateTime;
	}
	
	public void changePassword(String oldPassword, String newPassword) {
		if(!password.equals(oldPassword)) {
			throw new WrongIdPasswordException();
		}
		
		this.password = newPassword;
	}
	
	// 암호일치 여부 확인
	public boolean matchPassword(String password) {
		return this.password.equals(password);
	}
}
