package com.smartdigit.zalewski.gamecenter.service;

import com.smartdigit.zalewski.gamecenter.domain.Player;
import com.smartdigit.zalewski.gamecenter.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@Transactional
public class PlayerService {

    PlayerRepository repository;

    @Autowired
    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }


    public Player getLoggedPlayer(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return repository.getByUsername(auth.getName());
    }


}
