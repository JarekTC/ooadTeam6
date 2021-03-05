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
        b.setCoord(0, 0, Constants.SEA);
        assertEquals(Constants.SEA, b.getCoord(0, 0));

        b.setShip(0, 2, 4, 2, 4, Constants.BATTLESHIP);
        b.setShip(0, 3, 3, 3, 3, Constants.DESTROYER);
        b.setShip(0, 4, 2, 4, 2, Constants.MINESWEEPER);

        // Should get a 0 when access coordinate where there are no ships
        assertEquals(Constants.SEA, b.getCoord(0, 9));

        // Should get a 1 when access coordinate where there is a ships
        assertEquals(Constants.SHIP, b.getCoord(0, 2));
        assertEquals(Constants.SHIP, b.getCoord(0, 3));
        assertEquals(Constants.SHIP, b.getCoord(0, 4));

    }

    @Test
    public void testSetCoord() {
        b.setCoord(0, 0, Constants.SEA);
        assertEquals(Constants.SEA, b.getCoord(0, 0));
    }

    @Test
    public void testSetShip() {
        // Place horizontal ship
        assertEquals(Constants.NONEERROR, b.setShip(0, 0, 2, 0, 3, Constants.MINESWEEPER));

        // Error when place ship diagonally
        assertEquals(Constants.ERROR, b.setShip(0, 0, 1, 1, 1, Constants.MINESWEEPER));

        //Error when length of ship doesn't match distance between corrdinates
        assertEquals(Constants.ERROR, b.setShip(3, 0, 7, 0, 3, Constants.MINESWEEPER));

        //Error when try to place ship that will overlap with another ship
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
        assertTrue(b.getShipLocations(new Point(0, 0)) instanceof MineSweeper);
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

}