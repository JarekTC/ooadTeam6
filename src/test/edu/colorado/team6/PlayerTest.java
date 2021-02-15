package edu.colorado.team6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player p;
    private String name = "Neefan";
    private Player.Record r;

    @BeforeEach
    public void setUp() {
        r = new Player.Record();
        p = new Player(name, r);
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