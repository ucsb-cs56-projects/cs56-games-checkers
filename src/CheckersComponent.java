package edu.ucsb.cs56.projects.games.checkers;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event. ActionEvent;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 * Component for playing Checkers
	<pre>
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
	|00|01|02|03|04|05|06|07|
	|08|09|10|11|12|13|14|15|
	|16|17|18|19|20|21|22|23|
	|24|25|26|27|28|29|30|31|
	|32|33|34|35|36|37|38|39|
	|40|41|42|43|44|45|46|47|
	|48|49|50|51|52|53|54|55|
	|56|57|58|59|60|61|62|63|
	-------------------------
	
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
	</pre>
 * @author Ryan Kroner
 * @version for CS56, W12, UCSB, 02/23/2012
 * @see CheckersBoard
 */
public class CheckersComponent extends JComponent
{
    private CheckersGame game;
    private MessageDestination md;

    private JButton [] spots  = new JButton[32];	//spots that an "x" or an "o" can go
    private JButton [] blanks = new JButton[32];	//ALWAYS blank = " "
    
    private int from = -1;
    private int to = -1;

    /** Constructor
     *
     * @param game a CheckersGame object that keeps track of the game 
     * @param md allows messages to be displayed under the game board in the GUI
     */
       
    public CheckersComponent(CheckersGame game, MessageDestination md) {
	super();
	
	this.game = game;	//A Game of Checkers
	this.md = md;		//md, where we can write messages

	// Sets number of rows to 8 (makes an 8x8 board).  This for loop sets up the board	
	this.setLayout(new GridLayout(8,0));
	
	//sets up the board initially (spots you can move to)
	for(int i = 0; i < 32; i++){
		initialMakeSpotButtons(Character.toString(game.getPiece(i)), i);	//refers to the CheckersGame class to make the buttons
	}//end for
	
	//sets up the 32 blank spots (spots you can't move to)
	for(int i = 0; i < 32; i++){
		String label = " ";
		JButton jb = new JButton(label);
		blanks[i] = jb;
	}//end for
	
	//places all the spots on the board
	setBoard();
	
	md.append(game.getTurn() + "'s turn:\n");
	
    }//end constructor 
    
    //function to initially set the spots[] array of buttons
    public void initialMakeSpotButtons(String x, int i){
    	String label = x;
	JButton jb = new JButton(label);
	spots[i] = jb;
	jb.addActionListener(new ButtonListener(i)); 
    }
    
    //function used to set the spots on the GUI after performing a move
    public void setSpots(){
    	for(int num = 0; num < 32; num++){
	    	JButton jb = spots[num];
		jb.setText(Character.toString(game.getPiece(num)));
		jb = spots[from];
		jb.setText(" ");
	}
    }
    
    //sets up the board
    public void setBoard(){
    	for(int i = 0; i < 32; i++){
		if(i < 4){
			this.add(blanks[i]);
			this.add(spots[i]);
		}else if(i < 8){
			this.add(spots[i]);
			this.add(blanks[i]);
		}else if(i < 12){
			this.add(blanks[i]);
			this.add(spots[i]);
		}else if(i < 16){
			this.add(spots[i]);
			this.add(blanks[i]);
		}else if(i < 20){
			this.add(blanks[i]);
			this.add(spots[i]);
		}else if(i < 24){
			this.add(spots[i]);
			this.add(blanks[i]);
		}else if(i < 28){
			this.add(blanks[i]);
			this.add(spots[i]);
		}else if(i < 32){
			this.add(spots[i]);
			this.add(blanks[i]);
		}
	}//end for loop
    }
    
    //FIX
    class ButtonListener implements ActionListener {    
	private int num;	//index of the button that is pressed

	public ButtonListener(int i) {
	    super();
	    this.num = i;
	}

	public void actionPerformed (ActionEvent event) {
		char turn = game.getTurn();
		char turn2 = turn;
		if(turn == 'x'){
			turn2 = 'X';
		}else if(turn == 'o'){
			turn2 = 'O';
		}
		
		if(from == -1){	//nothing initialized to from
			if((turn == game.getPiece(num)) || (turn2 == game.getPiece(num))){	//if the piece you click is yours
				from = num;
			}else{
				md.append("You don't own this square, please select an " + turn + " piece that you own\n");
				return;
			}
		}else{	//then to isn't initialized
			try{
				game.move(from,num);
				setSpots();
				from = -1;
				md.append(game.getTurn() + "'s turn:\n");
			}catch (CheckersIllegalMoveException e){
				md.append("Illegal Move.  You can not move to this spot, please try again\n");
				from = -1;
			}
		}
				
		if(game.getOCount() == 0){
			md.append("X Wins!\n");
		}else if(game.getXCount() == 0){
			md.append("O Wins!\n");
		}
		
		return;
	}
    }

}

