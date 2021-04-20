package edu.colorado.team6;

public class Destroyer extends Ship {
  public Destroyer() {

    super(Constants.DESTROYER);

    initializeCorrectHealth(3);

    setCaptainsQuarters(1);
  }
}
