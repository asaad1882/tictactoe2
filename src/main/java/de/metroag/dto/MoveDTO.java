package de.metroag.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import de.metroag.enums.GameStatus;
import de.metroag.enums.Piece;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MoveDTO {

    private int boardColumn;
    private int boardRow;
    private Date created;
    private String userName;
    private GameStatus gameStatus;
    private String playerPieceCode;
	public int getBoardColumn() {
		return boardColumn;
	}
	public void setBoardColumn(int boardColumn) {
		this.boardColumn = boardColumn;
	}
	public int getBoardRow() {
		return boardRow;
	}
	public void setBoardRow(int boardRow) {
		this.boardRow = boardRow;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public GameStatus getGameStatus() {
		return gameStatus;
	}
	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}
	public String getPlayerPieceCode() {
		return playerPieceCode;
	}
	public void setPlayerPieceCode(String playerPieceCode) {
		this.playerPieceCode = playerPieceCode;
	}
}
