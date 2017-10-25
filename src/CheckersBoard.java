package edu.ucsb.cs56.projects.games.checkers;
import java.util.ArrayList;


//Basic classes for Checkers, Non-GUI. Plus a J-Unit Test similar to Lab 07 from S11: https://foo.cs.ucsb.edu/56wiki/index.php/S11:Labs:lab07

/**
 * A Checkers Board
 
 
 
 * @author Ryan Kroner
 * @version for CS56, W12, UCSB, 02/23/2012
 * 
*/

public class CheckersBoard implements CheckersGame
{
	//instance variables
	private ArrayList<Character> 	pieces = new ArrayList<Character>();	//array of pieces
	private ArrayList<String>	coordinates = new ArrayList<String>();	//array of coordinates
	private char 			turn = 'x';				//turn starts off as x
	private char 			opponent = 'o';				//always opposite of turn, starts as o
	private int 			topLeft;				//for top left
	private int 			topRight;				//for top right			tL		tR
	private int 			bottomLeft;				//for bottom left			S
	private int 			bottomRight;				//for bottom right		bL		bR
	private int 			jumpSpotTL;				//index in array of jumped spot top left
	private int 			jumpSpotTR;				//index in array of jumped spot top right
	private int 			jumpSpotBL;				//index in array of jumped spot bottom left
	private int 			jumpSpotBR;				//index in array of jumped spot bottom right
	private boolean			jumped;					//true if jumped, false if didnt jump
	private int			oCount = 12;				//count of o pieces
	private int			xCount = 12;				//count of x pieces
	private char			winner = ' ';				//will be ' ' until either 'x' or 'o' wins
	private boolean 		canJump = false;			//false if you cant jump, true if you can
	
	/**
	 *  Constructor for objects of class CheckerBoard which initializes the entire game board and the coordinates associated with it
	 */
	public CheckersBoard(){
		//initialize the checkers board
		for(int i = 0; i < 32; i++){
			if(i < 12){
				pieces.add(i, 'o');
			}else if(i > 19){
				pieces.add(i, 'x');
			}else{
				pieces.add(i,' ');
			}
		}//end for
		
		//Lines up the coordinates with the pieces
		coordinates.add(0,"B1");
		coordinates.add(1,"D1");
		coordinates.add(2,"F1");
		coordinates.add(3,"H1");
		coordinates.add(4,"A2");
		coordinates.add(5,"C2");
		coordinates.add(6,"E2");
		coordinates.add(7,"G2");
		coordinates.add(8,"B3");
		coordinates.add(9,"D3");
		coordinates.add(10,"F3");
		coordinates.add(11,"H3");
		coordinates.add(12,"A4");
		coordinates.add(13,"C4");
		coordinates.add(14,"E4");
		coordinates.add(15,"G4");
		coordinates.add(16,"B5");
		coordinates.add(17,"D5");
		coordinates.add(18,"F5");
		coordinates.add(19,"H5");
		coordinates.add(20,"A6");
		coordinates.add(21,"C6");
		coordinates.add(22,"E6");
		coordinates.add(23,"G6");
		coordinates.add(24,"B7");
		coordinates.add(25,"D7");
		coordinates.add(26,"F7");
		coordinates.add(27,"H7");
		coordinates.add(28,"A8");
		coordinates.add(29,"C8");
		coordinates.add(30,"E8");
		coordinates.add(31,"G8");
	}
	
	public char getTurn(){ return turn; }
	
	public char getPiece(int i){ return pieces.get(i); }
	
	public char getWinner(){ return winner; }
	
	/** Used by the TextCheckers.java class for the inputted coordinates of the user
	 * @see TextCheckers
	 * @param s A String value that we compare to the coordinates array
	 * @return The index of the array so you can access that spot in the pieces array
	 */
	public int findCoordinates(String s){
		for(int i = 0; i < 32; i++){
			if(s.equals(coordinates.get(i))){
				return i;
			}
		}
		return -1;
	}
	
