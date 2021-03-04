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

}