package com.radiantk.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.radiantk.command.ErrorResponse;
import com.radiantk.command.RegisterRequest;
import com.radiantk.dao.MemberDao;
import com.radiantk.entity.Member;
import com.radiantk.exception.DuplicateMemberException;
import com.radiantk.service.MemberRegisterService;

@RestController
public class RestMemberController {

	private MemberDao memberDao;
	private MemberRegisterService memberRegisterService;
	
	@GetMapping("api/members")
	public List<Member> members(){
		return memberDao.selectAll();
	}
	
//	@GetMapping("/api/members/{id}")
//	public Member member(@PathVariable Long id,
//			HttpServletResponse response) throws IOException {
//		
//		Member member = memberDao.selectById(id);
//		if(member == null) {
//			response.sendError(HttpServletResponse.SC_NOT_FOUND);
//			return null;
//		}
//		return member;
//	}
	
	@GetMapping("/api/members/{id}")
	public ResponseEntity<Object> member(@PathVariable Long id){
		Member member = memberDao.selectById(id);
		if(member == null) {
			// 응답을 HttpServletResponse는 HTML 형식으로 제공하는데
			// ResponseEntity를 사용해서 JSON형태의 body에 사용한 객체로 응답결과를 반환
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ErrorResponse("no member"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(member);
	}
	
//	@PostMapping("/api/members")
//	public void newMember(@RequestBody @Valid RegisterRequest regReq,
//			HttpServletResponse response) throws IOException {
//		
//		try {
//			Long newMemberId = memberRegisterService.regist(regReq);
//			response.setHeader("Location", "/api/members/" + newMemberId);
//			response.setStatus(HttpServletResponse.SC_CREATED);
//		}catch(DuplicateMemberException e) {
//			response.sendError(HttpServletResponse.SC_CONFLICT);
//		}
//	}
	
	@PostMapping("/api/members")
	public ResponseEntity<Object> newMember(
			@RequestBody @Valid RegisterRequest regReq/*, Errors errors*/) {
		/*
		if(errors.hasErrors()) {
			String errorCodes = errors.getAllErrors() // List<ObjectError>
				.stream()
				.map(error -> error.getCodes()[0]) // error는 ObjectError
				.collect(Collectors.joining(","));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorResponse("errorCodes= " + errorCodes));
		}
		*/
		try {
			Long newMemberId = memberRegisterService.regist(regReq);
			URI uri = URI.create("/api/members/" + newMemberId);
			return ResponseEntity.created(uri).build();
			
		}catch(DuplicateMemberException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}
	
}
