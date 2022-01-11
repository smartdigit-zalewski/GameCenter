package com.smartdigit.zalewski.gamecenter.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
public class Player {

    private long id;
    private String username;
    private String email;
    private int totalScore;
    private int gamesPlayed;

    public Player() {
    }

    public Player(String username, String email) {
        this.username = username;
        this.email = email;
    }



}
