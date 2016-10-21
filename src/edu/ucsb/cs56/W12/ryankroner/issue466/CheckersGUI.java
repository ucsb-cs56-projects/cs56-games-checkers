package edu.ucsb.cs56.W12.ryankroner.issue466;
import javax.swing.JFrame;
import java.awt.ComponentOrientation;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;

/**
 * CheckersGui.java is a GUI for playing Checkers
 *
 * @author Ryan Kroner
 * @version for CS56, W12, UCSB, 03/12/2012
 * @see CheckersTest
 */
public class CheckersGUI {

    /** main method to fire up a JFrame on the screen  
          @param args not used
     */

    public static void main (String[] args) {
       JFrame frame = new JFrame() ;
       frame. setDefaultCloseOperation(JFrame. EXIT_ON_CLOSE) ;
       
       // A JTextArea (see p. 414-415 of textbook)
       JTextAreaMessageDestination text = new JTextAreaMessageDestination(10,30);
       JScrollPane scroller = new JScrollPane(text);
       scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
       scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
       frame.getContentPane().add(BorderLayout.SOUTH,scroller);
       
       // since TicTacToeGrid implements the TicTacToeGame interface,
       // we can use TicTacToeGame on left hand side.

       CheckersGame game = new CheckersBoard();
       CheckersComponent cc = new CheckersComponent(game,text);
       frame.getContentPane().add(cc);

       // to make sure that grids go left to right, no matter what

       frame.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
       frame.setSize(800,800);
       frame.setVisible(true);
    }
}

