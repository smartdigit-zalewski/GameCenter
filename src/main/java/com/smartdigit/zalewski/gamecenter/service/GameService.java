package com.smartdigit.zalewski.gamecenter.service;

import com.smartdigit.zalewski.gamecenter.domain.Game;
import com.smartdigit.zalewski.gamecenter.domain.Player;
import com.smartdigit.zalewski.gamecenter.domain.enums.GameStatus;
import com.smartdigit.zalewski.gamecenter.domain.enums.GameType;
import com.smartdigit.zalewski.gamecenter.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.midi.Soundbank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GameService {

    private final GameRepository repository;
    private final ApplicationContext applicationContext;

    @Autowired
    public GameService(GameRepository repository, ApplicationContext applicationContext) {
        this.repository = repository;
        this.applicationContext = applicationContext;
    }

    public Game saveNewGame(Player player) {

        Game game = applicationContext.getBean(Game.class);
        game.setGameType(GameType.BATTLE_SHIPS);
        game.setFirstPlayer(player);
        game.setCreated(LocalDateTime.now());
        game.setGameStatus(GameStatus.WAITS_FOR_SECOND_PLAYER);

        return repository.save(game);
    }

    public Game getGameById(Long id) {
        Game tmpGame = repository.getById(id);
        Game game = applicationContext.getBean(Game.class);
        game.copyFields(tmpGame);
        return game;
    }

    public Game updateGame(Game game) {
        return repository.save(game);
    }

    public void deleteGameById(Long id) {

        repository.deleteById(id);

    }


    public List<Game> getGamesWithStatus(GameStatus gameStatus) {

        return repository.findAllByGameStatus(gameStatus);
    }
}
