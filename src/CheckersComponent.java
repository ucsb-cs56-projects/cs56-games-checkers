package edu.ucsb.cs56.projects.games.checkers;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event. ActionEvent;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JTextArea;

/** A series of JUnit tests to test checkerboard and moves
   @author Ryan Kroner
   @author Graham Foster
   @author Matthew Maatubang
   @version UCSB CS56, F17
 */

public class CheckersComponent extends JComponent
{
    private CheckersGame game;
    private MessageDestination md;

    private JButton[][] squares  = new JButton[8][8];	//spots that an "x" or an "o" can go
    
    private int fromI = -1;
    private int fromJ = -1;
    private boolean validFrom = false;

    /** Constructor
     *
     * @param game a CheckersGame object that keeps track of the game 
     * @param md allows messages to be displayed under the game board in the GUI
     */
       
    public CheckersComponent(CheckersGame g, MessageDestination m) {
		super();
	
		game = g;	//A Game of Checkers
		md = m;		//md, where we can write messages

		// Sets number of rows to 8 (makes an 8x8 board).  This for loop sets up the board	
		this.setLayout(new GridLayout(8,0));
	
		// Sets up the board initially (spots you can move to)
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
			        squares[i][j] = makeButton(game.getPiece(i, j), i, j);
				this.add(squares[i][j]);
			}
		}

		md.append(game.getTurn() + "'s turn:\n");
    } // End constructor 
    
    public JButton makeButton(char c, int i, int j){
	        String label = String.valueOf(c);
		JButton jb = new JButton(label);
		jb.addActionListener(new ButtonListener(i, j)); 
		return jb;
    }
    
    //function used to set the spots on the GUI after performing a move
    public void reprintBoard(){
    	JButton jb;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				jb = squares[i][j];
				jb.setText(String.valueOf(game.getPiece(i, j)));
			}
		}

    }

    class ButtonListener implements ActionListener {    
		private int currentI, currentJ;	// Index of the button that is pressed

		public ButtonListener(int i, int j) {
	    	super();
	    	currentI = i;
	    	currentJ = j;
		}

		public void actionPerformed (ActionEvent event) {
			char turn = game.getTurn();
			char currentPiece = game.getPiece(currentI, currentJ);
		
			if (!validFrom) { // Click to select piece
				if ( (currentPiece == turn) || (currentPiece == Character.toUpperCase(turn)) ) { // Selected the correct players piece
					fromI = currentI;
					fromJ = currentJ;
					validFrom = true;
				} else{
					md.append("You don't own this square, please select an " + turn + " piece that you own\n");
				}
			} else { // Click to select destination
				game.move(fromI, fromJ, currentI, currentJ);
				if (!game.moveWasMade()) {
					md.append("Invalid move, try again");
					validFrom = false;
					return;
				} 
				reprintBoard();			

				game.changeTurn();

				if(game.getWinner() == 'x'){
					md.append("X Wins!\n");
					return;
				} else if (game.getWinner() == 'o'){
					md.append("O Wins!\n");
					return;
				}

				md.append(game.getTurn() + "'s turn:\n");
				validFrom = false;
			}
			return;
		}
    } // End class ButtonListener

} // End class CheckersComponent

