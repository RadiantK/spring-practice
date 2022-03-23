package com.radiantk.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.radiantk.config.AppContextBeanPrototype;
import com.radiantk.spring.Client;
import com.radiantk.spring.User;

public class MainForBeanType {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppContextBeanPrototype.class);
		
		Client client1 = ctx.getBean(Client.class);
		Client client2 = ctx.getBean(Client.class);
		
		System.out.println(client1 == client2);
		
		User user1 = ctx.getBean(User.class);
		User user2 = ctx.getBean(User.class);
		
		System.out.println(user1 == user2);
		
		ctx.close();
	}

}
