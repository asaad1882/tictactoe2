package de.metroag.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import de.metroag.config.AppProperties;
import de.metroag.domains.Game;
import de.metroag.dto.BoardDTO;
import de.metroag.dto.GameDTO;
import de.metroag.dto.PieceDTO;
import de.metroag.services.GameService;
import de.metroag.services.PlayerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    PlayerService playerService;

    @Autowired
    HttpSession httpSession;
    @Autowired
    AppProperties appProperties;

    Logger logger = LoggerFactory.getLogger(GameController.class);
    @RequestMapping(value = "/availablePieces", method = RequestMethod.GET)
    public List<PieceDTO> listAvailablePieces() {
    	List<PieceDTO> arrayList=new ArrayList<>();
    	arrayList.add(new PieceDTO(appProperties.getMarker1()));
    	arrayList.add(new PieceDTO(appProperties.getMarker2()));
    	return arrayList;
    	
    }
    @RequestMapping(value = "/drawBoard", method = RequestMethod.GET)
    public List<List<BoardDTO>> drawBoard() {
    	List<List<BoardDTO>> arrayList=new ArrayList<>();
    	int boardSize=Integer.parseInt(appProperties.getSize());
    	for(int i=1;i<=boardSize;i++){
    		List<BoardDTO> boardDTOs=new ArrayList<>();
    		for(int j=1;j<=boardSize;j++){
    			boardDTOs.add(new BoardDTO(i+""+j,"padding-bottom:"+100/boardSize+"%;width:"+100/boardSize+"%"));
        	}
    		arrayList.add(boardDTOs);
    	}
    	
    
    	return arrayList;
    	
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Game createNewGame(@RequestBody GameDTO gameDTO) {
    	gameDTO.setSize(Integer.parseInt(appProperties.getSize()));
    	Game game=null;
    	if(gameDTO.getPiece().equalsIgnoreCase(appProperties.getMarker1())|| gameDTO.getPiece().equalsIgnoreCase(appProperties.getMarker2())){

         game = gameService.createGame(playerService.getLoggedUser(), gameDTO);
        httpSession.setAttribute("gameId", game.getId());

        logger.info("new game id: " + httpSession.getAttribute("gameId")+ " stored in session" );
    	}
        return game;
    }

    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Game> getGamesToJoin() {
        return gameService.getGamesToJoin(playerService.getLoggedUser());
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public Game joinGame(@RequestBody GameDTO gameDTO) {
        Game game = gameService.joinGame(playerService.getLoggedUser(), gameDTO);
        return game;
    }


    @RequestMapping(value = "/player/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Game> getPlayerGames() {
        return gameService.getPlayerGames(playerService.getLoggedUser());
    }

    @RequestMapping(value = "/{id}")
    public Game getGameProperties(@PathVariable Long id) {

        httpSession.setAttribute("gameId", id);

        return gameService.getGame(id);
    }



}
