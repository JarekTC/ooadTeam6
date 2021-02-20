package edu.colorado.team6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DestroyerTest {
    @Test
    public void canMakeDestroyer() {
        new Destroyer();
    }

    @Test
    public void canTakeDamage() {
        Destroyer d1 = new Destroyer();
        d1.takeDamage();
    }

    @Test
    public void initializeHealthTest() {
        Destroyer d2 = new Destroyer();
        d2.initializeCorrectHealth(3);
    }

    @Test
    public void canDisplayTypeName() {
        Destroyer d3 = new Destroyer();
        d3.showShipType();
    }

}