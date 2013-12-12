/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id2212.project.gameserver.entities;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Adam
 */
 public class GameSession {
     private final int id;
     private final String name;
     private final Player creator;
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
        this.joined=null;
        
    }

    public void setJoined(Player joined) {
        this.joined = joined;
    }
    
    public void move(Player p, int x, int y){
        if(p.equals(creator)&&(whoseTurn.equals(BoardMoves.X))){
            board.set(x, y, BoardMoves.X);
            checkGameState();
            whoseTurn = BoardMoves.O;
        }
        else if (p.equals(joined)&&(whoseTurn.equals(BoardMoves.O))){
            board.set(x, y, BoardMoves.O);
            checkGameState();
            whoseTurn = BoardMoves.X;
        }
        
    }

    private void checkGameState(){
        if(board.getGameState().equals(GameStatus.X_WON)){
            creatorScore++;
            board = new Board();
        }else if(board.getGameState().equals(GameStatus.O_WON)){
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

    public Player getJoined() {
        return joined;
    }
    
    
    
    
    
     
     
    
}
