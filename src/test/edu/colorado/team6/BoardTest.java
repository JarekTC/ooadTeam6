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
    void testGetCoord() {
        b.setCoord(0, 0, SEA);
        assertEquals(SEA, b.getCoord(0, 0));

    }

    @Test
    void testSetCoord() {
        b.setCoord(0, 0, SEA);
        assertEquals(SEA, b.getCoord(0, 0));
    }
}