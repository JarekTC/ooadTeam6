package edu.colorado.team6;

import java.lang.Math;
import java.util.ArrayList;

public class Player {
  public static class Record {
    int x;
    int y;
    int hitMiss;

    Record(int x, int y, int hitMiss) {
      this.x = x;
      this.y = y;
      this.hitMiss = hitMiss;
    }

    public int[] getRecord() {
      int arr[] = {this.x, this.y, this.hitMiss};
      return arr;
    }

    public int getHitMiss() {
      return hitMiss;
    }

    public void setHitMiss(int hitMiss) {
      this.hitMiss = hitMiss;
    }
  }

  private String name;
  private Record record;
  private int score = 0;
  private ArrayList<Record> hitsMisses = new ArrayList<Record>();
  private int[][] board = new int[10][10];
  private final int SHIP = 1;
  private final int SEA = 0;
  private final int HIT = 1;
  private final int MISS = 0;
  private final int ERROR = -1;
  private final int NONERROR = 1;

  // constructor
  public Player(String name, Player.Record record) {
    this.name = name;
    this.record = record;
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

  public int getPosition(int x, int y) {
    return this.board[y][x];
  }

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
        this.board[y1][i] = label;
      }
    } else {
      for (int i = y1; i <= y2; i++) {
        this.board[i][x1] = label;
      }
    }
    return NONERROR;
  }

  public int hit(int x, int y, Player enemy) {
    int hitStat;
    hitStat = enemy.getPosition(x, y);
    if (hitStat == SEA){ // SHOULDN'T A HIT BE WHEN hitStat IS A 1. IF SO, USE SHIP final VARIABLE?
      addRecord(x,y, MISS);
    }
    else{
      addRecord(x,y, HIT);
    }

    if (hitStat == 1) {
      System.out.println("Ship hit!");
      return hitStat;
    } else {
      System.out.println("Missed!");
      return hitStat;
    }
  }

  public Record lookupRecord(int x, int y) {
    for(int i = 0; i < hitsMisses.size(); i++) {
      if(x == hitsMisses.get(i).x && y == hitsMisses.get(i).y) {
        return hitsMisses.get(i);
      }
    }
    return new Record(-1, -1, -1);
  }

  public boolean addRecord(int x, int y, int hitMiss){
    //append Record to array list of records
    // CHECK IF RECORD EXISTS
    for(int i = 0; i < hitsMisses.size(); i++) {
      if(x == hitsMisses.get(i).x && y == hitsMisses.get(i).y) {
        if(hitMiss == hitsMisses.get(i).hitMiss){
          System.out.println("The record already exists!");
          return false;
        }
        else{
          hitsMisses.get(i).hitMiss = hitMiss;
          return true;
        }
      }
    }
    Record r = new Record(x,y,hitMiss);
    this.hitsMisses.add(r);
    return true;
  }
}
