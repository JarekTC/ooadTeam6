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
  Janitor j = new Janitor();

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

    for(Map.Entry<String,ArrayList<Point>> pair: master.entrySet()){
      String ship = (String)pair.getKey();
      ArrayList<Point> shipCoord = (ArrayList<Point>)pair.getValue();
      //if not submarine
      int len = shipCoord.size();
      if(!ship.equals(Constants.SUBMARINE)){
        //TODO:Check whether arraylists in master are empty or not (when have only 1 ship in fleet, get error)
        Point start = shipCoord.get(0);
        Point end = shipCoord.get(len - 1);
        int x1 = start.x;
        int y1 = start.y;
        int x2 = end.x;
        int y2 = end.y;
        //check bounds
        int ok = 0;
        switch(direction) {
          case('N'):
            ok = b.outOfBoundsCheck(x1,y1 + 1,x2,y2 + 1,ship);
            break;
          case('E'):
            ok = b.outOfBoundsCheck(x1 + 1,y1,x2 + 1,y2,ship);
            break;
          case('S'):
            ok = b.outOfBoundsCheck(x1,y1 - 1,x2,y2 - 1,ship);
            break;
          case('W'):
            ok = b.outOfBoundsCheck(x1 - 1,y1,x2 - 1,y2,ship);
            break;
        }
        //move ship
        if (ok == Constants.NONEERROR){
          System.out.println("pre");
          System.out.println(b.getShipLocations(start));
          System.out.println(master);
          Ship s = b.getShipLocations(start).get(0);
          System.out.println("post");
          // call cleanup !!!!!!!!!!!!!!!!!!!!!!!!!!!
          j.cleanupOnAisle5(b,s,shipCoord, ship);
          System.out.println(master);
          //set new ship
          int health = s.getShipHealth();

          switch(direction) {
            case('N'):
              b.setShip(x1,y1 + 1,x2 ,y2 + 1, health,ship);
              break;
            case('E'):
              b.setShip(x1 + 1,y1,x2 + 1,y2, health,ship);
              break;
            case('S'):
              b.setShip(x1,y1 - 1,x2,y2 - 1, health,ship);
              break;
            case('W'):
              b.setShip(x1 - 1,y1,x2 - 1,y2, health,ship);
              break;
          }
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
        int ok = 0;
        switch(direction) {
          case('N'):
            ok = b.outOfBoundsCheck(x1,y1 + 1,x2,y2 + 1,ship);
            break;
          case('E'):
            ok = b.outOfBoundsCheck(x1 + 1,y1,x2 + 1,y2,ship);
            break;
          case('S'):
            ok = b.outOfBoundsCheck(x1,y1 - 1,x2,y2 - 1,ship);
            break;
          case('W'):
            ok = b.outOfBoundsCheck(x1 - 1,y1,x2 - 1,y2,ship);
            break;
        }
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
          j.cleanupOnAisle5(b,s,shipCoord, ship);
          //move
          int health = s.getShipHealth();

          switch(direction) {
            case('N'):
              b.setSub(x1,y1 + 1,x2 ,y2 + 1, health,ship);
              break;
            case('E'):
              b.setSub(x1 + 1,y1,x2 + 1,y2, health,ship);
              break;
            case('S'):
              b.setSub(x1,y1 - 1,x2,y2 - 1, health,ship);
              break;
            case('W'):
              b.setSub(x1 - 1,y1,x2 - 1,y2, health,ship);
              break;
          }
          movedShips.add(ship);
        }
      }
    }
    return movedShips;
  }

  //TODO: prevent stationary ship from moving (if previously were out of bounds)
  public void undoMove(char move, Board b){
    //figure out reverse direction
    char reverse = 'x';
    switch(move){
      case('N'):
        reverse = 'S';
        break;
      case('S'):
        reverse = 'N';
        break;
      case('W'):
        reverse = 'E';
        break;
      case('E'):
        reverse = 'W';
        break;
    }
    //call moveFleet
    moveFleet(b,reverse);
  }

  public void redoMove(char move, Board b){
    //call moveFleet again
    moveFleet(b,move);
  }
}







