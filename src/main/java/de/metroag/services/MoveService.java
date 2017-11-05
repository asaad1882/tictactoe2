package de.metroag.services;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import de.metroag.config.AppProperties;
import de.metroag.domains.Game;
import de.metroag.domains.Move;
import de.metroag.domains.Player;
import de.metroag.domains.Position;
import de.metroag.dto.CreateMoveDTO;
import de.metroag.dto.MoveDTO;
import de.metroag.enums.GameStatus;
import de.metroag.enums.GameType;

import de.metroag.repository.MoveRepository;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class MoveService {
	@Autowired
	private AppProperties env;

    private final MoveRepository moveRepository;


    @Autowired
    public MoveService(MoveRepository moveRepository) {
        this.moveRepository = moveRepository;
    }

    public Move createMove(Game game, Player player, CreateMoveDTO createMoveDTO) {
        Move move = new Move();
        move.setBoardColumn(createMoveDTO.getBoardColumn());
        move.setBoardRow(createMoveDTO.getBoardRow());
        move.setCreated(new Date());
        move.setPlayer(player);
        move.setGame(game);

        moveRepository.save(move);

        return move;
    }

    public Move autoCreateMove(Game game) {
        Move move = new Move();
        Position position=GameLogic.nextAutoMove(getTakenMovePositionsInGame(game));
        move.setBoardColumn(position.getBoardColumn());
        move.setBoardRow(position.getBoardRow());
        move.setCreated(new Date());
        move.setPlayer(null);
        move.setGame(game);

        moveRepository.save(move);

        return move;
    }

    public GameStatus checkCurrentGameStatus(Game game) {
    	GameLogic.setEnv(env);
        if (GameLogic.isWinner(getPlayerMovePositionsInGame(game, game.getFirstPlayer()))) {
            return GameStatus.FIRST_PLAYER_WON;
        } else if (GameLogic.isWinner(getPlayerMovePositionsInGame(game, game.getSecondPlayer()))) {
            return GameStatus.SECOND_PLAYER_WON;
        } else if (GameLogic.isBoardIsFull(getTakenMovePositionsInGame(game))) {
            return GameStatus.TIE;
        } else if (game.getGameType() == GameType.COMPETITION && game.getSecondPlayer() == null ) {
            return GameStatus.WAITS_FOR_PLAYER;
        } else {
            return GameStatus.IN_PROGRESS;
        }

    }


    public List<MoveDTO> getMovesInGame(Game game) {

        List<Move> movesInGame = moveRepository.findByGame(game);
        List<MoveDTO> moves = new ArrayList<>();
        String currentPiece = game.getFirstPlayerPieceCode();

        for(Move move :  movesInGame) {
            MoveDTO moveDTO = new MoveDTO();
            moveDTO.setBoardColumn(move.getBoardColumn());
            moveDTO.setBoardRow(move.getBoardRow());
            moveDTO.setCreated(move.getCreated());
            moveDTO.setGameStatus(move.getGame().getGameStatus());
            moveDTO.setUserName(move.getPlayer() == null ? GameType.COMPUTER.toString() : move.getPlayer().getUserName() );
            moveDTO.setPlayerPieceCode(currentPiece);
            moves.add(moveDTO);

            currentPiece = currentPiece == game.getMarker1() ? game.getMarker2() : game.getMarker1();
        }

        return moves;
    }

    public List<Position> getTakenMovePositionsInGame(Game game) {
        return moveRepository.findByGame(game).stream()
                .map(move -> new Position(move.getBoardRow(), move.getBoardColumn()))
                .collect(Collectors.toList());
    }

    public List<Position> getPlayerMovePositionsInGame(Game game, Player player) {

        return moveRepository.findByGameAndPlayer(game, player).stream()
                .map(move -> new Position(move.getBoardRow(), move.getBoardColumn()))
                .collect(Collectors.toList());
    }

    public int getTheNumberOfPlayerMovesInGame(Game game, Player player) {
        return moveRepository.countByGameAndPlayer(game, player);
    }

    public boolean isPlayerTurn(Game game, Player firstPlayer, Player secondPlayer) {
    	GameLogic.setEnv(env);
        return GameLogic.playerTurn(getTheNumberOfPlayerMovesInGame(game, firstPlayer),
                getTheNumberOfPlayerMovesInGame(game, secondPlayer));
    }




}
