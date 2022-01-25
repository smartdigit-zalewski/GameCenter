package com.smartdigit.zalewski.gamecenter.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;


@Getter
@Setter
@Entity
@Table(name = "player")
@Component
public class Player extends BaseEntity{


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return totalScore == player.totalScore && gamesPlayed == player.gamesPlayed && Objects.equals(username, player.username) && Objects.equals(email, player.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, totalScore, gamesPlayed);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + this.getId() +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", totalScore=" + totalScore +
                ", gamesPlayed=" + gamesPlayed +
                '}';
    }
}
