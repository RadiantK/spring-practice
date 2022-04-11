package com.radiantk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Controller : 웹 요청을 처리하고 그 결과를 뷰에 전달하는 스프링 빈 객체
@Controller // spring MVC에서 컨트롤러로 사용
public class HelloController {
	
	// Model객체는 컨트롤러의 처리결과를 view에 전달할 때 사용
	@GetMapping("hello")
	public String hello(Model model
			,@RequestParam(value="name", required=false) String name) {
		model.addAttribute("greeting", "안녕하세요, " + name);
		
		return "hello";
	}
}
