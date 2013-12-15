/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.kth.id2212.project.gameserver.utilities;

import com.google.gson.Gson;
import java.util.List;
import se.kth.id2212.project.gameserver.entities.Board;
import se.kth.id2212.project.gameserver.entities.GameSession;
import se.kth.id2212.project.gameserver.network.Response;

/**
 *
 * @author Adam
 */
public class Serializer {

    public static String getBoard(Board board) {
               
        return serialize(board);
    }

    public static String getGamesList(List<GameSession> gamesList) {        
        return serialize(gamesList);
    }
    
    private static String serialize(Object o){
        Gson gson = new Gson();
	String json = gson.toJson(o);
        return json;
    }

    public static String getGameSession(GameSession game) {
        return serialize(game);
    }

    public static String getResponse(Response resp) {
        
        return serialize(resp);
    }

    
}
