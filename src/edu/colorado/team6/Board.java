package edu.colorado.team6;

import java.awt.*;

public class Board {
    private final int SHIP = 1;
    private final int SEA = 0;
    private final int ERROR = -1;
    private final int NONEERROR = 1;
    private int[][] board = new int[10][10];

//    private Point[] MineS_pos = new Point[2];
//    private Point[] Dest_pos = new Point[3];
//    private Point[] BattleS_pos = new Point[4];

    public Integer getCoord(int x, int y) {
        return this.board[y][x];
    }

    public void setCoord(int x, int y, int shipOrSea) {
        this.board[y][x] = shipOrSea;
    }

    public int setShip(int x1, int y1, int x2, int y2, int health){ //replace health
        int label = 1;
        if ((Math.abs(x1 - x2) != 0) && (Math.abs(y1 - y2) != 0)) {
            System.out.println("Cannot place ships diagonally!");
            return ERROR;
        }
        if((Math.abs(x1 - x2) > health) || (Math.abs(y1 - y2) > health)){
            System.out.println("Coordinates longer than health!");
            return ERROR;
        }
        // adapted for cartesian coordinates
        // choose position array
        if (Math.abs(x1 - x2) != 0) {
            for (int i = x1; i <= x2; i++) {
                this.setCoord(i,y1,label);
            }
        } else {
            for (int i = y1; i <= y2; i++) {
                this.setCoord(x1,i,label);
            }
        }
        return NONEERROR;
    }
}
