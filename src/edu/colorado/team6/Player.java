package edu.colorado.team6;

import java.awt.*;
import java.lang.Math;
import java.util.HashMap;

public class Player {

  private String name;
//  private Record record;
  private int score = 0;
  private HashMap<Point, Integer> record = new HashMap<Point, Integer>();
//  private int[][] board = new int[10][10];
  private final int SHIP = 1;
  private final int SEA = 0;
  private final int HIT = 1; //Remove HIT and MISS. Replace variables with SHIP and SEA accordingly
  private final int MISS = 0;
  private final int ERROR = -1;
  private final int NONEERROR = 1;
  private Board b = new Board();

  // constructor
  public Player(String name) {
    this.name = name;
  }
  // getter for name
  public String getName() {
    return name;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

//  public int getPosition(int x, int y) {
//    return this.b.board[y][x];
//  }

  // COME BACK LATER TO ACTUALLY SET LOCATIONS OF SHIPS
  // come back later to verify length of ship matches dist between points
  // Also, prevent overlapping ships
  public int setShip(int x1, int y1, int x2, int y2, int label) {
    if (label != SEA && label != SHIP) {
      System.out.println("Label must be a 1(ship), or a 0(sea)");
      return ERROR;
    }
    if ((Math.abs(x1 - x2) != 0) && (Math.abs(y1 - y2) != 0)) {
      System.out.println("Cannot place ships diagonally!");
      return ERROR;
    }
    // adapted for cartesian coordinates
    if (Math.abs(x1 - x2) != 0) {
      for (int i = x1; i <= x2; i++) {
        this.b.setCoord(i,y1,label);
      }
    } else {
      for (int i = y1; i <= y2; i++) {
        this.b.setCoord(x1,i,label);
      }
    }
    return NONEERROR;
  }

  public int hit(int x, int y, Player enemy) {
    int hitStat = enemy.b.getCoord(x, y);
    if (hitStat == SEA) { // SHOULDN'T A HIT BE WHEN hitStat IS A 1. IF SO, USE SHIP final VARIABLE?
      addRecord(x, y, MISS);
    } else {
      addRecord(x, y, HIT);
    }

    if (hitStat == SHIP) {
      System.out.println("Ship hit!");
      return hitStat;
    } else {
      System.out.println("Missed!");
      return hitStat;
    }
  }

  public int lookupRecord(int x, int y) {
    Point p = new Point(x,y);
    if(this.record.containsKey(p)){
        return record.get(p);
    }
    return ERROR;
  }

  public boolean addRecord(int x, int y, int hitMiss) {
    // append Record to array list of records
    // CHECK IF RECORD EXISTS
    Point p = new Point(x,y);
    if(this.record.containsKey(p)){
      System.out.println("The record already exists!");
      return false;
    }
    this.record.put(p, hitMiss);
    return true;
  }
}
