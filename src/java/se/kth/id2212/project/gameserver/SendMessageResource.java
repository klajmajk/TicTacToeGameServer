/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.kth.id2212.project.gameserver;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author Adam
 */

@Stateless
@Path("sendMessage")
public class SendMessageResource {    
    
    @EJB
    private GameBean gameBean;

    /**
     * Creates a new instance of ListGamesResource
     */
    public SendMessageResource() {
    }

    /**
     * Retrieves representation of an instance of se.kth.id2212.project.gameserver.ListGamesResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getSendMessage() {
        //TODO return proper representation object
        gameBean.sendGMC();
        return "Sent";
    }

    
}
