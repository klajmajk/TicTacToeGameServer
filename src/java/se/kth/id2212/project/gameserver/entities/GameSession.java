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
     private final String name;
     private final Player creator;
     private Player joined;
     private Board board;
     private int creatorScore;
     private int joinedScore;

    public GameSession(String name, Player creator) {
        this.name = name;
        this.creator = creator;
        this.board = new Board();
    }

    public void setJoined(Player joined) {
        this.joined = joined;
    }
    
    public void move(Player p, int x, int y){
        if(p.equals(creator)){
            board.set(x, y, BoardMoves.X);
        }
        else board.set(x, y, BoardMoves.O);
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
    
    
     
     
    
}
