package edu.colorado.team6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleShipTest {
  @Test
  public void canMakeBattleShip() {
    new BattleShip();
  }

  @Test
  public void canTakeDamage() {
    BattleShip b1 = new BattleShip();
    b1.takeDamage(1);
  }

  @Test
  public void initializeHealthTest() {
    BattleShip b2 = new BattleShip();
    b2.initializeCorrectHealth(4);
  }

  @Test
  public void canDisplayTypeName() {
    BattleShip b3 = new BattleShip();
    b3.showShipType();
  }

  @Test
  public void canViewHealth() {
    BattleShip b4 = new BattleShip();
    b4.getShipHealth();
  }

  @Test
  public void hasCaptainsQuarters() {
    BattleShip b5 = new BattleShip();
    b5.initializeCorrectHealth(3);
    b5.setCaptainsQuarters(1);
  }

  @Test
  public void canViewCaptainsQuarters() {
    BattleShip b6 = new BattleShip();
    b6.initializeCorrectHealth(3);
    b6.setCaptainsQuarters(1);
    b6.getCaptainsQuarters();
  }

  @Test
  public void captainsQuartersHit() {
    BattleShip b7 = new BattleShip();
    b7.initializeCorrectHealth(3);
    b7.setCaptainsQuarters(1);
    b7.takeDamage(1);
    b7.takeDamage(1);
  }
}
