package edu.colorado.team6;

import java.awt.*;
import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Game {

    // undo stack A
    private Stack<Board> undoStackA = new Stack<Board>();
    // undo stack B
    private Stack<Board> undoStackB = new Stack<Board>();

    // redo stack A
    private Stack<Board> redoStackA = new Stack<Board>();
    // redo Stack B
    private Stack<Board> redoStackB = new Stack<Board>();

    private Player playerA;
    private Player playerB;

    //player names for game instance
    private String playerNameA = "";
    private String playerNameB = "";

    private Boolean whoseTurn = Constants.PLAYERA;


    public Game() {
        //get player a name
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter player one name:");

        playerNameA = myObj.nextLine();  // Read user input
        System.out.println("player one name is: " + playerNameA);  // Output user input

        // get player b name
        System.out.println("Enter player two name:");

        playerNameB = myObj.nextLine();  // Read user input
        System.out.println("player two name is: " + playerNameB);  // Output user input

        playerA = new Player(playerNameA);
        playerB = new Player(playerNameB);

        runGame(myObj);
    }

    private int runGame(Scanner readIn){

        Boolean exit = false;

        System.out.println("The Game is afoot! Player One BEGIN!!");

        //Scanner readIn = new Scanner(System.in);  // Create a Scanner object

        System.out.println("----------");
        System.out.println("Player One, set your ships:");
        boardSetup(playerA, readIn);

        System.out.println("----------");
        System.out.println("Player Two, set your ships:");
        boardSetup(playerB, readIn);

        while(!exit){

            //Player A turn
            if (whoseTurn == Constants.PLAYERA){
                System.out.println("Player One, your turn!");

                exit = runTurn(playerA, readIn, playerB); //should be playerA in first spot???

                whoseTurn = Constants.PLAYERB;
            }

            //Player B turn
            else if (whoseTurn == Constants.PLAYERB){
                System.out.println("Player Two, your turn!");

                exit = runTurn(playerB, readIn, playerA);

                whoseTurn = Constants.PLAYERA;
            }
        }
        return Constants.NONEERROR;
    }


    private Boolean runTurn(Player whichPlayer, Scanner readIn, Player enemy){

        Boolean endGame = false;
        int correctInput = 0;
        //Boolean nuke = false;
        whichPlayer.printRecord();
        System.out.println();
        whichPlayer.getB().printBoard();
        if(whichPlayer.getCountHits() == 6){
            whichPlayer.setNuke();
        }
        do {
            System.out.println("----------");
            System.out.println("Select an option:");
            System.out.println("1.End");
            System.out.println("2.Move Fleet");
            System.out.println("3.Attack Opponent");
            System.out.println("4.User Perk");

            int select = readIn.nextInt();  // Read user input

            switch (select) {
                case 1:
                    endGame = true;
                    correctInput = 0;
                    break;
                case 2:
                    userMoveFleet(whichPlayer, readIn);
                    correctInput = 0;
                    break;
                case 3:
                    userAttackOpponent(whichPlayer, readIn, enemy);
                    correctInput = 0;
                    break;
                case 4:
                    endGame = dealPerks(whichPlayer, readIn, enemy);
                    break;

                default:
                    System.out.println("Please enter a choice from the menu");
                    correctInput = -1;
                    break;
            }
        } while(correctInput == -1);

        return endGame;
    }

    private int boardSetup(Player p, Scanner readIn){

        Ship[] listOfShips = {new BattleShip(), new Submarine(), new Destroyer(), new MineSweeper()};

        for (Ship ship : listOfShips) {
            int isError = 0;
            String[] coords;
            String input = "";

            do {
                try {
                    System.out.println("Enter x and y coordinates for the endpoints of your " + ship.showShipType() + " separated by spaces:");
                    input = readIn.nextLine();
                    coords = input.split("\\s");
                    isError = p.placeShip(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]), Integer.parseInt(coords[3]), ship.getShipHealth(), ship.showShipType());
                    p.getB().printBoard();
                }
                catch (Exception e) {
                    System.out.println("ERROR: problem with input. Re-enter coordinates");
                }
            } while (!(input.matches("\\d\\s\\d\\s\\d\\s\\d")) | isError == Constants.ERROR); //Use single | so no short circuiting
        }

        return Constants.NONEERROR;
    }

    private int userMoveFleet(Player p, Scanner readIn){

        Character direction = '0';

        ArrayList<Character> validDirections = new ArrayList<Character>();

        validDirections.add('N');
        validDirections.add('S');
        validDirections.add('E');
        validDirections.add('W');

        do {
            System.out.println("Enter the Compass direction in which to move your fleet:");
            System.out.println("Please enter 'N', 'S', 'E', or 'W'");
            direction = Character.toUpperCase(readIn.next().charAt(0));
            p.moveFleetPlayer(direction);
        } while (!(validDirections.contains(direction)));

        return Constants.NONEERROR;
    }


    private int userAttackOpponent(Player whichPlayer, Scanner readIn, Player enemy){
        int x;
        int y;
        String input = "";
        String[] coords = new String[0];
        do {
            System.out.println("Enter x and y coordinates for the location you want to attack separated by spaces:");
            input = readIn.nextLine();
            coords = input.split("\\s");
            //isError = p.placeShip(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]), Integer.parseInt(coords[3]), ship.getShipHealth(), ship.showShipType());
            //which.getB().printBoard();
        } while (!(input.matches("\\d\\s\\d")) );//| isError == Constants.ERROR
        Boolean code = whichPlayer.getActivationCode();
        x = Integer.parseInt(coords[0]);
        y = Integer.parseInt(coords[1]);
        compare(whichPlayer.hit(x,y,enemy,code), whichPlayer.lookupRecord(x,y));

        return Constants.NONEERROR;
    }

    private void compare(int hitStat, int record){
        if(record == 0 && hitStat == 0){
            System.out.println("You've hit water!");
        }
        else if(record == 1 && hitStat == 0){
            System.out.println("You've hit a ship!");
        }
        else if(record == 3 && hitStat == 2){
            System.out.println("You've hit a ship!");
        }
        else if(record == 1 && hitStat == 1){
            System.out.println("You've hit the captain's quarters of a ship!");
        }
        else if(record == 3 && hitStat == 3){
            System.out.println("You've hit the captain's quarters of a ship!");
        }
        else if(record == 3 && hitStat == 0){
            System.out.println("You've hit part of a ship and a submarine!");
        }
        else if(record == 2 && hitStat == 0){
            System.out.println("You've hit a submarine!");
        }
        else if(record == 2 && hitStat == 2){
            System.out.println("You've hit the captain's quarters of a submarine!");
        }
    }

    private Boolean dealPerks(Player currentPlayer, Scanner readIn, Player enemy){
        int correctInput = 0;
        Boolean end = false;
        do{
            System.out.println("----------");
            System.out.println("Select an option:");
            System.out.println("1.Nuke");
            System.out.println("2.Sonar");
            System.out.println("3. [NEW FEATURE]");

            int select = readIn.nextInt();  // Read user input

            switch (select) {
                case 1:
                    if(currentPlayer.getNuke() == true){
                        System.out.println("You just nuked your enemy!");
                        end = true;
                    }
                    else{
                        System.out.println("Nuke not available!");
                    }
                    correctInput = 0;
                    break;
                case 2:
                    Perks p = currentPlayer.getPerks();
                    String input = "";
                    String[] coords = new String[0];
                    do {
                        System.out.println("Enter x and y coordinates for the location you want use the sonar at:");
                        input = readIn.nextLine();
                        coords = input.split("\\s");
                    } while (!(input.matches("\\d\\s\\d")));
                    Point xandy = new Point(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
                    Board enemyBoard = enemy.getB();
                    HashMap<Point, Integer> revealed = p.sonar(xandy, enemyBoard);
                    //make sense of results and print nicely
                    correctInput = 0;
                    break;
                case 3:
                    // feature coming soon!!!!!!!!!!!!
                    correctInput = 0;
                    break;

                default:
                    System.out.println("Please enter a choice from the menu");
                    correctInput = -1;
                    break;
            }
        }while(correctInput != -1);


        return end;
    }
}
















