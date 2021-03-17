package edu.colorado.team6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PerksTest {
  private Board b;
  private Perks p;

  @BeforeEach
  public void setUp() {
    b = new Board();
    p = new Perks(b);
  }

  @Test
  public void testSonar() {
    HashMap<Point, Integer> radar = new HashMap<Point, Integer>(p.sonar(new Point(3, 3)));
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
}
