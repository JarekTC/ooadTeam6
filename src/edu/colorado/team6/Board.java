package edu.colorado.team6;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Board {
  private int[][] board = new int[10][10];
  private HashMap<Point, Ship> shipLocations = new HashMap<Point, Ship>();
  private MineSweeper ms = new MineSweeper();
  private Destroyer ds = new Destroyer();
  private BattleShip bs = new BattleShip();
  private ArrayList<Point> msOrientation = new ArrayList<Point>();
  private ArrayList<Point> dsOrientation = new ArrayList<Point>();
  private ArrayList<Point> bsOrientation = new ArrayList<Point>();

  public int getCoord(int x, int y) {
    int pos = this.board[y][x];
    if (pos == 0) {
      return pos;
    } else {
      Point coord = new Point(x, y);
      Ship s = getShipLocations(coord);
      int shipIndex;
      if (s instanceof MineSweeper) {
        shipIndex = this.msOrientation.indexOf(coord);
      } else if (s instanceof Destroyer) {
        shipIndex = this.dsOrientation.indexOf(coord);
      } else {
        shipIndex = this.bsOrientation.indexOf(coord);
      }
      // inflict damage
      System.out.println(shipIndex);
      s.takeDamage(shipIndex);
      return pos;
    }
  }

  public void setCoord(int x, int y, int shipOrSea) {
    this.board[y][x] = shipOrSea;
  }

  public int getPos(int x, int y) {
    return this.board[y][x];
  }

  public int setShip(int x1, int y1, int x2, int y2, int health, String ship) { // replace health
    int label = 1;
    if ((Math.abs(x1 - x2) != 0) && (Math.abs(y1 - y2) != 0)) {
      System.out.println("Cannot place ships diagonally!");
      return Constants.ERROR;
    }
    if ((Math.abs(x1 - x2) > health) || (Math.abs(y1 - y2) > health)) {
      System.out.println("Coordinates longer than health!");
      return Constants.ERROR;
    }
    // adapted for cartesian coordinates
    int retVal = setShipArray(x1, y1, x2, y2, health, ship);
    if (Math.abs(x1 - x2) != 0) {
      for (int i = x1; i <= x2; i++) {
        Point coord = new Point(i, y1);
        if (shipLocations.containsKey(coord)) {
          return Constants.ERROR;
        }
        this.setCoord(i, y1, label);
        int success = setShipLocations(coord, ship);
      }
    } else {
      for (int i = y1; i <= y2; i++) {
        Point coord = new Point(x1, i);
        if (shipLocations.containsKey(coord)) {
          return Constants.ERROR;
        }
        this.setCoord(x1, i, label);
        int success = setShipLocations(coord, ship);
      }
    }
    return Constants.NONEERROR;
  }

  public int setShipLocations(Point coord, String ship) {
    if (ship.equals(Constants.MINESWEEPER)) {
      this.shipLocations.put(coord, this.ms);
      return Constants.NONEERROR;
    } else if (ship.equals(Constants.DESTROYER)) {
      this.shipLocations.put(coord, this.ds);
      return Constants.NONEERROR;
    } else if (ship.equals(Constants.BATTLESHIP)) {
      this.shipLocations.put(coord, this.bs);
      return Constants.NONEERROR;
    } else {
      return Constants.ERROR;
    }
  }

  public Ship getShipLocations(Point coord) {
    return this.shipLocations.get(coord);
  }

  public int setShipArray(int x1, int y1, int x2, int y2, int health, String ship) {
    ArrayList<Point> posi = new ArrayList<Point>();
    if (Math.abs(x1 - x2) != 0) {
      for (int i = x1; i <= x2; i++) {
        Point coord = new Point(i, y1);
        posi.add(coord);
      }
    } else {
      for (int i = y1; i <= y2; i++) {
        Point coord = new Point(x1, i);
        posi.add(coord);
      }
    }
    if (ship.equals(Constants.MINESWEEPER)) {
      this.msOrientation = posi;
      return Constants.NONEERROR;
    } else if (ship.equals(Constants.DESTROYER)) {
      this.dsOrientation = posi;
      return Constants.NONEERROR;
    } else if (ship.equals(Constants.BATTLESHIP)) {
      this.bsOrientation = posi;
      return Constants.NONEERROR;
    }
    return Constants.ERROR;
  }
}