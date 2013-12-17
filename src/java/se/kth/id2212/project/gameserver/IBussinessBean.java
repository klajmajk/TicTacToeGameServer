/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id2212.project.gameserver;

import java.util.List;
import se.kth.id2212.project.gameserver.entities.GameSession;
import se.kth.id2212.project.gameserver.entities.Player;

/**
 *
 * @author Adam
 */
public interface IBussinessBean {

    void dropGame(int gameId);

    List<GameSession> getGamesList();

    GameSession newBoard(int gameId, String playerId);

    GameSession startNewGame(String name, Player player);
    
    public GameSession getGameSessionById(int id);

}
