package edu.colorado.team6;

import java.awt.*;
import java.util.HashMap;

public class Board {
    private int[][] board = new int[10][10];
    private HashMap<Point, Ship> shipLocations = new HashMap<Point, Ship>();
    private MineSweeper ms = new MineSweeper();
    private Destroyer ds = new Destroyer();
    private BattleShip bs = new BattleShip();

    public int getCoord(int x, int y) {
        int pos = this.board[y][x];
        if (pos == 0){
            return pos;
        }
        else{
            Point coord = new Point(x,y);
            Ship s = getShipLocations(coord);
            //inflict damage
            //s.takeDamage();
            return pos;
        }
    }

    public void setCoord(int x, int y, int shipOrSea) {
        this.board[y][x] = shipOrSea;
    }

    public int setShip(int x1, int y1, int x2, int y2, int health, String ship){ //replace health
        int label = 1;
        if ((Math.abs(x1 - x2) != 0) && (Math.abs(y1 - y2) != 0)) {
            System.out.println("Cannot place ships diagonally!");
            return Constants.ERROR;
        }
        if((Math.abs(x1 - x2) > health) || (Math.abs(y1 - y2) > health)){
            System.out.println("Coordinates longer than health!");
            return Constants.ERROR;
        }
        // adapted for cartesian coordinates
        // choose position array
        if (Math.abs(x1 - x2) != 0) {
            for (int i = x1; i <= x2; i++) {
                this.setCoord(i,y1,label);
                Point coord = new Point(i,y1);
                int success = setShipLocations(coord,ship);
            }
        } else {
            for (int i = y1; i <= y2; i++) {
                this.setCoord(x1,i,label);
                Point coord = new Point(x1,i);
                int success = setShipLocations(coord,ship);
            }
        }
        return Constants.NONEERROR;
    }

    public int setShipLocations(Point coord, String ship){
        if (ship.equals(Constants.MINESWEEPER)){
            this.shipLocations.put(coord, this.ms);
            return Constants.NONEERROR;
        }
        else if (ship.equals(Constants.DESTROYER)){
            this.shipLocations.put(coord, this.ds);
            return Constants.NONEERROR;
        }
        else if (ship.equals(Constants.BATTLESHIP)){
            this.shipLocations.put(coord, this.bs);
            return Constants.NONEERROR;
        }
        else {
            return Constants.ERROR;
        }
    }

    public Ship getShipLocations(Point coord){
        return this.shipLocations.get(coord);
    }
}










