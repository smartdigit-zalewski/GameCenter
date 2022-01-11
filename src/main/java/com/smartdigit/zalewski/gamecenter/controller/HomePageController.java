package com.smartdigit.zalewski.gamecenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String getHomePage() {

        return "homepage";
    }

}
