package com.smartdigit.zalewski.gamecenter.domain;

import com.smartdigit.zalewski.gamecenter.domain.enums.ShipStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Getter
@Setter
public class Ship {


    private String name;
    private int shipLength;
    private String[] positions;
    private ShipStatus shipStatus;


    public Ship(int shipLength) {
        this.shipLength = shipLength;
        this.positions = new String[shipLength];
        this.name = "ship_" + Integer.toString(shipLength);
        this.shipStatus = ShipStatus.POSITIONS_NOT_SET;
    }

    public boolean isDestroyed() {
        return this.shipStatus.isDestroyed();
    }

    public boolean checkIfShipSunk(){
        int hits = 0;
        for (String s : positions){
            if(s.equalsIgnoreCase("X")) {
                hits++;
            }
        }
        if (shipLength == hits) {
            this.shipStatus = ShipStatus.DESTROYED;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "name='" + name + '\'' +
                ", shipLength=" + shipLength +
                ", positions=" + Arrays.toString(positions) +
                ", shipStatus=" + shipStatus +
                '}';
    }
}
