package edu.colorado.team6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
  private Board b;
  private MineSweeper ms = new MineSweeper();
  private Destroyer ds = new Destroyer();
  private BattleShip bs = new BattleShip();

  @BeforeEach
  public void setUp() {
    b = new Board();
  }

  @Test
  public void testGetCoord() {
    b.setCoord(1, 0, Constants.SHIP);
    assertEquals(Constants.SHIP, b.getCoord(1, 0));
    assertEquals(Constants.SEA, b.getCoord(0, 1));
  }

  @Test
  void testGetStandardIndex() {
    b.setShip(0, 0, 0, 1, 2, Constants.MINESWEEPER);
    assertEquals(0, b.getStandardIndex(0, 0, 0));

    b.setShip(0, 3, 2, 3, 3, Constants.DESTROYER);
    assertEquals(1, b.getStandardIndex(1, 3, 0));

    b.setShip(5, 8, 5, 5, 4, Constants.BATTLESHIP); // First coordinate is left section of ship as displayed in writeup
    assertEquals(1, b.getStandardIndex(5, 7, 0));

    // Set submarine that is partially under battleship
    b.setSub(5, 5, 8, 5, 4, Constants.SUBMARINE);
    b.printBoard();
    assertEquals(3, b.getStandardIndex(8, 5, 0));
    assertEquals(0, b.getStandardIndex(5, 5, 1));
  }

//  @Test
//  void testGetOverlapIndex() {}

//  @Test
//  void testBombApplyDamage() {}

  @Test
  public void testSetCoord() {
    b.setCoord(0, 0, Constants.SEA);
    assertEquals(Constants.SEA, b.getCoord(0, 0));
  }

  @Test
  public void testSetShip() {
    // Place horizontal ship
    assertEquals(Constants.NONEERROR, b.setShip(0, 0, 1, 0, 2, Constants.MINESWEEPER));

    // Add submarine, so destroyer at 5, 9 overlaps submarine. USE SPECIAL SUBMARINE ADD FUNCTION
    //assertEquals(Constants.NONEERROR, b.setShip(2, 9, 5, 9, 4, Constants.SUBMARINE));

    // Place horizontal ship (coordinates largest to smallest)
    assertEquals(Constants.NONEERROR, b.setShip(5, 9, 5, 7, 3, Constants.DESTROYER));

    // Error, because ship already placed at 0,0
    assertEquals(Constants.ERROR, b.setShip(1, 0, 0, 0, 2, Constants.MINESWEEPER));

    // Error when place ship diagonally
    assertEquals(Constants.ERROR, b.setShip(0, 0, 1, 1, 1, Constants.MINESWEEPER));

    // Error when length of ship doesn't match distance between coordinates
    assertEquals(Constants.ERROR, b.setShip(3, 0, 7, 0, 2, Constants.MINESWEEPER));

    // Error when try to place ship that will overlap with another ship
    assertEquals(Constants.ERROR, b.setShip(0, 0, 0, 4, 4, Constants.BATTLESHIP));
    assertEquals(Constants.ERROR, b.setShip(0, 0, 4, 0, 4, Constants.BATTLESHIP));
  }

  @Test
  public void testSetShipLocations() {
    assertEquals(Constants.NONEERROR, b.setShipLocations(new Point(0, 0), Constants.MINESWEEPER));
    assertEquals(Constants.ERROR, b.setShipLocations(new Point(0, 0), "randomString"));

    assertEquals(Constants.NONEERROR, b.setShipLocations(new Point(0, 0), Constants.DESTROYER));

    assertEquals(Constants.NONEERROR, b.setShipLocations(new Point(0, 0), Constants.BATTLESHIP));
  }

  @Test
  public void testGetShipLocation() {
    b.setShipLocations(new Point(0, 0), Constants.MINESWEEPER);
    assertTrue(b.getShipLocations(new Point(0, 0)).get(0) instanceof MineSweeper);

    b.setShipLocations(new Point(1, 0), Constants.DESTROYER);
    assertTrue(b.getShipLocations(new Point(1, 0)).get(0) instanceof Destroyer);

    b.setShipLocations(new Point(2, 0), Constants.BATTLESHIP);
    assertTrue(b.getShipLocations(new Point(2, 0)).get(0) instanceof BattleShip);

    b.setShipLocations(new Point(3, 0), Constants.SUBMARINE);
    assertTrue(b.getShipLocations(new Point(3, 0)).get(0) instanceof Submarine);
  }

  @Test
  public void testSetShipArray() {
    b.setShip(0, 2, 4, 2, 4, Constants.BATTLESHIP);
    b.setShip(0, 3, 3, 3, 3, Constants.DESTROYER);
    b.setShip(0, 4, 2, 4, 2, Constants.MINESWEEPER);

    assertEquals(Constants.NONEERROR, b.setShipArray(0, 2, 4, 2, 4, Constants.BATTLESHIP));
    assertEquals(Constants.NONEERROR, b.setShipArray(0, 3, 3, 3, 3, Constants.DESTROYER));
    assertEquals(Constants.NONEERROR, b.setShipArray(0, 4, 2, 4, 2, Constants.MINESWEEPER));
    assertEquals(Constants.ERROR, b.setShipArray(0, 4, 2, 8, 20, "randomString"));
  }

  @Test
  public void testDiagonal() {
    //diagonalBoundsCheck(int x1, int y1, int x2, int y2)
    assertEquals(Constants.ERROR,b.diagonalBoundsCheck(0, 0, 2, 2));
    assertEquals(Constants.NONEERROR,b.diagonalBoundsCheck(0, 0, 0, 2));
    assertEquals(Constants.ERROR,b.diagonalBoundsCheck(3, 3, 0, 0));
  }

  @Test
  public void testLengthCheck() {
    assertEquals(Constants.NONEERROR,b.lengthCheck(0, 1, 2, 1, 3, Constants.DESTROYER));
    assertEquals(Constants.ERROR,b.lengthCheck(0, 1, 6, 1, 4, Constants.BATTLESHIP));
  }

  @Test
  public void testOutOfBounds() {
    assertEquals(Constants.ERROR,b.outOfBoundsCheck(0, 10, 0, 7, Constants.BATTLESHIP));
    assertEquals(Constants.NONEERROR,b.outOfBoundsCheck(0, 9, 0, 6, Constants.BATTLESHIP));
    assertEquals(Constants.ERROR,b.outOfBoundsCheck(0, 1, 0, 4, Constants.SUBMARINE));
    assertEquals(Constants.NONEERROR,b.outOfBoundsCheck(0, 9, 0, 6, Constants.SUBMARINE));
  }

}














