package edu.colorado.team6;

public class Board {
    private int[][] board = new int[10][10];

    public Integer getCoord(int x, int y) {
        return this.board[y][x];
    }

    public void setCoord(int x, int y, int shipOrSea) {
        this.board[y][x] = shipOrSea;
    }
}
