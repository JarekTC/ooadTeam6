package edu.colorado.team6;

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
        System.out.println("player two name is: " + playerNameA);  // Output user input

        playerA = new Player(playerNameA);
        playerB = new Player(playerNameB);

        runGame();
    }

    private int runGame(){

        Boolean exit = false;

        System.out.println("The Game is afoot! Player One BEGIN!!");

        Scanner readIn = new Scanner(System.in);  // Create a Scanner object



        while(!exit){

            //Player A turn
            if (whoseTurn == Constants.PLAYERA){
                System.out.println("Player One, your turn!");

                exit = runTurn(playerB, readIn);

                whoseTurn = Constants.PLAYERB;
            }

            //Player B turn
            else if (whoseTurn == Constants.PLAYERB){
                System.out.println("Player Two, your turn!");

                exit = runTurn(playerB, readIn);

                whoseTurn = Constants.PLAYERA;
            }
        }
        return Constants.NONEERROR;
    }


    private Boolean runTurn(Player whichPlayer, Scanner readIn){

        whichPlayer.getB().printBoard();
        boardSetup(whichPlayer, readIn);

        System.out.println("----------");
        System.out.println("Select an option:");
        System.out.println("1.End");
        System.out.println("");


        int select = readIn.nextInt();  // Read user input

        switch (select){
            case 1:
                return true;


        }

        return true;
    }

    private int boardSetup(Player p, Scanner readIn){

        Ship[] listOfShips = {new BattleShip(), new Submarine(), new Destroyer(), new MineSweeper()};

        for (Ship ship : listOfShips) {
            int isError = 0;
            String coords = "";

            do {
                try {
                    System.out.println("Enter x and y coordinates for the endpoints of your " + ship.showShipType() + " separated by spaces:");
                    coords = readIn.nextLine();
                    int start1 = Integer.parseInt(coords.substring(0, 1));
                    int start2 = Integer.parseInt(coords.substring(2, 3));
                    int end1 = Integer.parseInt(coords.substring(4, 5));
                    int end2 = Integer.parseInt(coords.substring(6, 7));
                    isError = p.placeShip(start1, start2, end1, end2, ship.getShipHealth(), ship.showShipType());
                    p.getB().printBoard();
                }
                catch (Exception e) {
                    System.out.println("ERROR: problem with input. Re-enter coordinates");
                }
            } while (!(coords.matches("\\d\\s\\d\\s\\d\\s\\d")) | isError == Constants.ERROR); //Use single | so no short circuiting
        }

            System.out.println("----------");
            System.out.println("Player One, set your ships:");

        return Constants.NONEERROR;
    }

}
