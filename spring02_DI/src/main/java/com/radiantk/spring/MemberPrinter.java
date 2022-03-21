package com.radiantk.spring;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberPrinter {
//	@Autowired(required = false)
//	private DateTimeFormatter dateTimeFormatter;
	
//	@Autowired
//	private Option<DateTimeFormatter> optFormatter;
	
//	@Autowired
//	@Nullable
//	private DateTimeFormatter dateTimeFormatter;
	
	private DateTimeFormatter dateTimeFormatter;
	
	public MemberPrinter() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
	}

	public void print(Member member) {
		if(dateTimeFormatter == null) {
			System.out.printf(
					"회원 정보 : 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF \n"
					, member.getId(), member.getEmail(),
					member.getName(), member.getRegDateTime());
		} else {
			System.out.printf(
					"회원 정보 : 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n"
					, member.getId(), member.getEmail(),
					member.getName(), 
					dateTimeFormatter.format(member.getRegDateTime()));
		}
	}
	
	// 매칭되는 빈이 없어도 예외가 발생하지 않으며 자동주입을 수행하지 않음
	@Autowired(required = false)
	public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}
	
//	@Autowired
//	public void setDateTimeFormatter(Optional<DateTimeFormatter> optFormatter) {
//		if(optFormatter.isPresent()) {
//			this.dateTimeFormatter = optFormatter.get();
//		} else {
//			this.dateTimeFormatter = null;
//		}
//	}
	
//	@Autowired
//	public void setDateTimeFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
//		this.dateTimeFormatter = dateTimeFormatter;
//	}
	
}
