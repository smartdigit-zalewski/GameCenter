package com.smartdigit.zalewski.gamecenter.util;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ContextUser {

    public static String getLoggedUser(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }


}
