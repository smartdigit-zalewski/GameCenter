package com.smartdigit.zalewski.gamecenter.cli;

import com.smartdigit.zalewski.gamecenter.domain.Game;
import com.smartdigit.zalewski.gamecenter.domain.Player;
import com.smartdigit.zalewski.gamecenter.domain.enums.GameStatus;
import com.smartdigit.zalewski.gamecenter.domain.enums.GameType;
import com.smartdigit.zalewski.gamecenter.service.GameService;
import com.smartdigit.zalewski.gamecenter.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class TestRunner {

    private final PlayerService playerService;
    private final GameService gameService;

    @Autowired
    public TestRunner(PlayerService playerService, GameService gameService) {
        this.playerService = playerService;
        this.gameService = gameService;
    }

    @Bean
    CommandLineRunner testingDB() {

        Player one = playerService.getByUsername("admin");
        Player two = playerService.getByUsername("user");
        Game game = new Game();
        game.setFirstPlayer(one);
        game.setSecondPlayer(two);
        game.setGameType(GameType.BATTLE_SHIPS);
        game.setCreated(LocalDateTime.now());
        game.setGameStatus(GameStatus.WAITS_FOR_PLAYER);
        game.addFleet(one);
        game.addFleet(two);



        return args -> {
            System.out.println("Player one: " + one);
            System.out.println("Player two " + two);
            System.out.println("SAVING GAME IN PROGRESS...");
            gameService.saveNewGame(game);
            System.out.println("Game created");
            System.out.println(game);

        };
    }


}
