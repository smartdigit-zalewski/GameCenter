package com.smartdigit.zalewski.gamecenter.controller;

import com.smartdigit.zalewski.gamecenter.domain.Player;
import com.smartdigit.zalewski.gamecenter.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    PlayerService playerService;

    @Autowired
    public HomePageController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        Player loggedPlayer = playerService.getLoggedPlayer();

        model.addAttribute("player",loggedPlayer);

        return "homepage";
    }

    @GetMapping("/fragments")
    public String getFragments() {
        return "fragments";
    }

    public String getNavbar(Model model){
        //Player loggedPlayer = playerService.getLoggedPlayer();

        //model.addAttribute("player",loggedPlayer);

        return "fragments/navbar";
    }

}
