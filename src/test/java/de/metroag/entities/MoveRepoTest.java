package de.metroag.entities;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;



import de.metroag.config.JpaConfig;
import de.metroag.domains.Game;


@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@ContextConfiguration(
		  classes = { JpaConfig.class }, 
		  loader = AnnotationConfigContextLoader.class)


public class MoveRepoTest {
	@Test	
	public void findByGame(){
		
//		Game game=new Game();
//		game.set
		 
	 }
//	    public void findByGameAndPlayer(Game game, Player player){
//	    	
//	    }
//	    int countByGameAndPlayer(Game game, Player player);

}
