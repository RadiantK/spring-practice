package com.radiantk.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.radiantk.interceptor.AuthCheckInterceptor;

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
		// ResourceBundleMessageSource는 MessageSource의 구현체
		// 자바의 프로퍼티 파일로부터 메시지를 읽어온다.
		ResourceBundleMessageSource ms =
				new ResourceBundleMessageSource();
		ms.setBasename("message.label"); // message패키지의 label파일을 읽어옴
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}
	
	// 인터셉터를 설정하는 메소드
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authCheckInterceptor())
		.addPathPatterns("/edit/**");
		// 인터셉터를 적용할 경로 패턴을 지정(Ant패턴 사용). 두개이상은 콤마로 구분
	}
	
	@Bean
	public AuthCheckInterceptor authCheckInterceptor() {
		return new AuthCheckInterceptor();
	}
	
	// 스프링 mvc는 자바객체를 HTTP응답으로 변환할 때 HttpMessageConverter를 사용한다.
	// extendMessageConverters메소드는 HttpMessageConverter를 추가 설정할 때 사용
	@Override
	public void extendMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		DateTimeFormatter formatter =
				DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		// ObjectMapper 객체를 JSON형식으로 변환할 때 사용
		// Jackson2ObjectMapperBuilder : ObjectMapper를 쉽게 생성할 수 있는 기능 제공
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder
				.json()
				.serializerByType(LocalDateTime.class, 
						new LocalDateTimeSerializer(formatter))
				.build();
		converters.add(
				0, new MappingJackson2HttpMessageConverter(objectMapper));
	}
}