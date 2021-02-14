package edu.colorado.team6;

public class Player {
    private String name;
    private int score = 0;

    //constructor
    public Player(String name) {
        this.name = name;
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
