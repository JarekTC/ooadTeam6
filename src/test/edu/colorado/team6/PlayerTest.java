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
  private Player.Record r1;
  private Player.Record r2;
  private final int SHIP = 1;
  private final int SEA = 0;
  private final int HIT = 1;
  private final int MISS = 0; //Remove HIT and MISS. Replace variables with SHIP and SEA accordingly
  private final int ERROR = -1;
  private final int NONERROR = 1;
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @BeforeEach
  public void setUp() {
    r1 = new Player.Record(0, 0, HIT);
    r2 = new Player.Record(0, 1, MISS);
    p1 = new Player(name1, r1);
    p2 = new Player(name2, r2);
  }

  @Test
  public void testGetRecord() {
    assertArrayEquals(new int[]{0, 0, HIT}, r1.getRecord());
  }

  @Test
  public void testGetHitMiss() {
    assertEquals(MISS, r2.getHitMiss());
  }

  @Test
  public void testSetHitMiss() {
    r2.setHitMiss(HIT);
    assertEquals(HIT, r2.getHitMiss());
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
  public void testSetShip() {
    // Place horizontal ship
    p2.setShip(0, 0, 2, 0, SHIP);
    assertEquals(SHIP, p1.hit(0, 0, p2));
    assertEquals(SHIP, p1.hit(1, 0, p2));
    assertEquals(SHIP, p1.hit(2, 0, p2));

    // Place vertical ship
    p2.setShip(0, 0, 0, 2, SHIP);
    assertEquals(SHIP, p1.hit(0, 0, p2));
    assertEquals(SHIP, p1.hit(0, 1, p2));
    assertEquals(SHIP, p1.hit(0, 2, p2));

    // Error when set board position to non 0, or 1 label
    assertEquals(ERROR, p2.setShip(0, 0, 2, 0, 90));

    // Error when place ship diagonally
    assertEquals(ERROR, p2.setShip(0, 0, 1, 1, 1));

  }

  @Test
  public void testHit() {
    p2.setShip(0, 0, 2, 0, HIT);
    assertEquals(HIT, p1.hit(0, 0, p2));
    assertEquals(HIT, p1.hit(1, 0, p2));
    assertEquals(HIT, p1.hit(2, 0, p2));
    assertEquals(MISS, p1.hit(0, 1, p2));
  }

  @Test
  public void testLookupRecord() {
    int x = p1.lookupRecord(400, 400).getRecord()[0];
    int y = p1.lookupRecord(400, 400).getRecord()[1];
    int hitMiss = p1.lookupRecord(400, 400).getRecord()[2];

    assertEquals(-1, x);
    assertEquals(-1, y);
    assertEquals(-1, hitMiss);
  }

  @Test
  public void testAddRecord() {
    // Add record
    Player.Record r = new Player.Record(0, 1, HIT);
    p1.addRecord(0, 1, HIT);

    int x = p1.lookupRecord(0, 1).getRecord()[0];
    int y = p1.lookupRecord(0, 1).getRecord()[1];
    int hitMiss = p1.lookupRecord(0, 1).getRecord()[2];

    assertEquals(0, x);
    assertEquals(1, y);
    assertEquals(HIT, hitMiss);

    //Try to add record again
    assertEquals(false, p1.addRecord(x, y, hitMiss));

    //Try to add record with same coordinates, but different label
    assertEquals(true, p1.addRecord(x, y, MISS));


  }
}
