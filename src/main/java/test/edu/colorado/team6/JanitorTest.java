package edu.colorado.team6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class JanitorTest {
  private Player p1;
  private Janitor j;
  private MineSweeper ms;
  private Destroyer ds;
  private BattleShip bs;
  private Submarine ss;

  @BeforeEach
  public void setUp() {
    p1 = new Player("Jareel");
    j = new Janitor();
    ms = new MineSweeper();
    ds = new Destroyer();
    bs = new BattleShip();
    ss = new Submarine();
  }

  @Test
  void testCleanupOnAisle5() {
    p1.placeShip(0, 0, 1, 0, 2, Constants.MINESWEEPER);
    p1.placeShip(7, 9, 9, 9, 3, Constants.DESTROYER);
    p1.placeShip(3, 4, 3, 7, 4, Constants.BATTLESHIP);
    p1.placeShip(3, 5, 6, 5, 5, Constants.SUBMARINE);

    p1.getB().printBoard();
    System.out.println();

    j.cleanupOnAisle5(p1.getB(), ms, p1.getB().getMsOrientation(), Constants.MINESWEEPER);
    j.cleanupOnAisle5(p1.getB(), ds, p1.getB().getDsOrientation(), Constants.DESTROYER);
    j.cleanupOnAisle5(p1.getB(), bs, p1.getB().getBsOrientation(), Constants.BATTLESHIP);
    j.cleanupOnAisle5(p1.getB(), ss, p1.getB().getSsOrientation(), Constants.SUBMARINE);
    p1.getB().printBoard();
  }
}
