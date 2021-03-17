package edu.colorado.team6;

import org.junit.jupiter.api.Test;

public class MineSweeperTest {
    @Test
    public void canMakeMineSweeper() {
        new MineSweeper();
    }

    @Test
    public void canTakeDamage() {
        MineSweeper mine1 = new MineSweeper();
        mine1.takeDamage(1);
    }

    @Test
    public void initializeHealthTest() {
        MineSweeper mine2 = new MineSweeper();
        mine2.initializeCorrectHealth(2);
    }

    @Test
    public void canDisplayTypeName() {
        MineSweeper mine3 = new MineSweeper();
        mine3.showShipType();
    }

    @Test
    public void canViewHealth() {
        MineSweeper m4 = new MineSweeper();
        m4.getShipHealth();
    }

    @Test
    public void hasCaptainsQuarters(){
        MineSweeper m5 = new MineSweeper();
        m5.initializeCorrectHealth(3);
        m5.setCaptainsQuarters(1);
    }

    @Test
    public void canViewCaptainsQuarters(){
        MineSweeper m6 = new MineSweeper();
        m6.initializeCorrectHealth(3);
        m6.setCaptainsQuarters(1);
        m6.getCaptainsQuarters();
    }

    @Test
    public void captainsQuartersHit(){
        MineSweeper m7 = new MineSweeper();
        m7.initializeCorrectHealth(3);
        m7.setCaptainsQuarters(1);
        m7.takeDamage(1);
    }
}
