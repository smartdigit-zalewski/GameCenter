package com.smartdigit.zalewski.gamecenter;

import com.smartdigit.zalewski.gamecenter.domain.Game;
import com.smartdigit.zalewski.gamecenter.domain.Player;
import com.smartdigit.zalewski.gamecenter.repository.GameRepository;
import com.smartdigit.zalewski.gamecenter.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

@SpringBootApplication
public class GameCenterApplication {


	public static void main(String[] args) {

		SpringApplication.run(GameCenterApplication.class, args);

		Player playerOne = new Player("admin","admin@abc.pl");
		Player playerTwo = new Player("user","user@abc.pl");

		Game testGame = new Game();




	}

	@Autowired
	public static GameRepository getGameRepository (GameRepository gameRepository) {
		return gameRepository;
	}

	@Autowired
	public static PlayerRepository getPlayerRepository (PlayerRepository playerRepository){
		return playerRepository;
	}

}
