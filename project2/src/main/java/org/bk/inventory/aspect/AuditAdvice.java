package org.bk.inventory.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditAdvice {

    private static Logger logger = LoggerFactory.getLogger(AuditAdvice.class);


    public Object aroundMethod(ProceedingJoinPoint joinpoint) {
        try {
            long start = System.nanoTime();
            logger.info("before method:" + joinpoint.getSignature().getName());
            Object result = joinpoint.proceed();
            logger.info("after method:" + joinpoint.getSignature().getName());
            long end = System.nanoTime();
            logger.info(String.format("%s took %d ns", joinpoint.getSignature(), (end - start)));
            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
        
}