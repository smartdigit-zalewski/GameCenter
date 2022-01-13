package com.smartdigit.zalewski.gamecenter.service;

import com.smartdigit.zalewski.gamecenter.domain.Player;
import com.smartdigit.zalewski.gamecenter.repository.PlayerRepository;
import com.smartdigit.zalewski.gamecenter.util.ContextUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@Transactional
public class PlayerService {

    private final PlayerRepository repository;

    @Autowired
    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }


    public Player getLoggedPlayer(){

        return repository.getByUsername(ContextUser.getLoggedUser());
    }

    public Player getByUsername(String username) {

        return repository.getByUsername(username);
    }

    public Player saveNewPlayer(Player player) {
        return repository.save(player);

    }


}
