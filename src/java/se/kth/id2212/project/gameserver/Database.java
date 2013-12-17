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
 *
 * @author Adam
 */
@Stateless 
public class Database {
    @PersistenceContext
    EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void initPlayer(Player player){
        
        Score score = em.find(Score.class, player.getGCMId());
        if(score == null){
            em.persist(new Score(player, 0));
        }
    }
    
    public void incrementScore(Player player, long increment){
        Score score = em.find(Score.class, player.getGCMId());
        score.setScore(score.getScore() + increment);
        em.merge(score);
    }
    
    public List<Score> getScores(){
        Query query = em.createQuery("SELECT s FROM Score s ORDER BY s.score DESC");
        return query.getResultList();
        
    }
}
