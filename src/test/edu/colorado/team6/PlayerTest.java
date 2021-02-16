package edu.colorado.team6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
  private Player p1;
  private Player p2;
  private String name1 = "Neefan";
  private String name2 = "Sefeel";
  private Player.Record r1;
  private Player.Record r2;

  @BeforeEach
  public void setUp() {
    r1 = new Player.Record();
    r2 = new Player.Record();
    p1 = new Player(name1, r1);
    p2 = new Player(name2, r2);
  }

  @Test
  public void testSetName() {
    assertEquals(name1, p1.getName());
  }

  @Test
  public void testGetScore() {
    assertEquals(0, p1.getScore());
  }

  @Test
  public void testSetScore() {
    int testScore = 90;
    p1.setScore(90);

    assertEquals(testScore, p1.getScore());
  }

  @Test
  public void testGetSetPosition() {
    int positionVal = 1;
    p2.setShip(0, 0, positionVal); // (0, 0) is a ship now
    assertEquals(positionVal, p2.getPosition(0, 0));

  }

  @Test
  public void testHit() {
    int hit = 1;
    int miss = 0;
    p2.setShip(0, 0, hit);
    p2.setShip(0, 1, miss);
    assertEquals(hit, p1.hit(0, 0, p2));
    assertEquals(miss, p1.hit(0, 1, p2));
  }
}
