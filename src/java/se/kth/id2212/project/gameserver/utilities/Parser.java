/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id2212.project.gameserver.utilities;

import com.google.gson.Gson;
import se.kth.id2212.project.gameserver.entities.GameSession;
import se.kth.id2212.project.gameserver.entities.Player;
import se.kth.id2212.project.gameserver.network.Request;

/**
 *
 * @author Adam
 */
public class Parser {


    public static Player getPlayer(String content) {
        return new Player(content);
    }

   

    public static GameSession getNewGame(String content) {        
        Gson gson = new Gson();
        //convert the json string back to object
        GameSession game = gson.fromJson(content, GameSession.class);
        return game;
    }

    public static GameSession getGame(String content) {
        Gson gson = new Gson();
        //convert the json string back to object
        GameSession game = gson.fromJson(content, GameSession.class);
        return game;
    }

    public static Request getRequest(String content) {
        Gson gson = new Gson();
        //convert the json string back to object
        Request req = gson.fromJson(content, Request.class);
        return req;
    }

}
