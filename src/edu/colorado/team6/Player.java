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
}
