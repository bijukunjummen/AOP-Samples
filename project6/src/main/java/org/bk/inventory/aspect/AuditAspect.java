package org.bk.inventory.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class AuditAspect {

    private static Logger logger = LoggerFactory.getLogger(AuditAspect.class);

    @Pointcut("execution(@org.bk.annotations.PerfLog * *.*(..))")
    public void performanceTargets(){}
   

    @Around("performanceTargets()")
    public Object logPerformanceStats(ProceedingJoinPoint joinpoint) {
        try {
            long start = System.nanoTime();
            Object result = joinpoint.proceed();
            long end = System.nanoTime();
            logger.info(String.format("%s took %d ns", joinpoint.getSignature(), (end - start)));
            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    
}