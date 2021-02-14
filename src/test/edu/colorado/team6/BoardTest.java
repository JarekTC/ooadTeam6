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
    assertArrayEquals(new int[10][10], b.getBoard());
  }

  @Test
  public void testSetBoard() {
    int[][] testBoard = new int[10][10];
    testBoard[0][0] = 1;
    b.setBoard(testBoard);
    assertArrayEquals(testBoard, b.getBoard());
  }
}
