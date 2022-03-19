package com.radiantk.assembler;

import com.radiantk.spring.ChangePasswordService;
import com.radiantk.spring.MemberDao;
import com.radiantk.spring.MemberRegisterService;

public class Assembler {

	private MemberDao memberDao;
	private MemberRegisterService regService;
	private ChangePasswordService pwdService;
	
	public Assembler() {
		memberDao = new MemberDao();
		regService = new MemberRegisterService(memberDao);
		pwdService = new ChangePasswordService();
		pwdService.setMemberDao(memberDao);
	}
	
	public MemberDao getMemberDao() {
		return memberDao;
	}
	
	public MemberRegisterService getMemberRegisterService() {
		return regService;
	}
	
	public ChangePasswordService getChangePasswordService() {
		return pwdService;
	}
	
}