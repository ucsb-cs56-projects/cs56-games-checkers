package edu.ucsb.cs56.W12.ryankroner.issue466;
import java.util.Scanner;

/**
 * A Text UI for playing Checkers
 * @author Ryan Kroner
 * @version for CS56, W12, UCSB, 02/23/2012
 * @see CheckersTest
 */
public class TextCheckers
{
    public static void main(String [] args) {
	Scanner sc = new Scanner(System.in);
	boolean done = false;
	char winner=' ';
	int num1,num2;
	System.out.println("To play, enter the piece coordinates of the piece you want to move, then enter, then the spot you want to move it to");
	
	CheckersBoard c = new CheckersBoard();
	
	while (!done) {
	   System.out.println(c); // c.toString() implicitly invoked
	   System.out.println("" + c.getTurn() + "'s turn: ");
	   do{
	   	String line1  = sc.nextLine();
	   	num1 = c.findCoordinates(line1);
	   	String line2 = sc.nextLine();
	   	num2 = c.findCoordinates(line2);
	   	if(num1 == -1 || num2 == -1){
	   		System.out.println("Invalid Coordinates, please input different coordinates:  ");
	   	}
	   }while(num1 == -1 || num2 == -1);
	   
	   
	   c.move(num1,num2);
	   winner = c.getWinner();
	   done = (winner!=' ');
	}//end while
	System.out.println(c);
	if (winner!=' ')
	    System.out.println(winner + " wins!");
	System.out.println("Game Over!");
    }

}

