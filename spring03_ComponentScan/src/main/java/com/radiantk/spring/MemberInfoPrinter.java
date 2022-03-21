package com.radiantk.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//Bean으로 등록되는 이름이 infoPrinter가 됨
@Component("infoPrinter")
public class MemberInfoPrinter {
	
	private MemberDao memberDao;
	private MemberPrinter memberPrinter;
	
	public void printMemberInfo(String email) {
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			System.out.println("데이터 없음\n");
			return;
		}
		memberPrinter.print(member);
		System.out.println();
	}
	
	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Autowired
	@Qualifier("printer")
	public void setMemberPrinter(MemberPrinter memberPrinter) {
		this.memberPrinter = memberPrinter;
	}

}
