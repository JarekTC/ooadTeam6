package edu.colorado.team6;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Board {
  private int[][] board = new int[10][10];
  private HashMap<Point, ArrayList<Ship>> shipLocations = new HashMap<Point, ArrayList<Ship>>();
  private MineSweeper ms = new MineSweeper();
  private Destroyer ds = new Destroyer();
  private BattleShip bs = new BattleShip();
  private Submarine ss = new Submarine();
  private ArrayList<Point> msOrientation = new ArrayList<Point>(); // Holds positions of each ship
  private ArrayList<Point> dsOrientation = new ArrayList<Point>();
  private ArrayList<Point> bsOrientation = new ArrayList<Point>();
  private ArrayList<Point> ssOrientation = new ArrayList<Point>();
  private HashMap<String, ArrayList<Point>> masterOrientation =
      new HashMap<String, ArrayList<Point>>();

  Board() {
    // Initialize shipLocations
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        shipLocations.put(new Point(i, j), new ArrayList<Ship>());
      }
    }

    // Initialize orientations
    masterOrientation.put(Constants.MINESWEEPER, msOrientation);
    masterOrientation.put(Constants.DESTROYER, dsOrientation);
    masterOrientation.put(Constants.BATTLESHIP, bsOrientation);
    masterOrientation.put(Constants.SUBMARINE, ssOrientation);
  }

  public int[][] getBoard() {
    return board;
  }

  public HashMap<Point, ArrayList<Ship>> getShipLocations() {
    return shipLocations;
  }

  public ArrayList<Point> getMsOrientation() {
    return msOrientation;
  }

  public ArrayList<Point> getDsOrientation() {
    return dsOrientation;
  }

  public ArrayList<Point> getBsOrientation() {
    return bsOrientation;
  }

  public ArrayList<Point> getSsOrientation() {
    return ssOrientation;
  }

  public HashMap<String, ArrayList<Point>> getMasterOrientation() {
    return masterOrientation;
  }

  public int getCoord(int x, int y) {
    return this.board[y][x];
  }

  public void setCoord(int x, int y, int shipOrSea) {
    this.board[y][x] = shipOrSea;
  }

  public void printBoard() {
    for (int y = 9; y >= 0; y--) {
      for (int x = 0; x < 10; x++) {
        System.out.print(getCoord(x, y) + " ");
      }
      System.out.println();
    }
  }

  // Function to be called by Hit() in Player class in order to get index of non overlapped ship/sub
  public int getStandardIndex(int x, int y, int shipNumber) {
    Point coord = new Point(x, y);
    Ship s =
        getShipLocations(coord)
            .get(shipNumber); // Handle just a ship or submarine at location (No overlap)
    int shipIndex;
    if (s instanceof MineSweeper) {
      shipIndex = this.msOrientation.indexOf(coord);
    } else if (s instanceof Destroyer) {
      shipIndex = this.dsOrientation.indexOf(coord);
    } else if (s instanceof BattleShip) {
      shipIndex = this.bsOrientation.indexOf(coord);
    } else {
      shipIndex = this.ssOrientation.indexOf(coord);
    }
    return shipIndex;
  }

  public int bombApplyDamage(int x, int y) {
    int index = getStandardIndex(x, y, 0);
    Point coord = new Point(x, y);
    Ship s =
        getShipLocations(coord).get(0); // Handle just a ship or submarine at location (No overlap)

    int preHealth = s.getShipHealth();
    s.takeDamage(index);

    // If a section of the ship is sunk, remove part of the ship from the board
    if (s instanceof MineSweeper && index == 0) { // Special case for minesweeper
      Point otherPoint = new Point();
      for (int i = 0; i < msOrientation.size(); i++) {
        if (!msOrientation.get(i).equals(coord)) {
          otherPoint = msOrientation.get(i);
        }
      }
      setCoord(x, y, Constants.SEA);
      setCoord(otherPoint.x, otherPoint.y, Constants.SEA);
    }
    if (s.getShipHealth() < preHealth) {
      if (getCoord(x, y) != Constants.SHIP_ON_TOP_SUB) {
        System.out.println("Not SHIP_ON_TOP_SUB");
        setCoord(x, y, Constants.SEA);
      } else {
        setCoord(x, y, Constants.SUB_UNDER_WATER);
      }
    }
    return 0;
  }

  /*
  //General idea is that hit function will call getCoord to know what is at spot, then call
  //correct functions to deal damage according to ship conditions and perk activations
  */
  public int laserApplyDamage(int x, int y, boolean overlap) {
    //    if (!overlap) {
    //      bombApplyDamage(x, y);
    //      return 0;
    //    }
    // ArrayList<Integer> indices = getOverlapIndex(x, y);
    Point coord = new Point(x, y);
    ArrayList<Ship> shipList = getShipLocations(coord); // account for overlap
    int incrementer = 0;

    for (Ship s : shipList) {
      int preHealth = s.getShipHealth();
      int index = getStandardIndex(x, y, incrementer);
      s.takeDamage(index);
      //      if((s instanceof MineSweeper) && (index == 0)){
      //        for (int i = 0; i < msOrientation.size(); i++) {
      //          Point otherPoint = new Point(x, y);
      //          setCoord(otherPoint.x, otherPoint.y, Constants.SEA);
      //        }
      //      }
      //      else if((s instanceof Destroyer) && (index == 1)){
      //
      //      }
      // If a section of the ship is sunk, remove part of the ship from the board
      if (s.getShipHealth() < preHealth) {
        setCoord(x, y, Constants.SEA);
      }
      incrementer++;
    }
    return 0;
  }

  public int getPos(int x, int y) {
    return this.board[y][x];
  }

  // Bounds Checking Functions ---------------------------------------------------------------------
  public int diagonalBoundsCheck(int x1, int y1, int x2, int y2) {
    if ((Math.abs(x1 - x2) != 0) && (Math.abs(y1 - y2) != 0)) {
      System.out.println("Cannot place ships diagonally!");
      return Constants.ERROR;
    }
    return Constants.NONEERROR;
  }

  public int lengthCheck(int x1, int y1, int x2, int y2, int length, String shipName) {

    if ((Math.abs(x1 - x2) + 1 != length) && (Math.abs(y1 - y2) + 1 != length)) {
      System.out.println("Coordinates don't match health!");
      return Constants.ERROR;
    }

    return Constants.NONEERROR;
  }

  public int outOfBoundsCheck(int x1, int y1, int x2, int y2, String shipName) {

    // This checks that all points are within the board
    if ((x1 < 0) || (x1 > 9) || (y1 < 0) || (y1 > 9)) {
      return Constants.ERROR;
    }

    if ((x2 < 0) || (x2 > 9) || (y2 < 0) || (y2 > 9)) {
      return Constants.ERROR;
    }

    // This checks if the submarine is within bounds
    if (shipName.equals(Constants.SUBMARINE)) {
      if ((x1 == 0) && (y1 < y2)) {
        System.out.println("Out of bounds!");
        return Constants.ERROR;
      }
      if ((y1 == 9) && (x1 < x2)) {
        System.out.println("Out of bounds!");
        return Constants.ERROR;
      }
      if ((x1 == 9) && (y1 > y2)) {
        System.out.println("Out of bounds!");
        return Constants.ERROR;
      }
      if ((y1 == 0) && (x1 > x2)) {
        System.out.println("Out of bounds!");
        return Constants.ERROR;
      }
    }

    return Constants.NONEERROR;
  }
  // -----------------------------------------------------------------------------------------------

  public int setShip(int x1, int y1, int x2, int y2, int health, String ship) {
    // Error checking
    if (diagonalBoundsCheck(x1, y1, x2, y2) == Constants.ERROR
        || lengthCheck(x1, y1, x2, y2, health, ship) == Constants.ERROR
        || outOfBoundsCheck(x1, y1, x2, y2, ship) == Constants.ERROR
        || ship.equals(Constants.SUBMARINE)) {
      return Constants.ERROR;
    }

    // Place non-submarine ship
    if (!ship.equals(Constants.SUBMARINE)) {
      // Horizontal ship (Smallest to largest coordinates)
      if ((x2 - x1) > 0) {
        for (int i = x1; i <= x2; i++) {
          System.out.println("in branch where placing vertical ship");
          // Check is there is a submarine at placement location
          if (getCoord(i, y1) == Constants.SUB_UNDER_WATER) {
            setCoord(i, y1, Constants.SHIP_ON_TOP_SUB);
          } else if (getCoord(i, y1) == Constants.SEA) {
            setCoord(i, y1, Constants.SHIP);
          } else {
            return Constants.ERROR;
          }
          setShipLocations(new Point(i, y1), ship);
        }
        // Horizontal ship (Largest to smallest coordinates)
      } else if ((x2 - x1) < 0) {
        for (int i = x1; i >= x2; i--) {
          // Check is there is a submarine at placement location
          if (getCoord(i, y1) == Constants.SUB_UNDER_WATER) {
            setCoord(i, y1, Constants.SHIP_ON_TOP_SUB);
          } else if (getCoord(i, y1) == Constants.SEA) {
            setCoord(i, y1, Constants.SHIP);
          } else {
            return Constants.ERROR;
          }
          setShipLocations(new Point(i, y1), ship);
        }
      }
      // Vertical ship (Smallest to largest coordinates)
      else if ((y2 - y1) > 0) {
        for (int i = y1; i <= y2; i++) {
          // Check is there is a submarine at placement location
          if (getCoord(x1, i) == Constants.SUB_UNDER_WATER) {
            setCoord(x1, i, Constants.SHIP_ON_TOP_SUB);
          } else if (getCoord(x1, i) == Constants.SEA) {
            setCoord(x1, i, Constants.SHIP);
          } else {
            return Constants.ERROR;
          }
          setShipLocations(new Point(x1, i), ship);
        }
      }
      // Vertical ship (Largest to smallest coordinates)
      else {
        for (int i = y1; i >= y2; i--) {
          // Check is there is a submarine at placement location
          if (getCoord(x1, i) == Constants.SUB_UNDER_WATER) {
            setCoord(x1, i, Constants.SHIP_ON_TOP_SUB);
          } else if (getCoord(x1, i) == Constants.SEA) {
            setCoord(x1, i, Constants.SHIP);
          } else {
            return Constants.ERROR;
          }
          setShipLocations(new Point(x1, i), ship);
        }
      }
    }
    setShipArray(x1, y1, x2, y2, health, ship);
    return Constants.NONEERROR;
  }

  public int setSub(int x1, int y1, int x2, int y2, int health, String ship) {
    if (diagonalBoundsCheck(x1, y1, x2, y2) == Constants.ERROR
        || lengthCheck(x1, y1, x2, y2, health - 1, ship) == Constants.ERROR
        || outOfBoundsCheck(x1, y1, x2, y2, ship) == Constants.ERROR
        || !ship.equals(Constants.SUBMARINE)) {
      return Constants.ERROR;
    }

    // Horizontal ship (Smallest to largest coordinates)
    if ((x2 - x1) > 0) {
      for (int i = x1; i <= x2; i++) {
        // System.out.println("in branch where placing vertical ship");
        // Check is there is a submarine at placement location
        if (getCoord(i, y1) == Constants.SHIP) {
          setCoord(i, y1, Constants.SHIP_ON_TOP_SUB);
        } else if (getCoord(i, y1) == Constants.SEA) {
          setCoord(i, y1, Constants.SUB_UNDER_WATER);
        } else {
          return Constants.ERROR;
        }
        setShipLocations(new Point(i, y1), ship);
      }
      if (getCoord(x2 - 1, y1 + 1) == Constants.SHIP) {
        setCoord(x2 - 1, y1 + 1, Constants.SHIP_ON_TOP_SUB);
      } else {
        setCoord(x2 - 1, y1 + 1, Constants.SUB_UNDER_WATER);
      }
      setShipLocations(new Point(x2 - 1, y1 + 1), ship);
      // Horizontal ship (Largest to smallest coordinates)
    } else if ((x2 - x1) < 0) {
      for (int i = x1; i >= x2; i--) {
        // Check is there is a submarine at placement location
        if (getCoord(i, y1) == Constants.SHIP) {
          setCoord(i, y1, Constants.SHIP_ON_TOP_SUB);
        } else if (getCoord(i, y1) == Constants.SEA) {
          setCoord(i, y1, Constants.SUB_UNDER_WATER);
        } else {
          return Constants.ERROR;
        }
        setShipLocations(new Point(i, y1), ship);
      }
      if (getCoord(x2 + 1, y1 - 1) == Constants.SHIP) {
        setCoord(x2 + 1, y1 - 1, Constants.SHIP_ON_TOP_SUB);
      } else {
        setCoord(x2 + 1, y1 - 1, Constants.SUB_UNDER_WATER);
      }
      setShipLocations(new Point(x2 + 1, y1 - 1), ship);
    }
    // Vertical ship (Smallest to largest coordinates)
    else if ((y2 - y1) > 0) {
      for (int i = y1; i <= y2; i++) {
        // Check is there is a submarine at placement location
        if (getCoord(x1, i) == Constants.SHIP) {
          setCoord(x1, i, Constants.SHIP_ON_TOP_SUB);
        } else if (getCoord(x1, i) == Constants.SEA) {
          setCoord(x1, i, Constants.SUB_UNDER_WATER);
        } else {
          return Constants.ERROR;
        }
        setShipLocations(new Point(x1, i), ship);
      }
      if (getCoord(x1 - 1, y2 - 1) == Constants.SHIP) {
        setCoord(x1 - 1, y2 - 1, Constants.SHIP_ON_TOP_SUB);
      } else {
        setCoord(x1 - 1, y2 - 1, Constants.SUB_UNDER_WATER);
      }
      setShipLocations(new Point(x1 - 1, y2 - 1), ship);
    } else {
      for (int i = y1; i >= y2; i--) {
        // Check is there is a submarine at placement location
        if (getCoord(x1, i) == Constants.SHIP) {
          setCoord(x1, i, Constants.SHIP_ON_TOP_SUB);
        } else if (getCoord(x1, i) == Constants.SEA) {
          setCoord(x1, i, Constants.SUB_UNDER_WATER);
        } else {
          return Constants.ERROR;
        }
        setShipLocations(new Point(x1, i), ship);
      }
      if (getCoord(x1 + 1, y2 + 1) == Constants.SHIP) {
        setCoord(x1 + 1, y2 + 1, Constants.SHIP_ON_TOP_SUB);
      } else {
        setCoord(x1 + 1, y2 + 1, Constants.SUB_UNDER_WATER);
      }
      setShipLocations(new Point(x1 + 1, y2 + 1), ship);
    }
    setShipArray(x1, y1, x2, y2, health, ship);
    return Constants.NONEERROR;
  }

  public int setShipLocations(Point coord, String ship) {

    switch (ship) {
      case Constants.MINESWEEPER:
        if (this.shipLocations.get(coord).size() == 0) {
          this.shipLocations.get(coord).add(this.ms);
        } else {
          this.shipLocations.get(coord).add(0, this.ms);
        }
        return Constants.NONEERROR;
      case Constants.DESTROYER:
        if (this.shipLocations.get(coord).size() == 0) {
          this.shipLocations.get(coord).add(this.ds);
        } else {
          this.shipLocations.get(coord).add(0, this.ds);
        }
        return Constants.NONEERROR;
      case Constants.BATTLESHIP:
        if (this.shipLocations.get(coord).size() == 0) {
          this.shipLocations.get(coord).add(this.bs);
        } else {
          this.shipLocations.get(coord).add(0, this.bs);
        }
        return Constants.NONEERROR;
      case Constants.SUBMARINE:
        this.shipLocations.get(coord).add(this.ss);
        return Constants.NONEERROR;
      default:
        return Constants.ERROR;
    }
  }

  public ArrayList<Ship> getShipLocations(Point coord) {
    return this.shipLocations.get(coord);
  }

  public int setShipArray(int x1, int y1, int x2, int y2, int health, String ship) {
    ArrayList<Point> posi = new ArrayList<Point>();
    // Horizontal ship (smallest to largest coordinates)
    if ((x1 - x2) < 0) {
      for (int i = x1; i <= x2; i++) {
        Point coord = new Point(i, y1);
        posi.add(coord);
      }
      // Add off one coordinate for sub
      if (ship.equals(Constants.SUBMARINE)) {
        posi.add(new Point(x2 - 1, y1 + 1));
      }
    }
    // Horizontal ship (largest to smallest coordinates)
    else if ((x1 - x2) > 0) {
      for (int i = x1; i >= x2; i--) {
        Point coord = new Point(i, y1);
        posi.add(coord);
      }
      if (ship.equals(Constants.SUBMARINE)) {
        posi.add(new Point(x2 + 1, y1 - 1));
      }
    }
    // Vertical ship (smallest to largest coordinates)
    else if ((y1 - y2) < 0) {
      for (int i = y1; i <= y2; i++) {
        Point coord = new Point(x1, i);
        // System.out.println("Calling from setShipArray():" + x1 + " " + i);
        posi.add(coord);
      }
      if (ship.equals(Constants.SUBMARINE)) {
        posi.add(new Point(x1 - 1, y2 - 1));
      }
    } else {
      for (int i = y1; i >= y2; i--) {
        Point coord = new Point(x1, i);
        posi.add(coord);
      }
      if (ship.equals(Constants.SUBMARINE)) {
        posi.add(new Point(x1 + 1, y2 + 1));
      }
    }

    switch (ship) {
      case Constants.MINESWEEPER:
        this.masterOrientation.put(Constants.MINESWEEPER, posi);
        this.msOrientation = posi;
        return Constants.NONEERROR;
      case Constants.DESTROYER:
        this.masterOrientation.put(Constants.DESTROYER, posi);
        this.dsOrientation = posi;
        return Constants.NONEERROR;
      case Constants.BATTLESHIP:
        this.masterOrientation.put(Constants.BATTLESHIP, posi);
        this.bsOrientation = posi;
        return Constants.NONEERROR;
      case Constants.SUBMARINE:
        this.masterOrientation.put(Constants.SUBMARINE, posi);
        for (int i = 0; i < posi.size(); i++) {
          System.out.println(ship + ": " + posi.get(i).x + ", " + posi.get(i).y);
        }
        this.ssOrientation = posi;
        return Constants.NONEERROR;
    }
    return Constants.ERROR;
  }
}
