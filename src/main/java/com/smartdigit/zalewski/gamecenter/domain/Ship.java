package com.smartdigit.zalewski.gamecenter.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ship {

    private String name;
    private int shipLength;
    private String[] positions;


    public Ship(int shipLength) {
        this.shipLength = shipLength;
        this.positions = new String[shipLength];
        this.name = "ship_" + Integer.toString(shipLength);
    }

}
