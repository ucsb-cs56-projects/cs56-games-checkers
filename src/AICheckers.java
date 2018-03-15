package edu.ucsb.cs56.projects.games.checkers;
import java.util.Scanner;
import java.util.Random;
import java.util.*;

/** A command line implementation of the CheckersGame
   @author Fuheng Zhao
   @version UCSB CS56, W18
 */

public class AICheckers {
	public static void main(String [] args) {
		Scanner sc          = new Scanner(System.in);
		int[] from          = new int[2];
		int[] to            = new int[2];
		boolean done        = false;
		boolean moveWasMade = false;
		char winner         = ' ';
		String input;
		String input2;
		int times = 1;
		boolean check;
		String alpha  = "ABCDEFGH";
		String result;

		ArrayList<String> possibleinput = new ArrayList<String>();
		for(int i=0; i<8;i++){
			for(int j=0; j<8; j++){
				result=alpha.charAt(j)+Integer.toString(i+1);
				possibleinput.add(result);
			
			}
		}
//		ArrayList<Integer> rmove= new ArrayList<Integer>();
//		rmove.add(-7);
//		remove.add();


		System.out.println("This is the AI version of the game, in which you can play the checker game by yourself.");
		System.out.println("To move your pieces, enter the piece coordinates of the piece you want to move, then enter, then the spot you want to move it to. You can also use retract, and then enter how many steps you want to move back.");

		CheckersBoard c = new CheckersBoard();
		
		while (!done) {
			c.recordHistory();

			if(c.getTurn()=='x'){
			//BASIC AI
				do{
					Random rand = new Random();
					int rnum1=rand.nextInt(64);
					from = c.parseInput(possibleinput.get(rnum1));
					int rnum2=rand.nextInt(64);
					to = c.parseInput(possibleinput.get(rnum2));
					if(from[0] == -1 || to[0] == -1 ){
						System.out.println("Invalid coordinates, please input different coordinates:  ");
                                        }
					else{
						c.moveAI(from[0], from[1], to[0], to[1]);
                                        	moveWasMade = c.moveWasMade();

                                        	if (moveWasMade) {
							c.recordText(possibleinput.get(rnum1), possibleinput.get(rnum2));
							
                                        	}
					}
					
				
				
				}while(!moveWasMade);
			
			}
			else{
				System.out.println(c); // c.toString() implicitly invoked
				System.out.println("" + c.getTurn() + "'s turn: ");
			
				do {
			
				

					input = sc.next();
				//System.out.println(input);
				// Read from command line
					if( input.equals("retract") ){
						System.out.println("Back how many turns?");
						do{
							check=true;
							try{	input2=sc.next();
								times=Integer.parseInt(input2);
							}catch(NumberFormatException exception){
								System.out.println("it's not a number");
								check=false;
								System.out.println("Enter again:");
							}
						}while(!check);

						c.retract(times);
						moveWasMade=true;
					
					}
			 		
					else{			
						from  = c.parseInput(input);
						input2 = sc.next();
						to = c.parseInput(input2);
				
			//		c.recordHistory();
				//	System.out.println("Record History");
				
				// If bad coordinates are entered or the move was invalid, ask for new inputs
						if(from[0] == -1 || to[0] == -1 || input.charAt(0)=='r'){
							System.out.println("Invalid coordinates, please input different coordinates:  ");
						} 	
						else {
							c.move(from[0], from[1], to[0], to[1]);
							moveWasMade = c.moveWasMade();

							if (!moveWasMade) {
								System.out.println("Invalid move, please input a valid move: ");
							}
							else{
								c.recordText(input, input2);
								c.printText();
							}
					
						}
					}
			

				} while (!moveWasMade);
			}

			c.checkWinner();
			if (c.getWinner() != ' ') {
				winner = c.getWinner();
				done = true;
			}
			c.changeTurn();
		} // end while

		System.out.println(c);
		System.out.println(winner + " wins!");
		System.out.println("Game Over!");
	}
}
