package edu.colorado.team6;

import org.junit.jupiter.api.Test;

public class ShipTest {
    @Test
    public void canMakeShip() {
        new Ship("TestShip");
    }

    @Test
    public void canSetHealth() {
        Ship ship = new Ship("TestShip");
        ship.initializeCorrectHealth(3);
    }

    @Test
    public void canTakeDamage() {
        Ship ship = new Ship("TestShip");
        ship.takeDamage();
    }

    @Test
    public void hasTypeName() {
        Ship ship = new Ship("TestShip");
        ship.showShipType();
    }


}
