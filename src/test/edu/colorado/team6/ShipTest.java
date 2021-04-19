package edu.colorado.team6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShipTest {
  @Test
  public void canMakeShip() {
    new Ship("TestShip");
  }

  @Test
  public void canSetHealth() {
    Ship ship = new Ship("TestShip");
    assertEquals(3, ship.initializeCorrectHealth(3));
  }

  @Test
  public void canUpdateHealth() {
    Ship ship = new Ship("TestShip");
    assertEquals(3, ship.updateHealth(3));
  }

  @Test
  public void canUpdateHits() {
    Ship ship = new Ship("TestShip");
    ship.initializeCorrectHealth(3);
    assertEquals(1, ship.updateHits(0, 0, 1));
  }

  @Test
  public void canTakeDamage() {
    Ship ship = new Ship("TestShip");
    ship.initializeCorrectHealth(3);
    assertEquals(0, ship.takeDamage(1)); // swit

    BattleShip bs = new BattleShip();
    int prehealth = bs.getShipHealth();
    bs.takeDamage(bs.getCaptainsQuarters());
    int poshealth = bs.getShipHealth();
    assertEquals(prehealth, poshealth);
  }

  @Test
  public void hitEmptySpot() {
    Ship ship = new Ship("TestShip");
    ship.initializeCorrectHealth(3);
    ship.takeDamage(1);
    assertEquals(0, ship.takeDamage(1));
  }

  @Test
  public void hasTypeName() {
    Ship ship = new Ship("TestShip");
    assertEquals("TestShip", ship.showShipType());
  }

  @Test
  public void canViewHealth() {
    Ship ship = new Ship("TestShip");
    ship.initializeCorrectHealth(3);
    assertEquals(3, ship.getShipHealth());
  }

  @Test
  public void hasCaptainsQuarters() {
    Ship ship = new Ship("TestShip");
    ship.initializeCorrectHealth(3);
    assertEquals(1, ship.setCaptainsQuarters(1));
  }

  @Test
  public void canViewCaptainsQuarters() {
    Ship ship = new Ship("TestShip");
    ship.initializeCorrectHealth(3);
    ship.setCaptainsQuarters(1);
    assertEquals(1, ship.getCaptainsQuarters());
  }

  @Test
  public void captainsQuartersHit() {
    Ship ship = new Ship("TestShip");
    ship.initializeCorrectHealth(3);
    ship.setCaptainsQuarters(1);
    ship.takeDamage(1);
    assertEquals(0, ship.takeDamage(1));
    assertEquals(0, ship.getShipHealth());
  }

  @Test
  public void checkCaptainsQuarterError() {
    Ship ship = new Ship("TestShip");
    ship.initializeCorrectHealth(3);
    assertEquals(-1, ship.getCaptainsQuarters());
  }
}
