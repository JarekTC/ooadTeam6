package edu.colorado.team6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DestroyerTest {
  @Test
  public void canMakeDestroyer() {
    new Destroyer();
  }

  @Test
  public void canTakeDamage() {
    Destroyer d1 = new Destroyer();
    d1.takeDamage(1);
  }

  @Test
  public void initializeHealthTest() {
    Destroyer d2 = new Destroyer();
    d2.initializeCorrectHealth(3);
  }

  @Test
  public void canDisplayTypeName() {
    Destroyer d3 = new Destroyer();
    d3.showShipType();
  }

  @Test
  public void canViewHealth() {
    Destroyer d4 = new Destroyer();
    d4.getShipHealth();
  }

  @Test
  public void hasCaptainsQuarters() {
    Destroyer d5 = new Destroyer();
    d5.initializeCorrectHealth(3);
    d5.setCaptainsQuarters(1);
  }

  @Test
  public void canViewCaptainsQuarters() {
    Destroyer d6 = new Destroyer();
    d6.initializeCorrectHealth(3);
    d6.setCaptainsQuarters(1);
    d6.getCaptainsQuarters();
  }

  @Test
  public void captainsQuartersHit() {
    Destroyer d7 = new Destroyer();
    d7.initializeCorrectHealth(3);
    d7.setCaptainsQuarters(1);
    d7.takeDamage(1);
    d7.takeDamage(1);
  }
}
