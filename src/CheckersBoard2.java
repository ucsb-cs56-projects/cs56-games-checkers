//package goes here
inport java.util.ArrayList;

//Basic classes for checkers command line version

public class CheckersBoard2 implements CheckersGame
{
    private Array[][] pieces[8][8]; //2D array of pieces
    //    private Array[][] coordinates[8][4]; //2D array of coordinates
    private char turn = 'x'; //x alwayds starts
    private char opponent = 'o'; //always opposite of turn, starts as o

    private int topLeft; //top left square of selected piece
    private int topRight; //top right square of slelected piece
    private int bottomLeft; //bottom left square of selected piece

    private int bottomRight; //bottom right square of selected piece
    private int jumpToTL; //spot to jump to top left
    private int jumpToTR; //spot to jump to top right
    private int jumpToBL; //spot to jump to bottom left
    private int jumpToBr; //spot to jump to bottom right

    private boolean jumped; //true if jumped

    private int xCount = 12; //count of x pieces
    private int oCount = 12; //count of o pieces

    private char winner = ''; //changed to either x,o, or t at end of game

    private boolean canJump = false; //true if can jump, false if not


    public CheckersBoard() {
	//initalize checkers board
	for(int i = 0; i < 8; i++) {
	    for (int j = 0; j < 3; j++) {
		if((i ^ j) & 1) pieces[i][j] = 'o'; // ^ xor &1 means only look at first bit so if the 1 bit doesnt match put an o
	    }
	}//end for loop
    }

    public char getTurn() { return turn; }

    public char getPiece(int row, int col) { return pieces[row][col] }

    public char getWinner() { return winner; }

    /** Used by TextCheckers.java class for inputted coordinates of the user
     * @see TextCheckers
     * @param s A string to convert to coordinates in the array
     * @return the index of the array so that spot can be accessed in pieces array
     */
    public int findCoordinates(String s) {
	//same as clean coordinates?
    }

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

    public void validMove(int i, int j) throws CheckersIllegalMoveException {
	if (i > 7 || j > 3 || orrectOwner(i,j) == false) {
	    throw new Checkers

    /** move method that allows the user to move
     * Will throw a CheckersIllegalMoveException if the from index is a spot that you don't own, or if you are trying to move to a spot that is not valid
     *  @param from the integer value of the index of the spot you are moving from
     *  @param to the integer value of the index of the spot you are moving to
     */
    public void move(int from, int to) throws CheckersIllegalMoveException {

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
