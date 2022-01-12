package com.smartdigit.zalewski.gamecenter.aspect;

import com.smartdigit.zalewski.gamecenter.domain.Player;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Aspect
@Component
public class PlayerControllerAspect {

    @Pointcut("execution(* com.smartdigit.zalewski.gamecenter.controller.HomePageController.getHomePage(..))")
    public void afterSecurityLogging(){};

    @AfterReturning("afterSecurityLogging()")
    public void printLoggedUser(){
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            Player player = new Player();
            player.setUsername(auth.getName());
            System.out.println("Player logged: " + player.getUsername());
        } else {
            System.out.println("Anonymous user breached!");
        }



    }

}
