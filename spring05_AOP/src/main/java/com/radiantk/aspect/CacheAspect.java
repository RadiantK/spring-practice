package com.radiantk.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
//@Order(2) // Aspect가 적용되는 순서를 직접 지정
public class CacheAspect {

	private Map<Long, Object> cache = new HashMap<>();
	
	@Pointcut("execution(public* com.radiantk.test..*(long))")
	public void cacheCut() {}
	
	@Around("cacheCut()")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		Long num = (Long)joinPoint.getArgs()[0]; // 대상객체의 적용되는 메소드의 파라미터 목록
		if(cache.containsKey(num)) {
			System.out.printf("CacheAspect:Cache에서 구함[%d]\n", num);
			return cache.get(num);
		}
		
		Object result = joinPoint.proceed(); // 메소드에서 리턴하는 결과값
		cache.put(num, result);
		System.out.printf("CacheAspect:Cache에 추가[%d]\n", num);
		return result;
	}
}
