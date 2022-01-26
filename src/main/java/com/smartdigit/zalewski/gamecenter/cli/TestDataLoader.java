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
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

@Configuration
@Profile("test")
public class TestDataLoader implements CommandLineRunner{

    private final PlayerService playerService;

    @Autowired
    public TestDataLoader(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public void run(String... args) throws Exception {

        Player player = Player.builder().username("admin").email("admin@abc.pl").totalScore(0).gamesPlayed(0).build();
        playerService.saveNewPlayer(player);

    }
}
