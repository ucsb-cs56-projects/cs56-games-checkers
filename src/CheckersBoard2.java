//package goes here
inport java.util.ArrayList;

//Basic classes for checkers command line version

public class CheckersBoard2 implements CheckersGame
{
    private Array[][] pieces[8][8]; //2D array of pieces
    //    private Array[][] coordinates[8][4]; //2D array of coordinates
    private char turn = 'x'; //x alwayds starts
    private char opponent = 'o'; //always opposite of turn, starts as o

    private int x1 = -1; // possible x,y moves 
    private int x2 = -1;
    private int x4 = -1;
    private int x4 = -1;
    private int y1 = -1;
    private int y2 = -1;
    private int y3 = -1;
    private int y4 = -1;
    
    private int jumpToTL; //spot to jump to top left
    private int jumpToTR; //spot to jump to top right
    private int jumpToBL; //spot to jump to bottom left
    private int jumpToBr; //spot to jump to bottom right

    private boolean jumped; //true if jumped

    private int xCount = 12; //count of x pieces
    private int oCount = 12; //count of o pieces

    private char winner = ''; //changed to either x,o, or t at end of game

    private boolean canJump = false; //true if can jump, false if not
    private boolean validMove = false;

    public CheckersBoard() {
	//initalize checkers board
	for(int i = 0; i < 8; i++) {
	    for (int j = 0; j < 3; j++) {
		if((i ^ j) & 1) {
		    pieces[i][j] = 'o'; // ^ xor &1 means only look at first bit so if the 1 bit doesnt match put an o
		}
	    }
	}//end for loop placing o's
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j > 4; j++) {
		if((i ^ j) & 1) {
		    pieces[i][j] = 'x';
		}
	    }
	}//end for loop placing x's
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		if(pieces[i][j] != 'o' || pieces[i][j] != 'x') {
		    pieces[i][j] = ' ';
		}
	    }
	}//end of placing empty squares
    }

    public char getTurn() { return turn; }

    public char getPiece(int row, int col) { return pieces[row][col] }

    public char getWinner() { return winner; }

    /**Checks if the index you are moving from is owned by the correct owner
     * @param i,j The index of the spot you are trying to move
     * @return True if you own the spot, or False if you do not own it
     */
    public boolean correctOwner(int i, int j) {
	if (turn == 'x') {
	    if(pieces[i][j] == 'x' || pieces[i][j] == 'X') {
		return true;
	    }
	    else {
		return false;
	    }
	}
	if (turn == 'o') {
	    if (pieces[i][j] == 'o' || pieces[i][j] == 'O') {
		return true;
	    }
	    else {
		return false;
	    }
	}
	return false;
    }

    /** move method that allows the user to move
     * Will throw a CheckersIllegalMoveException if the from index is a spot that you don't own, or if you are trying to move to a spot that is not valid
     *  @param from the integer value of the index of the spot you are moving from
     *  @param to the integer value of the index of the spot you are moving to
     */
    public void validMove(int fromI, int fromJ, int toI, int toJ) throws CheckersIllegalMoveException {

	if (checkOwner(fromI, fromJ) == false) {
	    throw CheckersIllegalMoveException("Thats not your piece");
	}
	if (turn == 'x') {
	    x1 = fromI - 1; // up left
	    y1 = fromI - 1;
	    x2 = fromI - 1; // up right
	    y2 = fromI + 1;
	    if (pieces[fromI][fromJ] == 'X') { // if its a king
	        x3 = fromI + 1; // back left
		y3 = fromI - 1;
		x4 = fromI + 1; // back right
		y4 = fromI + 1;
	    }
	}
	if (turn == 'o') {
	    x1 = fromI + 1; // up right
	    y1 = fromJ - 1;
	    x2 = fromI + 1; // up left
	    y2 = fromJ + 1;
	    if (pieces[fromI][fromJ] == 'O') { // if its a king
		x3 = fromI - 1; // back right
		y3 = fromJ - 1;
		x4 = fromI - 1; // back left
		y4 = fromJ + 1;
	    }
	}
	if (x1 > 7 || x2 > 7 || x3 > 7 || x4 > 7 || y1 > 7 || y2 > 7 || y3 > 7 || y4 > 7) { //check if out of bounds
	    throw CheckersIllegalMoveException("You cant move off the board");
	}
	else if ((toI != x1 && toJ != y1) || (toI != x2 && toJ != y2) || (toI != x3 && toJ != y3) || (toI != x4 && toJ != y4)) { // check if 1 of 4 valid moves
            throw CheckersIllegalMoveException("That's not a valid move");
	}
	else if ((if turn == 'o' && pieces[toI][toJ] == 'x') || (if turn == 'x' && pieces[toI][toJ] == 'o')) { //check if existing piece in spot
	    checkJump(fromI, fromJ, toI, toJ);
	}
	else {
	    validMove = true;
	}
    }
    public void checkJump(int fromI, int fromJ, int toI, int toJ) {
	int jumpI, jumpJ;
	if (turn == 'x') {
	    if(toI == x1 && toJ == y1) {
		jumpI = x1 - 1;
		jumpJ = y1 - 1;
	    }
	    else if (toI == x2 && toJ == y2) {
		jumpI = x2 - 1;
		jumpJ = y2 + 1;
	    }
	    else if (toI == x3 && toJ == y3) {
		jumpI = x3 + 1;
		jumpJ = y3 - 1;
	    }
	    else if (toI == x4 && toJ == y4) {
		jumpI = x4 + 1;
		jumpJ = y4 + 1;
	    }
	}
	if (turn == 'y'
    }
    public int getOCount() { return oCount; }
    
    public int getXcount() { return xCount; }

    /**A toString method for the CheckersBoard, allows easier access for console based interface
     * @return result The result of the toString(), aka the gameBoard
     */
    public String toString() {

    }

    /** A formatting function that allows multiple different types of inputs
     * Matt already completed?
     * @param input from user, either piece theyre selecting or spot they want to move to
     * @return input in proper format
     */
    public String cleanCoord(String s) {

    }

}//end class CheckersBoard

    
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
