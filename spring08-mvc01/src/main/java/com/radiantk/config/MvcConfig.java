package com.radiantk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//WebMvcConfigurer 인터페이스 : 스프링 MVC의 개별설정을 조정할 때 사용
@Configuration // 스프링 설정 클래스
@EnableWebMvc // 스프링 MVC를 구성할 때 필요한 빈의 설정을 자동으로 해줌(빈의 등록을 손쉽게 할 수 있음)
public class MvcConfig implements WebMvcConfigurer {

//	*.jsp는 톰캣안에 매핑되어있는데 defaultServlet도 매핑되어있다.
//	그래서 defaultServlet를 재정의하면 내장된 매핑보다 우선적으로 적용된다.(dispather가 먼저적용)
	
//	DispatcherSerlvet이 처리하지 못한 요청을 DefaultSerlvet에게 넘겨주는 역할을 하는 핸들러
//	*.css와 같은 컨트롤러에 매핑되어 있지 않은 URL요청은 최종적으로 DefaultServlet에 전달되어 처리하는 역할
//	DispatcherServlet의 매핑이 "/"로 지정하면 JSP를 제외한 모든 요청이 DispatcherServlet으로 가기 때문에,
//	WAS가 제공하는 Default Servlet이 *.html, *.css같은 요청을 처리할 수 없게됨.
//	Default ServletHandler는 이런 요청들을 Default Servlet에게 전달해주는 Handler이다.
//	요청 URL에 매핑되는 컨트롤러가 존재하지 않을 때, 404응답 대신,
//	DefaultServlet이 해당 요청 URL을 처리하도록 함.
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	// JSP를 이용해서 컨트롤러의 실행결과를 보여주기 위한 설정.
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// jsp(prefix, suffix)
		// prefix + 뷰 이름 + suffix의 경로를 뷰 코드로 사용하는 뷰 객체를 리턴
		registry.jsp("/WEB-INF/view/", ".jsp");
	}
}