package edu.colorado.team6;

import org.junit.jupiter.api.Test;

public class MineSweeperTest {
    @Test
    public void canMakeMineSweeper(){
        new MineSweeper("MineSweeper");
    }

    @Test
    public void canTakeDamage(){
        MineSweeper mine1 = new MineSweeper("MineSweeper");
        mine1.takeDamage();
    }

    @Test
    public void initializeHealthTest(){
        MineSweeper mine2 = new MineSweeper("MineSweeper");
        mine2.initializeCorrectHealth(2);
    }
}
