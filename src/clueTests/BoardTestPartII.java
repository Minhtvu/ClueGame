package clueTests;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import clueGame.Board;

public class BoardTestPartII {

	Board board;
	@Before
	public void setUp() throws Exception {
		board = new Board();
		board.initialize();
	}

	@Test
	public void testWalkwayAdjacent() {
		assertEquals(2, board.getAdjList(5, 12).size());
		assertTrue(board.getAdjList(5,12).contains(board.getCellAt(5, 11)));
		assertTrue(board.getAdjList(5,12).contains(board.getCellAt(4, 12)));

	}


	@Test
	public void testEdges(){
		//Left Test
		assertEquals(1, board.getAdjList(7,0).size());
		assertEquals(board.getCellAt(7, 0), board.getAdjList(8,0).get(0));

		//Top Test
		assertEquals(1, board.getAdjList(0,9).size());
		assertEquals(board.getCellAt(0, 8), board.getAdjList(0,9).get(0));

		//Bottom Test
		assertEquals(1, board.getAdjList(25, 7).size());
		assertEquals(board.getCellAt(25, 6), board.getAdjList(25,7).get(0));

		//Right Test
		assertEquals(1, board.getAdjList(25, 11).size());
		assertEquals(board.getCellAt(25, 10), board.getAdjList(25,11).get(0));
		
	}
	
	// There are tests for locations that are beside a room cell that isn't a doorway above, specifically any of the edge tests.
	
	@Test
	public void testDoorways(){
		assertTrue(board.getAdjList(8,0).contains(board.getCellAt(7, 0)));
		assertTrue(board.getAdjList(1,0).contains(board.getCellAt(2, 0)));
		assertTrue(board.getAdjList(25,1).contains(board.getCellAt(25, 0)));
		assertTrue(board.getAdjList(25,10).contains(board.getCellAt(25, 9)));
	}
	
	@Test
	public void testInDoorway(){
		assertEquals(1, board.getAdjList(2,0).size());
		assertEquals(board.getCellAt(1, 0), board.getAdjList(2,0).get(0));
	}
	
	@Test
	public void testWalkwayTargets(){
		board.calcTargets(5, 12, 1);
		assertEquals(2, board.getTargets().size());
		assertTrue(board.getTargets().contains(board.getCellAt(5, 11)));
		assertTrue(board.getTargets().contains(board.getCellAt(4, 12)));
		
		board.calcTargets(0, 4, 2);
		assertEquals(2, board.getTargets().size());
		
		board.calcTargets(0, 4, 3);
		assertEquals(3, board.getTargets().size());	
	}
	
	@Test
	public void testEnteringRoom(){
		board.calcTargets(4, 4, 1);
		assertEquals(3, board.getTargets().size());
		assertTrue(board.getTargets().contains(board.getCellAt(4, 3)));
	}
	
	@Test
	public void testLeavingRoom(){
		board.calcTargets(2, 0, 1);
		assertEquals(1, board.getTargets().size());
		assertTrue(board.getTargets().contains(board.getCellAt(1, 0)));
	}
}
