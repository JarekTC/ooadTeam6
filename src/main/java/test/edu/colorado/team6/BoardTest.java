package edu.colorado.team6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
  private Board b;


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
  public void testGetBoard() {
    int[][] board = new int[10][10];
    assertArrayEquals(board, b.getBoard());
  }

  @Test
  void testGetStandardIndex() {
    b.setShip(0, 0, 0, 1, 2, Constants.MINESWEEPER);
    assertEquals(0, b.getStandardIndex(0, 0, 0));

    b.setShip(0, 3, 2, 3, 3, Constants.DESTROYER);
    assertEquals(1, b.getStandardIndex(1, 3, 0));

    b.setShip(
        5,
        8,
        5,
        5,
        4,
        Constants.BATTLESHIP); // First coordinate is left section of ship as displayed in writeup
    assertEquals(1, b.getStandardIndex(5, 7, 0));

    // Set submarine that is partially under battleship
    b.setSub(5, 5, 8, 5, 5, Constants.SUBMARINE);
    b.printBoard();
    assertEquals(3, b.getStandardIndex(8, 5, 0));
    assertEquals(0, b.getStandardIndex(5, 5, 1));
  }

  //  @Test
  //  void testGetOverlapIndex() {}

  @Test
  void testBombApplyDamage() {
    b.setShip(5, 5, 8, 5, 4, Constants.BATTLESHIP);
    b.setSub(5, 5, 8, 5, 5, Constants.SUBMARINE);
    assertEquals(0, b.bombApplyDamage(5, 5));
  }

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
    assertEquals(Constants.NONEERROR, b.setSub(2, 8, 5, 8, 5, Constants.SUBMARINE));

    // Place horizontal ship (coordinates largest to smallest)
    assertEquals(Constants.NONEERROR, b.setShip(2, 8, 4, 8, 3, Constants.DESTROYER));

    // Horizontal ship smallest to largest coordinates, ship on top of ship error
    assertEquals(Constants.ERROR, b.setShip(1, 0, 2, 0, 2, Constants.MINESWEEPER));
    b.setCoord(2, 0, Constants.SEA);

    // Horizontal ship largest to smallest coordinates, ship on top of ship error
    assertEquals(Constants.ERROR, b.setShip(2, 0, 1, 0, 2, Constants.MINESWEEPER));
    b.setCoord(2, 0, Constants.SEA);

    // Horizontal ship under sub, largest to smallest coordinates
    b.setSub(0, 2, 3, 2, 5, Constants.SUBMARINE);
    assertEquals(Constants.NONEERROR, b.setShip(4, 2, 3, 2, 2, Constants.MINESWEEPER));

    // Vertical ship. Smallest to largest coordinates
    assertEquals(Constants.NONEERROR, b.setShip(0, 2, 0, 3, 2, Constants.MINESWEEPER));

    // Place ship vertically on top of existing ship. Largest to smallest coordinates.
    assertEquals(Constants.ERROR, b.setShip(0, 4, 0, 3, 2, Constants.MINESWEEPER));
    b.setCoord(0, 4, Constants.SEA);

    // Vertical ship smallest to largest. Error stacked ships
    assertEquals(Constants.ERROR, b.setShip(0, 3, 0, 4, 2, Constants.MINESWEEPER));
    b.setCoord(0, 4, Constants.SEA);

    // Ship on top of ship
    assertEquals(Constants.ERROR, b.setShip(4, 8, 4, 6, 3, Constants.DESTROYER));
    // Clean up set coordinates from error ship
    b.setCoord(4, 6, Constants.SEA);
    b.setCoord(4, 7, Constants.SEA);

    // Vertical ship. Largest to smallest. Place ship above sub
    assertEquals(Constants.NONEERROR, b.setShip(1, 3, 1, 2, 2, Constants.MINESWEEPER));

    // Error, because ship already placed at 0,0
    assertEquals(Constants.ERROR, b.setShip(1, 0, 0, 0, 2, Constants.MINESWEEPER));

    // Error when place ship diagonally
    assertEquals(Constants.ERROR, b.setShip(0, 0, 1, 1, 1, Constants.MINESWEEPER));

    // Error when length of ship doesn't match distance between coordinates
    assertEquals(Constants.ERROR, b.setShip(3, 0, 7, 0, 2, Constants.MINESWEEPER));

    // Error when try to place ship that will overlap with another ship
    assertEquals(Constants.ERROR, b.setShip(0, 0, 0, 4, 4, Constants.BATTLESHIP));
    assertEquals(Constants.ERROR, b.setShip(0, 0, 4, 0, 4, Constants.BATTLESHIP));

    // Error when use setShip with submarine
    assertEquals(Constants.ERROR, b.setShip(0, 5, 3, 5, 5, Constants.SUBMARINE));
    b.printBoard();
  }

  @Test
  public void testSetSub() {
    // Error when use setSub with non-submarine ship
    assertEquals(Constants.ERROR, b.setSub(0, 0, 1, 0, 2, Constants.MINESWEEPER));

    // Place horizontal sub on top of sub
    b.setSub(0, 0, 3, 0, 5, Constants.SUBMARINE);
    assertEquals(Constants.ERROR, b.setSub(3, 0, 6, 0, 5, Constants.SUBMARINE));

    // Place sub, where extra part is under an existing ship
    b.setShip(2, 4, 3, 4, 2, Constants.MINESWEEPER);
    assertEquals(Constants.NONEERROR, b.setSub(0, 3, 3, 3, 5, Constants.SUBMARINE));

    // Horizontal sub. Largest to smallest coordinates
    assertEquals(Constants.NONEERROR, b.setSub(3, 5, 0, 5, 5, Constants.SUBMARINE));

    // Horizontal sub under ship. Largest to smallest coordinates
    b.setShip(0, 7, 1, 7, 2, Constants.MINESWEEPER);
    assertEquals(Constants.NONEERROR, b.setSub(3, 7, 0, 7, 5, Constants.SUBMARINE));

    // Horizontal sub on top of existing sub. Largest to smallest
    assertEquals(Constants.ERROR, b.setSub(6, 7, 3, 7, 5, Constants.SUBMARINE));
    b.setCoord(6, 7, Constants.SEA);
    b.setCoord(5, 7, Constants.SEA);
    b.setCoord(4, 7, Constants.SEA);

    // Horizontal arm . largest to smallest
    b.setShip(7, 7, 8, 7, 2, Constants.MINESWEEPER);
    assertEquals(Constants.NONEERROR, b.setSub(9, 8, 6, 8, 5, Constants.SUBMARINE));

    // vertical ship sub overlap (smallest to largest)
    assertEquals(Constants.NONEERROR, b.setSub(8, 4, 8, 7, 5, Constants.SUBMARINE));

    // vertical sub sub overlap (small to large)
    assertEquals(Constants.ERROR, b.setSub(8, 4, 8, 7, 5, Constants.SUBMARINE));

    // vertical sub sub overlap (small to large)
    b.setShip(5, 6, 6, 6, 2, Constants.MINESWEEPER);
    assertEquals(Constants.NONEERROR, b.setSub(6, 4, 6, 7, 5, Constants.SUBMARINE));

    // vertical large to small, sub-sub overlap
    assertEquals(Constants.ERROR, b.setSub(8, 7, 8, 4, 5, Constants.SUBMARINE));

    // vertical large to small, sub-ship overlap
    b.setShip(7, 1, 8, 1, 2, Constants.MINESWEEPER);
    assertEquals(Constants.NONEERROR, b.setSub(7, 3, 7, 0, 5, Constants.SUBMARINE));
    b.printBoard();
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

    assertEquals(Constants.NONEERROR, b.setShipArray(0, 2, 4, 2, Constants.BATTLESHIP));
    assertEquals(Constants.NONEERROR, b.setShipArray(0, 3, 3, 3, Constants.DESTROYER));
    assertEquals(Constants.NONEERROR, b.setShipArray(0, 4, 2, 4, Constants.MINESWEEPER));
    assertEquals(Constants.ERROR, b.setShipArray(0, 4, 2, 8, "randomString"));
  }

  @Test
  public void testDiagonal() {
    // diagonalBoundsCheck(int x1, int y1, int x2, int y2)
    assertEquals(Constants.ERROR, b.diagonalBoundsCheck(0, 0, 2, 2));
    assertEquals(Constants.NONEERROR, b.diagonalBoundsCheck(0, 0, 0, 2));
    assertEquals(Constants.ERROR, b.diagonalBoundsCheck(3, 3, 0, 0));
  }

  @Test
  public void testLengthCheck() {
    assertEquals(Constants.NONEERROR, b.lengthCheck(0, 1, 2, 1, 3));
    assertEquals(Constants.ERROR, b.lengthCheck(0, 1, 6, 1, 4));
  }

  @Test
  public void testOutOfBounds() {
    assertEquals(Constants.ERROR, b.outOfBoundsCheck(0, 10, 0, 7, Constants.BATTLESHIP));
    assertEquals(Constants.NONEERROR, b.outOfBoundsCheck(0, 9, 0, 6, Constants.BATTLESHIP));
    assertEquals(Constants.ERROR, b.outOfBoundsCheck(0, 1, 0, 4, Constants.SUBMARINE));
    assertEquals(Constants.NONEERROR, b.outOfBoundsCheck(0, 9, 0, 6, Constants.SUBMARINE));
    assertEquals(Constants.ERROR, b.outOfBoundsCheck(8, 9, -9, 8, Constants.BATTLESHIP));
    assertEquals(Constants.ERROR, b.outOfBoundsCheck(0, 9, 3, 9, Constants.SUBMARINE));
    assertEquals(Constants.ERROR, b.outOfBoundsCheck(9, 9, 9, 6, Constants.SUBMARINE));
    assertEquals(Constants.ERROR, b.outOfBoundsCheck(3, 0, 0, 0, Constants.SUBMARINE));
  }
}
