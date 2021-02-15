package edu.colorado.team6;


// This is the  baseclass for your ship.  Modify accordingly
// TODO: practice good OO design
public class Ship {
  private String typeName;
  // TODO: create appropriate getter and setter methods
  // TODO: Understand encapsulation
  // TODO: Understand what these todo comments mean

  private static int health;


  public void show() { // dunno why this is here maybe it is just an example method
    System.out.println("IF you can't see this then something is severely wrong!!");
  }

  public Ship(String typeOfShip) {
    typeName = typeOfShip;
  }

  public static void takeDamage(int i) {
    if (health != 0) {
      health = health - 1;
    }
  }

  public void showShipType() {
    System.out.println(typeName);
  }
}
