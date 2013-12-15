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
import javax.ejb.Singleton;
import se.kth.id2212.project.gameserver.entities.Board;
import se.kth.id2212.project.gameserver.entities.GameSession;
import se.kth.id2212.project.gameserver.entities.Player;
import se.kth.id2212.project.gameserver.network.Request;
import se.kth.id2212.project.gameserver.network.RequestHandler;
import se.kth.id2212.project.gameserver.network.Response;
import se.kth.id2212.project.gameserver.utilities.GCMHandler;

/**
 * Singleton session bean used to store the name parameter for "/helloWorld"
 * resource
 *
 * @author mkuchtiak
 */
@Singleton
public class GameBean {

    private List<GameSession> gameSessions;
    private RequestHandler reqHandler;


    public List<GameSession> getGamesList() {
        System.out.println("Get game list");
        return gameSessions;
    }

    public GameSession startNewGame(String name, Player player) {
        if (gameSessions == null) {
            gameSessions = new ArrayList<GameSession>();
        }
        GameSession game = new  GameSession(gameSessions.size(), name, player);
        System.out.println("Start new game" + game);
        gameSessions.add(game);
        return game;
    }
    

    public void joinGame(int gameId, Player player) {
        GameSession game = getGameSessionById(gameId);
        System.out.println("Join game" + game);
        game.setJoined(player);
        GCMHandler.sendGMC(game.getCreator());
    }
    public void handleMove(int gameId, int x, int y, String playerId){
        getGameSessionById(gameId).move(new Player(playerId), x, y);
    }

    

    
    public GameSession getGameSessionById(int id) {
        for (GameSession gameSession : gameSessions) {
            if (gameSession.getId() == id) {
                return gameSession;
            }
        }
        return null;
    }

    

    public GameSession refreshGame(int gameId) {      

        GameSession game = getGameSessionById(gameId);
        return game;
    }

    public Response handleRequest(Request req) {
        if (reqHandler == null) {
            reqHandler = new RequestHandler(this);
        }
        return reqHandler.handleRequest(req);
    }

    public void dropGame(int gameId) {
        GameSession game = getGameSessionById(gameId);
        gameSessions.remove(game);
    }

    

}
