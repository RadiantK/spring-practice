package com.radiantk.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.radiantk.config.AppContext;
import com.radiantk.spring.Client;
import com.radiantk.spring.User;

public class Main {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppContext.class);
		
		Client client = ctx.getBean(Client.class);
		client.send();
		
		User user = ctx.getBean(User.class);
		user.send();
		
		ctx.close();
	}

}
