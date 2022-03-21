package com.radiantk.spring;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// Bean으로 등록되는 이름이 listPrinter가 됨
@Component(value = "listPrinter")
public class MemberListPrinter {

	private MemberDao memberDao;
	private MemberPrinter printer;
	
	public MemberListPrinter() {}
	
	public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
		this.memberDao = memberDao;
		this.printer = printer;
	}
	
	public void printAll() {
		Collection<Member> members = memberDao.selectAll();
		members.forEach(member -> printer.print(member));
	}

	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Autowired
	@Qualifier("summaryPrinter")
	public void setPrinter(MemberPrinter memberPrinter) {
		this.printer = memberPrinter;
	}
	
}
