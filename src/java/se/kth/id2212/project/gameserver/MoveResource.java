/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.kth.id2212.project.gameserver;

import se.kth.id2212.project.gameserver.utilities.Serializer;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import se.kth.id2212.project.gameserver.entities.Move;
import se.kth.id2212.project.gameserver.utilities.Parser;

/**
 * REST Web Service
 *
 * @author Adam
 */
@Path("move")
@Stateless
public class MoveResource {

    @EJB
    private GameBean gameBean;

    /**
     * Creates a new instance of MoveResource
     */
    public MoveResource() {
    }

    /**
     * Retrieves representation of an instance of se.kth.id2212.project.gameserver.MoveResource
     * @return an instance of java.lang.String
     */
    
   

    /**
     * PUT method for updating or creating an instance of MoveResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/plain")
    @Path("putMove")
    
    public String putMove(String content) {
        Move move = Parser.getMove(content);
        return Serializer.getBoard(gameBean.handleMove(move));
    }
}
