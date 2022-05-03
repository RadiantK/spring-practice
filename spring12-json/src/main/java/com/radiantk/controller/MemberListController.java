package com.radiantk.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.radiantk.command.ListCommand;
import com.radiantk.dao.MemberDao;
import com.radiantk.entity.Member;

@Controller
public class MemberListController {
	
	private MemberDao memberDao;
	
	public void setMemberListController(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	// Errors타입 파라미터가 list Command 바로 뒤에 위치시킨 것에 유의
	@RequestMapping("/members")
	public String list(@ModelAttribute("cmd") ListCommand listCommand,
			Errors errors, Model model) {
		if(errors.hasErrors()) {
			return "member/memberList";
		}
		
		if(listCommand.getFrom() != null && listCommand.getTo() != null) {
			List<Member> members = memberDao.selectByRegdate(
					listCommand.getFrom(), listCommand.getTo());
			model.addAttribute("members", members);
		}
		return "member/memberList";		
	}
}
