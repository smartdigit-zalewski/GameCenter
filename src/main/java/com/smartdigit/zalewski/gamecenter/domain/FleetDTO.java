package com.smartdigit.zalewski.gamecenter.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SmartDigit Michal Zalewski
 * Date: 16.01.2022
 */
public class FleetDTO {

    private long playerId;
    private Map<String, String[]> fleet;

    public FleetDTO(long playerId, Map<String, String[]> fleet) {
        this.playerId = playerId;
        this.fleet = fleet;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public Map<String, String[]> getFleet() {
        return fleet;
    }

    public void setFleet(Map<String, String[]> fleet) {
        this.fleet = fleet;
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (String key : fleet.keySet()) {
            sb.append(key + "=" + Arrays.toString(fleet.get(key)) + ", ");
        }

        return "FleetDTO{" +
                "playerId= " + playerId +
                ", fleet= " + sb.toString();
    }

}
