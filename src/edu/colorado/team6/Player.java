package edu.colorado.team6;

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

  // COME BACK LATER TO ACTUALLY SET LOCATIONS OF SHIPS
  public void setShip(int x, int y, int shipOrSea) {
    this.board[x][y] = shipOrSea;
  }

  public int getPosition(int x, int y){
    return this.board[x][y];
  }

  public int hit(int x, int y, Player enemy) {
    int hitStat;
    hitStat = enemy.getPosition(x,y);
    if (hitStat == 1) {
      System.out.print("Ship hit!");
      return hitStat;
    } else if (hitStat == 0) {
      System.out.print("Missed!");
      return hitStat;
    } else {
      System.out.println("Error: enemy board hitStatus returned non 0, or 1 value");
      return -1;
    }
  }
}
