package edu.colorado.team6;


import java.util.Arrays;

public class Ship {

    // Basic private member variables
    private String typeName;
    private static int health;
    private static int[][] hits;

    // Constructor, currently initialized with name of subclass type
    public Ship(String typeOfShip) {
        typeName = typeOfShip;
    }

    // Helper function for subclasses to set proper starting health
    // and initialize array to correct size
    public static void initializeCorrectHealth(int startHealth) {
        health = startHealth;
        hits = new int[health][2];
        for (int i=0; i<hits.length; i++){
            hits[i][1] = 1;
        }
    }

    // Decrement ship health
    public void takeDamage(int shipIndex) {
        if (health != 0) health = health - 1;

        if (hits[shipIndex][0] == 1){
            captainsQuartersHit(shipIndex);
        }
        else if (hits[shipIndex][1] != 0) {
            hits[shipIndex][1] += -1;
        }
        else{
            System.out.println("Spot already reduced to 0.");
        }
    }

    // Displays subclass type name passed into constructor
    public void showShipType() {
        System.out.println(typeName);
    }

    // Ship Health getter
    public int getShipHealth(){
        return health;
    }

    // Set Captains quarters
    public static void setCaptainsQuarters(int cpt){
        hits[cpt][0] = 1;
        hits[cpt][1] = 2;
    }

    public void getCaptainsQuarters(){
        for (int i=0; i < hits.length; i++){
            if (hits[i][1] == 2){
                System.out.println("Captains Quarters in slot " + i + " of " + typeName);
            }
        }
    }

    public void captainsQuartersHit(int index) {
        if (hits[index][1] == 2){
            hits[index][1]--;
            health--;
        }
        else if (hits[index][1] == 1){
            for(int i=0; i < hits.length; i++){
                hits[i][1] = 0;
            }
            health = 0;
        }
    }
}
