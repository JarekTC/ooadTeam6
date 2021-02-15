package edu.colorado.team6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShipTest {
  @Test
  public void canMakeShip() {

    new Ship("MineSweeper");
  }

  @Test
  public void canTakeDamage(){
    Ship ship = new Ship("MineSweeper");
    ship.takeDamage(0);
  }

  @Test
  public void hasTypeName(){
    Ship ship = new Ship("MineSweeper");
    ship.showShipType();
  }
}
