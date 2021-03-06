/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id2212.project.gameserver.network;

import se.kth.id2212.project.gameserver.GameBean;
import se.kth.id2212.project.gameserver.entities.GameSession;

/**
 * This class handles all requests and returns appropriate responses
 * 
 * @author Adam
 */
public class RequestHandler {

    private GameBean gameBean;

    public RequestHandler(GameBean gameBean) {
        this.gameBean = gameBean;
    }

    /**
     * Main handeling method
     * @param req request
     * @return Response
     */
    public Response handleRequest(Request req) {
        Response resp = null;
        System.out.println("Received request: " + req);
        switch (req.getStatus()) {
            case NEW_GAME:
                resp = handleNewGame(req);
                break;
            case JOIN_GAME:
                resp = handleJoinGame(req);
                break;
            case MOVE:
                resp = handleMove(req);
                break;
            case LIST_GAMES:
                resp = handleListGames(req);
                break;
            case REFRESH:
                resp = handleRefresh(req);
                break;
            case DROP_GAME:
                resp = handleDropGame(req);
                break;
            case NEW_BOARD:
                resp = handleNewBoard(req);
                break;
            case HIGH_SCORES:
                resp = handleHighscores(req);
                break;

        }
        return resp;

    }

    
    private Response handleListGames(Request req) {
        System.out.println("Handler call getgameList");
        return new Response(ResponseStatus.LIST, gameBean.getGamesList(), null);
    }

    private Response handleMove(Request req) {
        gameBean.handleMove(req.getGameId(), req.getMove().getX(), req.getMove().getY(), req.getPlayerId());
        return new Response(ResponseStatus.GAME_SESSION, gameBean.getGameSessionById(req.getGameId()));
    }

    private Response handleJoinGame(Request req) {
        gameBean.joinGame(req.getGameId(), req.getPlayer());
        return new Response(ResponseStatus.GAME_SESSION, gameBean.getGameSessionById(req.getGameId()));
    }

    private Response handleNewGame(Request req) {
        GameSession game = gameBean.startNewGame(req.getName(), req.getPlayer());
        return new Response(ResponseStatus.GAME_SESSION, game);
    }

    private Response handleRefresh(Request req) {
        return new Response(ResponseStatus.GAME_SESSION, gameBean.refreshGame(req.getGameId()));
    }

    private Response handleDropGame(Request req) {
        gameBean.dropGame(req.getGameId());
        return new Response(ResponseStatus.OK);
    }

    private Response handleNewBoard(Request req) {
        return new Response(ResponseStatus.GAME_SESSION, gameBean.newBoard(req.getGameId(), req.getPlayerId()));
    }

    private Response handleHighscores(Request req) {
        return new Response(ResponseStatus.HIGH_SCORES, null, gameBean.getHighScores());
    }
}
