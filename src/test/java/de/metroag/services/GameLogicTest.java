package de.metroag.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import de.metroag.config.AppProperties;
import de.metroag.domains.Position;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(
		  classes = { AppProperties.class }, 
		  loader = AnnotationConfigContextLoader.class)


public class GameLogicTest {


	@Autowired
	private AppProperties env;
	
	@Before
	public void setUp() throws Exception {
		GameLogic.setEnv(env);
	}


@Test
public void testWinningPositions(){
	

	List<List<Position>> positions=GameLogic.getWinningPositions();
	assertEquals(12, positions.size());
}
@Test
public void testgetAllPositions(){
	
	List<Position> positions=GameLogic.getAllPositions();
	assertEquals(25, positions.size());
}
	
	@Test
	public void testIsWinner() throws Exception {
		
		List<Position> positions=new ArrayList<>();
		positions.add(new Position(1,1));
		positions.add(new Position(1, 2));
		positions.add( new Position(1,3));
		positions.add( new Position(1,4));
		positions.add( new Position(1,5));
		boolean winner=GameLogic.isWinner(positions);
	
		assertEquals(winner, true);
		
	}

	@Test
	public void testIsBoardIsFull() throws Exception {
		List<Position> positions=new ArrayList<>();
		positions.add(new Position(1,1));
		positions.add(new Position(1, 2));
		positions.add( new Position(1,3));
		positions.add( new Position(1,4));
		positions.add( new Position(1,5));
		
		positions.add(new Position(2,1));
		positions.add(new Position(2, 2));
		positions.add( new Position(2,3));
		positions.add( new Position(2,4));
		positions.add( new Position(2,5));
		boolean isBoardFull=GameLogic.isBoardIsFull(positions);
		

		assertEquals(false, isBoardFull);
		
	}
	
	@Test
	public void testNextMove() throws Exception {
		List<Position> positions=new ArrayList<>();
		positions.add(new Position(1,1));
		positions.add(new Position(1, 2));
		positions.add( new Position(1,3));
		positions.add( new Position(1,4));
		positions.add( new Position(1,5));
		
		positions.add(new Position(2,1));
		positions.add(new Position(2, 2));
		positions.add( new Position(2,3));
		positions.add( new Position(2,4));
		positions.add( new Position(2,5));
		assertEquals(true, GameLogic.nextAutoMove(positions)!=null);;
	}
	
	

	@Test
	public void testOpenPositions() {
		
		assertEquals(25, GameLogic.getOpenPositions(new ArrayList<>()).size());
	}
	
	
}
