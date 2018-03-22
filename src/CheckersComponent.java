package edu.ucsb.cs56.projects.games.checkers;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Icon;
import javax.swing.text.JTextComponent;
import javax.swing.text.DefaultCaret;

/** A series of JUnit tests to test checkerboard and moves
   @author Ryan Kroner
   @author Graham Foster
   @author Matthew Maatubang
   @version UCSB CS56, F17
 */

public class CheckersComponent extends JComponent
{
    private CheckersGame game;
    private JTextArea md;
    private JTextArea hist;
    private JTextField jf;
    private JButton[][] squares  = new JButton[8][8];	//spots that an "x" or an "o" can go
    private int fromI = -1;
    private int fromJ = -1;
    private boolean retract = false;
    private boolean validFrom = false;

    /** Constructor
     *
     * @param game a CheckersGame object that keeps track of the game 
     * @param md allows messages to be displayed under the game board in the GUI
     */
       
    public CheckersComponent(CheckersGame g, JTextArea m, JTextArea h, JTextField j1) {
		super();
	
		game = g;	//A Game of Checkers
		md = m;	//md, where we can write messages
		DefaultCaret caret = (DefaultCaret)md.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		hist=h;
		jf=j1;
		// Sets number of rows to 8 (makes an 8x8 board).  This for loop sets up the board	
		this.setLayout(new GridLayout(8,0));
	
		// Sets up the board initially (spots you can move to)
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
			        squares[i][j] = makeButton(game.getPiece(i, j), i, j);
			        
				this.add(squares[i][j]);
			}
		}
		
		md.append(game.getTurn() + "'s turn, Or you can go back to previous turn by enter a number in the box above\n");
		hist.append("HISTORY:\n");
		jf.addActionListener(new FieldListener());
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

    class FieldListener implements ActionListener{
    	public void actionPerformed(ActionEvent event){
    		String k = jf.getText();
    		int z = Integer.parseInt(String.valueOf(k));
    		reprintBoard();
    		//game.set(6,1);
    		/*for (int i =0;i<8;i++){
    			for (int j = 0; j < 8; j++){
    				hist.append(String.valueOf(game.ep(i,j)));

    			}
    			hist.append("\n");
    		}*/
    		if (z>hist.getRows()-1){
    			md.append("the retract time is invalid, please let input be smaller than "+ hist.getRows()+".\n");
    			return;
    		}
    		for (int i =0; i < z; i++){
    			game.retractgui(1);
    			String a = hist.getText();
    			int b = a.length();
				hist.replaceRange("",b-28,b);
			}
			reprintBoard();
    		//hist.append(String.valueOf(game.ep()));


    		//game.retract(1);
    		/*for (int i =0;i<8;i++){
    			for (int j = 0; j < 8; j++){
    				hist.append(String.valueOf(game.getPiece(i,j)));

    			}
    			hist.append("\n");
    		}
    		hist.append("pig");*/
    		//game.move(5,2,4,3);
       		reprintBoard();
       		md.append("Retracted! It is " + game.getTurn() +" turn\n");
       		reprintBoard();
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
				    squares[currentI][currentJ].setForeground(Color.RED);
				    md.append("Select a square to move to\n");
					fromI = currentI;
					fromJ = currentJ;
					validFrom = true;
					//squares[currentI][currentJ].setForeground(Color.BLACK);
				} else{
					md.append("You don't own this square, please select an " + turn + " piece that you own\n");
				}
			} else { // Click to select destination
				game.recordHistory();
				squares[fromI][fromJ].setForeground(Color.BLACK);
				squares[currentI][currentJ].setForeground(Color.BLACK);
				game.move(fromI, fromJ, currentI, currentJ);
				if (!game.moveWasMade()) {
					md.append("Invalid move, try again\n");
					md.append("Select a " + turn + " piece to move from\n");
					validFrom = false;
					return;
				} 

				reprintBoard();
				int a=	8-fromI;
				int b=	fromJ+1;
				int c= 	8-currentI;
				int d=  currentJ+1;
				hist.append(turn + " moves from (" + b + ","+ a+ ") to (" + d +","+ c +")\n");
				game.changeTurn();
				


				if(game.getWinner() == 'x'){
					md.append("X Wins!\n");
					return;
				} else if (game.getWinner() == 'o'){
					md.append("O Wins!\n");
					return;
				}

				md.append(game.getTurn() + "'s turn:\n");
				md.append("Select a " + game.getTurn() + " piece to move from\n");
				validFrom = false;
			}
			return;
		}
    } // End class ButtonListener

} // End class CheckersComponent

