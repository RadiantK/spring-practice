package com.radiantk.main;

import com.radiantk.test.ExecuteTimeCalculator;
import com.radiantk.test.ImpeCalculator;
import com.radiantk.test.RecursiveCalculator;

public class MainProxy {

	public static void main(String[] args) {

		ExecuteTimeCalculator calc1 = 
				new ExecuteTimeCalculator(new ImpeCalculator());
		System.out.println(calc1.factorial(10));
		
		ExecuteTimeCalculator calc2 = 
				new ExecuteTimeCalculator(new RecursiveCalculator());
		System.out.println(calc2.factorial(10));
	}

}
