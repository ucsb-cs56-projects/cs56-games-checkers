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
       	
	/** Tells you if you have a piece on the spot you want to move
	 * 
	 * @param i The index in the arraylist where the spot is
	 * @return True if the piece is on the spot you want to move
	 */	
        public boolean correctOwner(int i, int j);
		
	/** Will allow the user to move, assuming that valid spot is true.  If it is false, will throw a CheckersIllegalMoveException
	 * 
	 * @param from The spot in the arraylist where the piece is current on (where you want to move from)
	 * @param to The spot in the arraylist where the piece is moving to	 
	 */
        public void move(int fromI, int fromJ, int toI, int toJ) throws CheckersIllegalMoveException;

        public void validMove(int fromI, int fromJ, int toI, int toJ) throws CheckersIllegalMoveException;
        public void setMoves(int fromI, int fromJ, int toI, int toJ);    

        public void checkJump(int fromI, int fromJ, int toI, int toJ);

        public Boolean kingCheck(int i, int j);
    
        public void changeTurn();
    
	public char getWinner();

        public int getXCount();

        public int getOCount();

        public String toString();

        public void checkWinner();

        public int findXCoordinate(String s);

        public int findYCoordinate(String s);
}
