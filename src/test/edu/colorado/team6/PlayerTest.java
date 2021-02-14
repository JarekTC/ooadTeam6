package edu.colorado.team6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player p;
    private String name = "Neefan";

    @BeforeEach
    public void setUp() {
        p = new Player(name);
    }

    @Test
    public void testSetName() {
        assertEquals(name, p.getName());
    }

    @Test
    public void testGetScore() {
        assertEquals(0, p.getScore());
    }

    @Test
    public void testSetScore() {
        int testScore = 90;
        p.setScore(90);

        assertEquals(testScore, p.getScore());
    }

}