package com.radiantk.command;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

// 커맨드 객체 Date타입 프로퍼티 변환 처리
public class ListCommand {
	// 커맨드 객체에 @DateTimeFormat이 적용되어있으면 지정한 pattern을 통해서 
	// 속성값으로 변환한 문자열을 LocalDateTime객체로 변환해준다.
	// 2020101015 -> 2020년 10월 10일 15시 값의 LocalDateTime객체로 변환
	@DateTimeFormat(pattern = "yyyyMMddHH")
	private LocalDateTime from;
	@DateTimeFormat(pattern = "yyyyMMddHH")
	private LocalDateTime to;
	
	public LocalDateTime getFrom() {
		return from;
	}
	
	public void setFrom(LocalDateTime from) {
		this.from = from;
	}
	
	public LocalDateTime getTo() {
		return to;
	}
	
	public void setTo(LocalDateTime to) {
		this.to = to;
	}
	
}
