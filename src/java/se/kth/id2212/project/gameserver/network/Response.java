/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.kth.id2212.project.gameserver.network;

import java.util.List;
import se.kth.id2212.project.gameserver.entities.GameSession;

/**
 *
 * @author Adam
 */
public class Response {
    private ResponseStatus status;
    private List<GameSession> list;
    private GameSession game;

    public Response(ResponseStatus status) {
        this.status = status;
    }

    public Response(ResponseStatus status, List<GameSession> list) {
        this.status = status;
        this.list = list;
    }

    public Response(ResponseStatus status, GameSession game) {
        this.status = status;
        this.game = game;
    }
    
    public ResponseStatus getStatus() {
        return status;
    }

    
    
}
