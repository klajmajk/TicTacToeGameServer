/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id2212.project.gameserver.entities;

/**
 *
 * @author Adam
 */
public class GameSession {

    private int id;
    private final String name;
    private final Player creator;
    private Player playerWhoseTurn;
    private Player joined;
    private Board board;
    private int creatorScore;
    private int joinedScore;
    private BoardMoves whoseTurn;

    public GameSession(int id, String name, Player creator) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.board = new Board();
        this.creatorScore = 0;
        this.joinedScore = 0;
        this.whoseTurn = BoardMoves.X;
        this.joined = null;
        this.playerWhoseTurn = creator;
    }

    public void setJoined(Player joined) {
        this.joined = joined;
    }

    public Player getJoined() {
        return joined;
    }

    public void move(Player p, int x, int y) {
        System.out.println("Handeling move "+ playerWhoseTurn +" "+ p );
        if (playerWhoseTurn.equals(p)) {
            if (creator.equals(p)) {
                board.set(x, y, BoardMoves.X);
                checkGameState();
                whoseTurn = BoardMoves.O;
                playerWhoseTurn = joined;
                System.out.println("Handeling move X");
            }else{
                board.set(x, y, BoardMoves.O);
                checkGameState();
                whoseTurn = BoardMoves.X;
                playerWhoseTurn = creator;
                
                System.out.println("Handeling move O");
            }
        }

    }

    private void checkGameState() {
        if (board.getGameState().equals(GameStatus.X_WON)) {
            creatorScore++;
            board = new Board();
        } else if (board.getGameState().equals(GameStatus.O_WON)) {
            joinedScore++;
            board = new Board();
        }
    }

    public Board getBoard() {
        return board;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Player getCreator() {
        return creator;
    }

    @Override
    public String toString() {
        return "GameSession [id=" + id + ", name=" + name + ", creator="
                + creator + ", joined=" + joined + ", board=" + board
                + ", creatorScore=" + creatorScore + ", joinedScore="
                + joinedScore + ", whoseTurn=" + whoseTurn + "]";
    }

    public void setId(int id) {
        this.id = id;
    }

    public BoardMoves getWhoseTurn() {
        return whoseTurn;
    }

    public void setWhoseTurn(BoardMoves whoseTurn) {
        this.whoseTurn = whoseTurn;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getPlayerWhoseTurn() {
        return playerWhoseTurn;
    }

    public void setPlayerWhoseTurn(Player playerWhoseTurn) {
        this.playerWhoseTurn = playerWhoseTurn;
    }

}
