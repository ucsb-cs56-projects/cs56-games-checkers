package edu.ucsb.cs56.W12.ryankroner.issue466;

/** An interface to represent a place to send messages.
  
   @author Ryan Kroner
   @version CS56, W12, UCSB, 02/23/2012
 */
public interface MessageDestination
{
    /** 
	Get this string to the user
	@param msg String to send to the user
     */
    public void append(String msg);
}


