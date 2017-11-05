package de.metroag.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.metroag.domains.Game;
import de.metroag.enums.GameStatus;
import de.metroag.enums.GameType;

import java.util.List;


@Repository
public interface GameRepository extends CrudRepository<Game, Long>{
    List<Game> findByGameTypeAndGameStatus(GameType GameType, GameStatus GameStatus);
    List<Game> findByGameStatus(GameStatus gameStatus);
}
