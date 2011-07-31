package org.bk.inventory.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditAspect {

    private static Logger logger = LoggerFactory.getLogger(AuditAspect.class);

    public void beforeMethod() {
        logger.info("before method");
    }

    public void afterMethod() {
        logger.info("after method");
    }

    public Object aroundMethod(ProceedingJoinPoint joinpoint) {
        try {
            long start = System.nanoTime();
            Object result = joinpoint.proceed();
            long end = System.nanoTime();
            logger.info(String.format("%s took %d ns", "method", (end - start)));
            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}