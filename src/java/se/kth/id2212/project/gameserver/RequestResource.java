/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.kth.id2212.project.gameserver;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import se.kth.id2212.project.gameserver.network.Request;
import se.kth.id2212.project.gameserver.network.Response;
import se.kth.id2212.project.gameserver.utilities.Parser;
import se.kth.id2212.project.gameserver.utilities.Serializer;

/**
 * REST Web Service
 *
 * @author Adam
 */
@Path("request")
@RequestScoped
public class RequestResource {
    
    @EJB
    private GameBean gameBean;
    /**
     * Creates a new instance of NewGameResource
     */
    public RequestResource() {
    
    }
    /**
     * PUT method for updating or creating an instance of NewGameResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/plain")
    public String putRequest(String content) {
        System.out.println("Requst in RequestResource");
        Request req = Parser.getRequest(content);
        Response resp = gameBean.handleRequest(req);
        return Serializer.getResponse(resp);
    }
}
