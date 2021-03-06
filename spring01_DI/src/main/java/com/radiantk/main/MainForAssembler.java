package com.radiantk.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.radiantk.assembler.Assembler;
import com.radiantk.spring.ChangePasswordService;
import com.radiantk.spring.DuplicateMemberException;
import com.radiantk.spring.MemberNotFoundException;
import com.radiantk.spring.MemberRegisterService;
import com.radiantk.spring.RegisterRequest;
import com.radiantk.spring.WrongIdPasswordException;

public class MainForAssembler {

	public static void main(String[] args) throws IOException {
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
				continue;
			} else if(command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			}
			printHelp();
		}
	}
	private static Assembler assembler = new Assembler();
	
	private static void processNewCommand(String[] args) {
		if(args.length != 5) {
			printHelp();
			return;
		}
		
		MemberRegisterService regSvc = assembler.getMemberRegisterService();
		RegisterRequest req = new RegisterRequest();
		
		req.setEmail(args[1]);
		req.setName(args[2]);
		req.setPassword(args[3]);
		req.setConfirmPassword(args[4]);
		
		if(!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호와 확인이 일치하지 않습니다.\n");
			return;
		}
		try {
			regSvc.regist(req);
			System.out.println("등록되었습니다.");
		} catch(DuplicateMemberException e) {
			System.out.println("이미 존재하는 이메일입니다.");
		}
	}
	
	private static void processChangeCommand(String[] args) {
		if(args.length != 4) {
			printHelp();
			return;
		}
		
		ChangePasswordService changePasswordService = 
				assembler.getChangePasswordService();
		
		try {
			changePasswordService.ChangePassword(args[1], args[2], args[3]);
			System.out.println("암호를 변경했습니다.");
		} catch(MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일입니다.");
		} catch(WrongIdPasswordException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다.\n");
		}
	}
	
	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래의 명령어 사용법을 확인하세요.");
		System.out.println("명령어 사용법");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change 이메일 현재비밀번호 변경비밀번호");
		System.out.println();
	}
}