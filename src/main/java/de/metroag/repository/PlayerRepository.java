package de.metroag.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.metroag.domains.Player;




@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
    Player findOneByUserName(String userName);
}
