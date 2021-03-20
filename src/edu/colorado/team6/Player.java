package edu.colorado.team6;

import java.awt.*;
import java.lang.Math;
import java.util.HashMap;

public class Player {

  private String name;
  private int score = 0;
  private HashMap<Point, Integer> record = new HashMap<Point, Integer>();
  private Board b = new Board();
  private Boolean code;

  public Board getB() {
    return b;
  }

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
    if(!ship.equals(Constants.SUBMARINE)){
      return this.b.setShip(x1, y1, x2, y2, health, ship);
    } else {
      System.out.println("PLACING SUB");
      return this.b.setSub(x1, y1, x2, y2, health, ship);
    }
  }

  public int hit(int x, int y, Player enemy, Boolean code) {
    int hitStat = enemy.b.getCoord(x, y); //ISAAC CHANGE THIS
    addRecord(x, y, hitStat);
    if (hitStat != Constants.SEA){
      // laser
      if (code){
        Boolean overlap = false;
        if(hitStat == Constants.SHIP_ON_TOP_SUB){
          overlap = true;
        }
        enemy.b.laserApplyDamage(x,y,overlap);
      }//bombs
      else{
        if(hitStat != Constants.SUB_UNDER_WATER) {
          System.out.println(hitStat + " sdjvbsdiuvbsidvisudvivhvvahjds");
          enemy.b.bombApplyDamage(x,y);
        }
      }
    }
    return enemy.b.getCoord(x, y);
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
