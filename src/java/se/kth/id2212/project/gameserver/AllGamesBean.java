/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.kth.id2212.project.gameserver;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import se.kth.id2212.project.gameserver.entities.GameSession;
import se.kth.id2212.project.gameserver.entities.Player;

/**
 *
 * @author Adam
 */
@Singleton
public class AllGamesBean{
    @EJB
    private Database database;
    private List<GameSession> gameSessions;

    //@Override
    public List<GameSession> getGamesList() {
        return gameSessions;
    }

    //@Override
    public GameSession startNewGame(String name, Player player) {
        if (gameSessions == null) {
            gameSessions = new ArrayList<GameSession>();
        }
        GameSession game = new GameSession(gameSessions.size(), name, player);
        gameSessions.add(game);
        return game;
    }    

    

    public Player getPlayerById(String id) {
        for (GameSession gameSession : gameSessions) {
            if (gameSession.getCreator().getGCMId().equals(id)) {
                return gameSession.getCreator();
            }
            if (gameSession.getJoined().getGCMId().equals(id)) {
                return gameSession.getJoined();
            }
        }
        return null;
    }

    public GameSession getGameSessionById(int id) {
        for (GameSession gameSession : gameSessions) {
            if (gameSession.getId() == id) {
                return gameSession;
            }
        }
        return null;
    } 

   

    //@Override
    public void dropGame(int gameId) {
        GameSession game = getGameSessionById(gameId);
        if (game != null) {
            database.incrementScore(game.getCreator(), game.getCreatorScore());
            database.incrementScore(game.getJoined(), game.getJoinedScore());
            gameSessions.remove(game);
        }
    }

    //@Override
    public GameSession newBoard(int gameId, String playerId) {
        GameSession game = getGameSessionById(gameId);
        game.newBoard();
        return game;
    }

  

    
}
