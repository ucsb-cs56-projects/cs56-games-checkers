package edu.ucsb.cs56.projects.games.checkers;

/**
 * An interface for Checkers games
 * 
 * @author Ryan Kroner
 * @version for CS56, W12, UCSB, 02/23/2012
 * @see CheckersBoard
 * 
*/
public interface CheckersGame 
{
	/** Checks the turn
	 * @return Either 'X' or 'O' - whoevers turn is it
	 */
	public char getTurn();
	
	/* Tells you what piece belongs in a certain spot on the checkers board
	 * 
	 * @param i The index in the arraylist where the spot is
	 */
	public char getPiece(int i);
	
	/** Tells you if you have a piece on the spot you want to move
	 * 
	 * @param i The index in the arraylist where the spot is
	 * @return True if the piece is on the spot you want to move
	 */	
	public boolean correctOwner(int i);
	
	/** Checks if the spot is blank or if the current piece can move to that spot, usually called from the move function
	 *  
	 * @param from The spot in the arraylist where the piece is current on (where you want to move from)
	 */
	public void validSpots(int from);
	
	/** Will allow the user to move, assuming that valid spot is true.  If it is false, will throw a CheckersIllegalMoveException
	 * 
	 * @param from The spot in the arraylist where the piece is current on (where you want to move from)
	 * @param to The spot in the arraylist where the piece is moving to	 
	 */
	public void move(int from, int to) throws CheckersIllegalMoveException;
	
	public int getOCount();
	
	public int getXCount();
	
	public char getWinner();
	
	public String toString();	
}
