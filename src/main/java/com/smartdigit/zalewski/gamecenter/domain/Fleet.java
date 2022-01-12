package com.smartdigit.zalewski.gamecenter.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Fleet {

    private List<Ship> fleetList;
    private String playerName;


    public Fleet(String playerName) {
        this.playerName = playerName;
        fleetList = new ArrayList<>();
        for(int i = 1; i <= 5; i++) {
            fleetList.add(new Ship(i));
        }
    }

    public boolean checkIfHit(String fieldName) {

        for(Ship ship: fleetList) {
            String[] s = ship.getPositions();
            for(int i = 0; i < ship.getShipLength(); i++) {
                if(s[i].equalsIgnoreCase(fieldName)){
                    return true;
                }
            }
        }
        return false;
    }

}
