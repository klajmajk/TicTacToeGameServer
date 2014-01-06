/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 * 
 * Contributor(s):
 * 
 * Portions Copyrighted 2008 Sun Microsystems, Inc.
 */
package se.kth.id2212.project.gameserver;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import se.kth.id2212.project.gameserver.entities.GameSession;
import se.kth.id2212.project.gameserver.entities.Player;
import se.kth.id2212.project.gameserver.entities.Score;
import se.kth.id2212.project.gameserver.network.Request;
import se.kth.id2212.project.gameserver.network.RequestHandler;
import se.kth.id2212.project.gameserver.network.Response;
import se.kth.id2212.project.gameserver.utilities.GCMHandler;

/**
 * Stateless bean handeling each game request
 *
 * @author Adam
 */
@Stateless
public class GameBean {

    @EJB
    private Database database;
    @EJB
    private AllGamesBean allGamesBean;
    private RequestHandler reqHandler;

    /**
     * 
     * @return all game sessions
     */
    public List<GameSession> getGamesList() {
        System.out.println("GameBean Get game list");

        return getAvailableGames(allGamesBean.getGamesList());
    }

    
    /**
     * Returns the available game sessions. (The ones where there is no joined player)
     * @param gameSessions all game sessions
     * @return available game session
     */
    private List<GameSession> getAvailableGames(List<GameSession> gameSessions) {
        List<GameSession> availableGames = new ArrayList<GameSession>();
        if (gameSessions != null) {
            for (GameSession gameSession : gameSessions) {
                if (gameSession.getJoined() == null) {
                    availableGames.add(gameSession);
                }
            }
        }
        return availableGames;
    }

    /**
     * Starts a new game session
     * 
     * @param name of session
     * @param player starting session
     * @return the instance of new gameSession
     */
    public GameSession startNewGame(String name, Player player) {        
        database.initPlayer(player);
        GameSession game = allGamesBean.startNewGame(name, player);
        System.out.println("Start new game" + game);
        return game;
    }

    /**
     * Joining already running game session
     * 
     * @param gameId of the joined session
     * @param player joining the session
     */
    public void joinGame(int gameId, Player player) {
        GameSession game = allGamesBean.getGameSessionById(gameId);
        database.initPlayer(player);
        game.setJoined(player);
        System.out.println("Join game" + game);
        //TODO if game destroyed in meantime null pointer exception here
        GCMHandler.sendGMC(game.getCreator());
    }

    /**
     * Handles move of a game
     * 
     * @param gameId identifying a game
     * @param x coordinate
     * @param y coordinate
     * @param playerId who plays 
     */
    public void handleMove(int gameId, int x, int y, String playerId) {
        GameSession game = allGamesBean.getGameSessionById(gameId);
        game.move(allGamesBean.getPlayerById(playerId), x, y);
        GCMHandler.sendGMC(game.getPlayerWhoseTurn());
        System.out.println("Sending refresh to " + game.getPlayerWhoseTurn());
    }

   /**
    * Called when client refreshes a game
    * @param gameId the game id to be refreshed
    * @return game session state
    */
    public GameSession refreshGame(int gameId) {

        GameSession game = allGamesBean.getGameSessionById(gameId);
        return game;
    }

    /**
     * Handles clients requests
     * @param req request to handle
     * @return the response
     */
    public Response handleRequest(Request req) {
        
        System.out.println("Requst in GameBean");
        if (reqHandler == null) {
            reqHandler = new RequestHandler(this);
        }
        return reqHandler.handleRequest(req);
    }

    /**
     * Dropping the game prom the server
     * 
     * @param gameId game to drop
     */
    public void dropGame(int gameId) {
        allGamesBean.dropGame(gameId);
    }

    /**
     * Creats a new board in a game
     * 
     * @param gameId 
     * @param playerId
     * @return game session with the new board
     */
    public GameSession newBoard(int gameId, String playerId) {
        GameSession game = allGamesBean.newBoard(gameId, playerId);
        GCMHandler.sendGMC(game.getJoined());
        GCMHandler.sendGMC(game.getCreator());

        return game;
    }

    /**
     * 
     * @return the highscores
     */
    public List<Score> getHighScores() {
        return database.getScores();
    }

    /**
     * 
     * @param id of the game session
     * @return the gameSession
     */
    public GameSession getGameSessionById(int id) {
        return allGamesBean.getGameSessionById(id);
    }

}
