/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.kth.id2212.project.gameserver.network;

import java.util.List;
import static javafx.scene.input.KeyCode.T;
import se.kth.id2212.project.gameserver.entities.GameSession;
import se.kth.id2212.project.gameserver.entities.Score;

/**
 *
 * @author Adam
 */
public class Response {
    private ResponseStatus status;
    private List<GameSession> list;
    private List<Score> scores;
    private GameSession game;

    public Response(ResponseStatus status) {
        this.status = status;
    }

    public Response(ResponseStatus status, List<GameSession> list, List<Score> scores) {
        this.status = status;
        this.list = list;
        this.scores = scores;
    }
    
    

    public Response(ResponseStatus status, GameSession game) {
        this.status = status;
        this.game = game;
    }
    
    public ResponseStatus getStatus() {
        return status;
    }

    public List getList() {
        return list;
    }

    public List<Score> getScores() {
        return scores;
    }
    
    
    
    

    
    
}
