package edu.colorado.team6;

public class MineSweeper extends Ship {
  public MineSweeper() {

    super(Constants.MINESWEEPER);

    initializeCorrectHealth(2);

    setCaptainsQuarters(0);
  }

  public int captainsQuartersHit(int index) {
    for (int i = 0; i < 2; i++) {
      updateHits(i, 1, 0);
    }
    return updateHealth(0);
  }
}
