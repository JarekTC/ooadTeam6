package edu.colorado.team6;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Janitor {

  public void cleanupOnAisle5(Board b, Ship s, ArrayList<Point> positions, String ship) {
    // remove locations from the board
    for (int i = 0; i < positions.size(); i++) {
      Point coord = positions.get(i);
      int x = coord.x;
      int y = coord.y;
      int label = b.getCoord(x, y);
      if (s instanceof Submarine) {
        if (label == Constants.SUB_UNDER_WATER) {
          b.setCoord(x, y, Constants.SEA);
        } else {
          b.setCoord(x, y, Constants.SHIP);
        }
      } else {
        if (label == Constants.SHIP_ON_TOP_SUB) {
          b.setCoord(x, y, Constants.SUB_UNDER_WATER);
        } else {
          b.setCoord(x, y, Constants.SEA);
        }
      }
    }
    // clean hashmap
    HashMap<Point, ArrayList<Ship>> locations = b.getShipLocations();
    for (int i = 0; i < positions.size(); i++) {
      locations.get(positions.get(i)).remove(s);
    }

    // Clean up master
    b.getMasterOrientation().put(ship, new ArrayList<Point>());
  }
}
