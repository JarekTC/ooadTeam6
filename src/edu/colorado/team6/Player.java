package edu.colorado.team6;

import javax.lang.model.type.NullType;
import java.awt.*;
import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;

public class Player {

  private String name;
  private int score = 0;
  private HashMap<Point, Integer> record = new HashMap<Point, Integer>();
  private Board b = new Board();

  //TODO: Implement method to use sonar perk
  private Perks p = new Perks();

  //TODO: implement method to update after player sinks one ship
  private Boolean activationCode = false;
  private int countHits = 0;
  private Boolean nuke = false;

  public Board getB() {
    return b;
  }

  // constructor
  public Player(String name) {
    this.name = name;
  }

  public void printRecord() {
    int xyValue;
    for (int y = 9; y >= 0; y--) {
      for (int x = 0; x < 10; x++) {
        try {
          xyValue = record.get(new Point(x, y));
          if (xyValue == 1 || xyValue == 2 || xyValue == 3) {
            System.out.print(" H ");
          } else {
            System.out.print(" M ");
          }
        }
        catch (Exception e) {
          System.out.print(" - ");
        }
      }
      System.out.println();
    }
  }

  public Boolean getActivationCode(){
    return this.activationCode;
  }

  public void setActivationCode(){
    this.activationCode = true;
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

  public int getCountHits(){
    return this.countHits;
  }

  public void setNuke(){
    this.nuke = true;
  }

  public Boolean getNuke(){
    return this.nuke;
  }

  public Perks getPerks(){
    return this.p;
  }

  public ArrayList<String> moveFleetPlayer(char direction) {
    return p.moveFleet(b, direction);
  }


  public int placeShip(int x1, int y1, int x2, int y2, int health, String ship) {
    if (!ship.equals(Constants.SUBMARINE)) {
      return this.b.setShip(x1, y1, x2, y2, health, ship);
    } else {
      System.out.println("PLACING SUB");
      return this.b.setSub(x1, y1, x2, y2, health, ship);
    }
  }
  //TODO: refactor design for hit to use the activation code
  public int hit(int x, int y, Player enemy, Boolean code) {
    int hitStat = enemy.b.getCoord(x, y);
    addRecord(x, y, hitStat);
    if (hitStat != Constants.SEA) {
      // laser
      if (code) {//TODO: this.activationCode
        Boolean overlap = false;
        if (hitStat == Constants.SHIP_ON_TOP_SUB) {
          overlap = true;
        }
        enemy.b.laserApplyDamage(x, y, overlap);
        this.countHits++;
      } // bombs
      else {
        if (hitStat != Constants.SUB_UNDER_WATER) {
          //System.out.println("coord before " + enemy.b.getCoord(x, y));
          Ship s = enemy.b.getShipLocations(new Point(x,y)).get(0);
          enemy.b.bombApplyDamage(x, y);
          this.countHits++;
          //System.out.println(enemy.b.getCoord(x, y));
          if(s.getShipHealth() == 0){
            setActivationCode();
          }
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
