package edu.colorado.team6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PerksTest {
  private Perks p;
  private Player p1;
  private Janitor j;

  @BeforeEach
  public void setUp() {
    p = new Perks();
    p1 = new Player("Jareel");
    j = new Janitor();
  }

  @Test
  public void testSonar() {
    HashMap<Point, Integer> radar = new HashMap<Point, Integer>(p.sonar(new Point(3, 3), p1.getB()));

    HashMap<Point, Integer> expected = new HashMap<Point, Integer>();
    expected.put(new Point(3, 4), 0);
    expected.put(new Point(3, 5), 0);

    expected.put(new Point(3, 2), 0);
    expected.put(new Point(3, 1), 0);

    expected.put(new Point(4, 3), 0);
    expected.put(new Point(5, 3), 0);

    expected.put(new Point(2, 3), 0);
    expected.put(new Point(1, 3), 0);

    expected.put(new Point(4, 4), 0);
    expected.put(new Point(4, 2), 0);
    expected.put(new Point(2, 2), 0);
    expected.put(new Point(2, 4), 0);
  }

  @Test
  public void testMoveFleet() {
    p1.placeShip(0, 0, 1, 0, 2, Constants.MINESWEEPER);
    p1.placeShip(7, 9, 9, 9, 3, Constants.DESTROYER);
    p1.placeShip(3, 4, 3, 7, 4, Constants.BATTLESHIP);
    p1.placeShip(3, 5, 6, 5, 5, Constants.SUBMARINE);
    p1.getB().printBoard();
    System.out.println();

    //Move ships, where none are overlapping
    ArrayList<String> movedS = new ArrayList<String>();
    movedS.add(Constants.MINESWEEPER);
    movedS.add(Constants.BATTLESHIP);
    movedS.add(Constants.SUBMARINE);
    ArrayList<String> actual1 = p1.moveFleet('N');

    for(int i = 0; i < movedS.size(); i++) {
      assertTrue(movedS.contains(actual1.get(i)));
    }

    ArrayList<String> actual2 = p1.moveFleet('E');

    for(int i = 0; i < movedS.size(); i++) {
      assertTrue(movedS.contains(actual2.get(i)));
    }

    ArrayList<String> actual3 = p1.moveFleet('S');

    for(int i = 0; i < movedS.size(); i++) {
      assertTrue(movedS.contains(actual3.get(i)));
    }

    ArrayList<String> actual4 = p1.moveFleet('W');

    for(int i = 0; i < movedS.size(); i++) {
      assertTrue(movedS.contains(actual4.get(i)));
    }
    p1.getB().printBoard();
  }

}
//0 0 0 0 0 0 0 1 1 1
//0 0 0 0 0 0 0 0 0 0
//0 0 0 1 0 0 0 0 0 0
//0 0 0 1 0 2 0 0 0 0
//0 0 0 3 2 2 2 0 0 0
//0 0 0 1 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0 0
//0 0 0 0 2 0 0 0 0 0
//1 1 2 2 2 2 0 0 0 0
//
//0 0 0 0 0 0 0 1 1 1
//0 0 0 0 0 0 0 0 0 0
//0 0 0 1 0 0 0 0 0 0
//0 0 0 1 0 2 0 0 0 0
//0 0 0 3 2 2 2 0 0 0
//0 0 0 1 2 0 0 0 0 0
//1 1 2 2 2 2 0 0 0 0
//1 1 0 0 0 0 0 0 0 0
//1 1 0 0 0 0 0 0 0 0
//1 1 0 0 0 0 0 0 0 0
