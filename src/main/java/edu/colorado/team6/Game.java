package edu.colorado.team6;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialogBuilder;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {

    private final Player playerA;
    private final Player playerB;

    private Boolean whoseTurn = Constants.PLAYERA;

    // Private variables for CLI lantern
    private DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
    private Terminal terminal = new DefaultTerminalFactory().createTerminal();
    private Screen screen = new TerminalScreen(terminal);
    final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);


    public Game() throws IOException {
        TextGraphics tg = screen.newTextGraphics();
        screen.startScreen();

        String result = new TextInputDialogBuilder()
                .setTitle("Enter player one name:")
                .setTextBoxSize(new TerminalSize(35, 5))
                .build()
                .showDialog(textGUI);

        screen.stopScreen();

        screen.refresh();
        screen.readInput();
        screen.stopScreen();



        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter player one name:");

        //player names for game instance
        String playerNameA = myObj.nextLine();  // Read user input
        System.out.println("player one name is: " + playerNameA);  // Output user input

        // get player b name
        System.out.println("Enter player two name:");

        String playerNameB = myObj.nextLine();  // Read user input
        System.out.println("player two name is: " + playerNameB);  // Output user input

        playerA = new Player(playerNameA);
        playerB = new Player(playerNameB);

        runGame(myObj);
    }

    private void runGame(Scanner readIn){

        Boolean exit = false;

        System.out.println("The Game is afoot! Player One BEGIN!!");

        System.out.println("----------");
        System.out.println("Player One, set your ships:");
        boardSetup(playerA, readIn);
        System.out.println("Your ships area ready!");
        System.out.println(ASCII_Art.asciiArt.get("Ship"));

        System.out.println("----------");
        System.out.println("Player Two, set your ships:");
        boardSetup(playerB, readIn);
        System.out.println("Your ships area ready!");
        System.out.println(ASCII_Art.asciiArt.get("Ship"));


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
        System.out.println("Winner let's celebrate!");
        System.out.println(ASCII_Art.asciiArt.get("Champagne"));
        System.out.println("Here is some poop for the loser!");
        System.out.println(ASCII_Art.asciiArt.get("Poo"));
    }


    private Boolean runTurn(Player whichPlayer, Scanner readIn, Player enemy){
        boolean endGame = false;
        int correctInput = 0;
        if(whichPlayer.getSonar()){
            superPrintRecord(whichPlayer.getRecord(), whichPlayer.getRevealed());
        }
        else{
            whichPlayer.printRecord();
        }
        System.out.println();
        whichPlayer.getB().printBoard();
        if(whichPlayer.getCountHits() == 3){
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
                    correctInput = dealPerks(whichPlayer, readIn, enemy, correctInput);
                    if(correctInput == 1){
                        endGame = true;
                    }
                    break;

                default:
                    System.out.println("Please enter a choice from the menu");
                    correctInput = -1;
                    break;
            }
        } while(correctInput == -1);

        return endGame;
    }

    private void boardSetup(Player p, Scanner readIn){
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
    }

    private void userMoveFleet(Player p, Scanner readIn){
        char direction;
        ArrayList<Character> validDirections = new ArrayList<>();
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
    }


    private int userAttackOpponent(Player whichPlayer, Scanner readIn, Player enemy){
        int x;
        int y;
        String input;
        String[] coords;
        do {
            System.out.println("Enter x and y coordinates for the location you want to attack separated by spaces:");
            input = readIn.nextLine();
            coords = input.split("\\s");
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
            System.out.println("Have some pie!");
            System.out.println(ASCII_Art.asciiArt.get("Pie"));
        }
        else if(record == 3 && hitStat == 2){
            System.out.println("You've hit a ship!");
            System.out.println("Have some pie!");
            System.out.println(ASCII_Art.asciiArt.get("Pie"));
        }
        else if(record == 1 && hitStat == 1){
            System.out.println("You've hit the captain's quarters of a ship!");
            System.out.println("Have some pie!");
            System.out.println(ASCII_Art.asciiArt.get("Pie"));
        }
        else if(record == 3 && hitStat == 3){
            System.out.println("You've hit the captain's quarters of a ship!");
            System.out.println("Have some pie!");
            System.out.println(ASCII_Art.asciiArt.get("Pie"));
        }
        else if(record == 3 && hitStat == 0){
            System.out.println("You've hit part of a ship and a submarine!");
            System.out.println("Have some pie!");
            System.out.println(ASCII_Art.asciiArt.get("Pie"));
        }
        else if(record == 2 && hitStat == 0){
            System.out.println("You've hit a submarine!");
            System.out.println("Have some pie!");
            System.out.println(ASCII_Art.asciiArt.get("Pie"));
        }
        else if(record == 2 && hitStat == 2){
            System.out.println("You've hit the captain's quarters of a submarine!");
            System.out.println("Have some pie!");
            System.out.println(ASCII_Art.asciiArt.get("Pie"));
        }
        else if(record == 3 && hitStat == 1){
            System.out.println("You've hit the captain's quarters of a stacked ship with laser!");
            System.out.println("Have some pie!");
            System.out.println(ASCII_Art.asciiArt.get("Pie"));
        }

    }

    private int dealPerks(Player currentPlayer, Scanner readIn, Player enemy, int correctInput){
        int correctI;
        do{
            System.out.println("----------");
            System.out.println("Select an option:");
            System.out.println("1.Nuke");
            System.out.println("2.Sonar");
            System.out.println("3.B2 Bomber");
            System.out.println("4.Exit perks");
            int select = readIn.nextInt();  // Read user input
            switch (select) {
                case 1:
                    if(currentPlayer.getNuke()){
                        System.out.println("You just nuked your enemy!");
                        System.out.println(ASCII_Art.asciiArt.get("Nuke"));
                        //end = true;
                        correctInput = 1;
                        correctI = 0;
                    }
                    else{
                        System.out.println("Nuke not available!");
                        correctInput = -1;
                        correctI = -1;
                    }
                    break;
                case 2:
                    Perks p = currentPlayer.getPerks();
                    String input;
                    String[] coords;
                    do {
                        System.out.println("Enter x and y coordinates for the location you want use the sonar at:");
                        input = readIn.nextLine();
                        coords = input.split("\\s");
                    } while (!(input.matches("\\d\\s\\d")));
                    Point xandy = new Point(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
                    Board enemyBoard = enemy.getB();
                    HashMap<Point, Integer> revealed = p.sonar(xandy, enemyBoard);
                    currentPlayer.setRevealed(revealed);
                    //make sense of results and print nicely
                    HashMap<Point, Integer> record = currentPlayer.getRecord();
                    superPrintRecord(record, revealed);
                    currentPlayer.setSonar();
                    correctInput = 0;
                    correctI = 0;
                    break;
                case 3:
//                    System.out.println("bomberavail = "+currentPlayer.getBomberAvail());
//                    if(currentPlayer.getBomberAvail() == 1){
//                        HashMap<Integer, Point> target = currentPlayer.b2Bomber(enemy);
//                        int stat = 0;
//                        Point c = new Point();
//                        for(Map.Entry<Integer, Point> pair : target.entrySet()){
//                            stat = pair.getKey();
//                            c = pair.getValue();
//                        }
//                        int x = c.x;
//                        int y = c.y;
//                        compare(stat, currentPlayer.lookupRecord(x,y));
//                        correctInput = 0;
//                        correctI = 0;
//                        //disable bomber
//                        currentPlayer.setBomberAvail();
//                    }
//                    else{
//                        System.out.println("Bomber not available!");
//                        correctInput = -1;
//                        correctI = -1;
//                    }

                    HashMap<Integer, Point> target = currentPlayer.b2Bomber(enemy);
                    int stat = 0;
                    Point c = new Point();
                    for(Map.Entry<Integer, Point> pair : target.entrySet()){
                        stat = pair.getKey();
                        c = pair.getValue();
                    }
                    int x = c.x;
                    int y = c.y;
                    compare(stat, currentPlayer.lookupRecord(x,y));
                    correctInput = 0;
                    correctI = 0;
                    //disable bomber

                    break;
                case 4:
                    correctInput = -1;
                    correctI = 0;
                    break;
                default:
                    System.out.println("Please enter a choice from the menu");
                    correctI = -1;
                    break;
            }
        }while(correctI == -1);
        return correctInput;
    }

    public void superPrintRecord(HashMap<Point, Integer> record, HashMap<Point, Integer> rev){
        int xyValue;
        for (int y = 9; y >= 0; y--) {
            for (int x = 0; x < 10; x++) {
                try {
                    if(record.containsKey(new Point(x, y))){
                        xyValue = record.get(new Point(x, y));
                        if (xyValue == 1 || xyValue == 2 || xyValue == 3) {
                            System.out.print(" H ");
                        } else {
                            System.out.print(" M ");
                        }
                    }
                    else{
                        xyValue = rev.get(new Point(x, y));
                        if (xyValue == 1 || xyValue == 2 || xyValue == 3) {
                            System.out.print(" "+xyValue+" ");
                        } else {
                            System.out.print(" 0 ");
                        }
                    }
                }
                catch (Exception e) {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }
}
















