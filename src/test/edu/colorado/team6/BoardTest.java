package edu.colorado.team6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board b;

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
        assertEquals(Constants.NONEERROR, b.setShipLocations(0, 0, Constants.BATTLESHIP));
        assertEquals(Constants.ERROR, b.setShipLocations(0, 0, Constants.BATTLESHIP));

    }

    @Test
    public void testGetShipLocation() {
        b.setShipLocations(0, 0);
        assertEquals(Constants.SHIP, b.getShipLocations(0, 0));
    }

}