package org.flightbooking.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* org.flightbooking.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("➡️ Calling: {}", joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "execution(* org.flightbooking.service.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("✅ Success: {}", joinPoint.getSignature());
        logger.debug("Result: {}", result); // debug level for detailed info
    }

    @AfterThrowing(pointcut = "execution(* org.flightbooking.service.*.*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {
        logger.error("❌ Exception in: {}", joinPoint.getSignature(), ex);
    }

    @Around("execution(* org.flightbooking.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        logger.info("⏱️ Time taken by {} : {} ms", joinPoint.getSignature(), (end - start));
        return result;
    }
}
