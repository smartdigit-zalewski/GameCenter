package com.smartdigit.zalewski.gamecenter.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartdigit.zalewski.gamecenter.domain.enums.GameStatus;
import com.smartdigit.zalewski.gamecenter.domain.enums.GameType;
import com.smartdigit.zalewski.gamecenter.util.HashMapConverter;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "game")
@Component
public class Game {

    private enum Turn {
        FIRST_PLAYER_TURN,
        SECOND_PLAYER_TURN
    }
    @Autowired
    @Transient
    private ObjectMapper objectMapper;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private GameType gameType;
    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;
    @Enumerated(EnumType.STRING)
    private Turn turn;
    @ManyToOne
    @JoinColumn(name = "player_one_id")
    private Player firstPlayer;
    @ManyToOne
    @JoinColumn(name = "player_two_id")
    private Player secondPlayer;
    private LocalDateTime created;
    @Column(name = "fleets")
    private String fleets;

    @Transient
    @JsonInclude
    @Convert(converter = HashMapConverter.class)
    private Map<Long,Fleet> fleetsMap;

    public boolean addFleet(Player player) {
        if (fleetsMap == null) {
            fleetsMap = new HashMap<>();
        } else {
            if(fleetsMap.containsKey(player.getId())){
                return false;
            }
        }
        fleetsMap.put(player.getId(), new Fleet(player.getUsername()));
        System.out.println("New fleet created, print all fleets: " + fleets);
        return true;
    }


    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", gameType=" + gameType +
                ", gameStatus=" + gameStatus +
                ", turn=" + turn +
                ", firstPlayer=" + firstPlayer +
                ", secondPlayer=" + secondPlayer +
                ", created=" + created +
                ", fleets=" + fleetsMap +
                '}';
    }
}
