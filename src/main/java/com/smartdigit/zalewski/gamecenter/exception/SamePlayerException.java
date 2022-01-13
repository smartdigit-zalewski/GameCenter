package com.smartdigit.zalewski.gamecenter.exception;

/**
 * Created by SmartDigit Michal Zalewski
 * Date: 13.01.2022
 */
public class SamePlayerException extends RuntimeException {

    public SamePlayerException(String name){
        super("User can't join game created by him. User: " + name);
    }
}
