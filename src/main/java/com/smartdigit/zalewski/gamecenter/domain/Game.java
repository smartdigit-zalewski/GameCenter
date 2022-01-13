package com.smartdigit.zalewski.gamecenter.domain;


import com.smartdigit.zalewski.gamecenter.domain.enums.FleetStatus;
import com.smartdigit.zalewski.gamecenter.domain.enums.GameStatus;
import com.smartdigit.zalewski.gamecenter.domain.enums.GameType;
import com.smartdigit.zalewski.gamecenter.util.HashMapConverter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@Entity
@Table(name = "game")
@Component
@NoArgsConstructor
@Scope("prototype")
public class Game implements Cloneable {

    @Autowired
    @Transient
    private HashMapConverter hmc;

    public enum Turn {
        FIRST_PLAYER_TURN,
        SECOND_PLAYER_TURN
    }


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
    private String fleets = "";

    @Transient
    private Map<Long,Fleet> fleetsMap;


    public boolean addFleet(Player player) {

        if(!(fleets.isEmpty() || fleets.equalsIgnoreCase("null"))) {
            fleetsMap = hmc.convertToEntityAttribute(fleets);
        }

        if (fleetsMap == null) {
            fleetsMap = new HashMap<>();
        } else {
            if(fleetsMap.containsKey(player.getId())){
                return false;
            }
        }

        fleetsMap.put(player.getId(), new Fleet(player.getUsername()));
        fleets = hmc.convertToDatabaseColumn(fleetsMap);

        return true;
    }

    public void setFleetShipsPositions(List<Ship> shipsPositions, Player player) {
        Fleet tempFleet = fleetsMap.get(player.getId());
        tempFleet.setFleetList(shipsPositions);
        tempFleet.setFleetStatus(FleetStatus.FLEET_READY);
        fleets = hmc.convertToDatabaseColumn(fleetsMap);

    }

    public Map<Long,Fleet> getFleetMap() {

        this.fleetsMap = hmc.convertToEntityAttribute(fleets);
        return this.fleetsMap;
    }

    public void copyFields(Game game) {
        this.id = game.getId();
        this.gameType = game.getGameType();
        this.gameStatus = game.getGameStatus();
        this.turn = game.getTurn();
        this.firstPlayer = game.getFirstPlayer();
        this.secondPlayer = game.getSecondPlayer();
        this.created = game.getCreated();
        this.fleets = game.getFleets();
        this.fleetsMap = game.getFleetsMap();
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
                ", fleets=" + fleets +
                '}';
    }


}
