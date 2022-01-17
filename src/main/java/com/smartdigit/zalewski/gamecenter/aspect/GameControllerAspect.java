package com.smartdigit.zalewski.gamecenter.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by SmartDigit Michal Zalewski
 * Date: 16.01.2022
 */
@Aspect
@Component
public class GameControllerAspect {


    @Pointcut("execution(* com.smartdigit.zalewski.gamecenter.controller.GameRestController.*(..))")
    public void beforeRestController(){};

    @Before("beforeRestController()")
    public void logRequest(JoinPoint joinPoint) {

        System.out.println(joinPoint.getSignature());
        System.out.println("Rest controller invoked");



    }


}
