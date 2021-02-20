package edu.colorado.team6;


public class Ship {

    // Basic private member variables
    private String typeName;
    private static int health;

    // Constructor, currently initialized with name of subclass type
    public Ship(String typeOfShip) {
        typeName = typeOfShip;
    }

    // Helper function for subclasses to set proper starting health
    public static void initializeCorrectHealth(int startHealth) {
        health = startHealth;
    }

    // Decrement ship health
    public static void takeDamage() {
        if (health != 0) health = health - 1;
    }

    // Displays subclass type name passed into constructor
    public void showShipType() {
        System.out.println(typeName);
    }
}
