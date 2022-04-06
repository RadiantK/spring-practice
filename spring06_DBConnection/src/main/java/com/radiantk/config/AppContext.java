package com.radiantk.config;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.radiantk.spring.ChangePasswordService;
import com.radiantk.spring.MemberDao;
import com.radiantk.spring.MemberInfoPrinter;
import com.radiantk.spring.MemberListPrinter;
import com.radiantk.spring.MemberPrinter;
import com.radiantk.spring.MemberRegisterService;

@Configuration
@EnableTransactionManagement
public class AppContext {
	
	@Bean(destroyMethod = "close") // 커넥션 풀에 보관된 커넥션을 닫음
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver"); // JDBC 드라이버 클래스 지정
		ds.setUrl("jdbc:mysql://localhost/spring5?characterEncoding=utf8"); // jdbc url지정
		ds.setUsername("spring5");
		ds.setPassword("spring5");
		ds.setInitialSize(2); // 초기 커넥션 개수(기본값 10)
		ds.setMaxActive(10); // 최대 커넥션 개수(기본값 100)
		ds.setTestWhileIdle(true); // 가동되지않는 커넥션 검사(유휴 커넥션)
		ds.setMinEvictableIdleTimeMillis(60000 * 3); // 최소 가동되지 않는 시간 3분
		ds.setTimeBetweenEvictionRunsMillis(1000 * 10); // 10초 주기로 유휴 검사
		
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		
		return tm;
	}

	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}
	
	@Bean
	public MemberRegisterService memberRegisterService() {
		return new MemberRegisterService(memberDao());
	}
	
	@Bean
	public ChangePasswordService changePasswordService() {
		ChangePasswordService pwdService = new ChangePasswordService();
		pwdService.setMemberDao(memberDao());
		
		return pwdService;
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberListPrinter memberListPrinter() {
		return new MemberListPrinter(memberDao(), memberPrinter());
	}
	
	@Bean
	public MemberInfoPrinter memberInfoPrinter() {
		MemberInfoPrinter printer = new MemberInfoPrinter();
		printer.setMemberDao(memberDao());
		printer.setMemberPrinter(memberPrinter());
		
		return printer;
	}
}
