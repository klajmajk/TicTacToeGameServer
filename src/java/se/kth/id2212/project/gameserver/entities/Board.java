/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id2212.project.gameserver.entities;

/**
 *
 * @author Adam
 */
public class Board {

    public BoardMoves[][] array;

    public Board() {
        this.array = new BoardMoves[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.array[i][j] = BoardMoves.EMPTY;
            }
        }
    }

    public BoardMoves get(int x, int y) {
        return array[x][y];
    }

    public void set(int x, int y, BoardMoves move) {
        array[x][y] = move;
    }

    public GameStatus getGameState() {
        for (int i = 0; i < 3; i++) {
            if (checkRow(i) != GameStatus.PLAYING) {
                return checkRow(i);
            }
            if (checkColumn(i) != GameStatus.PLAYING) {
                return checkColumn(i);
            }
        }
        if (checkDiagonals() != GameStatus.PLAYING) {
            return checkDiagonals();
        }
        return checkDraw();
    }

    private GameStatus checkRow(int y) {
        if ((array[0][y] == array[1][y]) && (array[1][y] == array[2][y])) {
            if (array[0][y].equals(BoardMoves.X)) {
                return GameStatus.X_WON;
            }
            if (array[0][y].equals(BoardMoves.O)) {
                return GameStatus.O_WON;
            }
            if (array[0][y].equals(BoardMoves.EMPTY)) {
                return GameStatus.PLAYING;
            }
        }
        return GameStatus.PLAYING;
    }

    private GameStatus checkColumn(int x) {
        if ((array[x][0] == array[x][1]) && (array[x][1] == array[x][2])) {
            if (array[x][0].equals(BoardMoves.X)) {
                return GameStatus.X_WON;
            }
            if (array[x][1].equals(BoardMoves.O)) {
                return GameStatus.O_WON;
            }
            if (array[x][2].equals(BoardMoves.EMPTY)) {
                return GameStatus.PLAYING;
            }
        }
        return GameStatus.PLAYING;
    }

    private GameStatus checkDiagonals() {
        if ((array[0][0] == array[1][1]) && (array[1][1] == array[2][2])) {
            if (array[0][0].equals(BoardMoves.X)) {
                return GameStatus.X_WON;
            }
            if (array[0][0].equals(BoardMoves.O)) {
                return GameStatus.O_WON;
            }
            if (array[0][0].equals(BoardMoves.EMPTY)) {
                return GameStatus.PLAYING;
            }
        }
        if ((array[0][2] == array[1][1]) && (array[1][1] == array[2][0])) {
            if (array[0][2].equals(BoardMoves.X)) {
                return GameStatus.X_WON;
            }
            if (array[0][2].equals(BoardMoves.O)) {
                return GameStatus.O_WON;
            }
            if (array[0][2].equals(BoardMoves.EMPTY)) {
                return GameStatus.PLAYING;
            }
        }
        return GameStatus.PLAYING;
    }

   private GameStatus checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (array[i][j].equals(BoardMoves.EMPTY)) {
                    return GameStatus.PLAYING;
                }
            }
        }
        return GameStatus.DRAW;
    }

}
