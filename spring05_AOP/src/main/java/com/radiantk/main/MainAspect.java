package com.radiantk.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.radiantk.config.AppContext;
import com.radiantk.test.Calculator;

public class MainAspect {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppContext.class);
		
		Calculator calc = ctx.getBean("calculator", Calculator.class);
//		Calculator calc = ctx.getBean("calculator", RecursiveCalculator.class);
		long fiveFact = calc.factorial(5);
		System.out.println("calc.factorial(5) : " + fiveFact);
		System.out.println(calc.getClass().getName());
		
		ctx.close();
	}

}
