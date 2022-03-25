package com.radiantk.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ExecuteTimeAspect {

	// com.radiantk.test패키지와 그 하위 패키지에 위치한 타입의 public 메서드를 지정
	@Pointcut("execution(public* com.radiantk.test..*(..))")
	private void cut() {}
	
	// ProceedingJoinPoint 프록시 대상 객체의 메소드를 호출할 때 사용
	@Around("cut()")
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.nanoTime();
		
		try {
			Object result = joinPoint.proceed(); // 메소드를 호출할 때 사용
			System.out.println("result : " + result);
			return result;
		}  finally {
			long finish = System.nanoTime();
			Signature sig = joinPoint.getSignature(); // 호출되는 메소드의 정보
			System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n",
					joinPoint.getTarget().getClass().getSimpleName(), // 대상 객체
					sig.getName(), Arrays.toString(joinPoint.getArgs()),
					(finish - start));
		}
	}
}
