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
import se.kth.id2212.project.gameserver.entities.Move;
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

    // name field
    private String name = "World";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GameSession> getGamesList() {
        System.out.println("Get game list");
        return gameSessions;
    }

    public GameSession startNewGame(GameSession game) {
        if (gameSessions == null) {
            gameSessions = new ArrayList<GameSession>();
        }
        game.setId(gameSessions.size());
        System.out.println("Start new game" + game);
        gameSessions.add(game);
        return game;
    }

    public void joinGame(GameSession game) {
        System.out.println("Join game" + game);
        findSessionById(game.getId()).setJoined(game.getJoined());
        GCMHandler.sendGMC(game.getCreator());
        GCMHandler.sendGMC(game.getJoined());
    }

    public Board handleMove(Move move) {
        move.doMove();        
        return getBoard(move.getGame().getId());
    }

    public Board getBoard(int gameSessionId) {
        GameSession gameSession = findSessionById(gameSessionId);
        return gameSession.getBoard();
    }

    private GameSession findSessionById(int id) {
        for (GameSession gameSession : gameSessions) {
            if (gameSession.getId() == id) {
                return gameSession;
            }
        }
        return null;
    }

    

    public GameSession refreshGame(GameSession gameReceived) {
        

        GameSession game = findSessionById(gameReceived.getId());
        game.setBoard(gameReceived.getBoard());
        return game;
    }

}
