package edu.colorado.team6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board b;

    @BeforeEach
    public void setUp() {
        int[][] board = new int[10][10];
        b = new Board(board);
    }

    @Test
    public void testGetBoard() {
        b.getBoard();
    }
}
