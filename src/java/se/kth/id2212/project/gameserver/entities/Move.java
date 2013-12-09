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
public class Move {
    private Player player;
    private int x, y;
    private GameSession game;

    public Move(Player player, int x, int y) {
        this.player = player;
        this.x = x;
        this.y = y;
    }

    public void doMove(){
        game.move(player, x, y);
    }

    public GameSession getGame() {
        return game;
    }
    
    
    
    
}
