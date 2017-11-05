package de.metroag.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.metroag.config.AppProperties;
import de.metroag.domains.Game;
import de.metroag.domains.Player;
import de.metroag.dto.GameDTO;
import de.metroag.enums.GameStatus;
import de.metroag.enums.GameType;
import de.metroag.repository.GameRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@PropertySource("application.properties")
public class GameService {
	@Autowired
	private AppProperties env;

    private final GameRepository gameRepository;


    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game createGame(Player player, GameDTO gameDTO) {
        Game game = new Game();
        game.setFirstPlayer(player);
        game.setGameType(gameDTO.getGameType());
        game.setFirstPlayerPieceCode(gameDTO.getPiece());
        game.setGameStatus(gameDTO.getGameType() == GameType.COMPUTER ? GameStatus.IN_PROGRESS :
                GameStatus.WAITS_FOR_PLAYER);
        game.setSize(gameDTO.getSize());
        game.setMarker1(env.getMarker1());
        game.setMarker2(env.getMarker2());
        game.setCreated(new Date());
        gameRepository.save(game);

        return game;
    }


    public Game updateGameStatus(Game game, GameStatus gameStatus) {
        Game g = getGame(game.getId());
        g.setGameStatus(gameStatus);

        return g;
    }

    public List<Game> getGamesToJoin(Player player) {
        return gameRepository.findByGameTypeAndGameStatus(GameType.COMPETITION,
                GameStatus.WAITS_FOR_PLAYER).stream().filter(game -> game.getFirstPlayer() != player).collect(Collectors.toList());

    }

    public Game joinGame(Player player, GameDTO gameDTO) {
        Game game =  getGame((long) gameDTO.getId());
        game.setSecondPlayer(player);
        gameRepository.save(game);

        updateGameStatus(game, GameStatus.IN_PROGRESS);

        return game;

    }

    public List<Game> getPlayerGames(Player player) {
        return gameRepository.findByGameStatus(
                GameStatus.IN_PROGRESS).stream().filter(game -> game.getFirstPlayer() == player).collect(Collectors.toList());
    }


    public Game getGame(Long id) {
        return gameRepository.findOne(id);
    }
}
