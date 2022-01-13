package com.smartdigit.zalewski.gamecenter.domain;

import com.smartdigit.zalewski.gamecenter.domain.enums.FleetStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Fleet {

    private List<Ship> fleetList;
    private String playerName;
    private FleetStatus fleetStatus;
    private int numberOfShips;


    public Fleet(String playerName) {
        this.fleetStatus = FleetStatus.SHIPS_NOT_SET;
        this.playerName = playerName;
        this.fleetList = new ArrayList<>();
        for(int i = 1; i <= 5; i++) {
            fleetList.add(new Ship(i));
        }
        this.numberOfShips = fleetList.size();
    }

    public boolean checkIfHit(String fieldName) {

        for(Ship ship: fleetList) {
            String[] s = ship.getPositions();
            for(int i = 0; i < ship.getShipLength(); i++) {
                if(s[i].equalsIgnoreCase(fieldName)){
                    if(ship.checkIfShipSunk()) {
                        this.numberOfShips--;
                    }
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return "Fleet{" +
                "fleetList=" + fleetList +
                ", playerName='" + playerName + '\'' +
                ", fleetStatus=" + fleetStatus +
                ", numberOfShips=" + numberOfShips +
                '}';
    }
}
