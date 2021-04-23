package edu.colorado.team6;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

class GameTest {
  private Game g;

  @Test
  public void testGame() throws IOException {
    String input =
        "Neel\n" // Player A name
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
            + "4\n" // A tries to use nuke, when is not available
            + "1\n"
            + "3\n" // dummy for A
            + "9 9\n"
            + "3\n" // dummy for B
            + "9 9\n"
            + "3\n" // A hit captains quarters of B's destroyer
            + "3 1\n" // 1 to 1
            + "3\n" // B Hit stacked ships with bomb
            + "1 1\n" // 3 to 2
            + "3\n" // Player A hit stacked ship captains quarter with bomb
            + "1 2\n" // 3 to 3
            + "3\n" // Player B hit submarine captain's quarters
            + "0 3\n" // 2 to 2
            + "3\n" // Player A sink minesweeper, so A has access to laser
            + "4 0\n" // 1 to 0
            + "3\n" // Player B hit minesweeper
            + "3 2\n" // 1 to 0 at position (3, 1)
            + "3\n" // Player A hit water
            + "9 9\n" // 0 to 0
            + "3\n" // Player B hit minesweeper quarters so B has access to laser
            + "3 1\n" // Not covering any of the cases
            + "3\n" // Player A hit submarine
            + "2 0\n" // 2 to 0
            + "3\n" // Player B hit stacked ships with submarine
            + "1 2\n" // 3 to 0
            + "3\n" // Player A random move
            + "9 9\n"
            + "3\n" // Player B hit battleships captains quarters
            + "1 3\n" // 3 to 1
            + "2345\n" // Player A enter choice not on menu
            + "4\n" // Player A access perks menu
            + "2\n" // Use sonar
            + "4 3\n"
            + "4\n" // Player B access perks menu
            + "844\n" // Try to use option not on menu
            + "2\n"
            + "3 2\n"
            + "4\n" // Player A use b2 bomber
            + "3\n"
            + "4\n" // Player B exit perks menu
            + "4\n"
            + "2\n"
            + "N\n"
            + "4\n" // Player A try to use b2 bomber again
            + "3\n"
            + "4\n" // Player B access perks menu
            + "1\n"; // Use nuke

    InputStream stdin = System.in;

    // Input player A's name
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    // Scanner scanner = new Scanner(System.in);
    g = new Game();

    System.setIn(stdin);
  }

  @Test
  public void testEndGame() throws IOException {
    String input =
        "Neel\n" // Player A name
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
            + "1\n"; // Player A end game
    InputStream stdin = System.in;

    // Input player A's name
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    // Scanner scanner = new Scanner(System.in);
    g = new Game();

    System.setIn(stdin);
  }

  @Test
  public void lanternaTest() throws IOException {
    Game g = new Game();
  }
}
