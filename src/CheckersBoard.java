package edu.ucsb.cs56.projects.games.checkers;

import java.util.*;

/** A series of JUnit tests to test checkerboard and moves
   @author Ryan Kroner
   @author Graham Foster
   @author Matthew Maatubang
   @version UCSB CS56, F17
 */

public class CheckersBoard implements CheckersGame {

	// BOARD VARS
	private char[][] pieces         = new char[8][8]; // 2D array of pieces
	private char turn               = 'x';            // x alwayds starts
	private char winner             = ' ';            // changed to either x,o, or t at end of game
	private int xCount              = 12;             // count of x pieces
	private int oCount              = 12;             // count of o pieces


	// MOVEMENT DIRECTION VARS
	private int forward;
	private int backward;
	private int left;
	private int right;
	private int jumpForward;
	private int jumpBackward;
	private int jumpLeft;
	private int jumpRight;
	private int forwardOffset;
	private int kingOffset;
	private int jumpedI;
	private int jumpedJ;
	private static int X_MOVEMENT	= -1;
	private static int O_MOVEMENT	= 1;
	private static int LEFT_OFFSET  = -1;
	private static int RIGHT_OFFSET = 1;
	private static int JUMP_FACTOR  = 2;
	private boolean canJump         = false;          // true if can jump, false if not
	private boolean validMove       = false;

	private Stack<char [][]> history=new Stack<char [][]>();
    private Stack<Integer> xstack = new Stack<Integer>();
	private Stack<Integer> ostack = new Stack<Integer>();
	private Stack<Integer> kingstack = new Stack<Integer>();
	private ArrayList<String> hisText=new ArrayList<String>();
	// BOARD FUNCTIONS

	/**
	*
	*/
	public CheckersBoard() {
		for (int j = 0; j < 8; j++) {
			if (j % 2 == 0) {
				pieces[0][j] = ' ';
				pieces[1][j] = 'o';
				pieces[2][j] = ' ';
				pieces[3][j] = ' ';
				pieces[4][j] = ' ';
				pieces[5][j] = 'x';
				pieces[6][j] = ' ';
				pieces[7][j] = 'x';
			} else {
				pieces[0][j] = 'o';
				pieces[1][j] = ' ';
				pieces[2][j] = 'o';
				pieces[3][j] = ' ';
				pieces[4][j] = ' ';
				pieces[5][j] = ' ';
				pieces[6][j] = 'x';
				pieces[7][j] = ' ';
			}
		}
	}
/////////////////////////////////////////////////////////////////
	public void recordText(String s1, String s2){
		hisText.add(turn+" moved from "+s1+" to "+s2);
	
	}

	public void printText(){
		for(int i=0; i<hisText.size();i++){
			System.out.println(i+": "+hisText.get(i));
		}
	
	}
	public void recordHistory(){
		char[][] temp = new char[8][8];
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				temp[i][j]=pieces[i][j];
			
			}
		}
		int tempo=oCount;
		int tempx=xCount;
		int tempk=kingOffset;
		history.push(temp);
		ostack.push(tempo);
		xstack.push(tempx);
		kingstack.push(tempk);
	}

	public void retract(int times){
		for(int i=times;i>0;i--){
			history.pop();
			ostack.pop();
			xstack.pop();
			kingstack.pop();

			hisText.remove(hisText.size()-1);
		}
		pieces=history.pop();
		resetMoves();
		oCount=ostack.pop();
		xCount=xstack.pop();
		kingOffset=kingstack.pop();
		//validMove=true;
		if(times%2==0){
			if(turn=='x')
				turn='o';
			else{
				turn='x';
			}
		}
	
	}

	public void retractgui(int times){
		pieces=history.pop();
		resetMoves();
		oCount=ostack.pop();
		xCount=xstack.pop();
		kingOffset=kingstack.pop();
		//validMove=true;
	
		if(turn=='x')
			turn='o';
		else{
			turn='x';
			}
		
	
	}

