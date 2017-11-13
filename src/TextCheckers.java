package edu.ucsb.cs56.projects.games.checkers;
import java.util.Scanner;

public class TextCheckers
{
    public static void main(String [] args) {
	Scanner sc = new Scanner(System.in);
	boolean done = false;
	char winner=' ';
	int num1x, num1y, num2x, num2y;
	System.out.println("To play, enter the piece coordinates of the piece you want to move, then enter, then the spot you want to move it to");
	
	CheckersBoard c = new CheckersBoard();
	
	while (!done) {
	   System.out.println(c); // c.toString() implicitly invoked
	   System.out.println("" + c.getTurn() + "'s turn: ");
	   do{
	   	String line1  = sc.next();
	   	num1x = c.findXCoordinate(line1);
		num1y = c.findYCoordinate(line1);
	   	String line2 = sc.next();
	   	num2x = c.findXCoordinate(line2);
		num2y = c.findYCoordinate(line2);
		
	   	if(num1x == -1 || num1y == -1 ||num2x == -1 || num2y == -1){
	   		System.out.println("Invalid Coordinates, please input different coordinates:  ");
	   	}
	   }while(num1x == -1 || num1y == -1 || num2x == -1 || num2y == -1);

	   
	   c.checkWinner();
	   c.move(num1x, num1y, num2x, num2y);
	   if (c.getError() == false) {
	       c.changeTurn();
	   }
	   c.setError();
	   winner = c.getWinner();
	   done = (winner!=' ');
	}//end while
	System.out.println(c);
	if (winner!=' ')
	    System.out.println(winner + " wins!");
	System.out.println("Game Over!");
    }

}

