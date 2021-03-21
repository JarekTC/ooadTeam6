package edu.colorado.team6;

public class Submarine extends Ship {
  public Submarine() {

    super(Constants.SUBMARINE);

    initializeCorrectHealth(5);

    setCaptainsQuarters(3);
  }
}
