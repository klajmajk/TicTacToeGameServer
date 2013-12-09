/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id2212.project.gameserver.utilities;

import com.google.gson.Gson;
import se.kth.id2212.project.gameserver.entities.GameSession;
import se.kth.id2212.project.gameserver.entities.Move;
import se.kth.id2212.project.gameserver.entities.Player;

/**
 *
 * @author Adam
 */
public class Parser {

    public static Move getMove(String content) {
        Gson gson = new Gson();
        //convert the json string back to object
        Move move = gson.fromJson(content, Move.class);
        return move;

    }

    public static Player getPlayer(String content) {
        return new Player(content);
    }

    public static int getGameId(String content) {
        
        Gson gson = new Gson();
        //convert the json string back to object
        int id = gson.fromJson(content, Integer.class);
        return id;
    }

    public static GameSession getNewGame(String content) {        
        Gson gson = new Gson();
        //convert the json string back to object
        GameSession game = gson.fromJson(content, GameSession.class);
        return game;
    }

}
