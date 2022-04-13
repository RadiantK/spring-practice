package com.radiantk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// WebMvcConfigurer 인터페이스 : 스프링 MVC의 개별설정을 조정할 때 사용
@Configuration // 스프링 설정 클래스
@EnableWebMvc // 스프링 MVC를 구성할 때 필요한 빈의 설정을 자동으로 해줌(빈의 등록을 손쉽게 할 수 있음)
public class MvcConfig implements WebMvcConfigurer {

	// DispatcherServlet의 매핑경로를'/'로 주었을 때
	// JSP/HTML/CSS 등을 올바르게 처리하기 위한 설정을 추가한다.
	// 우선순위가 가장 낮기때문에 모든 요청의 경로를 defaultServlet이 처리하게 된다.
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	// JSP를 이용해서 컨트롤러의 실행결과를 보여주기 위한 설정을 추가한다.
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// jsp(prefix, suffix)
		// prefix + 뷰 이름 + suffix의 경로를 뷰 코드로 사용하는 뷰 객체를 리턴
		registry.jsp("/WEB-INF/view/", ".jsp");
	}
}
