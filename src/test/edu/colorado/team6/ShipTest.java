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
        ship.initializeCorrectHealth(3);
        ship.takeDamage(1);
    }

    @Test
    public void hitEmptySpot() {
        Ship ship = new Ship("TestShip");
        ship.initializeCorrectHealth(3);
        ship.takeDamage(1);
        ship.takeDamage(1);
    }

    @Test
    public void hasTypeName() {
        Ship ship = new Ship("TestShip");
        ship.showShipType();
    }

    @Test
    public void canViewHealth() {
        Ship ship = new Ship("TestShip");
        ship.getShipHealth();
    }

    @Test
    public void hasCaptainsQuarters(){
        Ship ship = new Ship("TestShip");
        ship.initializeCorrectHealth(3);
        ship.setCaptainsQuarters(1);
    }

    @Test
    public void canViewCaptainsQuarters(){
        Ship ship = new Ship("TestShip");
        ship.initializeCorrectHealth(3);
        ship.setCaptainsQuarters(1);
        ship.getCaptainsQuarters();
    }

    @Test
    public void captainsQuartersHit(){
        Ship ship = new Ship("TestShip");
        ship.setCaptainsQuarters(1);
        ship.takeDamage(1);
        ship.takeDamage(1);
    }
}
