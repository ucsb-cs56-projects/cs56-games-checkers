package edu.ucsb.cs56.projects.games.checkers;

/** An interface for Checkers games
    @author Ryan Kroner
    @author Graham Foster
    @author Matthew Maatubang
    @version UCSB CS56, F17
 */

public interface CheckersGame {

	// BOARD FUNCTIONS

	/** A toString method for the CheckersBoard
	* Allows easier access for console based interface
	* @return A String containing the gameBoard
	*/
	public String toString(); 

	/**  Returns who will make the next move
	* @return 'x' or 'o' depending on the move
	*/
	public char getTurn(); 

	/**  Changes the current turn of the internal CheckersBoard
	* @return Internally switch between 'x' and 'o' depending on the previous move
	*/
	public void changeTurn(); 
	
	/**  Returns the winner of the game
	* @return 'x' or 'o' depending on the loser having 0 pieces
	*/
	public char getWinner(); 

	/**  Checks the # of x's and o's remaining on board
	* Sets winner if a player has 0 pieces left
	* @return Internally set 'x' or 'o' depending on the move
	*/
	public void checkWinner(); 

	/**  Returns number of 'x' pieces
	* @return # of x's remaining on board
	*/
	public int getXCount(); 

	/**  Returns number of 'o' pieces
	* @return # of o's remaining
	*/
	public int getOCount(); 

        /** Return char at the coordinates
	* @return Value of the piece at i, j
	*/
        public char getPiece(int i , int j);
    
	// MOVEMENT FUNCTIONS 

	/**  Moves a piece from the from coordinates to the to coordinates, if possible
	* @return Internally execute move if possible, do nothing otherwise
	*/
	public void move(int fromI, int fromJ, int toI, int toJ); 
	
	/**  Returns whether an entered move was actually executed
	* @return true if the move was made, false otherwise
	*/
	public boolean moveWasMade(); 

	/** A formatting function that allows multiple different types of inputs
	 * @param input from user, either piece theyre selecting or spot they want to move to
	 * @return int array holding { x coordinate, y coordinate }
	 */
	public int[] parseInput(String s); 

	// W18 Fuheng Zhao Douglas Gao;
	public void recordHistory();
	
	public void retract(int times);

	public void recordText(String s1, String s2);

	public void printText();
	
	public void retractgui(int times);

}
