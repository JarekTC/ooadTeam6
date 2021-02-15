package edu.colorado.team6;

public class Player {
    public static class Record{
        int x;
        int y;
        String hitMissNone;
    }

    private String name;
    private Record record;
    private int score = 0;

    private int[][] board = new int[10][10];
    private Board b = new Board(board);

    //constructor
    public Player(String name, Player.Record record) {
        this.name = name;
        this.record = record;
    }
    //getter for name
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void hit(int x, int y, Player enemy){
        int hitStat;
        hitStat = enemy.b.hitStatus(x,y);
        if (hitStat == 1) {
            System.out.print("Ship hit!");
        }
        else if(hitStat == 0){
            System.out.print("Missed!");
        }
    }
}
