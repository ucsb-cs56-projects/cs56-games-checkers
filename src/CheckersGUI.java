package edu.ucsb.cs56.projects.games.checkers;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.ComponentOrientation;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;
import javax.swing.JTextField;

/** A GUI implementation of checkers game
   @author Ryan Kroner
   @author Graham Foster
   @author Matthew Maatubang
   @version UCSB CS56, F17
 */

public class CheckersGUI {

    /** main method to fire up a JFrame on the screen  
          @param args not used
     */

    public static void main (String[] args) {
       JFrame frame = new JFrame() ;
       frame. setDefaultCloseOperation(JFrame. EXIT_ON_CLOSE) ;
       
       // A JTextArea (see p. 414-415 of textbook)
       JTextAreaMessageDestination message = new JTextAreaMessageDestination(10,30);
       JScrollPane messageScroller = new JScrollPane(message);
       messageScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
       messageScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
       frame.getContentPane().add(BorderLayout.SOUTH,messageScroller);

       // History JTextArea
       JTextArea history = new JTextArea(10,15);
       JScrollPane historyScroller = new JScrollPane(history);
       historyScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
       historyScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
       frame.getContentPane().add(BorderLayout.EAST,historyScroller);

       //Retract bottom
       JTextField jf = new JTextField(20);
       frame.getContentPane().add(BorderLayout.NORTH,jf);
       // since TicTacToeGrid implements the TicTacToeGame interface,
       // we can use TicTacToeGame on left hand side.

       CheckersGame game = new CheckersBoard();
       CheckersComponent cc = new CheckersComponent(game, message,history,jf);
       frame.getContentPane().add(cc);

       // to make sure that grids go left to right, no matter what

       frame.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
       frame.setSize(800,800);
       frame.setVisible(true);
    }
}

