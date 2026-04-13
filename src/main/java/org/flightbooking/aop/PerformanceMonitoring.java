package org.flightbooking.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceMonitoring {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(PerformanceMonitoring.class);
	
	@Around("execution(* org.flightbooking.service.*.*(..))")
	public Object performance(ProceedingJoinPoint joinPoint) throws Throwable {
		
		long start = System.currentTimeMillis();
		
		Object obj=joinPoint.proceed();
		long end = System.currentTimeMillis();
		
		LOGGER.info("Time taken by "+joinPoint.getSignature().getName() +"is"+(end-start)+"ms");
		
		return obj;
	}

}
