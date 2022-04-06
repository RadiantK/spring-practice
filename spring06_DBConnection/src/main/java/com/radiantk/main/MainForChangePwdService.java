package com.radiantk.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.radiantk.config.AppContext;
import com.radiantk.spring.ChangePasswordService;
import com.radiantk.spring.MemberNotFoundException;
import com.radiantk.spring.WrongIdPasswordException;

public class MainForChangePwdService {
	public static void main(String[] args) {

		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppContext.class);
		
		ChangePasswordService cps = ctx.getBean(ChangePasswordService.class);
		
		try {
			cps.ChangePassword(
					"radiantk@radiantk.com", "1234", "1111");
			System.out.println("암호를 변경했습니다.");
		} catch(MemberNotFoundException e) {
			System.out.println("회원 데이터가 존재하지 않습니다.");
		} catch(WrongIdPasswordException e) {
			System.out.println("아이디가 올바르지 않습니다.");
		}
		
		ctx.close();
	}

}
