package com.radiantk.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {
	
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver"); // JDBC 드라이버 클래스 지정
		ds.setUrl("jdbc:mysql://localhost/spring5?characterEncoding=utf8"); // jdbc url지정
		ds.setUsername("spring5");
		ds.setPassword("spring5");
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		
		return ds;
	}
}
