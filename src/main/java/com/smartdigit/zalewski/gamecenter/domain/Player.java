package com.smartdigit.zalewski.gamecenter.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String email;
    @Column(name = "total_score")
    private int totalScore;
    @Column(name = "games_played")
    private int gamesPlayed;

    public Player() {
    }

    public Player(String username, String email) {
        this.username = username;
        this.email = email;
    }



}