////////////////////////////////////////////////////////////////////	

	/** A toString method for the CheckersBoard, allows easier access for console based interface
	 * @return result The result of the toString(), aka the gameBoard
	 */
	public String toString() {
		String result;
		result = " | A | B | C | D | E | F | G | H |";

		for (int i = 0; i < 8; i++) {
			result += "\n-+---+---+---+---+---+---+---+---+\n";
			result += Integer.toString(i + 1) + "|";
			for (int j = 0; j < 8; j++) {
				result += " " +  pieces[i][j] + " |";
			}
		}
		result += "\n-+---+---+---+---+---+---+---+---+\n";
		return result;
	}

	public char getTurn() { return turn; }

	public void changeTurn() {
		if (turn == 'x') turn = 'o';
		else turn = 'x';
		resetMoves();
	}

	public char getWinner() { return winner; }

	public void checkWinner() {
		if (xCount == 0) {
			winner = 'o';
		} else if (oCount == 0) {
			winner = 'x';
		}
	}

	public int getXCount() { return xCount; }

	public int getOCount() { return oCount; }

	public char getPiece(int i, int j) { return pieces[i][j]; }

	// MOVEMENT FUNCTIONS 

	public void move(int fromI, int fromJ, int toI, int toJ) {
		setMoves(fromI, fromJ);
		validMove(toI, toJ);

		if (validMove) { // Valid coordinates input, execute the move
			pieces[toI][toJ] = pieces[fromI][fromJ];
			pieces[fromI][fromJ] = ' ';
			if (canJump) { // Remove the jumped over piece
				if (turn == 'x') oCount--;
				else xCount--;
				pieces[jumpedI][jumpedJ] = ' ';
			}
			if (makeKing(toI, toJ)) {
				pieces[toI][toJ] = Character.toUpperCase(pieces[toI][toJ]);
			}
		}
	}

	public boolean moveWasMade() { return validMove; }

	/** Move method that allows the user to move
	 * Will throw a CheckersIllegalMoveException if the from index is a spot that you don't own, or if you are trying to move to a spot that is not valid
	 *  @param from the integer value of the index of the spot you are moving from
	 */
	private void setMoves(int fromI, int fromJ) {
		if (!correctOwner(fromI, fromJ)) {
			//	    throw new CheckersIllegalMoveException("Thats not your piece");
			System.out.println("Thats not your piece");
			validMove = false;
			return;
		}

		if (turn == 'x') forwardOffset      = X_MOVEMENT;
		else forwardOffset                  = O_MOVEMENT;
		kingOffset = forwardOffset * -1;

		forward = fromI + forwardOffset;
		left    = fromJ + LEFT_OFFSET;
		right   = fromJ + RIGHT_OFFSET;

		jumpForward = fromI + forwardOffset * JUMP_FACTOR;
		jumpLeft    = fromJ + LEFT_OFFSET   * JUMP_FACTOR;
		jumpRight   = fromJ + RIGHT_OFFSET  * JUMP_FACTOR;

		if (Character.toUpperCase(turn) == pieces[fromI][fromJ]) {
			backward     = fromI + kingOffset;
			jumpBackward = fromI + kingOffset * JUMP_FACTOR;
		} else { // Non kings cannot move backward
			backward 	 = -1; 
			jumpBackward = -1;
		}
	}

	private void validMove(int toI, int toJ) {
		boolean validLatMove, validLongMove, validLatJump, validLongJump;

		validLatMove  = ((toJ == left) 			|| (toJ == right)		);
		validLatJump  = ((toJ == jumpLeft) 		|| (toJ == jumpRight)	);
		validLongMove = ((toI == forward) 		|| (toI == backward)	);
		validLongJump = ((toI == jumpForward) 	|| (toI == jumpBackward));

		if (validLatMove && validLongMove) { // tired to combine all this into one if statement with ||
			squareOpen(toI, toJ);          // but wasnt working and worked when separated
		} else if (validLatJump && validLongJump) { //see comment above
			checkJump(toI, toJ);
		} else { // Coords were valid but spot wasn't correct
		validMove = false;
		}
	}

	private void squareOpen(int i, int j) {
		validMove = (pieces[i][j] == ' ');
	}

	/** Checks if the index you are moving from is owned by the correct owner
	 * @param i,j The index of the spot you are trying to move
	 * @return True if you own the spot, or False if you do not own it
	 */
	private boolean correctOwner(int i, int j) {
		return ( (turn == pieces[i][j]) ||
			(Character.toUpperCase(turn) == pieces[i][j]) );
	}

	private void jumped(int i, int j) {
		if (turn == 'x') validMove = ((pieces[i][j] == 'o') || (pieces[i][j] == 'O'));
		else validMove = ((pieces[i][j] == 'x') || (pieces[i][j] == 'X') );
	}

	/** Checks if the user input a valid jump, sets validMove to whether it was
	* @param toI and toJ are valid jump coordinates from fromI and fromJ
	*/
	private void checkJump(int toI, int toJ) {
		int checkI, checkJ;

		// Set the check coords to the square that was jumped over
		if (toI == jumpForward) {
			checkI  = forward;
		} else
		checkI  = backward;

		if (toJ == jumpLeft) {
			checkJ  = left;
		} else
		checkJ  = right;

		// Make sure that square had an opponent
		jumped(checkI, checkJ);

		if (validMove) {
			canJump = true;
			jumpedI = checkI;
			jumpedJ = checkJ;
		} else {
			canJump = false;
		}

	}

	private Boolean makeKing(int i, int j) {
		if (turn == 'x') return (i == 0 && (j % 2 == 1));
		else             return (i == 7 && (j % 2 == 0));
	}

	/** A formatting function that allows multiple different types of inputs
	 * @param input from user, either piece theyre selecting or spot they want to move to
	 * @return int array holding { x coordinate, y coordinate }
	 */
	public int[] parseInput(String s) {
		char Cpre, Csuf;
		int[] coords = new int[]{ -1, -1 };

		// S isn't 2 chars, invalid, return entry
		if (s.length() != 2) return coords;

		Cpre = s.charAt(0);
		Csuf = s.charAt(1);

		// First make sure that format is <Letter><Digit>
		if (Character.isDigit(Cpre) && Character.isLetter(Csuf)) { // Format is <Digit><Letter>, switch it
			char temp = Csuf;
			Csuf = Cpre;
			Cpre = temp;
		} else if (!(Character.isLetter(Cpre) && Character.isDigit(Csuf))) { // Was something other than a letter and digit
			return coords;
		}
		Cpre = Character.toUpperCase(Cpre);

		coords[1] = Cpre - 'A';
		coords[0] = Character.getNumericValue(Csuf) - 1;

		if (coords[0] < 0 || coords[0] > 7 || coords[1] < 0 || coords[1] > 7)
			coords[0] = -1;
		return coords;
	}

	public void resetMoves() {
		forward = -1;
		backward = -1;
		left = -1;
		right = -1;
		forwardOffset = -1;
		kingOffset = -1 ;
		jumpedI = -1;
		jumpedJ = -1;
		canJump = false;
		validMove = false;
	}

}//end class CheckersBoard
