package de.metroag.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.metroag.domains.Game;
import de.metroag.domains.Move;
import de.metroag.domains.Player;

import java.util.List;


@Repository
public interface MoveRepository extends CrudRepository<Move, Long> {

    List<Move> findByGame(Game game);
    List<Move> findByGameAndPlayer(Game game, Player player);
    int countByGameAndPlayer(Game game, Player player);
}
