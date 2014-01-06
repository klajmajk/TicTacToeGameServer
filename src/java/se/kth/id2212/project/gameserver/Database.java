/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.kth.id2212.project.gameserver;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import se.kth.id2212.project.gameserver.entities.Player;
import se.kth.id2212.project.gameserver.entities.Score;

/**
 * Database interaction class using JPA
 * @author Adam
 */
@Stateless 
public class Database {
    @PersistenceContext
    EntityManager em;

    /**
     * Initializes the player in database
     * @param player 
     */
    public void initPlayer(Player player){
        
        Score score = em.find(Score.class, player.getGCMId());
        if(score == null){
            em.persist(new Score(player, 0));
        }
    }
    /**
     * Increments the score of a player
     * 
     * @param player player whose score should be incremented
     * @param increment the increment of the score
     */
    public void incrementScore(Player player, long increment){
        Score score = em.find(Score.class, player.getGCMId());
        score.setScore(score.getScore() + increment);
        em.merge(score);
    }
    
    
    /**
     * 
     * @return all scores of all players
     */
    public List<Score> getScores(){
        Query query = em.createQuery("SELECT s FROM Score s ORDER BY s.score DESC");
        return query.getResultList();
        
    }
}
