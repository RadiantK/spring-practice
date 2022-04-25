package com.radiantk.spring;

import org.springframework.transaction.annotation.Transactional;

public class ChangePasswordService {

	private MemberDao memberDao;
	
	// 트랜잭션은 런타임예외가 발생하면 롤백하는데 SQL, IOException은 런타임예외를 상속하지 않으므로
	// 런타임 이외의 예외에도 트랜젝션을 롤백하고 싶으면 rollbackFor속성으로 예외를 지정함
//	@Transactional(rollbackFor = {SQLException.class, IOException.class})
	@Transactional
	public void ChangePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			throw new MemberNotFoundException();
		}
		
		member.changePassword(oldPwd, newPwd);
		memberDao.update(member);
	}
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
}
