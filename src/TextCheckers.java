package edu.ucsb.cs56.projects.games.checkers;
import java.util.Scanner;

/** A command line implementation of the CheckersGame
   @author Ryan Kroner
   @author Graham Foster
   @author Matthew Maatubang
   @version UCSB CS56, F17
 */

public class TextCheckers {
	public static void main(String [] args) {
		Scanner sc          = new Scanner(System.in);
		int[] from          = new int[2];
		int[] to            = new int[2];
		boolean done        = false;
		boolean moveWasMade = true;
		char winner         = ' ';
		String input;


		System.out.println("To play, enter the piece coordinates of the piece you want to move, then enter, then the spot you want to move it to");

		CheckersBoard c = new CheckersBoard();

		while (!done) {
			System.out.println(c); // c.toString() implicitly invoked
			System.out.println("" + c.getTurn() + "'s turn: ");

			do {
				// Read from command line
				input = sc.next();
				from  = c.parseInput(input);
				input = sc.next();
				to    = c.parseInput(input);

				// If bad coordinates are entered or the move was invalid, ask for new inputs
				if ( from[0] == -1 || to[0] == -1 )  {
					System.out.println("Invalid coordinates, please input different coordinates:  ");
				} else {
					c.move(from[0], from[1], to[0], to[1]);
					moveWasMade = c.moveWasMade();
					if (!moveWasMade) {
						System.out.println("Invalid move, please input a valid move: ");
					}
				}
			} while (!moveWasMade);

			c.checkWinner();
			if (c.getWinner() != ' ') {
				winner = c.getWinner();
				done = true;
			}
			c.changeTurn();
		} // end while

		System.out.println(c);
		System.out.println(winner + " wins!");
		System.out.println("Game Over!");
	}

}
