package edu.colorado.team6;

import java.awt.*;
import java.lang.Math;
import java.util.HashMap;

public class Player {

  private String name;
  private int score = 0;
  private HashMap<Point, Integer> record = new HashMap<Point, Integer>();
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

  // COME BACK LATER TO ACTUALLY SET LOCATIONS OF SHIPS
  // come back later to verify length of ship matches dist between points
  // Also, prevent overlapping ships
  public int placeShip(int x1, int y1, int x2, int y2, int health, String ship) {
    return this.b.setShip(x1, y1, x2, y2, health, ship);
  }

  public int hit(int x, int y, Player enemy) {
    int hitStat = enemy.b.getCoord(x, y);
    if (hitStat
        == Constants
            .SEA) { // SHOULDN'T A HIT BE WHEN hitStat IS A 1. IF SO, USE SHIP final VARIABLE?
      addRecord(x, y, Constants.SEA);
    } else {
      addRecord(x, y, Constants.SHIP);
    }

    if (hitStat == Constants.SHIP) {
      System.out.println("Ship hit!");
      return hitStat;
    } else {
      System.out.println("Missed!");
      return hitStat;
    }
  }

  public int lookupRecord(int x, int y) {
    Point p = new Point(x, y);
    if (this.record.containsKey(p)) {
      return record.get(p);
    }
    return Constants.ERROR;
  }

  public boolean addRecord(int x, int y, int hitMiss) {
    // append Record to array list of records
    // CHECK IF RECORD EXISTS
    Point p = new Point(x, y);
    if (this.record.containsKey(p)) {
      System.out.println("The record already exists!");
      return false;
    }
    this.record.put(p, hitMiss);
    return true;
  }
}
