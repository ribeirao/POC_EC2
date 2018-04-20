package com.ribeirao.cloud.application.config.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Transactional(propagation = Propagation.NEVER)
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    public LoggingAspect() {}

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {}

    @Pointcut("within(com.ribeirao..*) && !within(com.ribeirao.cloud.application.config..*))")
    public void anyMethodToLog() {}

    @Pointcut("@annotation(Loggable)")
    public void methodLoggable() {}

    @Pointcut("publicMethod() && methodLoggable()")
    public void publicAndLoggableMethod() {}

    @Before("anyMethodToLog()")
    public void logServiceCall(JoinPoint joinPoint) throws NoSuchMethodException {
        logger.info("Call      " + this.generateMethodCallDescription(joinPoint)
                + ReflectionHelper.generateMethodArgumentsDescription(joinPoint));
    }

    @AfterReturning(value = "anyMethodToLog()")
    public void logServiceReturn(JoinPoint joinPoint) throws NoSuchMethodException {
        logger.info("Return    " + this.generateMethodCallDescription(joinPoint) + " - Success");
    }

    @AfterThrowing(value = "anyMethodToLog()", throwing = "ex")
    public void logServiceException(JoinPoint joinPoint, Throwable ex)
            throws NoSuchMethodException {
        logger.error("Exception " + this.generateMethodCallDescription(joinPoint) + " - Error - "
                + ex.getClass().getSimpleName() + " - " + ex.getMessage(), ex);
    }

    @SuppressWarnings("rawtypes")
    private String generateMethodCallDescription(JoinPoint joinPoint) throws NoSuchMethodException {
        return getString(joinPoint);
    }

    static String getString(JoinPoint joinPoint) {
        StringBuilder builder = new StringBuilder();

        Class aClass = joinPoint.getSignature().getDeclaringType();
        MethodSignature method = (MethodSignature) joinPoint.getSignature();

        String className = aClass.getSimpleName();
        String methodName = method.getName();

        builder.append(className);
        builder.append(".");
        builder.append(methodName);

        return builder.toString();
    }

}
