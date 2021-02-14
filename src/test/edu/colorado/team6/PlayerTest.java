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

}