package edu.colorado.team6;

import org.junit.jupiter.api.*;

import java.awt.*;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
  private Player p1;
  private Player p2;
  private String name1 = "Neefan";
  private String name2 = "Sefeel";

  @BeforeEach
  public void setUp() {

    p1 = new Player(name1);
    p2 = new Player(name2);
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
  public void testPlaceShip() {
    // Place horizontal ship
    assertEquals(Constants.NONEERROR, p2.placeShip(0, 0, 1, 0, 2, Constants.MINESWEEPER));
    assertEquals(Constants.SEA, p1.hit(0, 0, p2, false)); // changed to SEA because it was sunk
    assertEquals(Constants.SEA, p1.hit(1, 0, p2, false));

    // Place vertical ship
    p2.placeShip(5, 5, 5, 6, 2, Constants.MINESWEEPER);
    assertEquals(Constants.SEA, p1.hit(5, 5, p2, false));
    assertEquals(Constants.SEA, p1.hit(5, 6, p2, false));

    // Error when place ship diagonally
    assertEquals(Constants.ERROR, p2.placeShip(0, 0, 1, 1, 2, Constants.MINESWEEPER));

    // Error when length of ship doesn't match distance between corrdinates
    assertEquals(Constants.ERROR, p2.placeShip(3, 0, 7, 0, 2, Constants.MINESWEEPER));
  }

  @Test
  public void testHit() {
    p2.placeShip(0, 0, 1, 0, 2, Constants.MINESWEEPER);
    // No laser, using bombs
    assertEquals(Constants.SEA, p1.hit(0, 0, p2, false)); // Hit captains quarter's
    assertEquals(Constants.SEA, p1.hit(1, 0, p2, false));
    assertEquals(Constants.SEA, p1.hit(0, 1, p2, false));
    p2.getB().printBoard();
    p2.placeShip(1, 0, 1, 3, 5, Constants.SUBMARINE);
    p2.getB().printBoard();
    assertEquals(Constants.SUB_UNDER_WATER, p1.hit(1, 1, p2, false));

    // Using laser
    assertEquals(Constants.SEA, p1.hit(1, 0, p2, true));
    assertEquals(Constants.SUB_UNDER_WATER, p1.hit(1, 3, p2, true)); // CHQ
    assertEquals(Constants.SEA, p1.hit(1, 0, p2, true));

    p2.placeShip(0, 8, 2, 8, 3, Constants.DESTROYER);
    p2.placeShip(0, 8, 0, 5, 5, Constants.SUBMARINE);
    assertEquals(Constants.SEA, p1.hit(0, 8, p2, true));
  }

  @Test
  public void testLookupRecord() {
    p1.addRecord(0, 0, Constants.SHIP);
    assertEquals(Constants.SHIP, p1.lookupRecord(0, 0));
    assertEquals(Constants.ERROR, p1.lookupRecord(2, 1));
  }

  @Test
  public void testAddRecord() {
    assertTrue(p1.addRecord(1, 1, Constants.SEA));
    assertFalse(p1.addRecord(1, 1, Constants.SEA));
  }

  @Test
  public void testErrorB2Bomber() {
    HashMap<Integer, Point> expected = new HashMap<Integer, Point>();
    HashMap<Integer, Point> actual = p1.b2Bomber(p2);
    assertTrue(expected.equals(actual));
  }
}
