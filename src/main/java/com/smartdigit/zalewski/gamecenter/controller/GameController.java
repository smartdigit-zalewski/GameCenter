package com.smartdigit.zalewski.gamecenter.controller;

import com.smartdigit.zalewski.gamecenter.domain.Game;
import com.smartdigit.zalewski.gamecenter.domain.Player;
import com.smartdigit.zalewski.gamecenter.domain.enums.GameStatus;
import com.smartdigit.zalewski.gamecenter.exception.SamePlayerException;
import com.smartdigit.zalewski.gamecenter.service.GameService;
import com.smartdigit.zalewski.gamecenter.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Component
public class GameController {


    private final PlayerService playerService;
    private final GameService gameService;

    @Autowired
    public GameController(PlayerService playerService, GameService gameService) {
        this.playerService = playerService;
        this.gameService = gameService;
    }

    @GetMapping("/createGame")
    public String createGame(Model model){
        System.out.println("Create game controller invoked");
        Player player = playerService.getLoggedPlayer();
        System.out.println("Print current player:  " + player);
        Game game = gameService.saveNewGame(player);

        model.addAttribute("currentGame", game);

        return "game-page";
    }

    @GetMapping("/joinGame")
    public String joinGame(@RequestParam("gameId") Long id, Model model) {
        Player secondPlayer = playerService.getLoggedPlayer();
        Game game = gameService.getGameById(id);
        if (!(game.getFirstPlayer().equals(secondPlayer))) {
            game.setSecondPlayer(secondPlayer);
            game.addFleet(secondPlayer);
            game.setGameStatus(GameStatus.SETTING_POSITIONS);
            game.setTurn(Game.Turn.FIRST_PLAYER_TURN);
            game = gameService.updateGame(game);

        } else {
            throw new SamePlayerException(secondPlayer.getUsername());
        }

        model.addAttribute("currentGame", game);

        return "game-page";
    }

    @GetMapping("/enter")
    public String enterGame(@RequestParam("gameId") Long id, Model model){
        Game game = gameService.getGameById(id);
        model.addAttribute("currentGame", game);

        return "game-page";
    }

    @GetMapping("/delete")
    public String deleteGame(@RequestParam("gameId") Long id){

        gameService.deleteGameById(id);

        return "redirect:/";
    }


}
