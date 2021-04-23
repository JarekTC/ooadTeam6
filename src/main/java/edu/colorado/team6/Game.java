package edu.colorado.team6;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.bundle.LanternaThemes;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextGraphicsWriter;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.*;
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
    private Terminal terminal = new DefaultTerminalFactory().createTerminal();
    private Screen screen = new TerminalScreen(terminal);
    private final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);



    public Game() throws IOException {
        screen.startScreen();

        // set theme
        textGUI.setTheme(LanternaThemes.getRegisteredTheme("businessmachine"));

        Window w = birthWindow("Enter player 1 name:", "Player 1", 20, 1);
        textGUI.addWindow(w);
        TerminalPosition s = w.getPosition();
        w.setPosition(new TerminalPosition(s.getColumn() + 30, 2));
        String playerNameA = readIn();
        textGUI.removeWindow(w);
        w = birthWindow("Enter player 2 name:", "Player 2", 20, 1);
        textGUI.addWindow(w);
        s = w.getPosition();
        w.setPosition(new TerminalPosition(s.getColumn() + 30, 2));
        String playerNameB = readIn();
        textGUI.removeWindow(w);

        //screen.refresh();


        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        System.out.println("player one name is: " + playerNameA);  // Output user input
        System.out.println("player two name is: " + playerNameB);  // Output user input

        playerA = new Player(playerNameA);
        playerB = new Player(playerNameB);

        runGame(myObj);
    }

    private void runGame(Scanner readIn) {

        Boolean exit = false;

        Window printW = birthWindowWithClose("The Game is afoot! Player One BEGIN!!", "Welcome!",20,10);

        textGUI.addWindowAndWait(printW);
        TerminalPosition a = printW.getPosition();
        printW.setPosition(new TerminalPosition(a.getColumn() + 10, a.getRow() + 10));


        textGUI.removeWindow(printW);

        printW = birthWindow("----------" + "\n" + "Player One, set your ships:", playerA.getName(), 40, 2);
        textGUI.addWindow(printW);
        TerminalPosition a1 = printW.getPosition();
        printW.setPosition(new TerminalPosition(a1.getColumn() + 80, 0));

        boardSetup(playerA, readIn);
        textGUI.removeWindow(printW);

        printW = birthWindowWithClose("Your ships area ready!" + ASCII_Art.asciiArt.get("Ship"), "Set",80,20);
        textGUI.addWindowAndWait(printW);
//        textGUI.removeWindow(printW);

        printW = birthWindow("----------" + "\n" + "Player Two, set your ships:", playerB.getName(), 40, 2);
        textGUI.addWindow(printW);
        a1 = printW.getPosition();
        printW.setPosition(new TerminalPosition(a1.getColumn() + 80, 0));
        System.out.println("----------");
        System.out.println("Player Two, set your ships:");

        boardSetup(playerB, readIn);
        textGUI.removeWindow(printW);
        System.out.println("Your ships area ready!");
        System.out.println(ASCII_Art.asciiArt.get("Ship"));
        printW = birthWindowWithClose("Your ships area ready!" + ASCII_Art.asciiArt.get("Ship"), "Set",80,20);
        textGUI.addWindowAndWait(printW);
//        textGUI.removeWindow(printW);

        while (!exit) {

            //Player A turn
            if (whoseTurn == Constants.PLAYERA) {
                System.out.println("Player One, your turn!");// pass to readIn**********************

                exit = runTurn(playerA, readIn, playerB); //should be playerA in first spot???

                whoseTurn = Constants.PLAYERB;
            }

            //Player B turn
            else if (whoseTurn == Constants.PLAYERB) {
                System.out.println("Player Two, your turn!");

                exit = runTurn(playerB, readIn, playerA);

                whoseTurn = Constants.PLAYERA;
            }
        }
        printW = birthWindowWithClose("Winner let's celebrate!" + ASCII_Art.asciiArt.get("Champagne"), "Conglaturations!!", 80, 60);
        textGUI.addWindowAndWait(printW);
        System.out.println("Winner let's celebrate!");
        System.out.println(ASCII_Art.asciiArt.get("Champagne"));
        a1 = printW.getPosition();
        printW.setPosition(new TerminalPosition(a1.getColumn() + 80, 0));
        //textGUI.removeWindow(printW);

        Window pooW = birthWindowWithClose("Here is some poop for the loser!" + ASCII_Art.asciiArt.get("Poo"), "YOU SUCK", 80, 20);
        textGUI.addWindowAndWait(pooW);
        TerminalPosition a2 = pooW.getPosition();
        pooW.setPosition(new TerminalPosition(a2.getColumn() + 80, 10));
        System.out.println("Here is some poop for the loser!");
        System.out.println(ASCII_Art.asciiArt.get("Poo"));
        //textGUI.removeWindow(printW);
    }


    private Boolean runTurn(Player whichPlayer, Scanner readIn, Player enemy) {
        //display board in gui
        Panel panel = new Panel();
        Label label = new Label(whichPlayer.getB().printBoard());
        panel.addComponent(label);
        Window gameBoard = new BasicWindow(whichPlayer.getName() + "'s Board");
        gameBoard.setFixedSize(new TerminalSize(20, 10));
        textGUI.addWindow(gameBoard);
        label.setText(whichPlayer.getB().printBoard());
        panel.addComponent(label);
        gameBoard.setComponent(panel);
        //gameBoard.setPosition(TerminalPosition.TOP_LEFT_CORNER);

        String record = guiSuperPrintRecord(whichPlayer.getRecord(), whichPlayer.getRevealed());
        Window recordDisplay = birthWindow(record, whichPlayer.getName() + "'s Record",35, 10);


        boolean endGame = false;
        int correctInput = 0;
        if (whichPlayer.getSonar()) {
            superPrintRecord(whichPlayer.getRecord(), whichPlayer.getRevealed());
        } else {
            whichPlayer.printRecord();
        }
        textGUI.addWindow(recordDisplay);
        TerminalPosition displayPos = recordDisplay.getPosition();
        recordDisplay.setPosition(new TerminalPosition(displayPos.getColumn() + 20, 0));

        System.out.println();
        whichPlayer.getB().printBoard();
        if (whichPlayer.getCountHits() == 3) {
            whichPlayer.setNuke();
        }
        do {
            System.out.println("----------");
            System.out.println("Select an option:");
            System.out.println("1.End");
            System.out.println("2.Move Fleet");
            System.out.println("3.Attack Opponent");
            System.out.println("4.User Perk");
            String menu = "----------" + "\n" + "Select an option:" + "\n" + "1.End" + "\n" + "2.Move Fleet" + "\n" + "3.Attack Opponent" + "\n" + "4.User Perk" + "\n";
            Window w = birthWindow(menu, "MENU", 20, 10);
            textGUI.addWindow(w);
            TerminalPosition s = w.getPosition();
            w.setPosition(new TerminalPosition(s.getColumn() + 10, s.getRow() + 20));
            int select = Integer.parseInt(readIn());  // Read user input
            textGUI.removeWindow(w);

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
                    if (correctInput == 1) {
                        endGame = true;
                    }
                    break;

                default:
                    Window errW = birthWindowWithClose("Please enter a choice from the menu","ERROR",40,1);
                    textGUI.addWindowAndWait(errW);
                    System.out.println("Please enter a choice from the menu"); // WINDOW HERE!!!!!!!!!!!!!
                    correctInput = -1;
                    break;
            }
        } while (correctInput == -1);
        textGUI.removeWindow(gameBoard);
        textGUI.removeWindow(recordDisplay);
        return endGame;
    }

    private void boardSetup(Player p, Scanner readIn) {
        Ship[] listOfShips = {new BattleShip(), new Submarine(), new Destroyer(), new MineSweeper()};

        Panel panel = new Panel();
        Label label = new Label(p.getB().printBoard());
        panel.addComponent(label);
        Window gameBoard = new BasicWindow(p.getName() + "'s Board");
        gameBoard.setFixedSize(new TerminalSize(20, 10));
        gameBoard.setComponent(panel);
        textGUI.addWindow(gameBoard);
        TerminalPosition a = gameBoard.getPosition();
        gameBoard.setPosition(new TerminalPosition(0, 0));

        for (Ship ship : listOfShips) {
            int isError = 0;
            String[] coords;
            String input = "";

            do {
                try {
                    Window w = birthWindow("Enter x and y coordinates for the endpoints of your " + ship.showShipType() + " separated by spaces:", "SET YOUR SHIPS", 100, 2);
                    textGUI.addWindow(w);
                    TerminalPosition s = w.getPosition();
                    w.setPosition(new TerminalPosition(s.getColumn() + 10, s.getRow() + 20));
                    input = readIn();
                    textGUI.removeWindow(w);
                    coords = input.split("\\s");
                    isError = p.placeShip(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2]), Integer.parseInt(coords[3]), ship.getShipHealth(), ship.showShipType());
                    if(isError == Constants.ERROR){
                        String message = p.getB().getMessage();
                        Window messageW = birthWindowWithClose(message, "ERROR",40,2);
                        textGUI.addWindowAndWait(messageW);
                    }
                    //panel.setLayoutManager(new GridLayout(1));
                    label.setText(p.getB().printBoard());
                    panel.addComponent(label);
                    gameBoard.setComponent(panel);

                    //tw.putString(p.getB().printBoard());
                } catch (Exception e) {
                    System.out.println("ERROR: problem with input. Re-enter coordinates");
                }
            } while (!(input.matches("\\d\\s\\d\\s\\d\\s\\d")) | isError == Constants.ERROR); //Use single | so no short circuiting
        }

        textGUI.removeWindow(gameBoard);

    }

    private void userMoveFleet(Player p, Scanner readIn) {
        char direction;
        ArrayList<Character> validDirections = new ArrayList<>();
        validDirections.add('N');
        validDirections.add('S');
        validDirections.add('E');
        validDirections.add('W');
        do {
            System.out.println("Enter the Compass direction in which to move your fleet:");
            System.out.println("Please enter 'N', 'S', 'E', or 'W'");
            String s = "Enter the Compass direction in which to move your fleet:" + "\n" + "Please enter 'N', 'S', 'E', or 'W'" + "\n";
            Window w = birthWindow(s, p.getName(), 40, 5);
            textGUI.addWindow(w);
            TerminalPosition x = w.getPosition();
            w.setPosition(new TerminalPosition(x.getColumn() + 10, x.getRow() + 20));
            direction = Character.toUpperCase(readIn().charAt(0)); //readIn.next().charAt(0)
            if(!(validDirections.contains(direction))){
                Window errW = birthWindowWithClose(s,"ERROR",40,5);
                textGUI.addWindowAndWait(errW);
            }
            textGUI.removeWindow(w);
            p.moveFleetPlayer(direction);
        } while (!(validDirections.contains(direction)));
    }


    private int userAttackOpponent(Player whichPlayer, Scanner readIn, Player enemy) {
        int x;
        int y;
        String input;
        String[] coords;
        do {
            System.out.println("Enter x and y coordinates for the location you want to attack separated by spaces:");
            String st = "Enter x and y coordinates for the location you want to attack separated by spaces:";
            Window w = birthWindow(st, whichPlayer.getName(), 20, 10);
            textGUI.addWindow(w);
            TerminalPosition a = w.getPosition();
            w.setPosition(new TerminalPosition(a.getColumn() + 10, a.getRow() + 20));
            input = readIn();//.nextLine();
            textGUI.removeWindow(w);
            coords = input.split("\\s");

            if(!(input.matches("\\d\\s\\d"))){
                Window err = birthWindowWithClose("Out of bounds!", "ERROR",20,2);
                textGUI.addWindowAndWait(err);
            }

        } while (!(input.matches("\\d\\s\\d")));//| isError == Constants.ERROR
        Boolean code = whichPlayer.getActivationCode();
        x = Integer.parseInt(coords[0]);
        y = Integer.parseInt(coords[1]);
        compare(whichPlayer.hit(x, y, enemy, code), whichPlayer.lookupRecord(x, y));
        return Constants.NONEERROR;
    }

    private void compare(int hitStat, int record) {
        //Window cw = birthWindow()
        StringBuilder sb = new StringBuilder();
        if (record == 0 && hitStat == 0) {
            System.out.println("You've hit water!");
            sb.append("You've hit water!");
        } else if (record == 1 && hitStat == 0) {
            System.out.println("You've hit a ship!");
            System.out.println("Have some pie!");
            System.out.println(ASCII_Art.asciiArt.get("Pie"));
            sb.append("You've hit a ship!"+"\n"+"Have some pie!"+"\n"+ASCII_Art.asciiArt.get("Pie"));
        } else if (record == 3 && hitStat == 2) {
            System.out.println("You've hit a ship!");
            System.out.println("Have some pie!");
            System.out.println(ASCII_Art.asciiArt.get("Pie"));
            sb.append("You've hit a ship!"+"\n"+"Have some pie!"+"\n"+ASCII_Art.asciiArt.get("Pie"));
        } else if (record == 1 && hitStat == 1) {
            System.out.println("You've hit the captain's quarters of a ship!");
            System.out.println("Have some pie!");
            System.out.println(ASCII_Art.asciiArt.get("Pie"));
            sb.append("You've hit the captain's quarters of a ship!"+"\n"+"Have some pie!"+"\n"+ASCII_Art.asciiArt.get("Pie"));
        } else if (record == 3 && hitStat == 3) {
            System.out.println("You've hit the captain's quarters of a ship!");
            System.out.println("Have some pie!");
            System.out.println(ASCII_Art.asciiArt.get("Pie"));
            sb.append("You've hit the captain's quarters of a ship!"+"\n"+"Have some pie!"+"\n"+ASCII_Art.asciiArt.get("Pie"));
        } else if (record == 3 && hitStat == 0) {
            System.out.println("You've hit part of a ship and a submarine!");
            System.out.println("Have some pie!");
            System.out.println(ASCII_Art.asciiArt.get("Pie"));
            sb.append("You've hit part of a ship and a submarine!"+"\n"+"Have some pie!"+"\n"+ASCII_Art.asciiArt.get("Pie"));
        } else if (record == 2 && hitStat == 0) {
            System.out.println("You've hit a submarine!");
            System.out.println("Have some pie!");
            System.out.println(ASCII_Art.asciiArt.get("Pie"));
            sb.append("You've hit a submarine!"+"\n"+"Have some pie!"+"\n"+ASCII_Art.asciiArt.get("Pie"));
        } else if (record == 2 && hitStat == 2) {
            System.out.println("You've hit the captain's quarters of a submarine!");
            System.out.println("Have some pie!");
            System.out.println(ASCII_Art.asciiArt.get("Pie"));
            sb.append("You've hit the captain's quarters of a submarine!"+"\n"+"Have some pie!"+"\n"+ASCII_Art.asciiArt.get("Pie"));
        } else if (record == 3 && hitStat == 1) {
            System.out.println("You've hit the captain's quarters of a stacked ship with laser!");
            System.out.println("Have some pie!");
            System.out.println(ASCII_Art.asciiArt.get("Pie"));
            sb.append("You've hit the captain's quarters of a stacked ship with laser!"+"\n"+"Have some pie!"+"\n"+ASCII_Art.asciiArt.get("Pie"));
        }
        String s = sb.toString();
        Window ws = birthWindowWithClose(s, "Message",80,20);
        textGUI.addWindowAndWait(ws);

    }

    private int dealPerks(Player currentPlayer, Scanner readIn, Player enemy, int correctInput) {
        int correctI;
        do {
            System.out.println("----------");
            System.out.println("Select an option:");
            System.out.println("1.Nuke");
            System.out.println("2.Sonar");
            System.out.println("3.B2 Bomber");
            System.out.println("4.Exit perks");
            String menu = "----------" + "\n" + "Select an option:" + "\n" + "1.Nuke" + "\n" + "2.Sonar" + "\n" + "3.B2 Bomber" + "\n" + "4.Exit perks" + "\n";
            Window w = birthWindow(menu, "PERKS MENU", 20, 10);
            textGUI.addWindow(w);
            TerminalPosition a = w.getPosition();
            w.setPosition(new TerminalPosition(a.getColumn() + 10, a.getRow() + 20));
            int select = Integer.parseInt(readIn());//.nextInt();  // Read user input
            textGUI.removeWindow(w);
            switch (select) {
                case 1:
                    if (currentPlayer.getNuke()) {
                        Window nukeW = birthWindowWithClose("You just nuked your enemy!"+"\n"+ASCII_Art.asciiArt.get("Nuke"),"Nuked",80,20);
                        textGUI.addWindowAndWait(nukeW);
                        System.out.println("You just nuked your enemy!");
                        System.out.println(ASCII_Art.asciiArt.get("Nuke"));
                        //end = true;
                        correctInput = 1;
                        correctI = 0;
                    } else {
                        Window nukeW = birthWindowWithClose("Nuke not available!","you sad, no nuke",80,20);
                        textGUI.addWindowAndWait(nukeW);
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
                        String st = "Enter x and y coordinates for the location you want use the sonar at:";
                        w = birthWindow(st, "SONAR", 100, 2);
                        textGUI.addWindow(w);
                        a = w.getPosition();
                        w.setPosition(new TerminalPosition(a.getColumn() + 10, a.getRow() + 20));
                        input = readIn();//.nextLine();
                        textGUI.removeWindow(w);
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
                    HashMap<Integer, Point> target = currentPlayer.b2Bomber(enemy);
                    int stat = 0;
                    Point c = new Point();
                    for (Map.Entry<Integer, Point> pair : target.entrySet()) {
                        stat = pair.getKey();
                        c = pair.getValue();
                    }
                    int x = c.x;
                    int y = c.y;
                    compare(stat, currentPlayer.lookupRecord(x, y));
                    correctInput = 0;
                    correctI = 0;
                    //disable bomber

                    break;
                case 4:
                    correctInput = -1;
                    correctI = 0;
                    break;
                default:
                    Window errW = birthWindowWithClose("Please enter a choice from the menu","Error",10,20);
                    textGUI.addWindowAndWait(errW);
                    System.out.println("Please enter a choice from the menu");
                    correctI = -1;
                    break;
            }
        } while (correctI == -1);
        return correctInput;
    }

    public String guiSuperPrintRecord(HashMap<Point, Integer> record, HashMap<Point, Integer> rev) {
        int xyValue;
        StringBuilder guiRecord = new StringBuilder();
        for (int y = 9; y >= 0; y--) {
            for (int x = 0; x < 10; x++) {
                try {
                    if (record.containsKey(new Point(x, y))) {
                        xyValue = record.get(new Point(x, y));
                        if (xyValue == 1 || xyValue == 2 || xyValue == 3) {
                            guiRecord.append(" H ");
                        } else {
                            guiRecord.append(" M ");
                        }
                    } else {
                        xyValue = rev.get(new Point(x, y));
                        if (xyValue == 1 || xyValue == 2 || xyValue == 3) {
                            guiRecord.append(" " + xyValue + " ");
                        } else {
                            guiRecord.append(" 0 ");
                        }
                    }
                } catch (Exception e) {
                    guiRecord.append(" - ");
                }
            }
            guiRecord.append('\n');
        }
        return guiRecord.toString();
    }

    public void superPrintRecord(HashMap<Point, Integer> record, HashMap<Point, Integer> rev) {
        int xyValue;
        for (int y = 9; y >= 0; y--) {
            for (int x = 0; x < 10; x++) {
                try {
                    if (record.containsKey(new Point(x, y))) {
                        xyValue = record.get(new Point(x, y));
                        if (xyValue == 1 || xyValue == 2 || xyValue == 3) {
                            System.out.print(" H ");
                        } else {
                            System.out.print(" M ");
                        }
                    } else {
                        xyValue = rev.get(new Point(x, y));
                        if (xyValue == 1 || xyValue == 2 || xyValue == 3) {
                            System.out.print(" " + xyValue + " ");
                        } else {
                            System.out.print(" 0 ");
                        }
                    }
                } catch (Exception e) {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }

    public String readIn() {

        return new TextInputDialogBuilder()
                .setTitle("-")
                .setTextBoxSize(new TerminalSize(35, 5))
                .build()
                .showDialog(textGUI);
    }

    public Window birthWindow(String info, String title, int col, int row) {
        Panel panel = new Panel();
        Label label = new Label(info);
        panel.addComponent(label);

        Window w = new BasicWindow(title);
        w.setFixedSize(new TerminalSize(col, row));
        w.setComponent(panel);
        return w;
    }

    public Window birthWindowWithClose(String info, String title,int col, int row) {
        Panel panel = new Panel();
        Label label = new Label(info);
        panel.addComponent(label);

        Window w = new BasicWindow(title);
        w.setFixedSize(new TerminalSize(col, row));
        w.setComponent(panel);
        panel.addComponent(new Button("Close", w::close));
        return w;
    }
}
















