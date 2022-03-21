package com.radiantk.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.radiantk.spring.MemberPrinter;
import com.radiantk.spring.MemberSummaryPrinter;
import com.radiantk.spring.VersionPrinter;

// @component가 붙은 클래스의 객체를 생성해서 빈으로 등록
@Configuration
@ComponentScan(basePackages = {"com.radiantk.spring"})
public class AppContext {

	@Bean
	@Qualifier("printer")
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	
	@Bean
	@Qualifier("summaryPrinter")
	public MemberPrinter memberPrinter2() {
		return new MemberSummaryPrinter();
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		
		return versionPrinter;
	}
}
