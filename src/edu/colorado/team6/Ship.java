package edu.colorado.team6;


import java.util.Arrays;

public class Ship {

    // Basic private member variables
    private final String typeName;
    private int health;
    private int[][] hits;

    // Constructor, currently initialized with name of subclass type
    public Ship(String typeOfShip) {
        typeName = typeOfShip;
    }

    public int updateHealth(int update){
        health = update;
        return health;
    }

    // Helper function for subclasses to set proper starting health
    // and initialize array to correct size
<<<<<<< HEAD
    public static int initializeCorrectHealth(int startHealth) {
=======
    public void initializeCorrectHealth(int startHealth) {
>>>>>>> origin/NeelStefanTest1
        health = startHealth;
        hits = new int[health][2];
        for (int i=0; i<hits.length; i++){
            hits[i][1] = 1;
        }
        return health;
    }
    // hits helper
    public int updateHits(int i, int j, int a){
        hits[i][j] = a;
        return hits[i][j];
    }

    // Decrement ship health
    public int takeDamage(int shipIndex) {
        if (health != 0) health = health - 1;

        if (hits[shipIndex][0] == 1){
            return captainsQuartersHit(shipIndex);
        }
        else if (hits[shipIndex][1] != 0) {
            hits[shipIndex][1] += -1;
        }
        else{
            System.out.println("Spot already reduced to 0.");
        }
        return hits[shipIndex][0];
    }

    // Displays subclass type name passed into constructor
    public String showShipType() {
        System.out.println(typeName);
        return typeName;
    }

    // Ship Health getter
    public int getShipHealth(){
        return health;
    }

    // Set Captains quarters
    public int setCaptainsQuarters(int cpt){
        hits[cpt][0] = 1;
        hits[cpt][1] = 2;

        return cpt;
    }

    public int getCaptainsQuarters(){
        for (int i=0; i < hits.length; i++){
            if (hits[i][1] == 2){
                System.out.println("Captains Quarters in slot " + i + " of " + typeName);
                return i;
            }
        }
        return Constants.ERROR;
    }

    public int captainsQuartersHit(int index) {
        if (hits[index][1] == 2){
            hits[index][1]--;
        }
        else if (hits[index][1] == 1){
            for(int i=0; i < hits.length; i++){
                hits[i][1] = 0;
            }
            health = 0;
        }
        return health;
    }
}
