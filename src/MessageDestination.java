package edu.ucsb.cs56.projects.games.checkers;

/** An interface to represent a place to send messages.
  
   @author Ryan Kroner
   @author Graham Foster
   @author Matthew Maatubang
   @version UCSB CS56, F17
 */
   
public interface MessageDestination
{
    /** 
	Get this string to the user
	@param msg String to send to the user
     */
    public void append(String msg);
}