	/**Checks if the index you are moving from is owned by the correct owner
	 * @param i The index of the spot you are trying to move
	 * @return True if you own the spot, or False if you do not own it
	 */
	public boolean correctOwner(int i){
		if(turn == 'x'){	//if its x's turn, makes sure the spot is owned by 'x'
			if(pieces.get(i) == 'x' || pieces.get(i) == 'X'){
				return true;
			}else{
				return false;
			}
		}
		if(turn == 'o'){	//if its o's turn, makes sure the spot is owned by 'o'
			if(pieces.get(i) == 'o' || pieces.get(i) == 'O'){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	/**Sets the topLeft variable to where you are able to move to (or -1 if you cant move to the top left)
	 * @param from The spot where the current piece is
	 * @param change Index of how far you are going to change to the topLeft
	 */
	public void setTopLeft(int from, int change){
		if(from+change > 31 || from+change < 0){//makes sure its in range
			jumpSpotTL = -1;
			topLeft = -1;
			return;
		}
		
		int x = from+change;
		while(x > 7){
			x -= 8; // if its a corner it will either turn into a 4 or a 3
		}
		
		if(change == 0){	//if you cant jump
			topLeft = -1;
		}else if(pieces.get(from+change) == ' ' && canJump == false){	//if its unoccupied and you cant jump any pieces
			topLeft = from+change;
			jumpSpotTL = -1;
		}else if(pieces.get(from+change) == opponent){	//if another persons piece occupies it, tests to see if you can jump
			if((x == 4) && (x == 3)){	//if you cant jump
				topLeft = -1;
			}else{
				if(change == -4){
					if(pieces.get(from+change-5) == ' '){ topLeft = from+change-5; jumpSpotTL=from+change; } else { topLeft = -1; }
				}else if(change == -5){
					if(pieces.get(from+change-4) == ' '){ topLeft = from+change-4; jumpSpotTL=from+change; } else { topLeft = -1; }
				}
			}
		}else if(pieces.get(from+change) == turn){	//if that spot is owned by your piece
			topLeft = -1;
		}
		
		if(topLeft < 0 || topLeft > 27){	//makes sure topLeft has a valid space
			topLeft = -1;
			jumpSpotTL = -1;
		}
	}//end setTopLeft
	
	/**Sets the topRight variable to where you are able to move to (or -1 if you cant move to the top right)
	 * @param from The spot where the current piece is
	 * @param change Index of how far you are going to change to the topRight
	 */
	public void setTopRight(int from, int change){
		if(from+change > 31 || from+change < 0){//makes sure its in range
			jumpSpotTR = -1;
			topRight = -1;
			return;
		}
		
		int x = from+change;
		while(x > 7){
			x -= 8; // if its a corner it will either turn into a 4 or a 3
		}
		
		if(change == 0){	//if you cant jump
			topRight = -1;
		}else if(pieces.get(from+change) == ' ' && canJump == false){	//if its unoccupied and you cant jump any pieces
			topRight = from+change;
			jumpSpotTR = -1;
		}else if(pieces.get(from+change) == opponent){	//if another persons piece occupies it, tests to see if you can jump
			if((x == 4) && (x == 3)){	//if you cant jump
				topRight = -1;
			}else{
				if(change == -4){
					if(pieces.get(from+change-3) == ' '){ topRight = from+change-3; jumpSpotTR=from+change; } else { topRight = -1; }
				}else if(change == -3){
					if(pieces.get(from+change-4) == ' '){ topRight = from+change-4; jumpSpotTR=from+change; } else { topRight = -1; }
				}
			}
		}else if(pieces.get(from+change) == turn){	//if that spot is owned by your piece
			topRight = -1;
		}
		
		if(topRight < 0 || topRight > 27){	//makes sure topRight has a valid space
			topRight = -1;
			jumpSpotTR = -1;
		}
	}//end setTopRight
	
	/**Sets the bottomLeft variable to where you are able to move to (or -1 if you cant move to the bottom left)
	 * @param from The spot where the current piece is
	 * @param change Index of how far you are going to change to the bottomLeft
	 */
	public void setBottomLeft(int from, int change){
		if(from+change > 31 || from+change < 0){//makes sure its in range
			jumpSpotBL = -1;
			bottomLeft = -1;
			return;
		}
		
		int x = from+change;
		while(x < 24){
			x += 8; // if its a corner it will either turn into a 4 or a 3
		}
		
		if(change == 0){	//if you cant jump
			bottomLeft = -1;
		}else if(pieces.get(from+change) == ' ' && canJump == false){	//if its unoccupied and you cant jump any pieces
			jumpSpotBL = -1;
			bottomLeft = from+change;
		}else if(pieces.get(from+change) == opponent){	//if another persons piece occupies it, tests to see if you can jump
			if((x == 27) && (x == 28)){	//if you cant jump
				bottomLeft = -1;
			}else{
				if(change == 4){
					if(pieces.get(from+change+3) == ' '){ bottomLeft = from+change+3; jumpSpotBL=from+change; } else { bottomLeft = -1; }
				}else if(change == 3){
					if(pieces.get(from+change+4) == ' '){ bottomLeft = from+change+4; jumpSpotBL=from+change; } else { bottomLeft = -1; }
				}
			}
		}else if(pieces.get(from+change) == turn){	//if that spot is owned by your piece
			bottomLeft = -1;
		}
		
		if(bottomLeft < 4 || bottomLeft > 31){	//makes sure bottomLeft has a valid space
			jumpSpotBL = -1;
			bottomLeft = -1;
		}
	}//end setBottomLeft
	
	/**Sets the bottomRight variable to where you are able to move to (or -1 if you cant move to the bottom right)
	 * @param from The spot where the current piece is
	 * @param change Index of how far you are going to change to the bottomRight
	 */
	public void setBottomRight(int from, int change){	//from is the spot where the current piece is, change is the change to bottomRight
		if(from+change > 31 || from+change < 0){//makes sure its in range
			jumpSpotBR = -1;
			bottomRight = -1;
			return;
		}
		
		int x = from+change;
		while(x > 24){
			x -= 8; // if its a corner it will either turn into a 4 or a 3
		}
		
		if(change == 0){	//if you cant jump
			bottomRight = -1;
		}else if(pieces.get(from+change) == ' ' && canJump == false){	//if its unoccupied and you cant jump any pieces
			jumpSpotBR = -1;
			bottomRight = from+change;
		}else if(pieces.get(from+change) == opponent){	//if another persons piece occupies it, tests to see if you can jump
			if((x == 27) && (x == 28)){	//if you cant jump
				bottomRight = -1;
			}else{
				if(change == 4){
					if(pieces.get(from+change+5) == ' '){ bottomRight = from+change+5; jumpSpotBR=from+change; } else { bottomRight = -1; }
				}else if(change == 5){
					if(pieces.get(from+change+4) == ' '){ bottomRight = from+change+4; jumpSpotBR=from+change; } else { bottomRight = -1; }
				}
			}
		}else if(pieces.get(from+change) == turn){	//if that spot is owned by your piece
			bottomRight = -1;
		}
		
		if(bottomRight < 4 || bottomRight > 31){	//makes sure bottomRight has a valid space
			jumpSpotBR = -1;
			bottomRight = -1;
		}
		
	}//end setBottomRight
	
	/** Checks all of the valid spots that are around the piece you select.
	 * -Sets value setTopLeft to the value of the top left corner you are able to move to (or 0 if invalid);
	 * -Sets value setTopRight to the value of the top right corner you are able to move to (or 0 if invalid);
	 * -Sets value setBottomLeft to the value of the bottom left corner you are able to move to (or 0 if invalid);
	 * -Sets value setBottomRight to the value of the bottom right corner you are able to move to (or 0 if invalid);
	 * @param from The spot you are moving from
	 */
	public void validSpots(int from){	//sets the corners to the correct spots
		if(from == 4 || from == 12 || from == 20 || from == 28){	//left side of the board
			setTopLeft(from, 0);		//cant go farther left
			setBottomLeft(from, 0);
			setTopRight(from, -4);
			setBottomRight(from, 4);
		}else if(from == 3 || from == 11 || from == 19 || from == 27){	//right side of the board
			setTopRight(from, 0);		//cant go farther right
			setBottomRight(from, 0);
			setTopLeft(from, -4);
			setBottomLeft(from, 4);
		}else{	//otherwise its the middle 
			int x = from;
			while(x >= 8){
				x -= 8; // will turn x into either a 0,1,2 (odd row) or a 5,6,7 (even row)
			}
			if(x < 3){	//odd row
				setTopLeft(from, -4);
				setTopRight(from, -3);
				setBottomLeft(from, 4);
				setBottomRight(from, 5);
			}else{		//even row
				setTopLeft(from, -5);
				setTopRight(from, -4);
				setBottomLeft(from, 3);
				setBottomRight(from, 4);
			}
		}
	}//end validSpots
	
	/** move method that allows the user to move
	 * Will throw a CheckersIllegalMoveException if the from index is a spot that you don't own, or if you are trying to move to a spot that is not valid
	 *  @param from the integer value of the index of the spot you are moving from
	 *  @param to the integer value of the index of the spot you are moving to
	 
	 */
	public void move(int from, int to) throws CheckersIllegalMoveException {
		//either moves the piece (if its valid) or sends an exception
		
		if(correctOwner(from) == false)	//makes sure that its the correct owner, otherwise throws an exception
			throw new CheckersIllegalMoveException("Illegal Move, you do not have a piece at spot " + coordinates.get(from));
		
		validSpots(from);	//sets all the corners to their proper numbers so we can check it against the parameter 'to'
		
		if(to == topLeft || to == bottomLeft || to == topRight || to == bottomRight){
			if(turn == 'x'){
				if(pieces.get(from) == 'x'){
					pieces.set(to,'x');
				}else if(pieces.get(from) == 'X'){
					pieces.set(to,'X');
				}
			}else if(turn == 'o'){
				if(pieces.get(from) == 'o'){
					pieces.set(to,'o');
				}else if(pieces.get(from) == 'O'){
					pieces.set(to,'O');
				}
			}
			pieces.set(from, ' ');
			//If you were able to move, checks if piece should be turned into a king
			if(turn == 'x'){
				if(pieces.get(to) == 'x'){
					if(to+6 < from){ //if you jumped the piece
						jumped = true;
						if(to == topLeft){
							pieces.set(jumpSpotTL, ' ');
						}else if(to == topRight){
							pieces.set(jumpSpotTR, ' ');
						}else if(to == bottomLeft){
							pieces.set(jumpSpotBL, ' ');
						}else if(to == bottomRight){
							pieces.set(jumpSpotBR, ' ');
						}
						--oCount;
					}else{
						jumped = false;
					}
				}else{	//its a KING
					if(to+6 < from || to > from+6){ //if you jumped the piece
						jumped = true;
						if(to == topLeft){
							pieces.set(jumpSpotTL, ' ');
						}else if(to == topRight){
							pieces.set(jumpSpotTR, ' ');
						}else if(to == bottomLeft){
							pieces.set(jumpSpotBL, ' ');
						}else if(to == bottomRight){
							pieces.set(jumpSpotBR, ' ');
						}
						--oCount;
					}else{
						jumped = false;
					}
				}
				
				if(to == 0 || to == 1 || to == 2 || to == 3){
					pieces.set(to, 'X');
				}
				
			}else if(turn == 'o'){
				if(pieces.get(to) == 'o'){
					if(to > from+6){ //if you jumped the piece
						jumped = true;
						if(to == topLeft){
							pieces.set(jumpSpotTL, ' ');
						}else if(to == topRight){
							pieces.set(jumpSpotTR, ' ');
						}else if(to == bottomLeft){
							pieces.set(jumpSpotBL, ' ');
						}else if(to == bottomRight){
							pieces.set(jumpSpotBR, ' ');
						}
						--xCount;
					}else{
						jumped = false;
					}
				}else{	//its a KING
					if(to+6 < from || to > from+6){ //if you jumped the piece
						jumped = true;
						if(to == topLeft){
							pieces.set(jumpSpotTL, ' ');
						}else if(to == topRight){
							pieces.set(jumpSpotTR, ' ');
						}else if(to == bottomLeft){
							pieces.set(jumpSpotBL, ' ');
						}else if(to == bottomRight){
							pieces.set(jumpSpotBR, ' ');
						}
						--xCount;
					}else{
						jumped = false;
					}
				}
				if(to == 28 || to == 29 || to == 30 || to == 31){
					pieces.set(to, 'O');
				}
			}
				
			//SWITCH TURNS
			char temp = opponent;	
			opponent = turn;
			turn = temp;
			jumped = false;
		}else{	//if its not a valid spot to move to
			throw new CheckersIllegalMoveException("Illegal Move.  You can not move " + coordinates.get(from) + " to " + coordinates.get(to));
		}
		
		if(xCount == 0){
			System.out.println("O WINS!");
		}else if(oCount == 0){
			System.out.println("X WINS!");
		}
	}//end move
	
	public int getOCount(){ return oCount; }
	
	public int getXCount(){ return xCount; }
	
	
	/**A toString method for the CheckersBoard, allows easier access for console based interface
	 * @return result The result of the toString(), aka the gameBoard
	 */
	public String toString(){//prints out the checkerBoard
		String result;
		result = " | A | B | C | D | E | F | G | H |";
		
		for(int i = 0; i < 32; i++){
			if(i%4 == 0){
				result += "\n-+---+---+---+---+---+---+---+---+\n";
			}
			
			if(i == 0){
				result += "1";
			}else if(i == 4){
				result += "2|";
			}else if(i == 8){
				result += "3";
			}else if(i == 12){
				result += "4|";
			}else if(i == 16){
				result += "5";
			}else if(i == 20){
				result += "6|";
			}else if(i == 24){
				result += "7";
			}else if(i == 28){
				result += "8|";
			}
					
			if(i < 4){
				result += "|   | " + pieces.get(i) + " ";
			}else if(i < 8){
				result += " " + pieces.get(i) + " |   |";
			}else if(i < 12){
				result += "|   | " + pieces.get(i) + " ";
			}else if(i < 16){
				result += " " + pieces.get(i) + " |   |";
			}else if(i < 20){
				result += "|   | " + pieces.get(i) + " ";
			}else if(i < 24){
				result += " " + pieces.get(i) + " |   |";
			}else if(i < 28){
				result += "|   | " + pieces.get(i) + " ";
			}else if(i < 32){
				result += " " + pieces.get(i) + " |   |";
			}
				
			if(i == 3 || i == 11 || i == 19 || i == 27){
				result += "|";
			}
		}//end for
		result += "\n-+---+---+---+---+---+---+---+---+\n";
		return result;
	}//end toString()
}//end class Checkers

/**
 | A | B | C | D | E | F | G | H |
-+---+---+---+---+---+---+---+---+
1|   | o |   | o |   | o |   | o |
-+---+---+---+---+---+---+---+---+
2| o |   | o |   | o |   | o |   |
-+---+---+---+---+---+---+---+---+
3|   | o |   | o |   | o |   | o |
-+---+---+---+---+---+---+---+---+
4|   |   |   |   |   |   |   |   |
-+---+---+---+---+---+---+---+---+
5|   |   |   |   |   |   |   |   |
-+---+---+---+---+---+---+---+---+
6| x |   | x |   | x |   | x |   |
-+---+---+---+---+---+---+---+---+
7|   | x |   | x |   | x |   | x |
-+---+---+---+---+---+---+---+---+
8| x |   | x |   | x |   | x |   |
-+---+---+---+---+---+---+---+---+


-------------------------
|__|00|__|01|__|02|__|03|
|04|__|05|__|06|__|07|__|
|__|08|__|09|__|10|__|11|
|12|__|13|__|14|__|15|__|
|__|16|__|17|__|18|__|19|
|20|__|21|__|22|__|23|__|
|__|24|__|25|__|26|__|27|
|28|__|29|__|30|__|31|__|
-------------------------

   |A|B|C|D|E|F|G|H|
-  -----------------
1  |_|x|_|x|_|x|_|x|
2  |x|_|x|_|x|_|x|_|
3  |_|x|_|x|_|x|_|x|
4  |_|_|_|_|_|_|_|_|
5  |_|_|_|_|_|_|_|_|
6  |o|_|o|_|o|_|o|_|
7  |_|o|_|o|_|o|_|o|
8  |o|_|o|_|o|_|o|_|
-  -----------------
*/
