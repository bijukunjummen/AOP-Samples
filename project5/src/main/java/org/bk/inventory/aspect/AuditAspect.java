package org.bk.inventory.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.bk.inventory.types.Inventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class AuditAspect {

    private static Logger logger = LoggerFactory.getLogger(AuditAspect.class);

    @Pointcut("execution(* org.bk.inventory.service.*.*(..))")
    public void serviceMethods(){
        //
    }
    @Pointcut("execution(* org.bk.inventory.service.*.*(org.bk.inventory.types.Inventory)) && args(inventory)")
    public void serviceMethodsWithInventoryAsParam(Inventory inventory){
        //
    }
    
    @Before("serviceMethods()")
    public void beforeMethod() {
        logger.info("before method");
    }

    @Around("serviceMethods()")
    public Object aroundMethod(ProceedingJoinPoint joinpoint) {
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
    
    @Around("serviceMethodsWithInventoryAsParam(inventory)")
    public Object aroundMethodWithParameter(ProceedingJoinPoint joinpoint, Inventory inventory) {
        try {
            Object result = joinpoint.proceed();
            logger.info(String.format("WITH PARAM: %s", inventory.toString()));
            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    
    @After("serviceMethods()")
    public void afterMethod() {
        logger.info("after method");
    }    
}