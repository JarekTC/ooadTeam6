package edu.colorado.team6;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
  private Player p1;
  private Player p2;
  private String name1 = "Neefan";
  private String name2 = "Sefeel";
  private final int SHIP = 1;
  private final int SEA = 0;
  private final int ERROR = -1;
  private final int NONERROR = 1;
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

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
    assertEquals(NONERROR, p2.placeShip(0, 0, 2, 0, 3));
    assertEquals(SHIP, p1.hit(0, 0, p2));
    assertEquals(SHIP, p1.hit(1, 0, p2));
    assertEquals(SHIP, p1.hit(2, 0, p2));

    // Place vertical ship
    p2.placeShip(0, 0, 0, 2, 3);
    assertEquals(SHIP, p1.hit(0, 0, p2));
    assertEquals(SHIP, p1.hit(0, 1, p2));
    assertEquals(SHIP, p1.hit(0, 2, p2));

    // Error when place ship diagonally
    assertEquals(ERROR, p2.placeShip(0, 0, 1, 1, 2));

    //Error when length of ship doesn't match distance between corrdinates
    assertEquals(ERROR, p2.placeShip(3, 0, 7, 0, 3));

  }

  @Test
  public void testHit() {
    p2.placeShip(0, 0, 2, 0, 3);
    assertEquals(SHIP, p1.hit(0, 0, p2));
    assertEquals(SHIP, p1.hit(1, 0, p2));
    assertEquals(SHIP, p1.hit(2, 0, p2));
    assertEquals(SEA, p1.hit(0, 1, p2));
  }

  @Test
  public void testLookupRecord() {
    p1.addRecord(0, 0, SHIP);
    assertEquals(SHIP, p1.lookupRecord(0, 0));
    assertEquals(ERROR, p1.lookupRecord(2, 1));
  }

  @Test
  public void testAddRecord() {
    assertTrue(p1.addRecord(1, 1, SEA));
    assertFalse(p1.addRecord(1, 1, SEA));
  }
}
