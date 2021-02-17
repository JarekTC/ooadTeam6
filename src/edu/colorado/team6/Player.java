package edu.colorado.team6;
import java.lang.Math;

public class Player {
  public static class Record {
    int x;
    int y;
    String hitMissNone;
  }

  private String name;
  private Record record;
  private int score = 0;

  private int[][] board = new int[10][10];

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

  public int getPosition(int x, int y){
    return this.board[y][x];
  }

  // COME BACK LATER TO ACTUALLY SET LOCATIONS OF SHIPS
  // come back later to verify length of ship matches dist between points
  // Also, prevent overlapping ships
  public int setShip(int x1, int y1, int x2, int y2, int label) {
    if(label != 0 && label != 1){
      System.out.println("Label must be a 1(ship), or a 0(sea)");
      return -1;
    }
    if((Math.abs(x1-x2) != 0) && (Math.abs(y1-y2) != 0)){
      System.out.println("Cannot place ships diagonally!");
      return -1;
    }
    //adapted for cartesian coordinates
    if (Math.abs(x1-x2) != 0){
      for(int i = x1; i <= x2; i++){
        this.board[y1][i] = label;
      }
    }
    else{
      for(int i = y1; i <= y2; i++){
        this.board[i][x1] = label;
      }
    }
    return 1;
  }

  public int hit(int x, int y, Player enemy) {
    int hitStat;
    hitStat = enemy.getPosition(x,y);
    if (hitStat == 1) {
      System.out.println("Ship hit!");
      return hitStat;
    } else {
      System.out.println("Missed!");
      return hitStat;
    }
  }
}
