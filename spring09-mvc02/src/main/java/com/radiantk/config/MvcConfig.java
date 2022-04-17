package com.radiantk.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//WebMvcConfigurer 인터페이스 : 스프링 MVC의 개별설정을 조정할 때 사용
@Configuration // 스프링 설정 클래스
@EnableWebMvc // 스프링 MVC를 구성할 때 필요한 빈의 설정을 자동으로 해줌(빈의 등록을 손쉽게 할 수 있음)
public class MvcConfig implements WebMvcConfigurer {

//	DispatcherSerlvet이 처리하지 못한 요청을 DefaultSerlvet에게 넘겨주는 역할을 하는 핸들러
//	*.css와 같은 컨트롤러에 매핑되어 있지 않은 URL요청은 최종적으로 DefaultServlet에 전달되어 처리하는 역할
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
	
	// 특정 요청 url에 대해 컨트롤러 로직 없이 바로 뷰를 리턴하는 경우
	// ViewController를 사용해서 뷰를 매핑할 수 있다.
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/main").setViewName("main");
	}
	
	// 메시지 파일에서 값을 읽어오는 MessageSource 빈 설정(빈은 messageSource 해야한다.)
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource ms =
				new ResourceBundleMessageSource();
		ms.setBasename("message.label");
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}
}