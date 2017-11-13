package edu.ucsb.cs56.projects.games.checkers;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CheckersTest
{
	@Test
	public void testToString01(){
		CheckersBoard c = new CheckersBoard();
		assertEquals((	" | A | B | C | D | E | F | G | H |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"1|   | o |   | o |   | o |   | o |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"2| o |   | o |   | o |   | o |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"3|   | o |   | o |   | o |   | o |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"4|   |   |   |   |   |   |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"5|   |   |   |   |   |   |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"6| x |   | x |   | x |   | x |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"7|   | x |   | x |   | x |   | x |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"8| x |   | x |   | x |   | x |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"), c.toString());
	}
	
	@Test
	public void testToString02(){
		CheckersBoard c = new CheckersBoard();
		assertEquals((	" | A | B | C | D | E | F | G | H |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"1|   | o |   | o |   | o |   | o |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"2| o |   | o |   | o |   | o |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"3|   | o |   | o |   | o |   | o |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"4|   |   |   |   |   |   |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"5|   |   |   |   |   |   |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"6| x |   | x |   | x |   | x |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"7|   | x |   | x |   | x |   | x |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"8| x |   | x |   | x |   | x |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"), c.toString());
		c.move(5,6,4,5);
		assertEquals((	" | A | B | C | D | E | F | G | H |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"1|   | o |   | o |   | o |   | o |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"2| o |   | o |   | o |   | o |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"3|   | o |   | o |   | o |   | o |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"4|   |   |   |   |   |   |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"5|   |   |   |   |   | x |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"6| x |   | x |   | x |   |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"7|   | x |   | x |   | x |   | x |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"8| x |   | x |   | x |   | x |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"), c.toString());
		c.move(2,5,3,4);
		assertEquals((	" | A | B | C | D | E | F | G | H |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"1|   | o |   | o |   | o |   | o |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"2| o |   | o |   | o |   | o |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"3|   | o |   | o |   |   |   | o |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"4|   |   |   |   | o |   |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"5|   |   |   |   |   | x |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"6| x |   | x |   | x |   |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"7|   | x |   | x |   | x |   | x |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"8| x |   | x |   | x |   | x |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"), c.toString());
		c.move(5,2,4,3);
		assertEquals((	" | A | B | C | D | E | F | G | H |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"1|   | o |   | o |   | o |   | o |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"2| o |   | o |   | o |   | o |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"3|   | o |   | o |   |   |   | o |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"4|   |   |   |   | o |   |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"5|   |   |   | x |   | x |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"6| x |   |   |   | x |   |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"7|   | x |   | x |   | x |   | x |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"8| x |   | x |   | x |   | x |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"), c.toString());
		c.move(3,4,5,2);
		assertEquals((	" | A | B | C | D | E | F | G | H |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"1|   | o |   | o |   | o |   | o |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"2| o |   | o |   | o |   | o |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"3|   | o |   | o |   |   |   | o |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"4|   |   |   |   |   |   |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"5|   |   |   |   |   | x |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"6| x |   | o |   | x |   |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"7|   | x |   | x |   | x |   | x |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"8| x |   | x |   | x |   | x |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"), c.toString());
		c.move(6,1,4,3);
		assertEquals((	" | A | B | C | D | E | F | G | H |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"1|   | o |   | o |   | o |   | o |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"2| o |   | o |   | o |   | o |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"3|   | o |   | o |   |   |   | o |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"4|   |   |   |   |   |   |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"5|   |   |   | x |   | x |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"6| x |   |   |   | x |   |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"7|   |   |   | x |   | x |   | x |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"8| x |   | x |   | x |   | x |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"), c.toString());
	}
	
    /*@Test
	public void testJump01(){
		CheckersBoard c = new CheckersBoard();
		c.move(21,17);
		c.move(10,14);
		c.move(17,10);
		assertEquals(11,c.getOCount());
		assertEquals(12,c.getXCount());		
	}
	
	@Test
	public void testJump02(){
		CheckersBoard c = new CheckersBoard();
		c.move(21,16);
		c.move(8,12);
		c.move(23,19);
		c.move(12,21);
		c.move(25,16);
		assertEquals((	" | A | B | C | D | E | F | G | H |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"1|   | o |   | o |   | o |   | o |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"2| o |   | o |   | o |   | o |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"3|   |   |   | o |   | o |   | o |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"4|   |   |   |   |   |   |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"5|   | x |   |   |   |   |   | x |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"6| x |   |   |   | x |   |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"7|   | x |   |   |   | x |   | x |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"8| x |   | x |   | x |   | x |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"), c.toString());
	}
	
	@Test
	public void testJump03(){
		CheckersBoard c = new CheckersBoard();
		c.move(22,17);
		c.move(11,15);
		c.move(17,13);
		c.move(15,19);
		c.move(26,22);
		c.move(8,17);
		c.move(21,14);
		c.move(19,26);
		assertEquals((	" | A | B | C | D | E | F | G | H |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"1|   | o |   | o |   | o |   | o |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"2| o |   | o |   | o |   | o |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"3|   |   |   | o |   | o |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"4|   |   |   |   | x |   |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"5|   |   |   |   |   |   |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"6| x |   |   |   | x |   |   |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"7|   | x |   | x |   | o |   | x |\n"+
				"-+---+---+---+---+---+---+---+---+\n"+
				"8| x |   | x |   | x |   | x |   |\n"+
				"-+---+---+---+---+---+---+---+---+\n"), c.toString());
	}
	
	@Test
	public void testNoWinner01(){
		CheckersBoard c = new CheckersBoard();
		assertEquals(' ',c.getWinner());
	}
	
	@Test
	public void testNoWinner02(){
		CheckersBoard c = new CheckersBoard();
		c.move(22,17);
		c.move(11,15);
		c.move(17,13);
		c.move(15,19);
		c.move(26,22);
		c.move(8,17);
		c.move(21,14);
		c.move(19,26);
		assertEquals(' ',c.getWinner());
	}
	
	
	@Test(expected=CheckersIllegalMoveException.class)
	public void testBadOwner01(){
		CheckersBoard c = new CheckersBoard();
		c.move(8,13);
	}
	
	@Test(expected=CheckersIllegalMoveException.class)
	public void testBadOwner02(){
		CheckersBoard c = new CheckersBoard();
		c.move(21,17);
		c.move(17,13);
	}
	
	@Test(expected=CheckersIllegalMoveException.class)
	public void testBadOwner03(){
		CheckersBoard c = new CheckersBoard();
		c.move(17,22);
	}
	
	@Test(expected=CheckersIllegalMoveException.class)
	public void testBadOwner04(){
		CheckersBoard c = new CheckersBoard();
		c.move(22,17);
		c.move(11,15);
		c.move(17,13);
		c.move(15,19);
		c.move(26,22);
		c.move(8,17);
		c.move(21,14);
		c.move(19,26);
		c.move(21,17);
	}
	
	@Test(expected=CheckersIllegalMoveException.class)
	public void testIllegalMove01(){
		CheckersBoard c = new CheckersBoard();
		c.move(21,22);
	}
	
	@Test(expected=CheckersIllegalMoveException.class)
	public void testIllegalMove02(){
		CheckersBoard c = new CheckersBoard();
		c.move(21,12);
	}
	
	@Test(expected=CheckersIllegalMoveException.class)
	public void testIllegalMove03(){
		CheckersBoard c = new CheckersBoard();
		c.move(22,17);
		c.move(11,20);
	}
	
	@Test(expected=CheckersIllegalMoveException.class)
	public void testIllegalMove04(){
		CheckersBoard c = new CheckersBoard();
		c.move(22,17);
		c.move(11,15);
		c.move(17,13);
		c.move(15,19);
		c.move(26,17);
	}
	
	@Test(expected=CheckersIllegalMoveException.class)
	public void testMoveToIsOccupied01(){
		CheckersBoard c = new CheckersBoard();
		c.move(25,21);
	}
	
	@Test(expected=CheckersIllegalMoveException.class)
	public void testMoveToIsOccupied02(){
		CheckersBoard c = new CheckersBoard();
		c.move(26,23);
	}
	
	@Test(expected=CheckersIllegalMoveException.class)
	public void testMoveToIsOccupied03(){
		CheckersBoard c = new CheckersBoard();
		c.move(21,16);
		c.move(4,8);
	}
	
	@Test(expected=CheckersIllegalMoveException.class)
	public void testMoveToIsOccupied04(){
		CheckersBoard c = new CheckersBoard();
		c.move(21,16);
		c.move(8,12);
		c.move(16,12);
	}
	
	@Test(expected=CheckersIllegalMoveException.class)
	public void testMoveToIsOccupied05(){
		CheckersBoard c = new CheckersBoard();
		c.move(21,16);
		c.move(8,12);
		c.move(16,13);
		c.move(9,13);
		}*/
}
