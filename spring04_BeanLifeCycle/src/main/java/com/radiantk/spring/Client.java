package com.radiantk.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Client implements InitializingBean, DisposableBean{

	private String host;
	
	public void setUser(String host) {
		this.host = host;
	}
	
	// 컨테이너 초기화 과정에서 실행
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("초기화 과정에서 실행하는 메소드 afterPropertiesSet()");
	}
	
	public void send() {
		System.out.println(host + " to send message");
	}
	
	// 컨테이너 소멸 과정에서 실행
	@Override
	public void destroy() throws Exception {
		System.out.println("소멸 과정에서 실행하는 메소드 destroy()");
	}

}
