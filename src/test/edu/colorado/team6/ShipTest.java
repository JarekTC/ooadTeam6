package edu.colorado.team6;

import org.junit.jupiter.api.Test;

public class ShipTest {
  @Test
  public void canMakeShip() {

    new Ship("MineSweeper");
  }

  @Test
  public void canSetHealth(){
    Ship ship = new Ship("MineSweeper");
    Ship.initializeCorrectHealth(3);
  }

  @Test
  public void canTakeDamage(){
    Ship ship = new Ship("MineSweeper");
    ship.takeDamage();
  }

  @Test
  public void hasTypeName(){
    Ship ship = new Ship("MineSweeper");
    ship.showShipType();
  }
}
