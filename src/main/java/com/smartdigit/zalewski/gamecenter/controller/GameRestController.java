package com.smartdigit.zalewski.gamecenter.controller;

import com.smartdigit.zalewski.gamecenter.domain.*;
import com.smartdigit.zalewski.gamecenter.domain.enums.FleetStatus;
import com.smartdigit.zalewski.gamecenter.domain.enums.ShipStatus;
import com.smartdigit.zalewski.gamecenter.service.GameService;
import com.smartdigit.zalewski.gamecenter.service.PlayerService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;


/**
 * Created by SmartDigit Michal Zalewski
 * Date: 15.01.2022
 */
@RestController
@RequestMapping("/api")
public class GameRestController {

    GameService gameService;
    PlayerService playerService;
    ApplicationContext applicationContext;

    public GameRestController(GameService gameService, PlayerService playerService, ApplicationContext applicationContext) {
        this.gameService = gameService;
        this.playerService = playerService;
        this.applicationContext = applicationContext;
    }

    @RequestMapping(value ="/games/{id}/fleets", method = RequestMethod.POST)
    public RestResponse persistFleet(@PathVariable long id, @RequestBody FleetDTO dto){

        Game game = applicationContext.getBean(Game.class);
        Game tmpGame = gameService.getGameById(id);
        if(tmpGame.getId() != id) return new RestResponse("Failure");
        game.copyFields(tmpGame);
        Player player = playerService.getById(dto.getPlayerId());
        int i = 0;

        Fleet fleet = new Fleet(player.getUsername());
        for(Ship ship : fleet.getFleetList()) {
            if (dto.getFleet().containsKey(ship.getName())) {
                ship.setPositions(dto.getFleet().get(ship.getName()));
                ship.setShipStatus(ShipStatus.POSITIONS_SET);
                i++;
            }
        }
        if (i == 5) {
            fleet.setFleetStatus(FleetStatus.FLEET_READY);
        } else {
            return new RestResponse("Failure, not all ships were set");
        }
        game.addFleet(player, fleet);
        gameService.updateGame(game);

        return new RestResponse("Success");


    }




}
