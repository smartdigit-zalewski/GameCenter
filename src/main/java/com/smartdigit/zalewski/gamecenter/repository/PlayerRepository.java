package com.smartdigit.zalewski.gamecenter.repository;

import com.smartdigit.zalewski.gamecenter.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player getByUsername(String username);
    Player getByEmail(String email);
}
