package com.radiantk.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.radiantk.config.AppContext;
import com.radiantk.spring.Member;
import com.radiantk.spring.MemberDao;

public class MainForMemberDao {
	private static MemberDao memberDao;
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppContext.class);
		
		memberDao = ctx.getBean(MemberDao.class);
		
		selectAll();
		updateMember();
		insertMember();
		
		ctx.close();
	}
	
	private static void selectAll() {
		System.out.println("----selectAll");
		int total = memberDao.count();
		System.out.println("전체 데이터 : " + total);
		List<Member> members = memberDao.selectAll(); // 전체 멤버 출력
		for(Member m : members) {
			System.out.println(m.getId()+":"+m.getEmail()+":"+m.getName());
		}
	}
	
	private static void updateMember() {
		System.out.println("----- updateMember");
		Member member = memberDao.selectByEmail("radiantk@radiantk.com");
		String oldPwd = member.getPassword();
		String newPwd = Double.toHexString(Math.random());
		member.changePassword(oldPwd, newPwd);
		
		memberDao.update(member);
		System.out.println("암호변경 : " + oldPwd + " > " + newPwd);
	}
	
	private static DateTimeFormatter formatter = 
			DateTimeFormatter.ofPattern("MMddHHmm");
	
	private static void insertMember() {
		System.out.println("----- insertMember");
		
		String prefix = formatter.format(LocalDateTime.now());
		Member member = new Member(
				prefix+"@test.com", prefix, prefix, LocalDateTime.now());
		memberDao.insert(member);
		System.out.println(member.getId()+" 데이터 추가");
	}
}
