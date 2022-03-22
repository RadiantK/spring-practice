package com.radiantk.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.radiantk.spring.MemberDao;
import com.radiantk.spring.MemberPrinter;
import com.radiantk.spring.MemberSummaryPrinter;
import com.radiantk.spring.VersionPrinter;

@Configuration
@ComponentScan(basePackages = {"com.radiantk.spring"},
		excludeFilters = @Filter(
				type = FilterType.ANNOTATION, classes = {ManualBean.class}))
public class AppContextWithExclude {

	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
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
