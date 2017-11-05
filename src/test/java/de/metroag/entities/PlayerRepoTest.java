package de.metroag.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import de.metroag.config.JpaConfig;
import static org.junit.Assert.*;
import de.metroag.domains.Player;
import de.metroag.repository.PlayerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration(
		  classes = { JpaConfig.class }, 
		  loader = AnnotationConfigContextLoader.class)

@DatabaseSetup("player-entries.xml")
public class PlayerRepoTest {
	@Resource
    private PlayerRepository repository;
	 @Test
	    public void createPlayer() {
	    	Player p=new Player("Annabel", "Annabel@Annabel.com", new BCryptPasswordEncoder().encode("Annabel"));
	         repository.save(p);
	    }
	 @Test
	    public void createAndRetrievePlayer() {
	    	Player p=new Player("David", "David@David.com",  new BCryptPasswordEncoder().encode("David"));
	    	repository.save(p);
	    	Player r=repository.findOneByUserName("David");
	    	assertEquals(p, r);
	    }
	 @Test
	 public void findOneByUserName(){
		 Player player=repository.findOneByUserName("David");
		 assertThat(player!=null);
	 }
	 @Test
	 public void listPlayers() {
	 List<Player> players = (List<Player>) repository.findAll();
	 assertThat(players.size()>1);
     }
	
	   
	   
}
