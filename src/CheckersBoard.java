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
    private ArrayList<ArrayList<CheckersPiece>> pieces = new ArrayList<ArrayList<CheckersPiece>>();   // 2D array of pieces
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

    private Stack<char[][]> history = new Stack<char[][]>();
    private Stack<Integer> xstack = new Stack<Integer>();
	private Stack<Integer> ostack = new Stack<Integer>();
	private Stack<Integer> kingstack = new Stack<Integer>();
	private ArrayList<String> hisText=new ArrayList<String>();
	// BOARD FUNCTIONS

	/**
	*
	*/
	public CheckersBoard() {
		for (int i = 0; i < 8; i++){
			ArrayList<CheckersPiece> l1 = new ArrayList<CheckersPiece>();
			pieces.add(l1);
		}

		
		for (int j = 0; j < 8; j++) {
			if (j % 2 == 0) {
			    CheckersPiece a1 = new CheckersPiece(' ',0,j,false);
			    pieces.get(0).add(j,a1);
			    CheckersPiece a2 = new CheckersPiece('o',1,j,false);
			    pieces.get(1).add(j,a2);
			    CheckersPiece a3 = new CheckersPiece(' ',2,j,false);
			    pieces.get(2).add(j,a3);
			    CheckersPiece a4 = new CheckersPiece(' ',3,j,false);
			    pieces.get(3).add(j,a4);
			    CheckersPiece a5 = new CheckersPiece(' ',4,j,false);
			    pieces.get(4).add(j,a5);
			    CheckersPiece a6 = new CheckersPiece('x',5,j,false);
			    pieces.get(5).add(j,a6);
			    CheckersPiece a7 = new CheckersPiece(' ',6,j,false);
			    pieces.get(6).add(j,a7);
			    CheckersPiece a8 = new CheckersPiece('x',7,j,false);
			    pieces.get(7).add(j,a8);
			} else {
			    CheckersPiece b1 = new CheckersPiece('o',0,j,false);
			    pieces.get(0).add(j,b1);
			    CheckersPiece b2 = new CheckersPiece(' ',1,j,false);
			    pieces.get(1).add(j,b2);
			    CheckersPiece b3 = new CheckersPiece('o',2,j,false);
			    pieces.get(2).add(j,b3);
			    CheckersPiece b4 = new CheckersPiece(' ',3,j,false);
			    pieces.get(3).add(j,b4);
			    CheckersPiece b5 = new CheckersPiece(' ',4,j,false);
			    pieces.get(4).add(j,b5);
			    CheckersPiece b6 = new CheckersPiece(' ',5,j,false);
			    pieces.get(5).add(j,b6);
			    CheckersPiece b7 = new CheckersPiece('x',6,j,false);
			    pieces.get(6).add(j,b7);
			    CheckersPiece b8 = new CheckersPiece(' ',7,j,false);
			    pieces.get(7).add(j,b8);
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
				temp[i][j]=pieces.get(i).get(j).getName();
			}
		}
	    //ArrayList<ArrayList<CheckersPiece>> temp = new ArrayList<ArrayList<CheckersPiece>>();
	    //temp = pieces;
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

		char m[][]=new char[8][8];
		m=history.pop();

		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				pieces.get(i).get(j).setName(m[i][j]);
			}
		}
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
		//pieces=history.pop();
		char m[][]=new char[8][8];
		m=history.pop();

		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				pieces.get(i).get(j).setName(m[i][j]);
			}
		}
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
			    result += " " +  pieces.get(i).get(j).getName() + " |";
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

	public char getPiece(int i, int j) { return pieces.get(i).get(j).getName(); }

	// MOVEMENT FUNCTIONS 

	public void move(int fromI, int fromJ, int toI, int toJ) {
		setMoves(fromI, fromJ);
		validMove(toI, toJ);

		if (validMove) { // Valid coordinates input, execute the move
			//recordHistory();
		    pieces.get(toI).get(toJ).setName(pieces.get(fromI).get(fromJ).getName());
		    pieces.get(fromI).get(fromJ).setName(' ');
			if (canJump) { // Remove the jumped over piece
				if (turn == 'x') oCount--;
				else xCount--;
				pieces.get(jumpedI).get(jumpedJ).setName(' ');
			}
			if (makeKing(toI, toJ)) {
			    pieces.get(toI).get(toJ).setName(Character.toUpperCase(pieces.get(toI).get(toJ).getName()));
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

		if (Character.toUpperCase(turn) == pieces.get(fromI).get(fromJ).getName()) {
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
	    validMove = (pieces.get(i).get(j).getName() == ' ');
	}

	/** Checks if the index you are moving from is owned by the correct owner
	 * @param i,j The index of the spot you are trying to move
	 * @return True if you own the spot, or False if you do not own it
	 */
	private boolean correctOwner(int i, int j) {
	    return ( (turn == pieces.get(i).get(j).getName()) ||
			(Character.toUpperCase(turn) == pieces.get(i).get(j).getName()) );
	}

	private void jumped(int i, int j) {
	    if (turn == 'x') validMove = ((pieces.get(i).get(j).getName() == 'o') || (pieces.get(i).get(j).getName() == 'O'));
		else validMove = ((pieces.get(i).get(j).getName() == 'x') || (pieces.get(i).get(j).getName() == 'X') );
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

	public char returnhis(int i, int j){
		char[][] t = new char[8][8];
		t = history.peek();
		char a = t[i][j];
		return a;
	}


}//end class CheckersBoard
