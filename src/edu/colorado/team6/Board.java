package edu.colorado.team6;

public class Board {
    private int[][] board = new int[10][10];

    public Board(int[][] board) {
        this.board = board;
    }
    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }
}
