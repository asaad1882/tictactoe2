package de.metroag.services;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;




import de.metroag.config.AppProperties;

import de.metroag.domains.Position;



public class GameLogic {
	 
	private static AppProperties env;
   
    static void setEnv(AppProperties environment){
    	env=environment;
    }
    /**
     * Checks if the player wins
     * @param positions Board positions from player moves retrieved from database
     * @return true or false if the player is a winner
     */
    static boolean isWinner(List<Position> positions) {

        return getWinningPositions().stream().anyMatch(positions::containsAll);
    }

    /**
     * Stores list of winning positions.
     * @return the list of the winning position's list
     */
    static List<List<Position>> getWinningPositions() {
        List<List<Position>> winingPositions = new ArrayList<>();
        int size=Integer.parseInt(env.getSize());
        //Wining rows
        for  (int row = 1; row <= size; row++) {
        	List<Position> possiblePositions=new ArrayList<>();
        	 for (int col = 1; col <= size; col++) {
        		 possiblePositions.add(new Position(row,col));
        		
        		
        	 }
        	 winingPositions.add(possiblePositions); 
        	 
        }
        //Wining col
        for  (int col = 1; col <= size; col++) {
        	List<Position> possiblePositions=new ArrayList<>();
        	 for (int row = 1; row <= size; row++) {
        		 possiblePositions.add(new Position(row,col));
        		
        	 }
        	 winingPositions.add(possiblePositions); 
        }
        //Diagonails 
        List<Position> possibleDiagPositions=new ArrayList<>();
        for (int row = 1; row <= size; row++){
        	possibleDiagPositions.add(new Position(row, row));
        }
        winingPositions.add(possibleDiagPositions);
       
        List<Position> possibleDiagRevPositions=new ArrayList<>();
        for (int row = size; row <= 1; row++){
        	if(row==size){
        		possibleDiagRevPositions.add(new Position(row, 1));
        	}else if(row==1){
        		possibleDiagRevPositions.add(new Position(1, row));
        	}else
        		possibleDiagRevPositions.add(new Position(row, row));
        }
        winingPositions.add(possibleDiagRevPositions);    
        

        return winingPositions;
    }

    /**
     * Stores all pairs of available rows and columns
     * @return list of all board's positions
     */
    static List<Position> getAllPositions() {
        List<Position> positions = new ArrayList<>();
        int size=Integer.parseInt(env.getSize());
        for (int row = 1; row <= size; row++) {
            for (int col = 1; col <= size; col++) {
                positions.add(new Position(row, col));
            }
        }
        return positions;
    }

    /**
     *
     * @param numberOfFirstPlayerMovesInGame
     * @param numberOfSecondPlayerMovesInGame
     * @return true or false depending on the count of the player's moves
     */
    static boolean playerTurn(int numberOfFirstPlayerMovesInGame, int numberOfSecondPlayerMovesInGame) {
        return numberOfFirstPlayerMovesInGame == numberOfSecondPlayerMovesInGame || numberOfFirstPlayerMovesInGame == 0;
    }

    static boolean isBoardIsFull(List<Position> takenPositions) {
    	int size=Integer.parseInt(env.getSize());
        return takenPositions.size() == size*size;
    }

    // GENERATE COMPUTER'S MOVES
    static List<Position> getOpenPositions(List<Position> takenPositions) {
        return getAllPositions().stream().filter(p -> !takenPositions.contains(p)).collect(Collectors.toList());
   }

    static Position nextAutoMove(List<Position> takenPositions) {
        return getOpenPositions(takenPositions).get(0);
   }







}
