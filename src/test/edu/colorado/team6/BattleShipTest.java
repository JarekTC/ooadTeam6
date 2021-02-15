package edu.colorado.team6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleShipTest {
    @Test
    public void canMakeBattleShip(){
        new BattleShip();
    }

    @Test
    public void canTakeDamage(){
        BattleShip b1 = new BattleShip();
        b1.takeDamage();
    }

    @Test
    public void initializeHealthTest(){
        BattleShip b2 = new BattleShip();
        b2.initializeCorrectHealth(4);
    }

}