package com.smartdigit.zalewski.gamecenter.controller;

import com.smartdigit.zalewski.gamecenter.domain.Game;
import com.smartdigit.zalewski.gamecenter.domain.Player;
import com.smartdigit.zalewski.gamecenter.domain.enums.GameStatus;
import com.smartdigit.zalewski.gamecenter.repository.GameRepository;
import com.smartdigit.zalewski.gamecenter.service.GameService;
import com.smartdigit.zalewski.gamecenter.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomePageController {

    PlayerService playerService;
    GameService gameService;

    @Autowired
    public HomePageController(PlayerService playerService, GameService gameService) {
        this.playerService = playerService;
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        Player loggedPlayer = playerService.getLoggedPlayer();
        List<Game> gamesToJoin = gameService.getGamesWithStatus(GameStatus.WAITS_FOR_SECOND_PLAYER);

        if(!(gamesToJoin == null || gamesToJoin.size() < 1)) {
            model.addAttribute("gamesToJoin",gamesToJoin);
        }

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
