package edu.colorado.team6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game g;

    @Test
    public void testGame() {
        String input = "Neel\n" // Player A name
                    + "Stefan\n" // Player B name
                    + "1 0 1 3\n" // Player A set battleship
                    + "1 0 1 3\n" // Player A set submarine
                    + "2 0 2 2\n" // Player A set destroyer
                    + "3 0 3 1\n" // Player A set minesweeper
                    + "0 0 0 3\n" // Player B set battleship
                    + "1 0 1 3\n" // Player B set submarine
                    + "2 0 2 347rfed\n" // Player B try set destroyer with bad input
                    + "2 0 2 2\n" // Player B set destroyer
                    + "3 0 3 1\n" // Player B set minesweeper
                    + "2\n" // Player A choose to move fleet
                    + "N\n" // Player A move fleet North
                    + "2\n" // Player B choose to move fleet
                    + "E\n" // Player B move fleet East

                    // BOARDS AFTER MOVE FLEETS
                    // Player A board
                    // 0 0 0 0 0 0 0 0 0 0
                    // 0 0 0 0 0 0 0 0 0 0
                    // 0 0 0 0 0 0 0 0 0 0
                    // 0 0 0 0 0 0 0 0 0 0
                    // 0 0 0 0 0 0 0 0 0 0
                    // 0 3 0 0 0 0 0 0 0 0
                    // 2 3 1 0 0 0 0 0 0 0
                    // 0 3 1 1 0 0 0 0 0 0
                    // 0 3 1 1 0 0 0 0 0 0
                    // 0 0 0 0 0 0 0 0 0 0
                    //
                    //
                    // Player B board
                    // 0 0 0 0 0 0 0 0 0 0
                    // 0 0 0 0 0 0 0 0 0 0
                    // 0 0 0 0 0 0 0 0 0 0
                    // 0 0 0 0 0 0 0 0 0 0
                    // 0 0 0 0 0 0 0 0 0 0
                    // 0 0 0 0 0 0 0 0 0 0
                    // 0 1 2 0 0 0 0 0 0 0
                    // 0 3 2 1 0 0 0 0 0 0
                    // 0 1 2 1 1 0 0 0 0 0
                    // 0 1 2 1 1 0 0 0 0 0


                + "3\n" // Player A attack opponent
                    + "0 0\n" // Player A attack opponent at (0, 0), hit sea
                    + "3\n" // Player B attack opponent
                    + "yilegfvifu\n" // Player B try to attack opponent with wrong input
                    + "0 0\n" // Player B attack opponent at (0, 0)
                    + "3\n" // Player A attack opponent just ship
                    + "1 1\n"
                    + "3\n" // Player B attack opponent submarine
                    + "1 1\n"
                    + "3\n" // Player A random move
                    + "9 9\n"
                    + "3\n" // Player B attack opponent on non-sub captains quarters
                    + "2 2\n"
                    + "3\n" // Player A sink B's minesweeper, so A can use laser
                    + "4 0\n"
                    + "3\n" // Player B random hit ot move on to A to use laser
                    + "0 0\n"
                    + "3\n" // Player A attack opponent stacked ships with laser
                    + "1 2\n"
                    + "3\n" // Player B random move
                    + "0 0\n"
                    + "3\n" // Player A attack opponent sub with laser
                    + "2 0\n"
                    + "3\n" // Player B random move
                    + "0 0\n"
                    + "3\n" // Player A attack sub captain's quarters with laser
                    + "2 3\n"
                    + "2345\n" // Player A enter choice not on menu
                    + "1\n"; // Player B end game


        InputStream stdin = System.in;

        // Input player A's name
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        //Scanner scanner = new Scanner(System.in);
        g = new Game();

        System.setIn(stdin);
    }

}