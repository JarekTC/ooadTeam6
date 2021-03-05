package edu.colorado.team6;

public class BattleShip extends Ship {
    public BattleShip() {

        super(Constants.BATTLESHIP);

        initializeCorrectHealth(4);

        setCaptainsQuarters(2);
    }
}
