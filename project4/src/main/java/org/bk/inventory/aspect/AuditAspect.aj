package org.bk.inventory.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public aspect AuditAspect {
    private static Logger logger = LoggerFactory.getLogger(AuditAspect.class);

    pointcut serviceMethods() : execution(* org.bk.inventory.service..*(..));

//    pointcut serviceMethodsWithInventoryAsParam(Inventory inventory) : execution(* org.bk.inventory.service.*.*(Inventory)) && args(inventory);

    before() : serviceMethods() {
    	logger.info("--------------------------------------------------------------------------------------");
        logger.info("before method {}", thisJoinPoint.getSignature().toShortString());
    }

    Object around() : serviceMethods() {
        long start = System.nanoTime();
        Object result = proceed();
        long end = System.nanoTime();
        logger.info(String.format("%s took %d ns", thisJoinPoint.getSignature().toShortString(),
                (end - start)));
        return result;
    }

//    Object around(Inventory inventory) : serviceMethodsWithInventoryAsParam(inventory) {
//        Object result = proceed(inventory);
//        logger.info(String.format("WITH PARAM: %s", inventory.toString()));
//        return result;
//    }
    after() : serviceMethods() {
        logger.info("after method {}", thisJoinPoint.getSignature().toShortString());
    }
}
