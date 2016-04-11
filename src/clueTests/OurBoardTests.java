package clueTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class OurBoardTests {

	// THIS CLASS MUST WORK IN PART II WITH A 4X4 GAME BOARD, NOT THE MATRIX WE MADE
	Board board = null;
	@Before
	public void initialization(){
		board = new Board("Clue Layout.csv", "Legend.txt");
		board.initialize();
	}

	@Test
	public void testTLCorner(){
		BoardCell cell = board.getCellAt(0,0);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCellAt(1, 0)));
		assertTrue(testList.contains(board.getCellAt(0, 1)));
		assertEquals(2, testList.size());
		// tests if the cell to the right and the one directly down from the TL corner are
		// in the list, and that they are the only two in the list.
	}

	@Test
	public void testBRCorner(){
		BoardCell cell = board.getCellAt(25,10);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCellAt(25, 11)));
		assertTrue(testList.contains(board.getCellAt(25, 9)));
		assertTrue(testList.contains(board.getCellAt(24, 10)));
		assertEquals(3, testList.size());
		// tests if the cell to the left and the one directly up from the BR corner are
		// in the list, and that they are the only two in the list.
	}

	@Test
	public void testREdge(){
		BoardCell cell = board.getCellAt(4,12);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCellAt(5, 12)));
		assertTrue(testList.contains(board.getCellAt(4, 11)));
		assertEquals(2, testList.size());
		// tests if the cells to the left, up, and down from a right edge are
		// in the list, and that they are the only ones in the list.
	}

	@Test
	public void testLEdge(){
		BoardCell cell = board.getCellAt(8,0);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCellAt(8, 1)));
		assertTrue(testList.contains(board.getCellAt(9, 0)));
		assertTrue(testList.contains(board.getCellAt(7, 0)));
		assertEquals(3, testList.size());
		// tests if the cells to the right, up, and down from a left edge are
		// in the list, and that they are the only ones in the list.
	}

	@Test
	public void testC2M(){ // C2M = Column 2, Middle
		BoardCell cell = board.getCellAt(1,1);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCellAt(0, 1)));
		//assertTrue(testList.contains(board.getCellAt(2, 1)));
		assertTrue(testList.contains(board.getCellAt(1, 0)));
		assertTrue(testList.contains(board.getCellAt(1, 2)));		
		assertEquals(3, testList.size());
		// tests if the cells in all four directions from [1,1] are
		// in the list, and that they are the only ones in the list.
	}

	@Test
	public void test2FLM(){	// 2FLM = 2 From Last Middle
		BoardCell cell = board.getCellAt(23,10);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCellAt(22, 10)));
		assertTrue(testList.contains(board.getCellAt(24, 10)));
		//assertTrue(testList.contains(board.getCellAt(23, 9)));
		//assertTrue(testList.contains(board.getCellAt(23, 11)));
		assertEquals(2, testList.size());
		// tests if the cells in all four directions from [23,10] are
		// in the list, and that they are the only ones in the list.
	}

	@Test
	public void pathCreation(){
		board.calcTargets(1, 0, 3);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(5, targets.size());
		assertTrue(targets.contains(board.getCellAt(0, 0)));
		assertTrue(targets.contains(board.getCellAt(1, 1)));
		assertTrue(targets.contains(board.getCellAt(2, 0)));
		assertTrue(targets.contains(board.getCellAt(0, 2)));
		assertTrue(targets.contains(board.getCellAt(1, 3)));
		// Tests if the the space has exactly 5 possible destinations
		// and that they are the correct ones
	}

}
