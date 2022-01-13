package com.smartdigit.zalewski.gamecenter.repository;

import com.smartdigit.zalewski.gamecenter.domain.Game;
import com.smartdigit.zalewski.gamecenter.domain.enums.GameStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findAllByGameStatus(GameStatus gameStatus);
}
