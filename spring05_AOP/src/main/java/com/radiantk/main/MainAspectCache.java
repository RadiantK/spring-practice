package com.radiantk.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.radiantk.config.AppContextCache;
import com.radiantk.test.Calculator;

public class MainAspectCache {
	
	public static void main(String[] args) {
	
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppContextCache.class);
		
		Calculator calc = ctx.getBean("calculator", Calculator.class);
		calc.factorial(6);
		calc.factorial(6);
		
		calc.factorial(5);
		calc.factorial(5);
		
		ctx.close();
	}
}
