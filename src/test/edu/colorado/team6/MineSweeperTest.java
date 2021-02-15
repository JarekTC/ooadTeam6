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
        MineSweeper.takeDamage(0);
    }
}
