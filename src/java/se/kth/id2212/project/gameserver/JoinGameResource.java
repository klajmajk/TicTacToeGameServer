/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.kth.id2212.project.gameserver;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import se.kth.id2212.project.gameserver.entities.GameSession;
import se.kth.id2212.project.gameserver.utilities.Parser;

/**
 * REST Web Service
 *
 * @author Adam
 */
@Path("joinGame")
@Stateless
public class JoinGameResource {
    
    @EJB
    private GameBean gameBean;


    /**
     * Creates a new instance of JoinGameResource
     */
    public JoinGameResource() {
    }

    

    /**
     * PUT method for updating or creating an instance of JoinGameResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/plain")
    public void putJoinGame(String content) {
        int gameId = Parser.getGameId(content);
        gameBean.joinGame(gameId);
    }
}
