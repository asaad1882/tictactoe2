package de.metroag.domains;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

import de.metroag.enums.GameStatus;
import de.metroag.enums.GameType;
import de.metroag.enums.Piece;

import javax.persistence.*;

import java.util.Date;




@Entity
@Getter
@Setter
@Check(constraints = 
        " game_type = 'COMPUTER' or game_type = 'COMPETITION' " +
        "and game_status = 'IN_PROGRESS' or game_status = 'FIRST_PLAYER_WON' or game_status = 'SECOND_PLAYER_WON'" +
        "or game_status = 'TIE' or game_status = 'WAITS_FOR_PLAYER' ")
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "second_player_id", nullable = true)
    private Player secondPlayer;

    @ManyToOne
    @JoinColumn(name = "first_player_id", nullable = false)
    private Player firstPlayer;

   
    private String firstPlayerPieceCode;
    
    @Column(name = "marker1", nullable = false)
    private String marker1;
    @Column(name = "marker2", nullable = false)
    private String marker2;

    @Enumerated(EnumType.STRING)
    private GameType gameType;

    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    @Column(name = "created", nullable = false)
    private Date created;
    @Column(name = "size", nullable = false)
    private int size;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Player getSecondPlayer() {
		return secondPlayer;
	}

	public void setSecondPlayer(Player secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

	public Player getFirstPlayer() {
		return firstPlayer;
	}

	public void setFirstPlayer(Player firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	public String getFirstPlayerPieceCode() {
		return firstPlayerPieceCode;
	}

	public void setFirstPlayerPieceCode(String firstPlayerPieceCode) {
		this.firstPlayerPieceCode = firstPlayerPieceCode;
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getMarker1() {
		return marker1;
	}

	public void setMarker1(String marker1) {
		this.marker1 = marker1;
	}

	public String getMarker2() {
		return marker2;
	}

	public void setMarker2(String marker2) {
		this.marker2 = marker2;
	}
}
