package com.smartdigit.zalewski.gamecenter.domain;

/**
 * Created by SmartDigit Michal Zalewski
 * Date: 17.01.2022
 */
public class RestResponse {

    private String response;

    public RestResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
