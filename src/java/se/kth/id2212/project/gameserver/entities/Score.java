/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.kth.id2212.project.gameserver.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Adam
 */
@Entity
public class Score implements Serializable {
    @Id
    @OneToOne(cascade = CascadeType.PERSIST)
    private Player player;
    private long score;

    public Score() {
    }
    
    

    public Score(Player player, long score) {
        this.player = player;
        this.score = score;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }    
    
}
