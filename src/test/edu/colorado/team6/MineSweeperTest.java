package edu.colorado.team6;

import org.junit.jupiter.api.Test;

public class MineSweeperTest {
    @Test
    public void canMakeMineSweeper(){
        new MineSweeper();
    }

    @Test
    public void canTakeDamage(){
        MineSweeper mine1 = new MineSweeper();
        mine1.takeDamage();
    }

    @Test
    public void initializeHealthTest(){
        MineSweeper mine2 = new MineSweeper();
        mine2.initializeCorrectHealth(2);
    }

    @Test
    public void canDisplayTypeName(){
        MineSweeper mine3 = new MineSweeper();
        mine3.showShipType();
    }
}
