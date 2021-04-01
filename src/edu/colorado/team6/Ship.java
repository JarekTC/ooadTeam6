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

  public int updateHealth(int update) {
    this.health = update;
    return this.health;
  }

  // Helper function for subclasses to set proper starting health
  // and initialize array to correct size
  public int initializeCorrectHealth(int startHealth) {
    this.health = startHealth;
    hits = new int[this.health][2];
    for (int i = 0; i < hits.length; i++) {
      hits[i][1] = 1;
    }
    return this.health;
  }

  // hits helper
  public int updateHits(int i, int j, int a) {
    hits[i][j] = a;
    return hits[i][j];
  }

  // Decrement ship health
  public int takeDamage(int shipIndex) {
    if (hits[shipIndex][1]
        == 1) { // Do not decrease the overall health of the ship if the captain's quarters has only
      // been hit once.
      System.out.println(this.typeName + this.health);
      if (this.health != 0)
        this.health =
            this.health
                - 1; // If at any point, the part of the ship at shipIndex is 1, decrease the
      // overall health
    }

    if (hits[shipIndex][0] == 1) {
      return captainsQuartersHit(shipIndex);
    } else if (hits[shipIndex][1] != 0) {
      hits[shipIndex][1] += -1;
    } else {
      System.out.println("Spot already reduced to 0.");
    }

    return hits[shipIndex][1]; // switched from 0 to 1
  }

  // Displays subclass type name passed into constructor
  public String showShipType() {
    return typeName;
  }

  // Ship Health getter
  public int getShipHealth() {
    return this.health;
  } // switched to this.health

  // Set Captains quarters
  public int setCaptainsQuarters(int cpt) {
    hits[cpt][0] = 1;
    hits[cpt][1] = 2;

    return cpt;
  }

  public int getCaptainsQuarters() {
    for (int i = 0; i < hits.length; i++) {
      if (hits[i][1] == 2) {
        System.out.println("Captains Quarters in slot " + i + " of " + typeName);
        return i;
      }
    }
    return Constants.ERROR;
  }

  public int captainsQuartersHit(int index) {
    if (hits[index][1] == 2) {
      hits[index][1]--;
    } else if (hits[index][1] == 1) {
      for (int i = 0; i < hits.length; i++) {
        hits[i][1] = 0;
      }
      this.health = 0;
    }
    return this.health;
  }
}
