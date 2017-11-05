package de.metroag.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.metroag.domains.Game;
import de.metroag.domains.Move;
import de.metroag.domains.Position;
import de.metroag.dto.CreateMoveDTO;
import de.metroag.dto.MoveDTO;
import de.metroag.services.GameService;
import de.metroag.services.MoveService;
import de.metroag.services.PlayerService;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/move")
public class MoveController {

    @Autowired
    private MoveService moveService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @Autowired
    private HttpSession httpSession;

    Logger logger = LoggerFactory.getLogger(MoveController.class);

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Move createMove(@RequestBody CreateMoveDTO createMoveDTO) {
        Long gameId = (Long) httpSession.getAttribute("gameId");
        logger.info("move to insert:" + createMoveDTO.getBoardColumn() + createMoveDTO.getBoardRow());


        Move move = moveService.createMove(gameService.getGame(gameId), playerService.getLoggedUser(), createMoveDTO);
        Game game = gameService.getGame(gameId);
        gameService.updateGameStatus(gameService.getGame(gameId), moveService.checkCurrentGameStatus(game));

        return move;
    }

    @RequestMapping(value = "/autocreate", method = RequestMethod.GET)
    public Move autoCreateMove() {
        Long gameId = (Long) httpSession.getAttribute("gameId");

        logger.info("AUTO move to insert:" );

        Move move = moveService.autoCreateMove(gameService.getGame(gameId));

        Game game = gameService.getGame(gameId);
        gameService.updateGameStatus(gameService.getGame(gameId), moveService.checkCurrentGameStatus(game));

        return move;
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<MoveDTO> getMovesInGame() {

        Long gameId = (Long) httpSession.getAttribute("gameId");

      return moveService.getMovesInGame(gameService.getGame(gameId));
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public List<Position> validateMoves() {
        Long gameId = (Long) httpSession.getAttribute("gameId");
        return moveService.getPlayerMovePositionsInGame(gameService.getGame(gameId), playerService.getLoggedUser());
    }

    @RequestMapping(value = "/turn", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean isPlayerTurn() {
        Long gameId = (Long) httpSession.getAttribute("gameId");
        return moveService.isPlayerTurn(gameService.getGame(gameId), gameService.getGame(gameId).getFirstPlayer(),
                gameService.getGame(gameId).getSecondPlayer());
    }



}
