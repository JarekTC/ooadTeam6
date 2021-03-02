package edu.colorado.team6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board b;
    private final int SHIP = 1;
    private final int SEA = 0;
    private final int ERROR = -1;
    private final int NONERROR = 1;

    @BeforeEach
    public void setUp() {
        b = new Board();
    }


    @Test
    public void testGetCoord() {
        b.setCoord(0, 0, SEA);
        assertEquals(SEA, b.getCoord(0, 0));

    }

    @Test
    public void testSetCoord() {
        b.setCoord(0, 0, SEA);
        assertEquals(SEA, b.getCoord(0, 0));
    }

    @Test
    public void testSetShip() {
        // Place horizontal ship
        assertEquals(NONERROR, b.setShip(0, 0, 2, 0, 3));

        // Error when place ship diagonally
        assertEquals(ERROR, b.setShip(0, 0, 1, 1, 1));

        //Error when length of ship doesn't match distance between corrdinates
        assertEquals(ERROR, b.setShip(3, 0, 7, 0, 3));
    }
}