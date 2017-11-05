package de.metroag.domains;

import javax.persistence.Table;

import lombok.*;


//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table
public class Position {
    int boardRow;
    int boardColumn;
	public int getBoardRow() {
		return boardRow;
	}
	public void setBoardRow(int boardRow) {
		this.boardRow = boardRow;
	}
	public int getBoardColumn() {
		return boardColumn;
	}
	public void setBoardColumn(int boardColumn) {
		this.boardColumn = boardColumn;
	}
	public Position(int boardRow, int boardColumn) {
		super();
		this.boardRow = boardRow;
		this.boardColumn = boardColumn;
	}
}
