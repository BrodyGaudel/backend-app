package com.brody.forumgabontunisie.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class AopConfig {
    @Before("execution(* com.brody.forumgabontunisie.services.implementations.InscriptionServiceImpl.*(..))")
    public void logMethodVmServiceEntry(JoinPoint joinPoint){
        logger(joinPoint);
    }

    @Before("execution(* com.brody.forumgabontunisie.services.implementations.UserServiceImpl.*(..))")
    public void logMethodUserServiceEntry(JoinPoint joinPoint){
        logger(joinPoint);
    }

    private void logger(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        log.info("In method : "+name+":");
    }
}
