package edu.colorado.team6;


public class Ship {

  //basic private member variables
  private String typeName;
  private static int health;


  public void show() { // dunno why this is here maybe it is just an example method
    System.out.println("IF you can't see this then something is severely wrong!!");
  }


  //constructor, currently initialized with name of subclass type
  public Ship(String typeOfShip) {
    typeName = typeOfShip;
  }

  //helper function for subclasses to set proper starting health
  public static void initializeCorrecthealth(int startHealth){
    health = startHealth;
  }

  //decrement ship health
  public static void takeDamage() {
    if (health != 0) {
      health = health - 1;
    }
  }

  //displays subclass type name passed into constructor
  public void showShipType() {
    System.out.println(typeName);
  }
}
