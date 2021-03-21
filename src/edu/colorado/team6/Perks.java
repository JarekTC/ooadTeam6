package edu.colorado.team6;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Perks {
  //    private
//  private Board b;
//
//  Perks(Board b) {
//    this.b = b;
//  }
//  ;
  Janitor j;

  public HashMap<Point, Integer> sonar(Point coord, Board b) {
    int x = coord.x;
    int y = coord.y;
    HashMap<Point, Integer> radar = new HashMap<Point, Integer>();
    // up
    if (y < 9) {
      if (y < 8) {
        radar.put(new Point(x, y + 2), b.getPos(x, y + 2));
      }
      radar.put(new Point(x, y + 1), b.getPos(x, y + 1));
    }
    // south
    if (y > 1) {
      if (y > 2) {
        radar.put(new Point(x, y - 2), b.getPos(x, y - 2));
      }
      radar.put(new Point(x, y - 1), b.getPos(x, y - 1));
    }
    // west
    if (x > 1) {
      if (x > 2) {
        radar.put(new Point(x - 2, y), b.getPos(x - 2, y));
      }
      radar.put(new Point(x - 1, y), b.getPos(x - 1, y));
    }
    // east
    if (x < 9) {
      if (x < 8) {
        radar.put(new Point(x + 2, y), b.getPos(x + 2, y));
      }
      radar.put(new Point(x + 1, y), b.getPos(x + 1, y));
    }
    // diagonals
    // north west
    if ((y < 9) && (x > 1)) {
      radar.put(new Point(x - 1, y + 1), b.getPos(x - 1, y + 1));
    }
    // north east
    if ((y < 9) && (x < 9)) {
      radar.put(new Point(x + 1, y + 1), b.getPos(x + 1, y + 1));
    }
    // south west
    if ((y > 1) && (x > 1)) {
      radar.put(new Point(x - 1, y - 1), b.getPos(x - 1, y - 1));
    }
    // south east
    if ((y > 1) && (x < 9)) {
      radar.put(new Point(x + 1, y - 1), b.getPos(x + 1, y - 1));
    }
    return radar;
  }

  public ArrayList<String> moveFleet(Board b, char direction){
    // for each ship, increment in direction
    // then check bounds, if no error, implement changes
    // otherwise don't modify
    HashMap<String,ArrayList<Point>> master = b.getMasterOrientation();
    Iterator it = master.entrySet().iterator();
    ArrayList<String> movedShips = new ArrayList<String>();
    while(it.hasNext()){
      Map.Entry pair = (Map.Entry)it.next();
      String ship = (String)pair.getKey();
      ArrayList<Point> shipCoord = (ArrayList<Point>)pair.getValue();
      //if not submarine
      int len = shipCoord.size();
      if(!ship.equals(Constants.SUBMARINE)){
        Point start = shipCoord.get(0);
        Point end = shipCoord.get(len - 1);
        int x1 = start.x;
        int y1 = start.y;
        int x2 = end.x;
        int y2 = end.y;
        //check bounds
        int ok = b.outOfBoundsCheck(x1,y1,x2,y2,ship);
        //move ship
        if (ok == Constants.NONEERROR){
          Ship s = b.getShipLocations(start).get(0);
          //call cleanup !!!!!!!!!!!!!!!!!!!!!!!!!!!
          j.cleanupOnAisle5(b,s,shipCoord);
          //set new ship
          int health = s.getShipHealth();
          b.setShip(x1,y1,x2,y2, health,ship);
          movedShips.add(ship);
        }
      }
      else{
        Point start = shipCoord.get(0);
        Point end = shipCoord.get(len - 2);
        int x1 = start.x;
        int y1 = start.y;
        int x2 = end.x;
        int y2 = end.y;
        //check bounds
        int ok = b.outOfBoundsCheck(x1,y1,x2,y2,ship);
        //move ship
        if (ok == Constants.NONEERROR){
          //set new ship
          ArrayList<Ship> shipsThere = b.getShipLocations(start);
          //check where sub is
          int access;
          if(shipsThere.size() == 1){
            access = 0;
          }
          else{
            access = 1;
          }
          Ship s = shipsThere.get(access);
          //call cleanup !!!!!!!!!!!!!!!!!!!!!!!!!!!
          j.cleanupOnAisle5(b,s,shipCoord);
          //move
          int health = s.getShipHealth();
          b.setSub(x1,y1,x2,y2, health,ship);
          movedShips.add(ship);
        }
      }
    }
    return movedShips;
  }
}







