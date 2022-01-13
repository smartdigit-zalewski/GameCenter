package com.smartdigit.zalewski.gamecenter.repository;

import com.smartdigit.zalewski.gamecenter.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
