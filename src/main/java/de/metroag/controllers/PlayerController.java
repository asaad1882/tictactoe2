package de.metroag.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.metroag.domains.Player;
import de.metroag.dto.PlayerDTO;
import de.metroag.services.PlayerService;
import de.metroag.services.Response;




@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerService playerService;


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Player createAccount(@RequestBody PlayerDTO newPlayerDTO) {
        Player newPlayer = playerService.createNewPlayer(newPlayerDTO);
        return newPlayer;
    }

    @RequestMapping(value = "/players", method = RequestMethod.GET)
    public void getPlayers() {
        playerService.listPlayers();}

    @RequestMapping(value = "/logged", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<Player> getLoggedPlayer() {
        return new Response<>(playerService.getLoggedUser(), Response.Status.CREATED );
    }


}


