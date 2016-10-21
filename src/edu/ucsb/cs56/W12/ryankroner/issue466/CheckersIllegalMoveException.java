package edu.ucsb.cs56.W12.ryankroner.issue466;

/**
 * An exception for illegal moves in Checkers, used in the CheckersBoard
 * 
 * @author Ryan Kroner
 * @version for CS56, W12, UCSB, 02/23/2012
 * @see CheckersBoard
 * 
*/

public class CheckersIllegalMoveException extends RuntimeException {

    public CheckersIllegalMoveException () {};
    
    public CheckersIllegalMoveException (String message) {
	super(message);
    }
}

