package edu.colorado.team6;

import javax.lang.model.type.NullType;
import java.awt.*;
import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
  private HashMap<Point, Integer> revealed = new HashMap<Point, Integer>();
  private Boolean sonar = false;
  private int bomberAvail = 1;

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

  public int getBomberAvail() {
    return 1;
  }
//  public int getBomberAvail(){
//    return this.bomberAvail;
//  }

  public void setBomberAvail(){
    this.bomberAvail = 0;
  }

  public Boolean getSonar(){
    return this.sonar;
  }

  public void setSonar(){
    this.sonar = true;
  }

  public void setRevealed(HashMap<Point, Integer> rev){
    this.revealed = rev;
  }

  public HashMap<Point, Integer> getRevealed(){return this.revealed;}

  public HashMap<Point, Integer> getRecord(){return this.record;}

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
          Ship s = enemy.b.getShipLocations(new Point(x,y)).get(0);
          enemy.b.bombApplyDamage(x, y);
          this.countHits++;
          if(s.getShipHealth() == 0){
            setActivationCode();
          }
        }
      }
    }
    return enemy.b.getCoord(x, y);
  }

  public HashMap<Integer, Point> b2Bomber(Player enemy){
    HashMap<Point,ArrayList<Ship>> targets = enemy.b.getShipLocations();
    Iterator it = targets.entrySet().iterator();
    for(Map.Entry<Point, ArrayList<Ship>> pair : targets.entrySet()){
      ArrayList<Ship> potential_target = (ArrayList<Ship>) pair.getValue();
      //if there is a ship at this location, hit it!
      if(potential_target.size() != 0){
        System.out.println("Target acquired!");
        Point coord = pair.getKey();
        int x = coord.x;
        int y = coord.y;
        int tar = hit(x,y,enemy,this.activationCode);
        HashMap<Integer, Point> result = new HashMap<Integer,Point>();
        result.put(tar,coord);
        return result;
      }
    }
    HashMap<Integer, Point> failed = new HashMap<Integer, Point>();
    return failed;
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
      return false;
    }
    this.record.put(p, hitMiss);
    return true;
  }
}
