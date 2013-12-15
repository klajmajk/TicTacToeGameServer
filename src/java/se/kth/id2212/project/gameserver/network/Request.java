/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.kth.id2212.project.gameserver.network;

import se.kth.id2212.project.gameserver.entities.Point;


/**
 *
 * @author Adam
 */
public class Request {
    private RequestStatus status;
    private String playerId;
    private int gameId;
    private Point move;
    private String name;

    /**
     * this constructor is for status JOIN_GAME and REFRESH
     * 
     * @param status
     * @param playerId
     * @param gameId 
     */
    public Request(RequestStatus status, String playerId, int gameId) {
        this.status = status;
        this.playerId = playerId;
        this.gameId = gameId;
       
    }

    /**
     * this constructor is for status  NEW_GAME
     * @param status
     * @param playerId
     * @param name 
     */
    public Request(RequestStatus status, String playerId, String name) {
        this.status = status;
        this.playerId = playerId;
        this.name = name;
    }
    
    
    /**
     * this constructor is for status  MOVE
     * 
     * @param status
     * @param playerId
     * @param gameId
     * @param move 
     */
    public Request(RequestStatus status, String playerId, int gameId, Point move) {
        this.status = status;
        this.playerId = playerId;
        this.gameId = gameId;
        this.move = move;
    }

    /**
     * this constructor is for status  LIST
     * @param status 
     */
    public Request(RequestStatus status) {
        this.status = status;
    }
    
    

    public RequestStatus getStatus() {
        return status;
    }

    public String getPlayerId() {
        return playerId;
    }

    public int getGameId() {
        return gameId;
    }

    public Point getMove() {
        return move;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Request{" + "status=" + status + ", playerId=" + playerId + ", gameId=" + gameId + ", move=" + move + ", name=" + name + '}';
    }
    
    
    
    
}