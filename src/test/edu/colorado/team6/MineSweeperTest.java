package edu.colorado.team6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MineSweeperTest {
    @Test
    public void canMakeMineSweeper() {
        new MineSweeper();
    }

    @Test
    public void canTakeDamage() {
        MineSweeper mine1 = new MineSweeper();
        mine1.initializeCorrectHealth(3);
        assertEquals(0, mine1.takeDamage(1));
    }

    @Test
    public void initializeHealthTest() {
        MineSweeper mine2 = new MineSweeper();
        assertEquals(3, mine2.initializeCorrectHealth(3));
    }

    @Test
    public void canDisplayTypeName() {
        MineSweeper mine3 = new MineSweeper();
        assertEquals(Constants.MINESWEEPER, mine3.showShipType());
    }

    @Test
    public void canViewHealth() {
        MineSweeper m4 = new MineSweeper();
        m4.initializeCorrectHealth(3);
        assertEquals(3, m4.getShipHealth());
    }

    @Test
    public void hasCaptainsQuarters(){
        MineSweeper m5 = new MineSweeper();
        m5.initializeCorrectHealth(3);
        assertEquals(1, m5.setCaptainsQuarters(1));
    }

    @Test
    public void canViewCaptainsQuarters(){
        MineSweeper m6 = new MineSweeper();
        m6.initializeCorrectHealth(3);
        m6.setCaptainsQuarters(1);
        assertEquals(1, m6.getCaptainsQuarters());
    }

    @Test
    public void captainsQuartersHit(){
        MineSweeper m7 = new MineSweeper();
        m7.initializeCorrectHealth(3);
        m7.setCaptainsQuarters(1);
        assertEquals(0, m7.takeDamage(1));
        assertEquals(0, m7.getShipHealth());
    }
}
