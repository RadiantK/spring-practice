package com.radiantk.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.radiantk.config.AppContext;
import com.radiantk.spring.ChangePasswordService;
import com.radiantk.spring.DuplicateMemberException;
import com.radiantk.spring.MemberInfoPrinter;
import com.radiantk.spring.MemberListPrinter;
import com.radiantk.spring.MemberNotFoundException;
import com.radiantk.spring.MemberRegisterService;
import com.radiantk.spring.RegisterRequest;
import com.radiantk.spring.WrongIdPasswordException;

public class Main {
	private static AnnotationConfigApplicationContext ctx = null;
	
	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppContext.class);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			System.out.println("명령어를 입력하세요.");
			String command = br.readLine();
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			if(command.startsWith("new ")) {
				processNewCommand(command.split(" "));
			} else if(command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
			} else if(command.equals("list")) {
				processListCommand();
			} else if(command.startsWith("info ")) {
				processInfoCommand(command.split(" "));
			} else {
				printHelp();
			}
		}
	}

	private static void processNewCommand(String[] arg) {
		if(arg.length != 5) {
			printHelp();
			return;
		}
		MemberRegisterService regService =
				ctx.getBean(MemberRegisterService.class);
		RegisterRequest request = new RegisterRequest();
		request.setEmail(arg[1]);
		request.setName(arg[2]);
		request.setPassword(arg[3]);
		request.setConfirmPassword(arg[4]);
		
		if(!request.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호와 확인이 일치하지 않습니다.");
			return;
		}
		try {
			regService.regist(request);
			System.out.println("등록했습니다.\n");
		} catch(DuplicateMemberException e) {
			System.out.println("이미 존재하는 이메일 입니다.");
		}
	}

	private static void processChangeCommand(String[] arg) {
		if(arg.length != 4) {
			printHelp();
			return;
		}
		ChangePasswordService changePasswordService =
				ctx.getBean(ChangePasswordService.class);
		
		try {
			changePasswordService.ChangePassword(arg[1], arg[2], arg[3]);
		} catch(MemberNotFoundException e) {
			System.out.println("존재하지 않느 이메일 입니다.");
		} catch(WrongIdPasswordException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다.");
		}
	}

	private static void processListCommand() {
		MemberListPrinter listPrinter = 
				ctx.getBean(MemberListPrinter.class);
		listPrinter.printAll();
	}

	private static void processInfoCommand(String[] arg) {
		if(arg.length != 2) {
			printHelp();
			return;
		}
		MemberInfoPrinter infoPrinter = 
				ctx.getBean(MemberInfoPrinter.class);
		infoPrinter.printMemberInfo(arg[1]);
	}

	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래의 명령어 사용법을 확인하세요.");
		System.out.println("명령어 사용법");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change 이메일 현재비밀번호 변경비밀번호");
		System.out.println("list");
		System.out.println("info 이메일");
		System.out.println();
	}
}
