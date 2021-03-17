package edu.colorado.team6;

import java.awt.*;
import java.util.HashMap;

public class Perks {
  //    private
  private Board b;
  Perks(Board b) {
    this.b = b;
  };

  public HashMap<Point, Integer> sonar(Point coord){
    int x = coord.x;
    int y = coord.y;
    HashMap<Point, Integer> radar = new HashMap<Point, Integer>();
    //up
    if (y < 9){
      if(y < 8){
        radar.put(new Point(x,y+2), b.getPos(x,y+2));
      }
      radar.put(new Point(x,y+1), b.getPos(x,y+1));
    }
    //south
    if (y > 1){
      if (y > 2){
        radar.put(new Point(x,y-2), b.getPos(x,y-2));
      }
      radar.put(new Point(x,y-1), b.getPos(x,y-1));
    }
    //west
    if (x > 1){
      if (x > 2){
        radar.put(new Point(x-2,y), b.getPos(x-2,y));
      }
      radar.put(new Point(x-1,y), b.getPos(x-1,y));
    }
    //east
    if (x < 9){
      if (x < 8){
        radar.put(new Point(x+2,y), b.getPos(x+2,y));
      }
      radar.put(new Point(x+1,y), b.getPos(x+1,y));
    }
    //diagonals
    //north west
    if ((y < 9) && (x > 1)){
      radar.put(new Point(x-1,y+1), b.getPos(x-1,y+1));
    }
    //north east
    if((y < 9) && (x < 9)){
      radar.put(new Point(x+1,y+1), b.getPos(x+1,y+1));
    }
    //south west
    if ((y > 1) && (x > 1)){
      radar.put(new Point(x-1,y-1), b.getPos(x-1,y-1));
    }
    //south east
    if((y > 1) && (x < 9)){
      radar.put(new Point(x+1,y-1), b.getPos(x+1,y-1));
    }
    return radar;
  }

}
