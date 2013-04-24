package org.bk.inventory.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class AuditAspect {

    private static Logger logger = LoggerFactory.getLogger(AuditAspect.class);

    @Pointcut("execution(@org.bk.annotations.PerfLog * org.bk.inventory.service..*(..))")
    public void performanceTargets(){}
   
    @Before("performanceTargets()")
    public void before(JoinPoint joinpoint ) {
    	logger.info("--------------------------------------------------------------------------------------");
    	logger.info("Before {}", joinpoint.getSignature().toShortString());
    }

    @Around("performanceTargets()")
    public Object logPerformanceStats(ProceedingJoinPoint joinpoint) {
        try {
            long start = System.nanoTime();
            Object result = joinpoint.proceed();
            long end = System.nanoTime();
            logger.info(String.format("%s took %d ns", joinpoint.getSignature().toShortString(), (end - start)));
            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
   
    @After("performanceTargets()")
    public void after(JoinPoint joinpoint ) {
    	logger.info("After {}", joinpoint.getSignature().toShortString());
    }    
}